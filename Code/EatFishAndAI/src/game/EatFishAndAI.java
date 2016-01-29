package game;

import input.GlobalInput;
import loading.LoadTask;
import screens.GameScreen;
import screens.LoadingScreen;
import screens.MenuScreen;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class EatFishAndAI extends Game {

	public static final int WIDTH = 1280, HEIGHT = 1024;
	public static final String WEBSITE = "https://github.com/Kjelli/EatFishAndAI/blob/master/Rules.md";

	@Override
	public void create() {
		init();

		setScreen(new LoadingScreen(this, new LoadTask[] { new LoadTask() {
			@Override
			public void load() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} }, new MenuScreen(this)));

	}

	private void init() {
		Assets.loadAllAssets();
		GlobalInput.init();
		initGL();
	}

	private void initGL() {
		// TODO
	}

	@Override
	public void setScreen(Screen screen) {
		setScreen(screen, false);
	}

	public void setScreen(Screen screen, boolean keepAlive) {
		Screen oldScreen = getScreen();
		if (oldScreen != null && !keepAlive) {
			oldScreen.dispose();
		}
		((GameScreen) screen).init();
		super.setScreen(screen);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
