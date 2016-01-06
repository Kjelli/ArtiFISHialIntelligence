package spawners;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.DummyFish;
import gameobjects.Fish;

public class DummySpawner implements Spawner {
	private GameContext context;

	private static final int SPAWN_TIME_MIN = 10, SPAWN_TIME_MAX = 50;

	private int spawnTimer;

	@Override
	public void update(float delta) {
		if (spawnTimer <= 0) {
			spawnTimer = (int) (SPAWN_TIME_MIN + Math.random()
					* (SPAWN_TIME_MAX - SPAWN_TIME_MIN));
			spawn();
		} else {
			spawnTimer -= delta;
		}
	}

	private void spawn() {
		boolean rightSide = Math.random() < 0.5f;
		float spawnY = (float) (Math.random() * EatFishAndAI.HEIGHT);
		float spawnX = (rightSide ? EatFishAndAI.WIDTH : -11);
		Fish f = new DummyFish(spawnX, spawnY);
		f.setVelocityX(rightSide ? -1 : 1);
		f.setVelocityY(0);
		context.spawn(f);
	}

	@Override
	public void setGameContext(GameContext context) {
		this.context = context;
	}

}
