package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.PredatorFish;
import gameobjects.ScaredFish;
import spawners.DummySpawner;
import spawners.Spawner;
import assets.Assets;

public class PlayScreen extends AbstractScreen {

	Spawner spawner;

	public PlayScreen(EatFishAndAI game) {
		super(game);
		setGameContext(new GameContext());
	}

	/**
	 * Screen is shown. Runs before each render loop. Use to init.
	 */

	@Override
	public void show() {
		setBackground(Assets.bg);

		spawner = new DummySpawner();
		spawner.setGameContext(getGameContext());

		// Random initial spawn

		// for (int i = 0; i < maxSpawn; i++) {
		// int randX = (int) (Math.random() * EatFishAndAI.WIDTH);
		// int randY = (int) (Math.random() * EatFishAndAI.HEIGHT);
		// Fish fish = new ScaredFish(randX, randY);
		// context.spawn(fish);
		// }

		// Controlled initial spawn
		for (int i = 0; i < 70; i++) {
			getGameContext().spawn(
					new ScaredFish(50 + ((i % 20) * 25),
							(i / 16 + 1) * 40 + 100));
		}
		int offset = 100;
		getGameContext().spawn(
				new PredatorFish(EatFishAndAI.WIDTH - offset,
						EatFishAndAI.HEIGHT - offset));
		getGameContext().spawn(
				new PredatorFish(EatFishAndAI.WIDTH - offset, offset));
		getGameContext().spawn(
				new PredatorFish(offset, EatFishAndAI.HEIGHT - offset));
		getGameContext().spawn(new PredatorFish(offset, offset));
	}

	/**
	 * The updating of gamelogic
	 * 
	 * @param delta
	 *            The time elapsed from previous iteration in seconds.
	 */

	protected void update(float delta) {
		spawner.update(delta);
	}

}
