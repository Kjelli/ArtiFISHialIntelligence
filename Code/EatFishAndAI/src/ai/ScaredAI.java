package ai;

import game.EatFishAndAI;
import gameobjects.Fish;
import gameobjects.GameObject;

public class ScaredAI extends AbstractAI {

	Fish target;
	int range = 20;

	float centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;

	@Override
	public void act() {
		if (Math.random() < 0.25f) {
			spotBiggest();

			if (target != null) {
				getFish().moveFrom(target);
				if (getFish().distanceTo(target) > range * target.getScale()
						|| !target.isAlive()) {
					target = null;
				}
			}
		}

		float centerAngle = getFish().angleTo(centerX, centerY);
		float centerPull = getFish().distanceTo(centerX, centerY);

		float velx = (float) (Math.cos(centerAngle) * (centerPull / centerX - 0.1));

		float vely = (float) (Math.sin(centerAngle) * (centerPull / centerY - 0.1));

		getFish().setVelocityX(
				(float) (getFish().getVelocityX() * 0.85 + velx * 0.15));
		getFish().setVelocityY(
				(float) (getFish().getVelocityY() * 0.85 + vely * 0.15));
	}

	public void spotBiggest() {
		for (int i = 0; i < getGameContext().getObjects().size(); i++) {
			GameObject o = getGameContext().getObjects().get(i);
			if (o == null || !o.isAlive() || !(o instanceof Fish)
					|| o == getFish()
					|| getFish().distanceTo(o) > range * o.getScale()) {
				continue;
			}
			Fish other = (Fish) o;
			if (getFish().lessThan(other)) {
				if (target == null) {
					target = other;
				} else {
					if (getFish().distanceTo(other) < getFish().distanceTo(
							target)) {
						target = other;
					}
				}
			}
		}
	}

}
