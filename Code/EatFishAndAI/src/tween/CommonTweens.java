package tween;

import com.badlogic.gdx.graphics.Camera;

import tween.accessors.CameraAccessor;
import tween.accessors.GameObjectAccessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import game.EatFishAndAI;
import gameobjects.GameObject;
import static tween.accessors.GameObjectAccessor.*;

public class CommonTweens {

	// Fade in and out (transparency)

	public static void quickFadeOut(GameObject go, float seconds) {
		GlobalTween.add(fadeOut(go, seconds));
	}

	public static Tween fadeOut(GameObject go, float seconds) {
		return Tween.to(go, SPRITE_A, seconds).target(0.0f);
	}

	public static void quickFadeIn(GameObject go, float seconds) {
		GlobalTween.add(fadeIn(go, seconds));
	}

	public static Tween fadeIn(GameObject go, float seconds) {
		return Tween.to(go, SPRITE_A, seconds).target(1.0f);
	}

	// Move to or from (position)

	public static void quickMoveFrom(GameObject go, float fromX, float fromY,
			float seconds) {
		GlobalTween.add(moveFrom(go, fromX, fromY, seconds));
	}

	public static Tween moveFrom(GameObject go, float fromX, float fromY,
			float seconds) {
		return Tween.from(go, POS_XY, seconds).target(fromX, fromY);
	}

	public static void quickMoveTo(GameObject go, float toX, float toY,
			float seconds) {
		GlobalTween.add(moveTo(go, toX, toY, seconds));
	}

	public static Tween moveTo(GameObject go, float toX, float toY,
			float seconds) {
		return Tween.to(go, POS_XY, seconds).target(toX, toY);
	}

	// Grow or shrink to or from (scale)

	public static void quickScaleTo(GameObject go, float toScale, float seconds) {
		GlobalTween.add(scaleTo(go, toScale, seconds));
	}

	public static Tween scaleTo(GameObject go, float toScale, float seconds) {
		return Tween.to(go, SCALE, seconds).target(toScale);
	}

	public static void quickScaleFrom(GameObject go, float fromScale,
			float seconds) {
		GlobalTween.add(scaleFrom(go, fromScale, seconds));
	}

	public static Tween scaleFrom(GameObject go, float fromScale, float seconds) {
		return Tween.from(go, SCALE, seconds).target(fromScale);
	}

	// Camera

	public static Timeline zoomAtGameObject(GameObject go, Camera cam,
			float zoom, float duration) {
		return zoomAtPoint(go.getCenterX(), go.getCenterY(), cam, zoom, duration);
	}

	public static Timeline zoomAtPoint(float x, float y, Camera cam, float zoom, float duration) {
		Timeline timeline = Timeline
				.createParallel()
				.push(Tween.to(cam, CameraAccessor.CAM_XY, duration).target(x, y))
				.push(Tween.to(cam, CameraAccessor.CAM_WH, duration).target(
						EatFishAndAI.WIDTH / zoom, EatFishAndAI.HEIGHT / zoom));
		return timeline;
	}
}
