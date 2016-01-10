package graphics.particles;

import game.EatFishAndAI;
import gameobjects.AbstractGameObject;
import gameobjects.Fish;
import assets.Assets;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fishbones extends AbstractGameObject {
	private final float size;
	boolean flipped = false;

	public Fishbones(float x, float y, Fish eater, Fish eaten) {
		super(x, y, 16, 12);

		setSprite(new Sprite(Assets.fishbones));

		this.size = eaten.getScale();
		this.flipped = eaten.getVelocityX() < 0;
		setMaxSpeed(100 / size);
		setVelocityX(-eater.getVelocityX() * eater.getMaxSpeed() / 20);
	}

	@Override
	public void update(float delta) {
		setVelocityX(getVelocityX() * 0.95f);
		setVelocityY(getVelocityY() * 1.05f + 0.0005f / size);
		move(delta);
		if (getY() > EatFishAndAI.HEIGHT) {
			destroy();
		}
	}

	@Override
	public float getScale() {
		return size;
	}

}
