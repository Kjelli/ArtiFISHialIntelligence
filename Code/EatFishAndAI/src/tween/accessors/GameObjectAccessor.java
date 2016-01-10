package tween.accessors;

import com.badlogic.gdx.graphics.Color;

import gameobjects.GameObject;
import aurelienribon.tweenengine.TweenAccessor;

public class GameObjectAccessor implements TweenAccessor<GameObject> {
	public static final int POS_X = 0, POS_Y = 1, POS_XY = 2, SIZE_W = 3,
			SIZE_H = 4, SIZE_WH = 5, SPRITE_R = 6, SPRITE_G = 7, SPRITE_B = 8,
			SPRITE_A = 9, SPRITE_RGB = 10, SPRITE_RGBA = 11, SCALE = 12;

	@Override
	public int getValues(GameObject o, int type, float[] values) {

		switch (type) {
		case POS_X:
			values[0] = o.getX();
			return 1;
		case POS_Y:
			values[0] = o.getY();
			return 1;
		case POS_XY:
			values[0] = o.getX();
			values[1] = o.getY();
			return 2;
		case SIZE_W:
			values[0] = o.getWidth();
			return 1;
		case SIZE_H:
			values[0] = o.getHeight();
			return 1;
		case SIZE_WH:
			values[0] = o.getWidth();
			values[1] = o.getHeight();
			return 2;
		case SPRITE_R:
			values[0] = o.getSprite().getColor().r;
			return 1;
		case SPRITE_G:
			values[0] = o.getSprite().getColor().g;
			return 1;
		case SPRITE_B:
			values[0] = o.getSprite().getColor().b;
			return 1;
		case SPRITE_A:
			values[0] = o.getSprite().getColor().a;
			return 1;
		case SPRITE_RGB:
			values[0] = o.getSprite().getColor().r;
			values[1] = o.getSprite().getColor().g;
			values[2] = o.getSprite().getColor().b;
			return 3;
		case SPRITE_RGBA:
			values[0] = o.getSprite().getColor().r;
			values[1] = o.getSprite().getColor().g;
			values[2] = o.getSprite().getColor().b;
			values[3] = o.getSprite().getColor().a;
			return 4;
		case SCALE:
			values[0] = o.getScale();
			return 1;
		default:
			System.err.println("Invalid tweenaccessor type!");
			return 0;
		}
	}

	@Override
	public void setValues(GameObject o, int type, float[] values) {
		switch (type) {
		case POS_X:
			o.setX(values[0]);
			return;
		case POS_Y:
			o.setY(values[0]);
			return;
		case POS_XY:
			o.setX(values[0]);
			o.setY(values[1]);
			return;
		case SIZE_W:
			o.setWidth(values[0]);
			return;
		case SIZE_H:
			o.setHeight(values[0]);
			return;
		case SIZE_WH:
			o.setWidth(values[0]);
			o.setHeight(values[1]);
			return;
		}
		Color c = o.getSprite().getColor();
		switch (type) {
		case SPRITE_R:
			o.getSprite().setColor(c.set(values[0], c.g, c.b, c.a));
			return;
		case SPRITE_G:
			o.getSprite().setColor(c.set(c.r, values[0], c.b, c.a));
			return;
		case SPRITE_B:
			o.getSprite().setColor(c.set(c.r, c.g, values[0], c.a));
			return;
		case SPRITE_A:
			o.getSprite().setColor(c.set(c.r, c.g, c.b, values[0]));
			return;
		case SPRITE_RGB:
			o.getSprite().setColor(c.set(values[0], values[1], values[2], c.a));
			return;
		case SPRITE_RGBA:
			o.getSprite().setColor(
					c.set(values[0], values[1], values[2], values[3]));
			return;
		case SCALE:
			o.setScale(values[0]);
			return;
		default:
			System.err.println("Invalid tweenaccessor type!");
			return;
		}
	}
}
