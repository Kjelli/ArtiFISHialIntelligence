package fishhandles;

import gameobjects.fish.Fish;

/**
 *
 * OtherFish is the class which is effectively the handle between your fish and
 * some other fish that might be a player's fish, or a randomly generated fish.
 * This class contains methods which are only considered as read-methods.
 *
 * @author Kjell Arne Hellum
 */
public class OtherFish {
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
	@Deprecated
	public OtherFish(Fish that) {
		this.fish = that;
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
	 * Convenience method to retrieve the pixel distance between this fish and
	 * the targetted fish in decimal.
	 * 
	 * @param that
	 *            the targetted fish to calculate the distance to
	 * @return the distance in pixels
	 */

	public final float distanceTo(OtherFish that) {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.distanceTo(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Convenience method to retrieve the pixel distance between this fish and
	 * the targetted fish in decimal.
	 * 
	 * @param that
	 *            the targetted fish to calculate the distance to
	 * @return the distance in pixels
	 */
	public final float distanceTo(YourFish that) {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.distanceTo(that.getCenterX(), that.getCenterY());
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
		if (!isAlive()) {
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
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return Fish.smallerThan(this.getScale(), that.getScale());
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

	public final boolean greaterThan(YourFish that) {
		if (!isAlive()) {
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

	public final boolean smallerThan(YourFish that) {
		if (!isAlive()) {
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
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getScale();
	}

	/**
	 * Retrieve the horizontal velocity of this fish
	 * 
	 * @return the horizontal velocity of this fish, ranging from -1 to 1
	 *         inclusive
	 */
	public final float getVelocityX() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getVelocityX();
	}

	/**
	 * Retrieve the vertical velocity of this fish
	 * 
	 * @return the vertical velocity of this fish, ranging from -1 to 1
	 *         inclusive
	 */
	public final float getVelocityY() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getVelocityY();
	}

	/**
	 * Retrieve the x position of the leftmost point on the fish
	 * 
	 * @return the x position of the leftmost point on the fish
	 */
	public final float getX() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getX();
	}

	/**
	 * Retrieve the y position of the bottommost point on the fish
	 * 
	 * @return the y position of the bottommost point on the fish
	 */
	public final float getY() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getY();
	}

	/**
	 * Retrieve the x position of the center point on the fish
	 * 
	 * @return the x position of the center point on the fish
	 */

	public final float getCenterX() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getCenterX();
	}

	/**
	 * Retrieve the y position of the center point on the fish
	 * 
	 * @return the y position of the center point on the fish
	 */
	public final float getCenterY() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getCenterY();
	}

	/**
	 * Retrieve the width of the fish. NOTE the scale affects the size of the
	 * fish.
	 * 
	 * @return the width of the fish.
	 */
	public final float getWidth() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getWidth();
	}

	/**
	 * Retrieve the height of the fish. NOTE the scale affects the size of the
	 * fish.
	 * 
	 * @return the height of the fish.
	 */
	public final float getHeight() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getHeight();
	}

	/**
	 * Retrieves the maximal speed of the fish. NOTE the scale affects the
	 * maximum speed of the fish.
	 * 
	 * @return the maximal speed of the fish.
	 */
	public final float getMaxSpeed() {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.getMaxSpeed();
	}

	/**
	 * Convenience method to calculate the angle from this fish to the other
	 * fish.
	 * 
	 * @param that
	 *            the targetted fish
	 * @return the angle in radians
	 */

	public final float angleTo(OtherFish that) {
		if (!isAlive()) {
			System.err.println(Fish.WARNING_DEAD);
		}
		return this.fish.angleTo(that.getCenterX(), that.getCenterY());
	}

	/**
	 * Used to compare if the given fish handle is equal to another.
	 * 
	 * @param that
	 *            The other fish handle to compare to
	 * @return boolean indicating equality
	 */

	public boolean matches(OtherFish that) {
		return this.fish.getHandle().equals(that);
	}

	/**
	 * Convenience method to calculate the angle from this fish to the your
	 * fish.
	 * 
	 * @param that
	 *            the targetted fish
	 * @return the angle in radians
	 */
	public double angleTo(YourFish that) {
		return this.fish.angleTo(that.getCenterX(), that.getCenterY());
	}

}
