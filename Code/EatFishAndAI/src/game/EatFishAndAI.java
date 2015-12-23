package game;

import screens.PlayScreen;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EatFishAndAI extends Game {

	public static final int WIDTH = 640, HEIGHT = 480;

	@Override
	public void create() {
		Assets.loadAssets();
		setScreen(new PlayScreen(this));
	}

}
