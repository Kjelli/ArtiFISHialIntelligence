package graphics.gui;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameobjects.AbstractGameObject;

public class Logo extends AbstractGameObject {

	public static final int WIDTH = 300, HEIGHT = 200;

	public Logo(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(Assets.splash, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void onSpawn() {

	}

	@Override
	public void onDespawn() {

	}

}
