package screens;

import game.EatFishAndAI;
import graphics.gui.buttons.Button;
import graphics.gui.buttons.ButtonAction;
import graphics.gui.buttons.ButtonAction.TYPE;
import graphics.gui.buttons.ButtonListener;
import graphics.gui.buttons.CustomTextButton;
import graphics.gui.buttons.ModifyPlayerButton;
import graphics.gui.buttons.AddPlayerButton;
import graphics.gui.buttons.StartGameButton;
import ai.AI;
import ai.AIConfiguration;
import ai.loader.AIFactory;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import configuration.GameConfiguration;

public class ConfigureScreen extends AbstractScreen {

	private final GlyphLayout layout;
	private final String playerLabel = "Add players";

	private final GlyphLayout layout2;
	private final String roundLabel = "Best of 3";

	GameConfiguration conf;
	float top = EatFishAndAI.HEIGHT, bottom = 0, left = 0,
			right = EatFishAndAI.WIDTH, centerX = EatFishAndAI.WIDTH / 2,
			centerY = EatFishAndAI.HEIGHT / 2;
	float aiButtonX = EatFishAndAI.WIDTH / 4;
	float roundButtons = EatFishAndAI.WIDTH * 3 / 4;

	float spacing = 55f;
	float topMargin = EatFishAndAI.HEIGHT / 6;

	int loadedAIS = 0;

	Button addPlayerButton;
	Button startGameButton;

	public ConfigureScreen(Game game) {
		super(game);
		conf = new GameConfiguration();
		conf.aiconf = new AIConfiguration();

		layout2 = new GlyphLayout(Assets.font30, roundLabel);
		layout = new GlyphLayout(Assets.font30, playerLabel);

		addPlayerButton = new AddPlayerButton(aiButtonX - AddPlayerButton.WIDTH
				/ 2, top - topMargin - 1 * spacing - AddPlayerButton.HEIGHT / 2);

		addPlayerButton.setButtonListener(new ButtonListener() {

			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					conf.aiconf.prompt();
				}
			}
		});

		startGameButton = new StartGameButton(centerX - StartGameButton.WIDTH
				/ 2, centerY / 2);

		startGameButton.setButtonListener(new ButtonListener() {

			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					game.setScreen(new PlayScreen(game, conf));
				}
			}
		});
	}

	@Override
	public void show() {
		setBackground(Assets.bg);
		getGameContext().spawn(addPlayerButton);
		getGameContext().spawn(startGameButton);

		for (int i = 0; i < 3; i++) {
			final int index = i;
			CustomTextButton button = new CustomTextButton(roundButtons
					- CustomTextButton.WIDTH / 2, top - topMargin
					- CustomTextButton.HEIGHT / 2 - (i + 1) * spacing,
					"Best of " + (i * 2 + 1));

			button.setButtonListener(new ButtonListener() {
				@Override
				public void handle(ButtonAction ba) {
					if (ba.type == TYPE.RELEASE) {
						conf.winLimit = index * 2 + 1;
						layout2.setText(Assets.font30, "Best of " + (index * 2 +1));
					}

				}
			});

			getGameContext().spawn(button);
		}
	}

	@Override
	protected void update(float delta) {
		if (conf.aiconf.getAIs().size() != loadedAIS) {
			loadedAIS = conf.aiconf.getAIs().size();
			addPlayerButton.setY(addPlayerButton.getY() - spacing);

			AIFactory<? extends AI> lastAdded = conf.aiconf.getAIs().get(
					conf.aiconf.getAIs().size() - 1);

			ModifyPlayerButton modifyPlayerButton = new ModifyPlayerButton(
					aiButtonX - ModifyPlayerButton.WIDTH / 2, top - topMargin
							- loadedAIS * spacing - ModifyPlayerButton.HEIGHT
							/ 2, lastAdded.newInstance().getClass().getName(),
					lastAdded.getFilename());

			modifyPlayerButton.setButtonListener(new ButtonListener() {

				@Override
				public void handle(ButtonAction ba) {
					if (ba.type == TYPE.RELEASE) {
						conf.aiconf.loadAI(modifyPlayerButton.getAIFilename());
					}
				}
			});

			getGameContext().spawn(modifyPlayerButton);
		}
	}

	@Override
	protected void drawScreen(SpriteBatch batch) {
		Assets.font30.draw(batch, layout, aiButtonX - layout.width / 2, top
				- topMargin + layout.height);
		Assets.font30.draw(batch, layout2, roundButtons - layout2.width / 2,
				top - topMargin + layout.height);
	}
}
