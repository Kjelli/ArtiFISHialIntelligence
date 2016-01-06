package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.Fish;
import gameobjects.PredatorFish;
import gameobjects.ScaredFish;
import spawners.DummySpawner;
import spawners.Spawner;
import assets.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen {
	EatFishAndAI game;

	// Used for drawing of objects. Shared between all drawables in the game.
	private final SpriteBatch batch;
	private final GameContext context;

	Spawner spawner;

	Texture background;

	public PlayScreen(EatFishAndAI game) {
		this.game = game;
		batch = new SpriteBatch();
		context = new GameContext();

	}

	/**
	 * Screen is shown. Runs before each render loop. Use to init.
	 */

	@Override
	public void show() {
		background = Assets.bg;
		int maxSpawn = 3;

		spawner = new DummySpawner();
		spawner.setGameContext(context);

		// Random initial spawn

		// for (int i = 0; i < maxSpawn; i++) {
		// int randX = (int) (Math.random() * EatFishAndAI.WIDTH);
		// int randY = (int) (Math.random() * EatFishAndAI.HEIGHT);
		// Fish fish = new ScaredFish(randX, randY);
		// context.spawn(fish);
		// }

		// Controlled initial spawn
		for (int i = 0; i < 70; i++) {
			context.spawn(new ScaredFish(50 + ((i % 20) * 25),
					(i / 16 + 1) * 40 + 100));
		}

		context.spawn(new PredatorFish(100, 50));
		context.spawn(new PredatorFish(600, 450));
		context.spawn(new PredatorFish(600, 50));
		context.spawn(new PredatorFish(100, 450));

	}

	/**
	 * Hiding the game triggers this method.
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/**
	 * Pausing the game triggers this method.
	 */

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * The main game loop. Delta is equivalent to the time elapsed from previous
	 * iteration in seconds.
	 */
	@Override
	public void render(float delta) {
		update(delta*2);
		draw(batch);
	}

	/**
	 * The updating of gamelogic
	 * 
	 * @param delta
	 *            The time elapsed from previous iteration in seconds(?).
	 */

	private void update(float delta) {
		context.update(delta);
		spawner.update(delta);
	}

	/**
	 * The drawing/renderring part of the game. Draw all drawables (i.e.
	 * objects, gui) from here.
	 */
	private void draw(SpriteBatch batch) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
				| GL20.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV
						: 0));
		batch.begin();
		batch.draw(background, 0, 0, 640, 480);

		context.draw(batch);

		batch.end();
	}

	/**
	 * Logic that occurs when resizing (ignore for now)
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Game regains focus. (Unpause?)
	 */

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * Dispose of resources used, ie textures and sounds.
	 */

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
