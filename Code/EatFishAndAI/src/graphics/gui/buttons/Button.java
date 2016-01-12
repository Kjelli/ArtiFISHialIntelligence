package graphics.gui.buttons;

import gameobjects.GameObject;

public interface Button extends GameObject {
	void onClick();
	
	void onRelease();

	void onEnter();

	void onExit();
}
