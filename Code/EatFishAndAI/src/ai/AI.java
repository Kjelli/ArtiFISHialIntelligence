package ai;

import gamecontext.GameContext;
import gameobjects.Fish;

public interface AI extends Runnable {
	void act(Fish fish, GameContext context);
	void kill();
}
