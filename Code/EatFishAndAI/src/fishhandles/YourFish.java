package fishhandles;

import gameobjects.fish.Fish;

public final class YourFish {

	private final Fish fish;

	/**
	 * Used to establish a handle to manipulate certain allowed methods on the
	 * fish.
	 * 
	 * <br>
	 * <br>
	 * <b>TECHDAYS NOTE</b>: Generating a fish handle does not achieve anything
	 * as you should not have direct access to any fish objects.
	 * 
	 * @param fish
	 *            The fish to manipulate
	 */

	public YourFish(Fish fish) {
		this.fish = fish;
	}

	public boolean matches(OtherFish that) {
		return this.fish.getHandle().equals(that);
	}

	/**
	 * States whether or not this fish is alive.
	 * 
	 * @return Boolean evaluating true if fish is alive, false if the fish is
	 *         not alive, i.e. it has been eaten.
	 */

	public final boolean isAlive() {
		return this.fish.isAlive();
	}

	/**
	 * Convenience method to calculate the angle between your fish and the
	 * target, and set the velocities equal to the horizontal and vertical
	 * components of the angle. Should be called repeatedly to update the angle
	 * if the targetfish is moving.
	 * 
	 * @param that
	 *            the targetted fish to move against
	 */

	public final void moveTowards(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		this.fish.moveTowards(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Convenience method to calculate the angle between your fish and the
	 * target, and set the velocities opposite to the horizontal and vertical
	 * components of the angle. Should be called repeatedly to update the angle
	 * if the targetfish is moving.
	 * 
	 * @param that
	 *            the targetted fish to move away from
	 */

	public final void moveFrom(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		this.fish.moveFrom(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Convenience method to retrieve the pixel distance between this fish and
	 * the targetted fish in decimal.
	 * 
	 * @param that
	 *            the targetted fish to calculate the distance to
	 * @return the distance in pixels
	 */

	public final float distanceTo(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return fish.distanceTo(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Set the velocity in the x direction. <br>
	 * <br>
	 * <b> NOTE: </b> the velocity is limited to the interval [-1, 1] as the
	 * resulting speed of the fish is the velocity multiplied by its maximum
	 * speed fixed by the simulator. If the value passed to this method exceeds
	 * this interval, it is capped automatically.
	 * 
	 * @param xvel
	 *            the velocity in the x axis, limited to the interval [-1,1]
	 */

	public final void setVelocityX(float xvel) {
		this.fish.setVelocityX(xvel);
	}

	/**
	 * Set the velocity in the y direction. <br>
	 * <br>
	 * <b> NOTE: </b> the velocity is limited to the interval [-1, 1] as the
	 * resulting speed of the fish is the velocity multiplied by its maximum
	 * speed (See getMaxSpeed()) fixed by the simulator. If the value passed to
	 * this method exceeds this interval, it is capped automatically.
	 * 
	 * @param yvel
	 *            the velocity in the y axis, limited to the interval [-1,1]
	 */
	public final void setVelocityY(float yvel) {
		this.fish.setVelocityY(yvel);
	}

	/**
	 * Convenience method for determining whether or not this fish is big enough
	 * to eat the other fish.
	 * 
	 * @param that
	 *            the targetted fish
	 * @return boolean evaluating true if this scale is greater than the other
	 *         fish's scale taking into consideration a small margin (see
	 *         Fish.MASS_DIFFERENCE_MARGIN)
	 */

	public final boolean greaterThan(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return Fish.greaterThan(this.getScale(), that.getScale());
	}

	/**
	 * Convenience method for determining whether or not this fish is small
	 * enough for the other fish to eat this fish. Somewhat similar to
	 * YourFish.greaterThan(OtherFish), but also taking into consideration the
	 * margin. (See Fish.MASS_DIFFERENCE_MARGIN)
	 * 
	 * @param that
	 *            the targetted fish
	 * @return boolean evaluating true if the given fish is large enough to eat
	 *         this fish, false if not.
	 */

	public final boolean smallerThan(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return Fish.smallerThan(this.getScale(), that.getScale());
	}

	/**
	 * Retrieve the scale of this fish.
	 * 
	 * @return the scale of the this fish.
	 */
	public final float getScale() {
		return this.fish.getScale();
	}

	/**
	 * Retrieve the horizontal velocity of this fish
	 * 
	 * @return the horizontal velocity of this fish, ranging from -1 to 1
	 *         inclusive
	 */

	public final float getVelocityX() {
		return this.fish.getVelocityX();
	}

	/**
	 * Retrieve the vertical velocity of this fish
	 * 
	 * @return the vertical velocity of this fish, ranging from -1 to 1
	 *         inclusive
	 */
	public final float getVelocityY() {
		return this.fish.getVelocityY();
	}

	/**
	 * Retrieve the x position of the leftmost point on the fish
	 * 
	 * @return the x position of the leftmost point on the fish
	 */

	public final float getX() {
		return this.fish.getX();
	}

	/**
	 * Retrieve the y position of the bottommost point on the fish
	 * 
	 * @return the y position of the bottommost point on the fish
	 */

	public final float getY() {
		return this.fish.getY();
	}

	/**
	 * Retrieve the x position of the center point on the fish
	 * 
	 * @return the x position of the center point on the fish
	 */

	public final float getCenterX() {
		return this.fish.getCenterX();
	}

	/**
	 * Retrieve the y position of the center point on the fish
	 * 
	 * @return the y position of the center point on the fish
	 */

	public final float getCenterY() {
		return this.fish.getCenterY();
	}

	/**
	 * Retrieve the width of the fish. NOTE the scale affects the size of the
	 * fish.
	 * 
	 * @return the width of the fish.
	 */

	public final float getWidth() {
		return this.fish.getWidth();
	}

	/**
	 * Retrieve the height of the fish. NOTE the scale affects the size of the
	 * fish.
	 * 
	 * @return the height of the fish.
	 */

	public final float getHeight() {
		return this.fish.getHeight();
	}

	/**
	 * Retrieves the maximal speed of the fish. NOTE the scale affects the
	 * maximum speed of the fish.
	 * 
	 * @return the maximal speed of the fish.
	 */
	public final float getMaxSpeed() {
		return this.fish.getMaxSpeed();
	}

	/**
	 * Convenience method to calculate the angle from this fish to the other
	 * fish.
	 * 
	 * @param that
	 *            the targetted fish
	 * @return the angle in degrees (TODO confirm)
	 */

	public final float angleTo(OtherFish that) {
		if (!that.isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.angleTo(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Convenience method to calculate the angle from this fish to a specified
	 * (x,y) point
	 * 
	 * @param x
	 *            the x coordinate of the point
	 * @param y
	 *            the y coordinate of the point
	 * @return the angle in degrees (TODO confirm)
	 */

	public final float angleTo(float x, float y) {
		return this.fish.angleTo(x, y);
	}

	/**
	 * Convenience method to calculate the angle between your fish and the given
	 * coordinate, and set the velocities equal to the horizontal and vertical
	 * components of the angle.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */

	public void moveTowards(float x, float y) {
		this.fish.moveTowards(x, y);
	}

	/**
	 * Convenience method to calculate the angle between your fish and the given
	 * coordinate, and set the velocities opposite to the horizontal and
	 * vertical components of the angle.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */

	public void moveFrom(float x, float y) {
		this.fish.moveFrom(x, y);
	}

}
