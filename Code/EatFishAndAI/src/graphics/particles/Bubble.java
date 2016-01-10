package graphics.particles;

import tween.CommonTweens;
import assets.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.EatFishAndAI;
import gameobjects.AbstractGameObject;
import gameobjects.Fish;

public class Bubble extends AbstractGameObject {

	public static final float WIDTH = 10, HEIGHT = 10;
	private final Fish bubbler;

	private boolean spawnspeedup = true;

	public Bubble(float x, float y) {
		this(x, y, 1.0f, null);
	}

	public Bubble(float x, float y, float scale) {
		this(x, y, scale, null);
	}

	public Bubble(float x, float y, float scale, Fish bubbler) {
		super(x, y, scale * WIDTH, scale * HEIGHT);

		Texture texture;
		if (scale < 1.0f) {
			texture = Assets.bubble_xs;
		} else if (scale > 1.0f && scale < 2.0f) {
			texture = Assets.bubble_s;
		} else {
			texture = Assets.bubble;
		}
		setSprite(new Sprite(texture));
		setScale(scale);

		this.bubbler = bubbler;
		if (bubbler != null) {
			setVelocityX(bubbler.getVelocityX());
		}
		setVelocityY(10 + 30 * scale);
		setMaxSpeed(5 + getMaxSpeed() * scale);

	}

	@Override
	public void update(float delta) {
		move(delta);
		if (getVelocityX() < 0.01f) {
			spawnspeedup = false;
		}
		if (spawnspeedup) {
			setVelocityX(getVelocityX() * 0.95f);
		} else {
			setVelocityX((float) Math.cos(getGameContext().getTicks()
					/ (20f * getScale())) / 3);
		}
		if (getY() > EatFishAndAI.HEIGHT) {
			destroy();
		}

	}

	@Override
	public void setVelocityX(float velx) {
		this.velocityX = Math.max(Math.min(velx, 1), -1);
	}

	@Override
	public void onSpawn() {
		if (Math.random() < 0.06f) {
			burst();
		} else {
			CommonTweens.quickScaleFrom(this, 0, 0.3f);
		}
	}

	public void burst() {
		int count = (int) (Math.random() * 10);
		for (int i = 0; i < count; i++) {
			getGameContext().spawn(
					new Bubble((float) (getX() + (0.5f - Math.random()) * 10),
							(float) (getY() + (0.5f - Math.random()) * 10),
							(float) (getScale() * Math.pow(0.9, i)), bubbler));
		}
		destroy();
	}

}
