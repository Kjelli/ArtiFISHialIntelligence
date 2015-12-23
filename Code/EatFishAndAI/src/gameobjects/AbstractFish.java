package gameobjects;

import graphics.particles.Bubble;
import ai.AI;
import assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractFish extends AbstractGameObject implements Fish {
	private final static int BUBBLE_TIMER_MAX = 300, BUBBLE_TIMER_MIN = 100;

	private AI ai;

	private int bubbleTimer;

	public AbstractFish(float x, float y, float width, float height) {
		super(x, y, width, height);
		bubbleTimer = (int) (BUBBLE_TIMER_MIN + Math.random()
				* (BUBBLE_TIMER_MAX - BUBBLE_TIMER_MIN));
	}

	@Override
	public final void draw(SpriteBatch batch) {
		batch.draw(Assets.fish, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {
		if (bubbleTimer > 0) {
			bubbleTimer--;
		} else {
			bubbleTimer = (int) (Math.random() * BUBBLE_TIMER_MAX);
			getGameContext().spawn(
					new Bubble(getX() + getWidth() / 2, getY() + getHeight()
							/ 2, (float) (Math.random() * 0.7f)));
		}
	}

	@Override
	public final void attachAI(AI ai) {
		this.ai = ai;
		new Thread(ai).start();
	}
	
	@Override
	public void onDespawn() {
		ai.kill();
	}

}
