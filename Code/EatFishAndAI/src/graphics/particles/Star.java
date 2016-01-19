package graphics.particles;

import gameobjects.AbstractGameObject;
import tween.CommonTweens;
import tween.GlobalTween;
import assets.Assets;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Star extends AbstractGameObject {

	public Star(float x, float y) {
		super(x, y, 8, 8);
		setSprite(new Sprite(Assets.star));
		setScale((float) (0.2f + Math.random() * 0.5f));
		setMaxSpeed((float) (100 * (Math.random()+1) * getScale()));
		getSprite().setColor((float) Math.random() * 0.5f + 0.5f,
				(float) Math.random() * 0.5f + 0.5f,
				(float) Math.random() * 0.5f + 0.5f, 1f);
	}

	@Override
	public void onSpawn() {
		GlobalTween.add(CommonTweens.fadeOut(this, 6.0f * getScale())
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int type, BaseTween<?> source) {
						if (type == COMPLETE) {
							destroy();
						}
					}
				}));
	}

	@Override
	public void update(float delta) {
		setVelocityY(getVelocityY() - 0.01f);
		move(delta);
	}

}
