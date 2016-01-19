package game;

import input.GlobalInput;
import loading.LoadTask;
import screens.GameScreen;
import screens.LoadingScreen;
import screens.MenuScreen;
import screens.WinnerScreen;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class EatFishAndAI extends Game {

	public static final int WIDTH = 800, HEIGHT = 600;

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
