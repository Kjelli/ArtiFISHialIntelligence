package ai;

import gamecontext.GameContext;
import gameobjects.Fish;

public abstract class AbstractAI implements AI {

	private GameContext context;
	private Fish fish;
	private volatile boolean running;

	@Override
	public final void run() {
		/*
		 * TODO Limit tick rate
		 */

		running = true;

		while (running) {
			try {
				if (!context.isPaused()) {
					act();
				}
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public void setGameContext(GameContext context) {
		this.context = context;
	}

	public GameContext getGameContext() {
		return context;
	}

	public Fish getFish() {
		return fish;
	}

	@Override
	public final void kill() {
		running = false;
	}
}
