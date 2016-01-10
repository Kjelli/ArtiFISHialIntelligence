package screens;

import game.EatFishAndAI;
import gamecontext.GameContext;
import input.GlobalInput;
import tween.GlobalTween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class AbstractScreen implements Screen {

	Game game;

	private final Camera camera;
	private final Viewport viewport;

	// Used for drawing of objects. Shared between all drawables in the game.
	private final SpriteBatch batch;

	// Contains all the gameobjects used in a particular screen.
	private GameContext context;

	// The background of the current screen
	private Texture background;

	public AbstractScreen(Game game) {
		this.game = game;

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
		context.update(delta);
		GlobalTween.getManager().update(delta);
		update(delta);
		context.getStage().act(delta);
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
		camera.update();

		batch.setTransformMatrix(camera.view);
		batch.setProjectionMatrix(camera.projection);
		batch.begin();

		if (background != null) {
			batch.draw(background, 0, 0, EatFishAndAI.WIDTH,
					EatFishAndAI.HEIGHT);
		}

		getGameContext().draw(batch);

		batch.end();
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
		GlobalInput.removeAllInputProcessing();
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
