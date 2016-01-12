package ai;

import gameobjects.Fish;
import gameobjects.GameObject;

public class PredatorAI extends AbstractAI {

	Fish prey;
	boolean initialHunt = true;

	@Override
	public void act() {
		if (initialHunt) {
			getFish().setVelocityX((float) Math.random() * 2 - 1);
			getFish().setVelocityY((float) Math.random() * 2 - 1);
			huntNewTarget();
			initialHunt = false;
		}
		if (Math.random() < 0.025f) {
			huntNewTarget();
			if (prey == null) {
				getFish().setVelocityX((float) Math.random() * 2 - 1);
				getFish().setVelocityY((float) Math.random() * 2 - 1);
			}
		}
		if (prey != null) {

			getFish().moveTowards(prey);
			if (!getFish().greaterThan(prey) || !prey.isAlive()) {
				prey = null;
				huntNewTarget();
			}
		}
	}

	private void huntNewTarget() {
		double highestInterest = 0;
		for (int i = 0; i < getGameContext().getObjects().size(); i++) {
			GameObject o = getGameContext().getObjects().get(i);
			if (!o.equals(getFish()) && o instanceof Fish && o.isAlive()) {
				Fish that = (Fish) o;
				if (getFish().greaterThan(that)) {
					if (prey == null) {
						prey = that;

						highestInterest = Math.pow(2 * that.getScale(), 2)
								/ getFish().distanceTo(that);
					} else {
						double interest = 10 * that.getScale()
								/ getFish().distanceTo(that);
						if (interest > highestInterest) {
							highestInterest = interest;
							prey = that;

						}
					}
				}

			}
		}
		if (prey != null) {
			getFish().moveTowards(prey);
		}
	}
}
