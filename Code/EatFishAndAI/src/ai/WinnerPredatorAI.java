package ai;

import java.io.IOException;
import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class WinnerPredatorAI extends AbstractAI {

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
			fish.setVelocityX(- 1);
			fish.setVelocityY(0);
			huntNewTarget(otherFish);
			initialHunt = false;
		}
		if (Math.random() < 0.025f) {
			huntNewTarget(otherFish);
			if (prey == null) {
				fish.setVelocityX(- 1);
				fish.setVelocityY(0);
			}
		}
		if (prey != null) {
			if (!prey.isAlive() || !fish.greaterThan(prey)) {
				prey = null;
				huntNewTarget(otherFish);
				fish.setVelocityX(- 1);
				fish.setVelocityY(0);
			} else {
				fish.moveTowards(prey);
			}
		}else{
			fish.setVelocityX(- 1);
			fish.setVelocityY(0);
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
