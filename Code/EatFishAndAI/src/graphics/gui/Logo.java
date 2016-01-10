package graphics.gui;

import tween.CommonTweens;
import tween.GlobalTween;
import tween.accessors.GameObjectAccessor;
import assets.Assets;
import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameobjects.AbstractGameObject;
import graphics.Draw;

public class Logo extends AbstractGameObject {

	public static final int WIDTH = 640, HEIGHT = 200;

	public Logo(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		setSprite(new Sprite(Assets.splash));
		getSprite().setAlpha(0.0f);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void onSpawn() {
		CommonTweens.quickFadeIn(this, 1.0f);
	}

	@Override
	public void onDespawn() {

	}

}
