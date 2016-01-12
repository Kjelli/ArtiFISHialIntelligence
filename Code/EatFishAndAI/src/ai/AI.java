package ai;

import gamecontext.GameContext;
import gameobjects.Fish;

public interface AI extends Runnable {
	void act();

	void kill();

	// TODO void onStart();

	void setGameContext(GameContext gc);

	void setFish(Fish fish);

	GameContext getGameContext();

	Fish getFish();
}
