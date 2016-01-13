package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import gamecontext.GameContext;

public interface AI extends Runnable {
	void init(YourFish fish);
	
	void act(List<OtherFish> otherFish);

	void kill();

	// TODO void onStart();

	void setGameContext(GameContext gc);

	void setFishHandler(YourFish fish);
}
