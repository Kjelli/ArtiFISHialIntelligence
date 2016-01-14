package spawners;

import tween.GlobalTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.math.Rectangle;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.fish.DummyFish;
import gameobjects.fish.Fish;

public class DummySpawner implements Spawner {
	private GameContext context;
	private Rectangle bounds;

	private static final int SPAWN_TIME_MIN = 10, SPAWN_TIME_MAX = 50;
	private static final int SCHOOL_MAX = 20;
	private static final double SCHOOL_SPREAD = 20;

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
		double determinator = Math.random();
		if (determinator < 0.8f) {
			singleFish();
		} else {
			System.out.println("SCHOOL");
			schoolOfFish();
		}
	}

	private void schoolOfFish() {
		int count = (int) (Math.random() * SCHOOL_MAX);

		boolean rightSide = Math.random() < 0.5f;
		float spawnY = (float) (Math.random() * (bounds.height - bounds.x) + bounds.x);
		float spawnX = (rightSide ? bounds.width : bounds.x - 11);
		for (int i = 0; i < count; i++) {
			float effectiveX = (float) (rightSide ? (Math.max(spawnX + (Math.random()) * 3 * SCHOOL_SPREAD, bounds.width)) :
			 (Math.min(spawnX + (Math.random() - 1f) * 3 * SCHOOL_SPREAD, bounds.x)));
			
			singleFish((float) (effectiveX),
					(float) (spawnY + Math.random() * SCHOOL_SPREAD),
					rightSide ? -1 : 1);
		}
	}

	private void singleFish() {
		boolean rightSide = Math.random() < 0.5f;
		float spawnY = (float) (Math.random() * (bounds.height - bounds.x) + bounds.x);
		float spawnX = (rightSide ? bounds.width : bounds.x - 11);
		singleFish(spawnX, spawnY, rightSide ? -1 : 1);
	}

	private void singleFish(float x, float y, float xvel) {
		Fish f = new DummyFish(x, y);
		f.setVelocityX(xvel);
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
