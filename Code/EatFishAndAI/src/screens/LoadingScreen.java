package screens;

import game.EatFishAndAI;
import graphics.particles.Bubble;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;

public class LoadingScreen extends AbstractScreen {
	int bubbleTimer = 0;

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		setBackgroundColor(new Color(0, 0, 0.1f, 0));
		for (int i = 0; i < 200; i++) {
			spawnBubble((float) Math.random() * EatFishAndAI.WIDTH,
					(float) (Math.random() * EatFishAndAI.HEIGHT),
					(float) (Math.random()));
		}
	}

	@Override
	protected void update(float delta) {
		if (bubbleTimer == 0) {
			spawnBubble((float) Math.random() * EatFishAndAI.WIDTH, 0,
					(float) (Math.random()));
			bubbleTimer = 2;
		} else {
			bubbleTimer -= delta;
		}
	}

	public void spawnBubble(float x, float y, float scale) {
		Bubble bubble = new Bubble(x, y, scale);

		getGameContext().spawn(bubble);
	}

}
