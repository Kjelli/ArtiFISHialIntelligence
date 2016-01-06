package gameobjects;

import gamecontext.GameContext;
import gamecontext.physics.Collidable;

public abstract class AbstractGameObject implements GameObject {
	private GameContext context;

	private float x, y, width, height;
	private float velocityX, velocityY;
	private boolean alive = true;

	private float speed = 30f;

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
	public final float getCenterX() {
		return x + getWidth() / 2;
	}

	@Override
	public final float getY() {
		return y;
	}

	@Override
	public final float getCenterY() {
		return y + getHeight() / 2;
	}

	protected final void setX(float x) {
		this.x = x;
	}

	protected final void setY(float y) {
		this.y = y;
	}

	@Override
	public void setMaxSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getMaxSpeed() {
		return speed;
	}

	@Override
	public final float getVelocityX() {
		return velocityX;
	}

	@Override
	public final float getVelocityY() {
		return velocityY;
	}

	@Override
	public void setVelocityX(float velx) {
		this.velocityX = Math.max(-1, Math.min(1, velx));
	}

	@Override
	public void setVelocityY(float vely) {
		this.velocityY = Math.max(-1, Math.min(1, vely));
	}

	@Override
	public final float getWidth() {
		return width * getScale();
	}

	@Override
	public final float getHeight() {
		return height * getScale();
	}

	protected final void move(float delta) {
		x += velocityX * speed * delta;
		y += velocityY * speed * delta;

		if (this instanceof Collidable) {
		}
	}

	@Override
	public void destroy() {
		alive = false;
		if (context != null) {
			context.despawn(this);
		}
	}

	public boolean contains(float x, float y) {
		return (x >= this.x && x <= this.x + this.width)
				&& (y >= this.y && y <= this.y + this.height);
	}

	private boolean valueInRange(float value, float min, float max) {
		return (value >= min) && (value <= max);
	}

	public boolean intersects(GameObject other) {
		boolean xOverlap = valueInRange(getX(), other.getX(), other.getX()
				+ other.getWidth())
				|| valueInRange(other.getX(), getX(), getX() + getWidth());

		boolean yOverlap = valueInRange(getY(), other.getY(), other.getY()
				+ other.getHeight())
				|| valueInRange(other.getY(), getY(), getY() + getHeight());

		return xOverlap && yOverlap;
	}

	@Override
	public float distanceTo(GameObject other) {
		return (float) Math.hypot(other.getCenterX() - getCenterX(),
				other.getCenterY() - getCenterY());
	}

	@Override
	public float distanceTo(float x, float y) {
		return (float) Math.hypot(x - getCenterX(), y - getCenterY());
	}

	public float angleTo(float x, float y) {
		float deltaY = y - getCenterY();
		float deltaX = x - getCenterX();
		float angle = (float) (Math.atan2(deltaY, deltaX));

		return angle;
	}

	public float angleTo(GameObject other) {
		float deltaY = other.getCenterY() - getCenterY();
		float deltaX = other.getCenterX() - getCenterX();
		float angle = (float) (Math.atan2(deltaY, deltaX));

		return angle;
	}

	@Override
	public void moveTowards(GameObject other) {
		float angle = angleTo(other);
		setVelocityX((float) Math.cos(angle));
		setVelocityY((float) Math.sin(angle));

	}

	public void moveTowards(float x, float y) {
		float angle = angleTo(x, y);
		setVelocityX((float) Math.cos(angle));
		setVelocityY((float) Math.sin(angle));

	}

	@Override
	public void moveFrom(GameObject other) {
		float angle = (float) (angleTo(other) - Math.PI);
		setVelocityX((float) Math.cos(angle));
		setVelocityY((float) Math.sin(angle));

	}

	public void moveFrom(float x, float y) {
		float angle = (float) (angleTo(x, y) - Math.PI);
		setVelocityX((float) Math.cos(angle));
		setVelocityY((float) Math.sin(angle));

	}

	@Override
	public float getScale() {
		return 1.0f;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
}
