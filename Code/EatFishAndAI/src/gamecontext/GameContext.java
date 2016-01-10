package gamecontext;

import gamecontext.physics.BruteForcePhysicsHandler;
import gamecontext.physics.PhysicsHandler;
import gameobjects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameContext {

	private final Game game;

	private final List<GameObject> objects;
	private final Stack<GameObject> add;
	private final Stack<GameObject> remove;
	private final Stack<GameObject> newlySpawned;
	private final Stack<GameObject> newlyDespawned;

	private Stage stage;
	private PhysicsHandler physics;
	private long ticks = 0;
	private double elapsedTime = 0;

	private float timeModifier = 1.0f;

	private boolean paused = false;

	public GameContext(Game game) {
		this.game = game;

		objects = new ArrayList<>();
		add = new Stack<>();
		remove = new Stack<>();
		newlySpawned = new Stack<>();
		newlyDespawned = new Stack<>();

		stage = new Stage();

		// TODO Optimize in the future
		physics = new BruteForcePhysicsHandler();
	}

	public void update(float delta) {
		ticks++;
		elapsedTime += delta;

		objects.iterator()
				.forEachRemaining(a -> a.update(delta * timeModifier));
		while (!add.isEmpty()) {
			GameObject n = add.pop();
			n.setGameContext(this);
			newlySpawned.push(n);
			objects.add(n);
		}
		while (!remove.isEmpty()) {
			GameObject o = remove.pop();
			newlyDespawned.push(o);
			objects.remove(o);
		}

		while (!newlySpawned.isEmpty()) {
			GameObject o = newlySpawned.pop();
			o.onSpawn();
		}

		while (!newlyDespawned.isEmpty()) {
			GameObject o = newlyDespawned.pop();
			o.onDespawn();
		}

		physics.collisonCheck(objects);
	}

	public final List<GameObject> getObjects() {
		return objects;
	}

	public void spawn(GameObject object) {
		add.push(object);
	}

	public void despawn(GameObject object) {
		remove.push(object);
	}

	public void draw(SpriteBatch batch) {
		objects.iterator().forEachRemaining(a -> a.draw(batch));
	}

	public void dispose() {
		// TODO
	}

	public long getTicks() {
		return ticks;
	}

	public Stage getStage() {
		return stage;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public Game getGame() {
		return game;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isPaused() {
		return paused;
	}

}
