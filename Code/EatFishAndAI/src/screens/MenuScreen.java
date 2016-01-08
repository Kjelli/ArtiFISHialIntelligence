package screens;

import com.badlogic.gdx.math.Rectangle;

import spawners.DummySpawner;
import spawners.Spawner;
import game.EatFishAndAI;
import graphics.gui.Logo;
import assets.Assets;

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
		
		getGameContext().spawn(
				new Logo(EatFishAndAI.WIDTH / 2 - Logo.WIDTH / 2,
						EatFishAndAI.HEIGHT * 3 / 4 - Logo.HEIGHT / 2));
	}

	@Override
	protected void update(float delta) {
		spawner.update(delta);
	}

}
