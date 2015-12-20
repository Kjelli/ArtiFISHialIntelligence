package gameobjects;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fish extends AbstractGameObject {

	public Fish(float x, float y) {
		super(x, y, 32, 32);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(Assets.fish, x, y, width, height);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
