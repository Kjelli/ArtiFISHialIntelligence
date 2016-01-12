package screens;

import com.badlogic.gdx.Game;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.PredatorFish;
import gameobjects.ScaredFish;
import spawners.DummySpawner;
import spawners.Spawner;
import assets.Assets;

public class PlayScreen extends AbstractScreen {

	Spawner spawner;

	public PlayScreen(Game game) {
		super(game);
		System.out.println("Playscreen activated");
	}

	@Override
	public void show() {
		System.out.println("Playscreen shown");
		setBackground(Assets.bg);

		spawner = new DummySpawner();
		spawner.setGameContext(getGameContext());


		// Controlled initial spawn
		// for (int i = 0; i < 70; i++) {
		// getGameContext().spawn(
		// new ScaredFish(50 + ((i % 20) * 25),
		// (i / 16 + 1) * 40 + 100));
		// }
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

	protected void update(float delta) {
		spawner.update(delta);
	}

}
