package input;

import com.badlogic.gdx.InputAdapter;

public class MenuInputProcessor extends InputAdapter {
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		System.out.println(screenX + " " + screenY);
		
		return true;
	}
}
