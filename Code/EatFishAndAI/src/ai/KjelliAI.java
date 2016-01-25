package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;

public class KjelliAI extends AbstractAI {
	// Modify the code from here
	//

	YourFish myFish;
	OtherFish prey;
	float preyDist = 10000;
	OtherFish hunter;
	float hunterDist = 10001;
	double offset = Math.PI / 5;

	@Override
	public void init(YourFish fish) {
		myFish = fish;
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		scoutPrey(otherFish);
		scoutHunter(otherFish);

		if (hunterDist < safeRange() && hunter != null && hunter.isAlive()) {
			offset = Math.PI / 5 * hunterDist / safeRange();
			float targetX, targetY;

			float angle = (float) (hunter.angleTo(myFish) + offset);
			targetX = myFish.getCenterX()
					+ (float) (hunter.getWidth()
							* (hunter.getScale() - myFish.getScale()) * Math
								.cos(angle));
			targetY = myFish.getCenterY()
					+ (float) (hunter.getWidth()
							* (hunter.getScale() - myFish.getScale()) * Math
								.sin(angle));
			if (targetX <= -myFish.getWidth()
					|| targetX >= EatFishAndAI.WIDTH + myFish.getWidth()
					|| targetY <= -myFish.getHeight()
					|| targetY >= EatFishAndAI.HEIGHT + myFish.getHeight()) {
				do {
					angle += 1 * offset;
					targetX = myFish.getCenterX()
							+ (float) (hunter.getWidth()
									* (hunter.getScale() - myFish.getScale()) * Math
										.cos(angle));
					targetY = myFish.getCenterY()
							+ (float) (hunter.getWidth()
									* (hunter.getScale() - myFish.getScale()) * Math
										.sin(angle));
				} while (targetX <= -myFish.getWidth()
						|| targetX >= EatFishAndAI.WIDTH + myFish.getWidth()
						|| targetY <= -myFish.getHeight()
						|| targetY >= EatFishAndAI.HEIGHT + myFish.getHeight());
			}
			myFish.moveTowards(targetX, targetY);
		} else if (prey != null && prey.isAlive()) {

			float targetX = prey.getCenterX() + myFish.distanceTo(prey)
					* prey.getMaxSpeed() * prey.getVelocityX() / 100;
			float targetY = prey.getCenterY() + myFish.distanceTo(prey)
					* prey.getMaxSpeed() * prey.getVelocityY() / 100;
			myFish.moveTowards(targetX, targetY);
		} else {
			myFish.moveTowards(EatFishAndAI.WIDTH / 2, EatFishAndAI.HEIGHT / 2);
		}

	}

	private float safeRange() {
		return myFish.getWidth() * 2;
	}

	private void scoutHunter(List<OtherFish> otherFish) {
		float minimumDistance = 10000;
		for (OtherFish that : otherFish) {
			if (that.greaterThan(myFish)
					&& myFish.distanceTo(that) < minimumDistance) {
				minimumDistance = myFish.distanceTo(that);
				hunterDist = minimumDistance;
				hunter = that;
			}
		}
	}

	private void scoutPrey(List<OtherFish> otherFish) {
		double highestInterest = 0;
		for (int i = 0; i < otherFish.size(); i++) {
			OtherFish that = otherFish.get(i);
			if (!that.isAlive()) {
				continue;
			}

			if (myFish.greaterThan(that)) {
				if (prey == null) {
					prey = that;

					highestInterest = Math.pow(2 * that.getScale(), 2)
							/ myFish.distanceTo(that);
				} else {
					double interest = 10 * that.getScale()
							/ myFish.distanceTo(that);
					if (headingAwayFromMe(that, myFish)) {
						interest /= 3;
					}
					if (interest > highestInterest) {
						highestInterest = interest;
						prey = that;

					}
				}
			}
		}

	}

	private boolean headingAwayFromMe(OtherFish prey, YourFish myFish) {
		if (prey.getVelocityX() > 0 && prey.getX() - myFish.getX() > 0) {
			return true;
		} else if (prey.getVelocityX() < 0 && myFish.getX() - prey.getX() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void ateFish(OtherFish handle) {
		if (handle == prey) {
			prey = null;
		}
	}
}
