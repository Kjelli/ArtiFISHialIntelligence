package gamecontext;

import java.util.List;

import gameobjects.GameObject;

public class GameContextSnapshot {
	private final GameContext context;

	public GameContextSnapshot(GameContext context) {
		this.context = context;
	}

	private List<GameObject> getObjects() {
		return context.objects;
	}

}
