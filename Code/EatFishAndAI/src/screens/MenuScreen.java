package screens;

import loading.LoadTask;
import game.EatFishAndAI;
import gameobjects.fish.PlayerFish;
import graphics.gui.Logo;
import graphics.gui.buttons.AbstractButton;
import graphics.gui.buttons.ButtonAction;
import graphics.gui.buttons.CustomTextButton;
import graphics.gui.buttons.ButtonAction.TYPE;
import graphics.gui.buttons.ButtonListener;
import graphics.gui.buttons.StartConfigurationButton;
import spawners.DummySpawner;
import spawners.Spawner;
import ai.AIConfiguration;
import assets.Assets;

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

		StartConfigurationButton startConfigurationButton = new StartConfigurationButton(
				centerX - StartConfigurationButton.WIDTH / 2, centerY * 4 / 5);

		startConfigurationButton.setButtonListener(new ButtonListener() {
			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					getGameContext().getGame().setScreen(
							new ConfigureScreen(getGameContext().getGame()));
				}
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
									for (int i = 0; i < 4; i++) {
										conf.aiconf
												.loadAI("src/ai/PredatorAI.java");
									}
								}
							} }, new PlayScreen(game, conf)));

				}
			}
		});

		getGameContext().spawn(quickStartButton);
		getGameContext().spawn(startConfigurationButton);

	}

	@Override
	protected void update(float delta) {
		spawner.update(delta);
	}

}
