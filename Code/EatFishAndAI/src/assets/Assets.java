package assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets {

	private static final String ASSETS_PATH = "assets";
	public static Texture fish;
	public static Texture bg;

	public static void loadAssets() {
		fish = load("splashyfish.png");
		bg = load("bg.jpg");
	}

	private static Texture load(String filename) {
		Texture texture = new Texture(ASSETS_PATH + "/" + filename);
		return texture;
	}
}
