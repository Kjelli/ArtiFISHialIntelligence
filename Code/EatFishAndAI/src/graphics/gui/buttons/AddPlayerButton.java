package graphics.gui.buttons;

import ai.AIConfiguration;
import assets.Assets;

public class AddPlayerButton extends AbstractButton {
	private final AIConfiguration conf;

	public AddPlayerButton(AIConfiguration conf, float x, float y) {
		super(x, y, Assets.button_add_player, Assets.button_add_player_hover,
				Assets.button_add_player_pressed);
		this.conf = conf;
	}

	@Override
	public void onClick() {
	}

	@Override
	public void onRelease() {
		conf.prompt();

	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

}
