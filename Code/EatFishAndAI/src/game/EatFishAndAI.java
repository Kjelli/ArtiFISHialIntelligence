package game;

import input.GlobalInput;
import screens.LoadingScreen;
import screens.MenuScreen;
import screens.PlayScreen;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EatFishAndAI extends Game {

	public static final int WIDTH = 640, HEIGHT = 480;

	@Override
	public void create() {
		init();

		setScreen(new MenuScreen(this));
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
		Screen oldScreen = getScreen();
		if (oldScreen != null) {
			oldScreen.dispose();
		}
		super.setScreen(screen);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
