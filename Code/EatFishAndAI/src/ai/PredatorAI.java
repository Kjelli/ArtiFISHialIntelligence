package ai;

import gamecontext.GameContext;
import gameobjects.Fish;
import gameobjects.GameObject;

public class PredatorAI extends AbstractAI {

	Fish prey;
	boolean initialHunt = true;

	public PredatorAI(Fish fish, GameContext context) {
		super(fish, context);
	}

	@Override
	public void act() {
		if (initialHunt) {
			fish.setVelocityX((float) Math.random() * 2 - 1);
			fish.setVelocityY((float) Math.random() * 2 - 1);
			huntNewTarget();
			initialHunt = false;
		}
		if (Math.random() < 0.025f) {
			huntNewTarget();
			if (prey == null) {
				fish.setVelocityX((float) Math.random() * 2 - 1);
				fish.setVelocityY((float) Math.random() * 2 - 1);
			}
		}
		if (prey != null) {
			fish.moveTowards(prey);
			if (fish.compareTo(prey) < 1 || !prey.isAlive()) {
				prey = null;
				huntNewTarget();
			}
		}
	}

	private void huntNewTarget() {
		double highestInterest = 0;
		for (int i = 0; i < context.getObjects().size(); i++) {
			GameObject o = context.getObjects().get(i);
			if (!o.equals(fish) && o instanceof Fish && o.isAlive()) {
				Fish that = (Fish) o;

				if (fish.compareTo(that) == 1) {
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
		}
		if (prey != null) {
			fish.moveTowards(prey);
		}
	}
}
