package ai;

import gamecontext.GameContext;
import gameobjects.Fish;

public interface AI extends Runnable {
	void act();
	void kill();
	
	void setGameContext(GameContext gc);
	void setFish(Fish fish);
	
	GameContext getGameContext();
	Fish getFish();
}
