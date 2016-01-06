package gameobjects;

import ai.PredatorAI;
import assets.Assets;

public class PredatorFish extends AbstractFish {

	public PredatorFish(float x, float y) {
		super(Assets.predatorfish, x, y, 16, 12);
		setSize((float) (1.4f + Math.random() * 1f));
	}

	@Override
	public void onSpawn() {
		attachAI(new PredatorAI(this, getGameContext()));
	}
}
