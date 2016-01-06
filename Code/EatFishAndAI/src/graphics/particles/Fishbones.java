package graphics.particles;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.EatFishAndAI;
import gameobjects.AbstractGameObject;
import gameobjects.Fish;

public class Fishbones extends AbstractGameObject {
	private final float size;
	boolean flipped = false;

	public Fishbones(float x, float y, Fish eater, Fish eaten) {
		super(x, y, 16, 12);
		this.size = eaten.getSize();
		this.flipped = eaten.getVelocityX() < 0;
		setMaxSpeed(100 / size);
		setVelocityX(-eater.getVelocityX()*eater.getMaxSpeed() / 20);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(Assets.fishbones, (flipped ? getWidth() : 0) + getX(),
				getY(), (flipped ? -1 : 1) * getWidth(), getHeight());
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
	public void onSpawn() {
	}

	@Override
	public void onDespawn() {

	}

	@Override
	public float getScale() {
		return size;
	}

}
