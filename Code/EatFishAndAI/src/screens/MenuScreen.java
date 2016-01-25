package screens;

import game.EatFishAndAI;
import graphics.gui.Logo;
import graphics.gui.buttons.ButtonAction;
import graphics.gui.buttons.ButtonAction.TYPE;
import graphics.gui.buttons.ButtonListener;
import graphics.gui.buttons.CustomTextButton;
import loading.LoadTask;
import spawners.DummySpawner;
import spawners.Spawner;
import ai.AIConfiguration;
import assets.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import configuration.GameConfiguration;

public class MenuScreen extends AbstractScreen {
	Spawner spawner;

	public MenuScreen(EatFishAndAI game) {
		super(game);
	}

	@Override
	public void show() {
		spawner = new DummySpawner();
		spawner.setGameContext(getGameContext());
		spawner.setBounds(new Rectangle(0, 0, EatFishAndAI.WIDTH,
				EatFishAndAI.HEIGHT / 4));
		setBackground(Assets.bg);

		int centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;

		getGameContext().spawn(
				new Logo(centerX - Logo.WIDTH / 2, centerY * 63 / 40
						- Logo.HEIGHT / 2));

		CustomTextButton startButton = new CustomTextButton(centerX
				- CustomTextButton.WIDTH / 2, centerY * 4 / 5, "Start");

		startButton.setButtonListener(new ButtonListener() {
			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					getGameContext().getGame().setScreen(
							new ConfigureScreen(getGameContext().getGame()));
				}
			}
		});

		CustomTextButton rulesButton = new CustomTextButton(centerX
				- CustomTextButton.WIDTH / 2, centerY * 3 / 5, "Rules");

		rulesButton.setButtonListener(e -> {
			if (e.type == TYPE.RELEASE) {
				Gdx.net.openURI(EatFishAndAI.WEBSITE);
			}
		});

		CustomTextButton quickStartButton = new CustomTextButton(centerX
				- CustomTextButton.WIDTH / 2, centerY * 2 / 5, "Quick start");

		quickStartButton.setButtonListener(new ButtonListener() {

			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					GameConfiguration conf = new GameConfiguration();
					game.setScreen(new LoadingScreen(game,
							new LoadTask[] { new LoadTask() {
								@Override
								public void load() {
									conf.gamename = "quickplay";
									conf.aiconf = new AIConfiguration();
									conf.aiconf.loadAI("src/ai/KjelliAI.java");
									conf.aiconf.loadAI("src/ai/KjelliAI.java");
									conf.aiconf.loadAI("src/ai/KjelliAI.java");
									conf.aiconf.loadAI("src/ai/KjelliAI.java");
								}
							} }, new PlayScreen(game, conf)));

				}
			}
		});

		CustomTextButton quitButton = new CustomTextButton(centerX
				- CustomTextButton.WIDTH / 2, centerY * 1 / 5, "Quit");

		quitButton.setButtonListener(e -> {
			if (e.type == TYPE.RELEASE) {
				Gdx.app.exit();
			}
		});

		getGameContext().spawn(startButton);
		getGameContext().spawn(rulesButton);
		getGameContext().spawn(quickStartButton);
		getGameContext().spawn(quitButton);

	}

	@Override
	protected void update(float delta) {
		spawner.update(delta);
	}

}
