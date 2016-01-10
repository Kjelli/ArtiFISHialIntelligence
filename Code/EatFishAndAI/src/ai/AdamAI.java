package ai;

import gameobjects.Fish;
import gameobjects.GameObject;

public class AdamAI extends AbstractAI {

	Fish target;

	@Override
	public void act() {
		if (target != null) {
			getFish().moveTowards(target);
			if (!target.isAlive()) {
				findNewTarget();
			}
		}
		if (target == null) {
			findNewTarget();
		}
	}

	private void findNewTarget() {
		target = null;
		while (target == null) {
			GameObject o = getGameContext().getObjects()
					.get((int) (Math.random() * getGameContext().getObjects()
							.size()));

			if (o instanceof Fish) {
				if (target == null && o.isAlive()) {
					target = (Fish) o;
				}
			}
		}
	}

}
