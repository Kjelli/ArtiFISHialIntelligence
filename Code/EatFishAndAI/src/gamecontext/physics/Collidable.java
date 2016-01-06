package gamecontext.physics;

import gameobjects.GameObject;

public interface Collidable extends GameObject {
	void onCollide(Collision collision);
}
