package gameobjects;

import ai.AI;

public interface Fish extends GameObject, Comparable<Fish> {
	public static final float MARGIN = 0.1f;

	float getSize();

	void setSize(float size);

	void attachAI(AI ai);

	void eat(Fish fish);

}
