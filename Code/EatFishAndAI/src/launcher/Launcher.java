package launcher;


import game.EatFishAndAI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = EatFishAndAI.WIDTH;
		config.height = EatFishAndAI.HEIGHT;
		new LwjglApplication(new EatFishAndAI(), config);
	}
}