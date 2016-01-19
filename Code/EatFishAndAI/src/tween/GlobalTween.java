package tween;

import com.badlogic.gdx.graphics.Camera;

import gameobjects.GameObject;
import tween.accessors.CameraAccessor;
import tween.accessors.TweenableFloatAccessor;
import tween.accessors.GameObjectAccessor;
import utils.TweenableFloat;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GlobalTween {
	private static TweenManager manager;

	public static TweenManager getManager() {
		if (manager == null) {
			Tween.registerAccessor(GameObject.class, new GameObjectAccessor());
			Tween.registerAccessor(Camera.class, new CameraAccessor());
			Tween.registerAccessor(TweenableFloat.class, new TweenableFloatAccessor());
			manager = new TweenManager();

		}

		return manager;
	}

	public static void add(Tween tween) {
		add(tween, GameObject.class);
	}

	public static void add(Tween tween, Class<?> cls) {
		tween.cast(cls).start(getManager());
	}

	public static void add(Timeline timeline) {
		timeline.start(getManager());
	}

}
