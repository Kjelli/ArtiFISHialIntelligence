package graphics.particles;

import assets.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.EatFishAndAI;
import gameobjects.AbstractGameObject;
import gameobjects.Fish;

public class Bubble extends AbstractGameObject {

	public static final float WIDTH = 10, HEIGHT = 10;
	private final Texture texture;
	private final float scale;
	private final Fish bubbler;

	public Bubble(float x, float y, float scale, Fish bubbler) {
		super(x, y, scale * WIDTH, scale * HEIGHT);
		this.scale = scale;
		this.bubbler = bubbler;
		setVelocityY(10 + 30 * scale);
		setVelocityX(bubbler.getVelocityX());

		if (scale < 1.0f) {
			texture = Assets.bubble_xs;
		} else if (scale > 1.0f && scale < 2.0f) {
			texture = Assets.bubble_s;
		} else {
			texture = Assets.bubble;
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {
		move(delta);
		setVelocityX(getVelocityX() * 0.95f);

		if (getY() > EatFishAndAI.HEIGHT) {
			destroy();
		}

	}

	@Override
	public void onSpawn() {
		if (Math.random() < 0.2f) {
			burst();
		}
	}

	public void burst() {
		int count = (int) (Math.random() * 5);
		for (int i = 0; i < count; i++) {
			getGameContext().spawn(
					new Bubble((float) (getX() + (0.5f - Math.random()) * 10),
							(float) (getY() + (0.5f - Math.random()) * 10),
							(float) (scale * Math.pow(0.6, i)), bubbler));
		}
		destroy();
	}

	@Override
	public void onDespawn() {
		// TODO Auto-generated method stub

	}

}
