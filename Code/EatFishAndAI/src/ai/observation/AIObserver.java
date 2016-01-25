package ai.observation;

import ai.AI;

public class AIObserver {

	public static enum Status {
		INITIALIZING, ALIVE, SLOW, SLOWER, SLOWEST, CRASHED, KILLED
	}

	private AI ai;
	private Status status = Status.INITIALIZING;
	private double timeAlive = 0;
	private boolean running = true;
	private double expectedInterval;

	public AIObserver(AI ai, double expectedInterval) {
		this.ai = ai;
		this.expectedInterval = expectedInterval;
	}

	public void indicateStart() {
		status = Status.ALIVE;
	}

	public void indicateCrash(Exception e) {
		running = false;
		status = Status.CRASHED;
	}

	public void tick(double millisSinceLastIteration) {
		if (millisSinceLastIteration > 8 * expectedInterval) {
			status = Status.SLOWEST;
		} else if (millisSinceLastIteration > 2 * expectedInterval) {
			status = Status.SLOWER;
		} else if (millisSinceLastIteration > expectedInterval) {
			status = Status.SLOW;
		} else {
			status = Status.ALIVE;
		}
		timeAlive += millisSinceLastIteration / 1000;
	}

	public void indicateKill() {
		running = false;
		status = Status.KILLED;
	}

	public Status getStatus() {
		return status;
	}

	public double getTimeAlive() {
		return timeAlive;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isRunning() {
		return running;
	}

}
