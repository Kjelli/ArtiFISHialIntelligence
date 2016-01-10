package gameobjects;

import ai.AI;
import ai.AdamAI;
import ai.PredatorAI;
import ai.loader.AILoader;
import assets.Assets;

public class PredatorFish extends AbstractFish {

	public PredatorFish(float x, float y) {
		super(Assets.predatorfish, x, y, 16, 12);
		setScale((float) (1.4f + Math.random() * 1f));
	}

	@Override
	public void onSpawn() {
		AI ai;

		try {
			ai = AILoader.load("src/ai/AdamAI.java");
			attachAI(ai);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// attachAI(new PredatorAI());
	}
}
