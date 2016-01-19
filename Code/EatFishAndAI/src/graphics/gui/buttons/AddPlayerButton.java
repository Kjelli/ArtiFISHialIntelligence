package graphics.gui.buttons;

import ai.AI;
import ai.loader.AIFactory;
import assets.Assets;

public class AddPlayerButton extends AbstractButton {

	private String aiFilename;

	public AddPlayerButton(float x, float y) {
		super(x, y, Assets.button_add_player, Assets.button_add_player_hover,
				Assets.button_add_player_pressed);
	}
	

	public String getAIFilename() {
		return aiFilename;
	}

}