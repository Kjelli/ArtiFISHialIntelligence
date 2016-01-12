package gameobjects;

import assets.Assets;

/**
 * TODO -
 * 
 * @author Kjell Arne Hellum
 *
 */
public class PlayerFish extends AbstractFish {

	public PlayerFish(float x, float y) {
		super(Assets.predatorfish, x, y, WIDTH, HEIGHT);
		setScale((float) (1.4f));
		setMaxSpeed(MAX_SPEED*1.2f);
	}

	@Override
	public void onSpawn() {
	}

}
