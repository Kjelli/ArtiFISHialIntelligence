package graphics.particles;

import tween.CommonTweens;
import tween.GlobalTween;

import com.badlogic.gdx.graphics.g2d.Sprite;

import assets.Assets;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import game.EatFishAndAI;
import gameobjects.AbstractGameObject;

public class Fireworks extends AbstractGameObject {

	public Fireworks(float x, float y) {
		super(x, y, 8, 8);
		setSprite(new Sprite(Assets.star));
	}

	@Override
	public void onSpawn() {
		GlobalTween.add(CommonTweens
				.moveFrom(this, (float) (Math.random() * 120f - 60f) + x,
						-8, (float) (3.3f + Math.random() * 1.0f))
				.ease(TweenEquations.easeOutQuart)
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
	public void onDespawn() {
		int count = (int) (Math.random() * 170);
		for (int i = 0; i < count; i++) {
			Star star = new Star(x, y);
			star.setVelocityX((float) (Math.random() * 2 - 1));
			star.setVelocityY((float) (Math.random() * 2 - 1));
			getGameContext().spawn(star);
		}
	}

	@Override
	public void update(float delta) {
		setScale(getScale()*0.995f);
	}

}
