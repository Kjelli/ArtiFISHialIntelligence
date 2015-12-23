package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.DummyFish;
import gameobjects.Fish;
import ai.BasicAI;
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

		for (int i = 5; i < 30; i += 5) {
			Fish fish = new DummyFish(20 * i - 400, 250);
			fish.attachAI(new BasicAI(fish, null));
			context.spawn(fish);
		}
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
		update(delta);
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
