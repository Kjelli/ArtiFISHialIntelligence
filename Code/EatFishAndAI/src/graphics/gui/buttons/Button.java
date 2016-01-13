package graphics.gui.buttons;

import gameobjects.GameObject;

public interface Button extends GameObject {
	void setButtonListener(ButtonListener listener);
	ButtonListener getButtonListener();
}
