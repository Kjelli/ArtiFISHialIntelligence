package gameobjects;

import java.io.FileNotFoundException;

import ai.AI;
import ai.loader.AILoader;
import ai.loader.MaliciousAICodeException;

import com.badlogic.gdx.graphics.Texture;
/**
 * TODO - 
 * @author Kjell Arne Hellum
 *
 */
public class PlayerFish extends AbstractFish {

	public PlayerFish(Texture texture, float x, float y, float width,
			float height) {
		super(texture, x, y, width, height);
	}

	@Override
	public void onSpawn() {
		AI ai;
		try {
			ai = AILoader.load("src/ai/DummyAI.java");
			attachAI(ai);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (MaliciousAICodeException e) {
			e.printStackTrace();

		}
	}

}
