package screens;

import game.EatFishAndAI;
import graphics.particles.Bubble;
import loading.LoadTask;
import assets.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen extends AbstractScreen {
	int bubbleTimer = 0;

	private GlyphLayout loadingTextLayout;

	private final LoadTask[] toLoad;
	private boolean loadComplete = false;
	private Screen nextScreen;

	private float loadingX, loadingY;
	private float radius = 20f;

	public LoadingScreen(Game game, LoadTask[] toLoad, Screen nextScreen) {
		super(game);
		this.toLoad = toLoad;
		this.nextScreen = nextScreen;

	}

	@Override
	public void show() {
		loadingTextLayout = new GlyphLayout(Assets.font50, "loading");

		loadingX = EatFishAndAI.WIDTH / 2 - loadingTextLayout.width / 2;
		loadingY = EatFishAndAI.HEIGHT / 2 + loadingTextLayout.height / 2;

		setBackgroundColor(new Color(0, 0, 0.1f, 0));
		for (int i = 0; i < 200; i++) {
			spawnBubble((float) Math.random() * EatFishAndAI.WIDTH,
					(float) (Math.random() * EatFishAndAI.HEIGHT),
					(float) (Math.random()));
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (LoadTask load : toLoad) {
					load.load();
				}
				loadComplete = true;
			}

		}).start();

	}

	@Override
	protected void update(float delta) {
		if (loadComplete) {
			game.setScreen(nextScreen);
			return;
		}

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

	@Override
	protected void drawScreen(SpriteBatch batch) {
		Assets.font50.draw(batch, loadingTextLayout, loadingX, loadingY
				+ (float) Math.sin(getGameContext().getTicks() / 40f) * radius);
	}
}
