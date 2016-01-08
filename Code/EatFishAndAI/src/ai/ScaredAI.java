package ai;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.Fish;
import gameobjects.GameObject;

public class ScaredAI extends AbstractAI {

	Fish target;
	int range = 20;

	float centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;

	public ScaredAI(Fish fish, GameContext context) {
		super(fish, context);
	}

	@Override
	public void act() {
		if (Math.random() < 0.25f) {
			spotBiggest();

			if (target != null) {
				fish.moveFrom(target);
				if (fish.distanceTo(target) > range * target.getScale()
						|| !target.isAlive()) {
					target = null;
				}
			}
		}

		float centerAngle = fish.angleTo(centerX, centerY);
		float centerPull = fish.distanceTo(centerX, centerY);

		float velx = (float) (Math.cos(centerAngle) * (centerPull / centerX - 0.1));

		float vely = (float) (Math.sin(centerAngle) * (centerPull / centerY - 0.1));

		fish.setVelocityX((float) (fish.getVelocityX() * 0.85 + velx * 0.15));
		fish.setVelocityY((float) (fish.getVelocityY() * 0.85 + vely * 0.15));
	}

	public void spotBiggest() {
		for (int i = 0; i < context.getObjects().size(); i++) {
			GameObject o = context.getObjects().get(i);
			if (o == null || !o.isAlive() || !(o instanceof Fish) || o == fish
					|| fish.distanceTo(o) > range * o.getScale()) {
				continue;
			}
			Fish other = (Fish) o;
			if (fish.compareTo(other) == -1) {
				if (target == null) {
					target = other;
				} else {
					if (fish.distanceTo(other) < fish.distanceTo(target)) {
						target = other;
					}
				}
			}
		}
	}

}
