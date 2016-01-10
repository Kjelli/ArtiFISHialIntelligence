package graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameobjects.GameObject;

public class Draw {

	public static void sprite(SpriteBatch batch, GameObject object) {
		Sprite sprite = object.getSprite();
		if (sprite == null) {
			System.err.println("Object has no sprite: " + object);
			return;
		}
		Color c_old = null;
		if (sprite.getColor() != null) {
			c_old = batch.getColor();
			batch.setColor(sprite.getColor());
		}
		batch.draw(sprite.getTexture(), object.getX(), object.getY(), 0, 0,
				object.getWidth(), object.getHeight(), 1.0f, 1.0f,
				sprite.getRotation(), 0, 0, sprite.getRegionWidth(),
				sprite.getRegionHeight(), sprite.isFlipX(), sprite.isFlipY());

		if (sprite.getColor() != null) {
			batch.setColor(c_old);
		}
	}
	// Texture texture, float x, float y, float originX, float originY, float
	// width, float height, float scaleX, float scaleY, float rotation, int
	// srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY

}
