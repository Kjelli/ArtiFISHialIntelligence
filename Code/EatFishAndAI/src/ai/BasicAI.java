package ai;

import gamecontext.GameContext;
import gameobjects.Fish;
import static java.lang.Math.*;

public class BasicAI extends AbstractAI {
	int angle;

	public BasicAI(Fish fish, GameContext context) {
		super(fish, context);
		angle = (int) (Math.random() * 360);

		fish.setVelocityX(20);
	}

	@Override
	public void act(Fish fish, GameContext context) {
		fish.setVelocityY((float) (cos(angle * PI / 180.0f) * 20));
		angle = (angle + 1) % 360;
	}

}
