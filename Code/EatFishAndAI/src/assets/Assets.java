package assets;

import com.badlogic.gdx.graphics.Texture;

public class Assets {

	private static final String ASSETS_PATH = "assets";
	public static Texture predatorfish, dummyfish, scaredfish;
	public static Texture bubble, bubble_s, bubble_xs;
	public static Texture bg;
	public static Texture fishbones;

	public static void loadAssets() {
		predatorfish = load("predatorfish.png");
		dummyfish = load("dummyfish.png");
		scaredfish = load("scaredfish.png");
		bg = load("bg.jpg");
		bubble_xs = load("bubble_xs.png");
		bubble_s = load("bubble_s.png");
		bubble = load("bubble.png");
		fishbones = load("fishbones.png");
	}

	private static Texture load(String filename) {
		Texture texture = new Texture(ASSETS_PATH + "/" + filename);
		return texture;
	}
}
