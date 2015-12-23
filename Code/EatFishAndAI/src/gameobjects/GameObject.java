package gameobjects;

import gamecontext.GameContext;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {

	public float getX();

	public float getY();

	public float getVelocityX();

	public float getVelocityY();

	public void setVelocityX(float velx);

	public void setVelocityY(float vely);

	public float getWidth();

	public float getHeight();

	public void draw(SpriteBatch batch);

	public void update(float delta);
	
	public void onSpawn();
	
	public void onDespawn();
	
	public void destroy();

	public void setGameContext(GameContext gameContext);
}
