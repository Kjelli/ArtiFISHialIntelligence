package spawners;

import com.badlogic.gdx.math.Rectangle;

import gamecontext.GameContext;

public interface Spawner {
	void update(float delta);

	void setGameContext(GameContext context);

	void setBounds(Rectangle rectangle);
}
