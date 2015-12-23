package gameobjects;

import gamecontext.GameContext;

public abstract class AbstractGameObject implements GameObject {
	private GameContext context;

	private float x, y, width, height;
	private float velocityX, velocityY;

	public AbstractGameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public final GameContext getGameContext() {
		return context;
	}

	public final void setGameContext(GameContext context) {
		if (this.context == null) {
			this.context = context;
		}
	}

	@Override
	public final float getX() {
		return x;
	}

	@Override
	public final float getY() {
		return y;
	}

	@Override
	public final float getVelocityX() {
		return x;
	}

	@Override
	public final float getVelocityY() {
		return y;
	}

	@Override
	public void setVelocityX(float velx) {
		this.velocityX = velx;
	}

	@Override
	public void setVelocityY(float vely) {
		this.velocityY = vely;
	}

	@Override
	public final float getWidth() {
		return width;
	}

	@Override
	public final float getHeight() {
		return height;
	}

	protected final void move(float delta) {
		x += velocityX * delta;
		y += velocityY * delta;
	}

	@Override
	public void destroy() {
		if (context != null) {
			context.despawn(this);
		}
	}

}
