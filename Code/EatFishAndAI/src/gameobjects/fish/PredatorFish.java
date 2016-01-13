package gameobjects.fish;

import ai.PredatorAI;
import ai.TemplateAI;
import assets.Assets;

public class PredatorFish extends AbstractFish {

	public PredatorFish(float x, float y) {
		super(Assets.predatorfish, x, y, WIDTH, HEIGHT);
		setScale((float) (1.4f + Math.random() * 1f));
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		attachAI(new PredatorAI());
	}
}
