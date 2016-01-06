package spawners;

import gamecontext.GameContext;

public interface Spawner {
	void update(float delta);

	void setGameContext(GameContext context);
}
