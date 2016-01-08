package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {

	EatFishAndAI game;

	// Used for drawing of objects. Shared between all drawables in the game.
	private final SpriteBatch batch;
	// Contains all the gameobjects used in a particular screen.
	private GameContext context;

	// The background of the current screen
	private Texture background;

	public AbstractScreen(EatFishAndAI game) {
		this.game = game;

		batch = new SpriteBatch();
		context = new GameContext();
	}

	/**
	 * The main game loop. Delta is equivalent to the time elapsed from previous
	 * iteration in seconds.
	 */
	@Override
	public void render(float delta) {
		context.update(delta);
		update(delta);
		draw(batch);
	}

	protected abstract void update(float delta);

	/**
	 * The drawing/renderring part of the game. Draw all drawables (i.e.
	 * objects, gui) from here.
	 */
	private final void draw(SpriteBatch batch) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
				| GL20.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV
						: 0));
		batch.begin();

		if (background != null) {
			batch.draw(background, 0, 0, 640, 480);
		}

		getGameContext().draw(batch);

		batch.end();
	}

	/**
	 * Logic that occurs when resizing (ignore for now)
	 */
	@Override
	public void resize(int arg0, int arg1) {
	}

	/**
	 * Game regains focus. (Unpause?)
	 */

	@Override
	public void resume() {
	}

	/**
	 * Hiding the game triggers this method.
	 */
	@Override
	public void hide() {
	}

	/**
	 * Pausing the game triggers this method.
	 */

	@Override
	public void pause() {
	}

	/**
	 * Dispose of resources used, ie textures and sounds.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		context.dispose();
		Gdx.input.setInputProcessor(null);
	}

	protected final void setBackground(Texture background) {
		this.background = background;
	}

	protected final Texture getBackground() {
		return background;
	}

	protected final void setGameContext(GameContext context) {
		this.context = context;
	}

	public GameContext getGameContext() {
		return context;
	}
}
