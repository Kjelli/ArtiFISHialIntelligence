package graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameobjects.GameObject;

public class Draw {

	public static void sprite(SpriteBatch batch, GameObject go) {
		if (go.getSprite() == null) {
			System.err.println("Object has no sprite: " + go);
			return;
		} else {
			sprite(batch, go.getSprite(), go.getX(), go.getY(), go.getWidth(),
					go.getHeight(), go.getRotation(), go.getSprite().getColor());
		}
	}

	// Texture texture, float x, float y, float originX, float originY, float
	// width, float height, float scaleX, float scaleY, float rotation, int
	// srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY

	public static void sprite(SpriteBatch batch, Sprite sprite, float x,
			float y, float w, float h, float rot) {

		sprite(batch, sprite, x, y, w, h, rot, sprite.getColor());
	}

	public static void sprite(SpriteBatch batch, Sprite sprite, float x,
			float y, float w, float h, float rot, Color color) {

		Color c_old = null;
		if (sprite.getColor() != null) {
			c_old = batch.getColor();
			batch.setColor(color);
		}
		batch.draw(sprite.getTexture(), x, y, 0, 0, w, h, 1.0f, 1.0f, rot, 0,
				0, sprite.getRegionWidth(), sprite.getRegionHeight(),
				sprite.isFlipX(), sprite.isFlipY());

		if (sprite.getColor() != null) {
			batch.setColor(c_old);
		}
	}

}
