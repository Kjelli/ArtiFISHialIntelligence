package graphics.particles;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.EatFishAndAI;
import gameobjects.AbstractGameObject;

public class Bubble extends AbstractGameObject {

	public static final float WIDTH = 10, HEIGHT = 10;
	private final float scale;

	public Bubble(float x, float y, float scale) {
		super(x, y, scale * WIDTH, scale * HEIGHT);
		this.scale = scale;
		setVelocityY(10 + 30 * scale);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(Assets.bubble, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {
		move(delta);

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
							(float) (scale * Math.pow(0.6, i))));
		}
		destroy();
	}

	@Override
	public void onDespawn() {
		// TODO Auto-generated method stub

	}

}
