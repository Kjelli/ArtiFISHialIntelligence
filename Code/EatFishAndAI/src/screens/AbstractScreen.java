package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;
import gameobjects.GameObject;
import input.GlobalInput;
import tween.GlobalTween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractScreen implements GameScreen {

	Game game;

	private Camera camera;
	private Viewport viewport;

	// Used for drawing of objects. Shared between all drawables in the game.
	private SpriteBatch batch;

	// Contains all the gameobjects used in a particular screen.
	private GameContext context;

	// The background of the current screen
	private Texture background;

	private boolean paused = false;

	private Color bgcolor = new Color(Color.BLACK);

	private boolean disposing = false;

	public AbstractScreen(Game game) {
		this.game = game;
	}

	public void init() {
		camera = new OrthographicCamera(EatFishAndAI.WIDTH, EatFishAndAI.HEIGHT);
		camera.position.set(camera.viewportWidth / 2f,
				camera.viewportHeight / 2f, 0);
		viewport = new StretchViewport(EatFishAndAI.WIDTH, EatFishAndAI.HEIGHT,
				camera);

		batch = new SpriteBatch();
		context = new GameContext(game);

		getGameContext().getStage().setViewport(viewport);

		camera.update();
		GlobalInput.addInputProcessor(getGameContext().getStage());
	}

	/**
	 * The main game loop. Delta is equivalent to the time elapsed from previous
	 * iteration in seconds.
	 */
	@Override
	public void render(float delta) {
		if (disposing) {
			return;
		}
		if (!paused) {
			context.update(delta);
			update(delta);
			context.getStage().act(delta);
			GlobalTween.getManager().update(delta);
		}
		draw(batch);
	}

	protected abstract void update(float delta);

	/**
	 * The drawing/renderring part of the game. Draw all drawables (i.e.
	 * objects, gui) from here.
	 */
	private final void draw(SpriteBatch batch) {

		Gdx.gl.glClearColor(bgcolor.r, bgcolor.g, bgcolor.b, bgcolor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
				| GL20.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV
						: 0));
		camera.update();

		batch.setTransformMatrix(camera.view);
		batch.setProjectionMatrix(camera.projection);
		batch.begin();

		if (background != null) {
			batch.draw(background, 0, 0, EatFishAndAI.WIDTH,
					EatFishAndAI.HEIGHT);
		}

		getGameContext().draw(batch);

		drawScreen(batch);
		batch.end();
	}

	protected void drawScreen(SpriteBatch batch) {
		// Overridable
	}

	/**
	 * Logic that occurs when resizing (ignore for now)
	 */
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.update();
	}

	/**
	 * Game regains focus. (Unpause?)
	 */

	@Override
	public void resume() {
		setPaused(false);
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
		setPaused(true);
	}

	/**
	 * Dispose of resources used, ie textures and sounds.
	 */
	@Override
	public void dispose() {
		disposing = true;
		batch.dispose();
		context.dispose();
		GlobalInput.removeAllInputProcessing();
	}

	protected final void setBackground(Texture background) {
		this.background = background;
	}

	protected final Texture getBackground() {
		return background;
	}

	public void setBackgroundColor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}

	public Color getBackgroundcolor() {
		return bgcolor;
	}

	protected final void setGameContext(GameContext context) {
		this.context = context;
	}

	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
		context.setPaused(paused);
	}

	public GameContext getGameContext() {
		return context;
	}

}
