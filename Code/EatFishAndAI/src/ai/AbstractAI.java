package ai;

import java.util.ArrayList;
import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import gamecontext.GameContext;

public abstract class AbstractAI implements AI {

	private GameContext context;
	private YourFish fish;
	private volatile boolean running;

	@Override
	public final void run() {
		/*
		 * TODO Limit tick rate further?
		 */

		running = true;

		init(fish);

		while (running) {
			try {
				if (!context.isPaused()) {
					/*
					 * TODO is this really bad to do each frame for all the
					 * fish? worst case O(n^2)
					 */
					List<OtherFish> otherFish = new ArrayList<>(
							context.getFishHandles());
					for (int i = 0; i < otherFish.size(); i++) {
						if (fish.matches(otherFish.get(i))) {
							otherFish.remove(i);
						}

					}
					act(otherFish);
				}
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public final void setGameContext(GameContext context) {
		this.context = context;
	}

	@Override
	public final void setFishHandler(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public final void kill() {
		running = false;
	}
}
