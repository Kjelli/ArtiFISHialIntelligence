package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public interface GameScreen extends Screen {
	void init();
	public void setBackgroundColorTint(Color color);

	public Color getBackgroundColorTint();
	
	public Rectangle getBackgroundBounds();
	
}
