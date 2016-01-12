package screens;

import game.EatFishAndAI;
import graphics.gui.buttons.AddPlayerButton;
import graphics.gui.buttons.Button;
import graphics.gui.buttons.ModifyPlayerButton;
import ai.AIConfiguration;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConfigureScreen extends AbstractScreen {

	private final GlyphLayout layout;
	private final String topLabel = "Add players and connect their AI to begin!";

	AIConfiguration conf;
	float top = EatFishAndAI.HEIGHT, bottom = 0, left = 0,
			right = EatFishAndAI.WIDTH, centerX = EatFishAndAI.WIDTH / 2,
			centerY = EatFishAndAI.HEIGHT / 2;
	float aiButtonX = EatFishAndAI.WIDTH / 4;

	float spacing = 55f;
	float topMargin = EatFishAndAI.HEIGHT / 6;

	int loadedAIS = 0;

	Button addPlayerButton;

	public ConfigureScreen(Game game) {
		super(game);
		conf = new AIConfiguration();

		layout = new GlyphLayout(Assets.font30, topLabel);

		addPlayerButton = new AddPlayerButton(conf, aiButtonX
				- AddPlayerButton.WIDTH / 2, top - topMargin - 1 * spacing
				- AddPlayerButton.HEIGHT / 2);
	}

	@Override
	public void show() {

		setBackground(Assets.bg);
		getGameContext().spawn(addPlayerButton);
	}

	@Override
	protected void update(float delta) {
		if (conf.getAIs().size() != loadedAIS) {
			loadedAIS = conf.getAIs().size();
			addPlayerButton.setY(addPlayerButton.getY() - spacing);
			getGameContext().spawn(
					new ModifyPlayerButton(aiButtonX - ModifyPlayerButton.WIDTH
							/ 2, top - topMargin - loadedAIS * spacing
							- ModifyPlayerButton.HEIGHT / 2, conf.getAIs()
							.get(conf.getAIs().size() - 1).newInstance()
							.getClass().getName()));
		}
	}

	@Override
	protected void drawScreen(SpriteBatch batch) {
		Assets.font30.draw(batch, layout, centerX - layout.width / 2, top
				- topMargin + layout.height);
	}

}
