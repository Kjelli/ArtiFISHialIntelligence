package gameobjects;

import ai.DummyAI;
import ai.ScaredAI;
import assets.Assets;

public class DummyFish extends AbstractFish {

	public DummyFish(float x, float y) {
		super(Assets.dummyfish.any(), x, y, WIDTH, HEIGHT);
		setScale((float) (0.8f + Math.random() * 0.1f));
	}

	@Override
	public void onSpawn() {
		attachAI(new DummyAI());
	}

}
