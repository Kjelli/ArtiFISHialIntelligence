package screens;

import java.util.List;

import game.EatFishAndAI;
import gameobjects.Fish;
import gameobjects.PlayerFish;
import gameobjects.PredatorFish;
import spawners.DummySpawner;
import spawners.Spawner;
import ai.AIConfiguration;
import ai.loader.AIFactory;
import assets.Assets;

import com.badlogic.gdx.Game;

import configuration.GameConfiguration;

public class PlayScreen extends AbstractScreen {

	Spawner spawner;
	private final GameConfiguration conf;

	public PlayScreen(Game game, GameConfiguration conf) {
		super(game);
		this.conf = conf;
	}

	@Override
	public void show() {
		setBackground(Assets.bg);

		spawner = new DummySpawner();
		spawner.setGameContext(getGameContext());

		// Preset predator ai
		// int offset = 100;
		// getGameContext().spawn(
		// new PredatorFish(EatFishAndAI.WIDTH - offset,
		// EatFishAndAI.HEIGHT - offset));
		// getGameContext().spawn(
		// new PredatorFish(EatFishAndAI.WIDTH - offset, offset));
		// getGameContext().spawn(
		// new PredatorFish(offset, EatFishAndAI.HEIGHT - offset));
		// getGameContext().spawn(new PredatorFish(offset, offset));
		int centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;
		List<AIFactory<?>> factories = conf.aiconf.getAIs();
		int count = factories.size(), radius = 200;
		float angle = (float) (2 * Math.PI / count);
		for (int i = 0; i < conf.aiconf.getAIs().size(); i++) {
			Fish player = new PlayerFish((float) (centerX - PlayerFish.WIDTH
					/ 2 + Math.cos(i*angle) * radius), (float) (centerY
					- PlayerFish.HEIGHT / 2 + Math.sin(i*angle) * radius));

			getGameContext().spawn(player);
			player.setGameContext(getGameContext());
			player.attachAI(factories.get(i).newInstance());
			player.start();
		}
	}

	protected void update(float delta) {
		spawner.update(delta);
	}

}
