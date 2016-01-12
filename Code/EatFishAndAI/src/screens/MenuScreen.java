package screens;

import game.EatFishAndAI;
import graphics.gui.Logo;
import graphics.gui.buttons.AbstractButton;
import graphics.gui.buttons.StartConfigurationButton;
import spawners.DummySpawner;
import spawners.Spawner;
import assets.Assets;

import com.badlogic.gdx.math.Rectangle;

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

		getGameContext().spawn(
				new StartConfigurationButton(centerX - AbstractButton.WIDTH / 2,
						centerY * 4 / 5));

	}

	@Override
	protected void update(float delta) {
		spawner.update(delta);
	}

}
