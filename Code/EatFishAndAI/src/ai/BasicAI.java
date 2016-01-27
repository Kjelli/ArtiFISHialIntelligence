package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class BasicAI extends AbstractAI {

	OtherFish prey;
	YourFish fish;
	boolean initialHunt = true;

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		if (prey == null || !prey.isAlive()) {
			huntNewTarget(otherFish);
		}
		fish.moveTowards(prey);
	}

	private void huntNewTarget(List<OtherFish> otherFish) {
		float shortestDistance = 10000;
		for (OtherFish other : otherFish) {
			if (!other.isAlive()) {
				continue;
			}
			float distance = fish.distanceTo(other);
			if (distance < shortestDistance) {
				shortestDistance = distance;
				prey = other;
			}
		}
	}

	@Override
	public void ateFish(OtherFish handle) {

	}
}
