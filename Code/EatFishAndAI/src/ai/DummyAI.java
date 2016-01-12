package ai;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class DummyAI extends AbstractAI {
	int angle;

	public DummyAI() {
		angle = (int) (Math.random() * 360);
	}

	@Override
	public void act() {
		getFish().setVelocityY((float) (cos(angle * PI / 180.0f) / 3));
		angle = (angle + 1) % 360;
	}

}
