package graphics.gui.buttons;

import assets.Assets;

public class AddPlayerButton extends AbstractButton {

	public AddPlayerButton(float x, float y) {
		super(x, y, Assets.button_add_player, Assets.button_add_player_hover,
				Assets.button_add_player_pressed);
	}

}