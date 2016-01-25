package ai;

import java.util.ArrayList;
import java.util.List;

import ai.observation.AIObserver;
import fishhandles.OtherFish;
import fishhandles.YourFish;
import gamecontext.GameContext;

public abstract class AbstractAI implements AI {

	private GameContext context;
	private YourFish fish;
	private volatile boolean running;
	private AIObserver observer;

	@Override
	public final void run() {
		/*
		 * TODO Limit tick rate further?
		 */

		setAIObserver(new AIObserver(this, 40f));

		running = true;

		/*
		 * Try to update the AI continuously. If some exception occurs, the AI
		 * is considered dead and the corresponding fish will not alter its
		 * behaviour any longer
		 */

		try {
			init(fish);
			observer.indicateStart();
			long last = System.nanoTime();
			while (running) {
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
					update(otherFish);

					double millisSinceLastIteration = (System.nanoTime() - last) / 1_000_000;
					last = System.nanoTime();
					observer.tick(millisSinceLastIteration);
				}
				
				Thread.sleep(20);
			}
		} catch (Exception e) {
			observer.indicateCrash(e);
		}
	}

	public final AIObserver getAIObserver() {
		return observer;
	}

	public void setAIObserver(AIObserver observer) {
		this.observer = observer;
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
		observer.indicateKill();
	}
}
