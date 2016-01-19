package graphics.gui.buttons;

import assets.Assets;

public class ModifyPlayerButton extends AbstractTextButton {
	
	final String filename;

	public ModifyPlayerButton(float x, float y, String name, String filename) {
		super(x, y, Assets.font20, name);
		this.filename = filename;
	}
	
	public String getAIFilename() {
		return filename;
	}

}
