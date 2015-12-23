package gamecontext;

import gameobjects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameContext {

	public final List<GameObject> objects;
	public final Stack<GameObject> add;
	public final Stack<GameObject> remove;

	public GameContext() {
		objects = new ArrayList<>();
		add = new Stack<>();
		remove = new Stack<>();
	}

	public void update(float delta) {

		objects.iterator().forEachRemaining(a -> a.update(delta));
		while (!add.isEmpty()) {
			GameObject n = add.pop();
			n.setGameContext(this);
			n.onSpawn();
			objects.add(n);
		}
		while (!remove.isEmpty()) {
			GameObject o = remove.pop();
			o.onDespawn();
			objects.remove(o);
		}
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
