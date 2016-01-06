package gamecontext;

import gamecontext.physics.BruteForcePhysicsHandler;
import gamecontext.physics.PhysicsHandler;
import gameobjects.Fish;
import gameobjects.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameContext {

	private final List<GameObject> objects;
	private final Stack<GameObject> add;
	private final Stack<GameObject> remove;
	private final Stack<GameObject> newlySpawned;
	private final Stack<GameObject> newlyDespawned;

	private PhysicsHandler physics;

	public GameContext() {
		objects = new ArrayList<>();
		add = new Stack<>();
		remove = new Stack<>();
		newlySpawned = new Stack<>();
		newlyDespawned = new Stack<>();

		physics = new BruteForcePhysicsHandler();
	}

	public void update(float delta) {

		objects.iterator().forEachRemaining(a -> a.update(delta));
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

}
