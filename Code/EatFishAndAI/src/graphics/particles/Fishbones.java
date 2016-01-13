package graphics.particles;

import tween.CommonTweens;
import game.EatFishAndAI;
import gameobjects.AbstractGameObject;
import gameobjects.fish.Fish;
import assets.Assets;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fishbones extends AbstractGameObject {
	boolean flipped = false;

	public Fishbones(float x, float y, Fish eater, Fish eaten) {
		super(x, y, 16, 12);

		setSprite(new Sprite(Assets.fishbones));

		setScale(eaten.getScale());
		this.flipped = eaten.getVelocityX() < 0;
		setMaxSpeed(100 / getScale());
		setVelocityX(-eater.getVelocityX() * eater.getMaxSpeed() / 20);
	}

	@Override
	public void onSpawn() {
		CommonTweens.quickFadeOut(this, 5.0f * getScale());
	}

	@Override
	public void update(float delta) {
		setVelocityX(getVelocityX() * 0.95f);
		setVelocityY(getVelocityY() * 1.05f + 0.0005f / getScale());
		move(delta);
		if (getY() > EatFishAndAI.HEIGHT) {
			destroy();
		}
	}
}
