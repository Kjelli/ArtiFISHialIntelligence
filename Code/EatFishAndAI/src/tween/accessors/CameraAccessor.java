package tween.accessors;

import com.badlogic.gdx.graphics.Camera;

import aurelienribon.tweenengine.TweenAccessor;

public class CameraAccessor implements TweenAccessor<Camera> {

	public static final int CAM_XY = 0, CAM_WH = 1;

	@Override
	public int getValues(Camera target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case CAM_XY:
			returnValues[0] = target.position.x;
			returnValues[1] = target.position.y;
			return 2;
			
		case CAM_WH:
			returnValues[0] = target.viewportWidth;
			returnValues[1] = target.viewportHeight;
			return 2;
		default:
			return -1;
		}

	}

	@Override
	public void setValues(Camera target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case CAM_XY:
			target.position.x = newValues[0];
			target.position.y = newValues[1];
			break;
		case CAM_WH:
			target.viewportWidth = newValues[0];
			target.viewportHeight = newValues[1];
		default:
			break;
		}
	}

}