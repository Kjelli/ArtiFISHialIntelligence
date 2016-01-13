package gameobjects.fish;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import gamecontext.GameContext;
import gameobjects.GameObject;
import ai.AI;

public interface Fish extends GameObject {
	public static final float MASS_DIFFERENCE_MARGIN = 0.1f;
	public final static int BUBBLE_TIMER_MAX = 1000, BUBBLE_TIMER_MIN = 100;
	public final static int EATING_COOLDOWN_MAX = 1;
	public static final float BUBBLE_SCALE = 0.96f;
	public static final float GROWTH_FACTOR = 0.2f;
	public static final float SLOW_FACTOR = 0.95f;
	public static final float MAX_SPEED = 70;
	public static final float WIDTH = 16, HEIGHT = 12;

	void attachAI(AI ai);

	void eat(Fish fish);

	void start();

	GameContext getGameContext();

	boolean greaterThan(Fish fish);

	boolean smallerThan(Fish other);

	OtherFish getHandle();

	static boolean smallerThan(Fish thisFish, Fish thatFish) {
		return smallerThan(thisFish.getScale(), thatFish.getScale());
	}

	static boolean smallerThan(float thisScale, float thatScale) {
		float diff = thisScale - thatScale;
		boolean result = diff < -thisScale * Fish.MASS_DIFFERENCE_MARGIN ? true
				: diff > thisScale * Fish.MASS_DIFFERENCE_MARGIN ? false
						: false;
		return result;
	}

	static boolean greaterThan(Fish thisFish, Fish thatFish) {
		return greaterThan(thisFish.getScale(), thatFish.getScale());
	}

	static boolean greaterThan(float thisScale, float thatScale) {
		float diff = thisScale - thatScale;
		boolean result = diff < -thisScale * Fish.MASS_DIFFERENCE_MARGIN ? false
				: diff > thisScale * Fish.MASS_DIFFERENCE_MARGIN ? true : false;
		return result;
	}

}
