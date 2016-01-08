package gameobjects;

import ai.BasicAI;
import assets.Assets;

public class DummyFish extends AbstractFish {

	public DummyFish(float x, float y) {
		super(Assets.dummyfish.any(), x, y, 16, 12);
		setSize((float) (0.8f + Math.random() * 0.1f));
	}

	@Override
	public void onSpawn() {
		attachAI(new BasicAI(this, getGameContext()));
	}

}
