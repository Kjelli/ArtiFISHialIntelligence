package ai;

import java.util.List;

import utils.Log;
import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;

public class SmartPredatorAI extends AbstractAI {
	// Modify the code from here
	//

	YourFish myFish;
	OtherFish prey;
	float preyDist = 10000;
	OtherFish hunter;
	float hunterDist = 10001;

	@Override
	public void init(YourFish fish) {
		myFish = fish;
	}

	@Override
	public void act(List<OtherFish> otherFish) {
		scoutPrey(otherFish);
		scoutHunter(otherFish);

		if (hunterDist < safeRange() && hunter != null && hunter.isAlive()) {
			myFish.moveFrom(hunter);
		} else if (prey != null && prey.isAlive()) {
			myFish.moveTowards(prey);
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
						interest /= 2;
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

}
