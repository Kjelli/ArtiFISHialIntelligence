package spawners;

import com.badlogic.gdx.math.Rectangle;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.fish.DummyFish;
import gameobjects.fish.Fish;

public class DummySpawner implements Spawner {
	private GameContext context;
	private Rectangle bounds;

	private static final int SPAWN_TIME_MIN = 10, SPAWN_TIME_MAX = 50;

	private int spawnTimer;

	public DummySpawner() {
		bounds = new Rectangle(0, 0, EatFishAndAI.WIDTH, EatFishAndAI.HEIGHT);
	}

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
		float spawnY = (float) (Math.random() * (bounds.height - bounds.x) + bounds.x);
		float spawnX = (rightSide ? bounds.width : bounds.x - 11);
		Fish f = new DummyFish(spawnX, spawnY);
		f.setVelocityX(rightSide ? -1 : 1);
		f.setVelocityY(0);
		context.spawn(f);

	}

	@Override
	public void setGameContext(GameContext context) {
		this.context = context;
	}

	@Override
	public void setBounds(Rectangle rectangle) {
		this.bounds = rectangle;
	}

}
