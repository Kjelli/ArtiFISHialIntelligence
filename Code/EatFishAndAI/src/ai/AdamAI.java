package ai;

import gamecontext.GameContext;
import gameobjects.Fish;
import gameobjects.GameObject;

public class AdamAI extends AbstractAI {
	public AdamAI(Fish fish, GameContext context) {
		super(fish, context);
	}

	Fish target;

	@Override
	public void act() {
		if (target != null) {
			fish.moveTowards(target);
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
			GameObject o = context.getObjects().get(
					(int) (Math.random() * context.getObjects().size()));

			if (o instanceof Fish) {
				if (target == null && o.isAlive()) {
					target = (Fish) o;
				}
			}
		}
	}

}
