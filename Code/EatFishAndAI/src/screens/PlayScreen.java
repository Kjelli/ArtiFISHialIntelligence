package screens;

import game.EatFishAndAI;
import gameobjects.Fish;
import gameobjects.GameObject;

import java.util.ArrayList;
import java.util.List;

import assets.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayScreen implements Screen {
	EatFishAndAI game;

	// Used for drawing of objects. Shared between all drawables in the game.
	SpriteBatch batch;
	List<GameObject> objects = new ArrayList<>();

	Texture background;

	public PlayScreen(EatFishAndAI game) {
		this.game = game;
		batch = new SpriteBatch();
	}

	/**
	 * Screen is shown. Runs before each render loop. Use to init.
	 */

	@Override
	public void show() {
		background = Assets.bg;
		objects.add(new Fish(50, 50));
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
	}

	/**
	 * The drawing/renderring part of the game. Draw all drawables (i.e.
	 * objects, gui) from here.
	 */
	private void draw(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0,640,480);
		for(GameObject go : objects){
			go.draw(batch);
		}
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
