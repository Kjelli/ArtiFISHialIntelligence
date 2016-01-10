package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {

	private static final String ASSETS_PATH = "assets";

	// Playscreen textures

	public static Texture predatorfish, scaredfish;
	public static Texture bubble, bubble_s, bubble_xs;
	public static Texture bg;
	public static Texture fishbones;

	public static TextureGroup dummyfish;

	// Menuscreen textures

	public static Texture splash;
	public static Texture button_start, button_start_hover,
			button_start_pressed;

	// Fonts

	public static BitmapFont font16, font20, font30;

	public static void loadAllAssets() {
		loadMenuscreenAssets();
		loadPlayscreenAssets();
		loadFonts();
	}

	private static void loadFonts() {
		// FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
		// Gdx.files.internal("PIXELOPERATORSC.TTF"));
		//
		// FreeTypeFontParameter size16 = new FreeTypeFontParameter();
		// size16.size = 16;
		// font16 = generator.generateFont(size16);
		//
		// FreeTypeFontParameter size20 = new FreeTypeFontParameter();
		// size20.size = 20;
		// font20 = generator.generateFont(size20);
		//
		// FreeTypeFontParameter size30a = new FreeTypeFontParameter();
		// size30a.size = 30;
		// font30 = generator.generateFont(size30a);
		//
		// generator.dispose();
	}

	private static void loadMenuscreenAssets() {
		splash = load("logo_splash.png");
		button_start = load("button_start.png");
		button_start_hover = load("button_start_hover.png");
		button_start_pressed = load("button_start_pressed.png");
	}

	private static void loadPlayscreenAssets() {
		predatorfish = load("predatorfish.png");
		dummyfish = loadall("dummyfish.png", "dummyfish2.png",
				"dummyfish3.png", "dummyfish4.png");
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

	private static TextureGroup loadall(String... filenames) {
		return new TextureGroup(filenames);
	}

	public static class TextureGroup {
		private final Texture[] textures;

		public TextureGroup(String... filenames) {
			textures = new Texture[filenames.length];
			for (int i = 0; i < textures.length; i++) {
				textures[i] = load(filenames[i]);
			}
		}

		public Texture get(int index) {
			return textures[index];
		}

		public Texture any() {
			return textures[(int) (Math.random() * textures.length)];
		}
	}

}
