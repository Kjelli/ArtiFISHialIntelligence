package gamecontext.physics;

import gameobjects.GameObject;

import java.util.Collection;

public interface PhysicsHandler {
	void collisonCheck(Collection<? extends GameObject> gameobjects);
}
