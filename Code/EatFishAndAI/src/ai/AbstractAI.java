package ai;

import gamecontext.GameContext;
import gameobjects.Fish;

public abstract class AbstractAI implements AI {

	private final GameContext context;
	private final Fish fish;
	private volatile boolean running;

	public AbstractAI(Fish fish, GameContext context) {
		this.fish = fish;
		this.context = context;
	}

	@Override
	public final void run() {
		/*
		 * TODO Limit tick rate
		 */

		running = true;

		while (running) {
			try {
				act(fish, context);
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void kill() {
		running = false;
	}
}
