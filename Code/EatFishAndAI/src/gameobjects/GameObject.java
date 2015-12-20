package gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {

	public float getX();

	public float getY();

	public float getVelocityX();

	public float getVelocityY();

	public float getWidth();

	public float getHeight();

	public void draw(SpriteBatch batch);

	public void update(float delta);
}
