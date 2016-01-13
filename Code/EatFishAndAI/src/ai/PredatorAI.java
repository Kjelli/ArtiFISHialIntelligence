package ai;

import java.util.List;

import utils.Log;
import fishhandles.OtherFish;
import fishhandles.YourFish;
import gameobjects.GameObject;
import gameobjects.fish.Fish;

public class PredatorAI extends AbstractAI {

	OtherFish prey;
	YourFish fish;
	boolean initialHunt = true;

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void act(List<OtherFish> otherFish) {
		if (initialHunt) {
			fish.setVelocityX((float) Math.random() * 2 - 1);
			fish.setVelocityY((float) Math.random() * 2 - 1);
			huntNewTarget(otherFish);
			initialHunt = false;
		}
		if (Math.random() < 0.025f) {
			huntNewTarget(otherFish);
			if (prey == null) {
				fish.setVelocityX((float) Math.random() * 2 - 1);
				fish.setVelocityY((float) Math.random() * 2 - 1);
			}
		}
		if (prey != null) {
			if (!prey.isAlive() || !fish.greaterThan(prey)) {
				prey = null;
				huntNewTarget(otherFish);
			} else {
				fish.moveTowards(prey);
			}
		}
		
	}

	private void huntNewTarget(List<OtherFish> otherFish) {
		double highestInterest = 0;
		for (int i = 0; i < otherFish.size(); i++) {
			OtherFish that = otherFish.get(i);
			if (!that.isAlive()) {
				continue;
			}

			if (fish.greaterThan(that)) {
				if (prey == null) {
					prey = that;

					highestInterest = Math.pow(2 * that.getScale(), 2)
							/ fish.distanceTo(that);
				} else {
					double interest = 10 * that.getScale()
							/ fish.distanceTo(that);
					if (interest > highestInterest) {
						highestInterest = interest;
						prey = that;

					}
				}
			}

		}
		if (prey != null) {
			fish.moveTowards(prey);
		}
	}
}
