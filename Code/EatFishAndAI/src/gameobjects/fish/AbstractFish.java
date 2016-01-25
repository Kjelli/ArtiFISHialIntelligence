package gameobjects.fish;

import fishhandles.OtherFish;
import game.EatFishAndAI;
import gamecontext.physics.Collidable;
import gamecontext.physics.Collision;
import gameobjects.AbstractGameObject;
import graphics.particles.Bubble;
import graphics.particles.Fishbones;
import ai.AI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractFish extends AbstractGameObject implements Fish,
		Collidable {

	private int bubbleTimer;
	private int eatingCooldown;
	private final OtherFish fishHandle;

	protected AI ai;
	private float startingSpeed = MAX_SPEED;

	public AbstractFish(Texture texture, float x, float y, float width,
			float height) {
		super(x, y, width, height);
		setSprite(new Sprite(texture));

		setMaxSpeed(MAX_SPEED);

		bubbleTimer = (int) (BUBBLE_TIMER_MIN + Math.random()
				* (BUBBLE_TIMER_MAX - BUBBLE_TIMER_MIN));

		fishHandle = new OtherFish(this);
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		getGameContext().getFishHandles().add(fishHandle);
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

		if ((getX() > EatFishAndAI.WIDTH && getVelocityX() > 0)
				|| (getX() + getWidth() < 0 && getVelocityX() < 0)
				|| (getY() > EatFishAndAI.HEIGHT && getVelocityY() > 0)
				|| (getY() + getHeight() < 0 && getVelocityY() < 0)) {
			destroy();
		}
	}

	@Override
	public void attachAI(AI ai) {
		this.ai = ai;
		this.ai.setGameContext(getGameContext());
		ai.setFishHandler(getGameContext().generateFishHandler(this));
		if (this instanceof PlayerFish) {
			((PlayerFish) this).setName(ai.getClass().getName());
		}
	}

	public final void start() {
		new Thread(ai).start();
	}

	@Override
	public void onCollide(Collision collision) {
		Collidable target = collision.getTarget();
		if (target instanceof Fish) {
			Fish other = (Fish) target;
			if (greaterThan(other)) {
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
			
			if(ai != null){
				ai.ateFish(fish.getHandle());
			}

			getGameContext().spawn(
					new Fishbones(getX()
							+ (getVelocityX() < 0 ? getWidth() * 0.8f
									: -getWidth() * 0.05f), getY() + 2
							* getHeight() / 8, this, fish));
			//
			// GlobalTween.add(Tween.to(this, GameObjectAccessor.ROTATION,
			// 0.200f)
			// .target(360));

		}
	}

	public boolean greaterThan(Fish that) {
		return Fish.greaterThan(this, that);
	}

	public boolean smallerThan(Fish that) {
		return Fish.smallerThan(this, that);
	}

	@Override
	public void onDespawn() {
		killAI();
	}

	@Override
	public void killAI() {
		if (ai != null) {
			ai.kill();
			ai = null;
		}
	}

	@Override
	public void dispose() {
		onDespawn();
	}

	public OtherFish getHandle() {
		return fishHandle;
	}

}
