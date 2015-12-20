package gameobjects;

public abstract class AbstractGameObject implements GameObject {
	float x, y, width, height;
	float velocityX, velocityY;

	public AbstractGameObject(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getVelocityX() {
		return x;
	}

	@Override
	public float getVelocityY() {
		return y;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

}
