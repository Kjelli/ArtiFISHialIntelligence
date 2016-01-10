package gameobjects;

import game.EatFishAndAI;
import gamecontext.physics.Collidable;
import gamecontext.physics.Collision;
import graphics.particles.Bubble;
import graphics.particles.Fishbones;
import ai.AI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractFish extends AbstractGameObject implements Fish,
		Collidable {

	private int bubbleTimer;
	private int eatingCooldown;

	private AI ai;
	private float startingSpeed = MAX_SPEED;

	public AbstractFish(Texture texture, float x, float y, float width,
			float height) {
		super(x, y, width, height);
		setSprite(new Sprite(texture));

		setMaxSpeed(MAX_SPEED);

		bubbleTimer = (int) (BUBBLE_TIMER_MIN + Math.random()
				* (BUBBLE_TIMER_MAX - BUBBLE_TIMER_MIN));
	}

	@Override
	public void update(float delta) {
		scale *= 0.9999f;
		if (eatingCooldown > 0) {
			eatingCooldown--;
		}

		if (bubbleTimer > 0) {
			bubbleTimer--;
		} else {
			bubbleTimer = (int) (BUBBLE_TIMER_MIN + Math.random()
					* (BUBBLE_TIMER_MAX - BUBBLE_TIMER_MIN));
			getGameContext().spawn(
					new Bubble(getX() + (getVelocityX() > 0 ? getWidth() : 0),
							getY() + getHeight() / 4, (float) ((Math.random())
									* Math.sqrt(getScale()) * BUBBLE_SCALE),
							this));
		}

		setMaxSpeed((float) (startingSpeed * Math.pow(SLOW_FACTOR, scale)));
		move(delta);
		if (getX() > EatFishAndAI.WIDTH || getX() + getWidth() < 0
				|| getY() > EatFishAndAI.HEIGHT || getY() + getHeight() < 0) {
			destroy();
		}
	}

	@Override
	public final void attachAI(AI ai) {
		this.ai = ai;
		this.ai.setGameContext(getGameContext());
		this.ai.setFish(this);
		new Thread(ai).start();
	}

	@Override
	public void onCollide(Collision collision) {
		Collidable target = collision.getTarget();
		if (target instanceof Fish) {
			Fish other = (Fish) target;
			if (compareTo(other) == 1) {
				if (distanceTo(other) < getHeight() / 2) {
					eat(other);
				}
			}
		}
	}

	@Override
	public void eat(Fish fish) {
		if (eatingCooldown == 0) {
			float oldSize = getScale();
			setScale(getScale() + fish.getScale() * GROWTH_FACTOR);
			setX(getX() - (getWidth() - getWidth() / getScale() * oldSize) / 2);
			setY(getY() - (getHeight() - getHeight() / getScale() * oldSize)
					/ 2);
			eatingCooldown = EATING_COOLDOWN_MAX;
			fish.destroy();

			getGameContext().spawn(
					new Fishbones(getX()
							+ (getVelocityX() < 0 ? getWidth() * 0.8f
									: -getWidth() * 0.05f), getY() + 2
							* getHeight() / 8, this, fish));
		}
	}

	@Override
	public int compareTo(Fish o) {
		float diff = getScale() - o.getScale();
		return diff < -getScale() * Fish.MASS_DIFFERENCE_MARGIN ? -1
				: diff > getScale() * Fish.MASS_DIFFERENCE_MARGIN ? 1 : 0;
	}

	@Override
	public void onDespawn() {
		if (ai != null) {
			ai.kill();
		}
	}

}
