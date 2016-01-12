package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {

	private static final String ASSETS_PATH = "assets";

	// Playscreen textures

	public static Texture predatorfish, scaredfish;
	public static Texture bubble, bubble_s, bubble_xs;
	public static Texture bg;
	public static Texture fishbones;

	public static TextureGroup dummyfish;

	// Menuscreen and button textures

	public static Texture splash;
	public static Texture button_start, button_start_hover,
			button_start_pressed;
	public static Texture button_empty, button_empty_hover,
			button_empty_pressed;

	// Configurescreen textures

	public static Texture button_add_player, button_add_player_hover,
			button_add_player_pressed;

	// Fonts

	public static BitmapFont font16, font20, font30, font50;

	public static void loadAllAssets() {
		loadMenuscreenAssets();
		loadPlayscreenAssets();
		loadConfigurescreenAssets();
		loadFonts();
	}

	private static void loadConfigurescreenAssets() {
		button_add_player = load("button_add_player.png");
		button_add_player_hover = load("button_add_player_hover.png");
		button_add_player_pressed = load("button_add_player_pressed.png");
	}

	private static void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("assets/PIXELOPERATORSC-BOLD.TTF"));

		generator.scaleForPixelHeight(16);
		FreeTypeFontParameter size16 = new FreeTypeFontParameter();
		size16.size = 16;
		size16.minFilter = Texture.TextureFilter.Nearest;
		size16.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		
		font16 = generator.generateFont(size16);

		FreeTypeFontParameter size20 = new FreeTypeFontParameter();
		size20.size = 20;
		size20.minFilter = Texture.TextureFilter.Nearest;
		size20.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		
		font20 = generator.generateFont(size20);

		FreeTypeFontParameter size30 = new FreeTypeFontParameter();
		size30.size = 30;
		size30.minFilter = Texture.TextureFilter.Nearest;
		size30.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		font30 = generator.generateFont(size30);

		FreeTypeFontParameter size50 = new FreeTypeFontParameter();
		size50.size = 50;
		size50.minFilter = Texture.TextureFilter.Nearest;
		size50.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		font50 = generator.generateFont(size50);

		generator.dispose();
	}

	private static void loadMenuscreenAssets() {
		splash = load("logo_splash.png");
		button_start = load("button_start.png");
		button_start_hover = load("button_start_hover.png");
		button_start_pressed = load("button_start_pressed.png");

		button_empty = load("button_empty.png");
		button_empty_hover = load("button_empty_hover.png");
		button_empty_pressed = load("button_empty_pressed.png");
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
