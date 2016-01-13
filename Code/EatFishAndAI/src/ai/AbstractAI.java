package ai;

import fishhandles.YourFish;
import gamecontext.GameContext;

public abstract class AbstractAI implements AI {

	private GameContext context;
	private YourFish fish;
	private volatile boolean running;

	@Override
	public final void run() {
		/*
		 * TODO Limit tick rate
		 */

		running = true;

		init(fish);

		while (running) {
			try {
				if (!context.isPaused()) {
					act(context.getFishHandles());
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
