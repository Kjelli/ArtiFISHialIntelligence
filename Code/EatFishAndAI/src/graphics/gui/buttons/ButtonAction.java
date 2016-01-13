package graphics.gui.buttons;

public class ButtonAction {
	public static enum TYPE {
		ENTER, EXIT, PRESS, RELEASE
	};

	public final TYPE type;
	public final float x, y;

	public ButtonAction(TYPE type, float x, float y, float mouseButton) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
