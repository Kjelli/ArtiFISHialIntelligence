package tween;

import tween.accessors.GameObjectAccessor;
import aurelienribon.tweenengine.Tween;
import gameobjects.GameObject;

public class CommonTweens {
	public static void fadeOut(GameObject go, float seconds) {
		GlobalTween.add(Tween.to(go, GameObjectAccessor.SPRITE_A, seconds)
				.target(0.0f));
	}

	public static void fadeIn(GameObject go, float seconds) {
		GlobalTween.add(Tween.to(go, GameObjectAccessor.SPRITE_A, seconds)
				.target(1.0f));
	}

	public static void moveFrom(GameObject go, float fromX, float fromY,
			float seconds) {
		GlobalTween.add(Tween.from(go, GameObjectAccessor.POS_XY, seconds)
				.target(fromX, fromY));
	}

	public static void moveTo(GameObject go, float fromX, float fromY,
			float seconds) {
		GlobalTween.add(Tween.to(go, GameObjectAccessor.POS_XY, seconds)
				.target(fromX, fromY));
	}
}
