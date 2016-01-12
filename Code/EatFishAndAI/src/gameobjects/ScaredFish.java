package gameobjects;

import ai.PredatorAI;
import ai.ScaredAI;
import assets.Assets;

public class ScaredFish extends AbstractFish {

	public ScaredFish(float x, float y) {
		super(Assets.scaredfish, x, y, WIDTH, HEIGHT);
		setScale((float) (1.0f + Math.random() * 0.1f));
	}

	@Override
	public void onSpawn() {
		attachAI(new ScaredAI());
	}

}
