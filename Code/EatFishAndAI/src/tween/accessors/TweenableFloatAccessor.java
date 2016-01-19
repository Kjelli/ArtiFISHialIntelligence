package tween.accessors;

import utils.TweenableFloat;
import aurelienribon.tweenengine.TweenAccessor;

public class TweenableFloatAccessor implements TweenAccessor<TweenableFloat> {

	@Override
	public int getValues(TweenableFloat target, int tweenType,
			float[] returnValues) {
		returnValues[0] = target.getValue();
		return 1;
	}

	@Override
	public void setValues(TweenableFloat target, int tweenType,
			float[] newValues) {
		target.setValue(newValues[0]);
	}

}
