package fishhandles;

import gameobjects.fish.Fish;

public final class YourFish {
	public static final String WARNING_DEAD = "Warning! Target is already dead!";

	private final Fish fish;

	public YourFish(Fish fish) {
		this.fish = fish;
	}

	public final boolean isAlive() {
		return this.fish.isAlive();
	}

	public final void moveTowards(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		this.fish.moveTowards(that.getCenterX(), that.getCenterY());
	}

	public final void moveFrom(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		this.fish.moveFrom(that.getCenterX(), that.getCenterY());
	}

	public final float distanceTo(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		return fish.distanceTo(that.getCenterX(), that.getCenterY());
	}

	public final void setVelocityX(float xvel) {
		this.fish.setVelocityX(xvel);
	}

	public final void setVelocityY(float yvel) {
		this.fish.setVelocityY(yvel);
	}

	public final boolean greaterThan(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		return Fish.greaterThan(this.getScale(), that.getScale());
	}

	public final boolean smallerThan(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		return Fish.smallerThan(this.getScale(), that.getScale());
	}

	public final float getScale() {
		return this.fish.getScale();
	}

	public final float getVelocityX() {
		return this.fish.getVelocityX();
	}

	public final float getVelocityY() {
		return this.fish.getVelocityY();
	}

	public final float getX() {
		return this.fish.getX();
	}

	public final float getY() {
		return this.fish.getY();
	}

	public final float getCenterX() {
		return this.fish.getCenterX();
	}

	public final float getCenterY() {
		return this.fish.getCenterY();
	}

	public final float getWidth() {
		return this.fish.getWidth();
	}

	public final float getHeight() {
		return this.fish.getHeight();
	}

	public final float angleTo(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(WARNING_DEAD);
		}
		return this.fish.angleTo(that.getCenterX(), that.getCenterY());
	}

	public final float angleTo(float x, float y) {
		return this.fish.angleTo(x, y);
	}

}
