package graphics.gui.buttons;

import screens.PlayScreen;
import assets.Assets;

public class StartButton extends AbstractButton {

	public StartButton(float x, float y) {
		super(x, y, Assets.button_start, Assets.button_start_hover,
				Assets.button_start_pressed);
	}

	@Override
	public void onClick() {
		getGameContext().getGame().setScreen(
				new PlayScreen(getGameContext().getGame()));
	}

	@Override
	public void onRelease() {
		// TODO Auto-generated method stub

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
