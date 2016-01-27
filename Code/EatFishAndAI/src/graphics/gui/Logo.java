package graphics.gui;

import gameobjects.AbstractGameObject;
import tween.CommonTweens;
import assets.Assets;

import com.badlogic.gdx.graphics.g2d.Sprite;

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
