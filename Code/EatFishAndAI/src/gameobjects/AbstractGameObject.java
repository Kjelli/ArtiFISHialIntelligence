package gameobjects;

import gamecontext.GameContext;
import graphics.Draw;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractGameObject implements GameObject {
	private GameContext context;

	protected float x, y, width, height;
	protected float velocityX, velocityY;
	protected boolean alive = true;

	protected Sprite sprite;

	protected float speed = 30f;
	protected float rot = 0f;
	protected float scale = 1.0f;

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

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
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

		if ((velocityX <= 0 && !getSprite().isFlipX())
				|| (velocityX >= 0 && getSprite().isFlipX())) {
			getSprite().flip(true, false);
		}
	}

	@Override
	public void setVelocityY(float vely) {
		this.velocityY = Math.max(-1, Math.min(1, vely));
	}

	@Override
	public final float getWidth() {
		return width * getScale();
	}

	public final void setWidth(float width) {
		this.width = width / getScale();
	}

	@Override
	public final float getHeight() {
		return height * getScale();
	}

	public final void setHeight(float height) {
		this.height = height / getScale();
	}

	protected final void move(float delta) {
		x += velocityX * speed * delta;
		y += velocityY * speed * delta;

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
	public void draw(SpriteBatch batch) {
		Draw.sprite(batch, this);
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public float getScale() {
		return scale;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void onSpawn() {
		// Left empty intentionally
	}

	public void onDespawn() {
		// Left empty intentionally
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
	public void setRotation(float rot){
		this.rot = rot % 360;
		if(sprite != null){
			sprite.setRotation(this.rot);
		}
	}
	
	public float getRotation() {
		return rot;
	}

	/**
	 * Cleanup method, use with caution
	 */
	@Override
	public void dispose() {
		// Left empty intentionally
	}
}
