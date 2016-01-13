package fishhandles;

import gameobjects.fish.Fish;

public class OtherFish {
	private final Fish fish;

	public OtherFish(Fish other) {
		this.fish = other;
	}

	public final boolean isAlive() {
		return this.fish.isAlive();
	}

	public final float distanceTo(OtherFish other) {
		return this.fish.distanceTo(other.getCenterX(), other.getCenterY());
	}

	public final float distanceTo(YourFish other) {
		return this.fish.distanceTo(other.getCenterX(), other.getCenterY());
	}

	public final boolean greaterThan(OtherFish other) {
		return Fish.greaterThan(this.getScale(), other.getScale());
	}

	public final boolean smallerThan(OtherFish other) {
		return Fish.smallerThan(this.getScale(), other.getScale());
	}

	public final boolean greaterThan(YourFish other) {
		return Fish.greaterThan(this.getScale(), other.getScale());
	}

	public final boolean smallerThan(YourFish other) {
		return Fish.smallerThan(this.getScale(), other.getScale());
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

}
