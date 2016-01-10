package tween;

import gameobjects.GameObject;
import tween.accessors.GameObjectAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GlobalTween {
	private static TweenManager manager;

	public static TweenManager getManager() {
		if (manager == null) {
			Tween.registerAccessor(GameObject.class, new GameObjectAccessor());
			manager = new TweenManager();
		}

		return manager;
	}

	public static void add(Tween tween) {
		tween.cast(GameObject.class).start(getManager());
	}

}
