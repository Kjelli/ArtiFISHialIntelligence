package gameobjects.fish;

import graphics.Draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ai.AI;
import assets.Assets;

/**
 * TODO -
 * 
 * @author Kjell Arne Hellum
 *
 */
public class PlayerFish extends AbstractFish implements Comparable<PlayerFish> {

	public static BitmapFont playerNameFont;

	public static final float NAME_LENGTH_LIMIT = 150;

	public static final float STARTING_SCALE = 1.2f;

	public static final Sprite CRASH = new Sprite(Assets.crash),
			SLOW = new Sprite(Assets.slow);

	private int score = 0;
	private GlyphLayout nameLayout;
	private GlyphLayout massScoreLayout;
	private String name;

	private Color statusColor = new Color(1, 1, 1, 1);

	public PlayerFish(float x, float y) {
		super(Assets.predatorfish, x, y, WIDTH, HEIGHT);
		setScale(STARTING_SCALE);
		setMaxSpeed(MAX_SPEED);
	}

	public void attachAI(AI ai) {
		super.attachAI(ai);
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		nameLayout = new GlyphLayout();
		if (playerNameFont == null) {
			playerNameFont = Assets.font20;
		}

		setName(name);

		nameLayout.setText(playerNameFont, getName());

		massScoreLayout = new GlyphLayout();
		massScoreLayout.setText(playerNameFont,
				String.format("%.0f", getScale()));
		System.out.println("Starting " + ai);
		start();
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		massScoreLayout.setText(playerNameFont,
				String.format("%.0f", getScale() * 10));
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name = s;
		if (nameLayout != null) {
			nameLayout.setText(playerNameFont, name);
			if (nameLayout.width > NAME_LENGTH_LIMIT) {
				setName(s.substring(0, s.length() - 2) + ".");
			}
		}
	}

	public GlyphLayout getNameLayout() {
		return nameLayout;
	}

	public GlyphLayout getMassScoreLayout() {
		return massScoreLayout;
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);

		playerNameFont.draw(batch, nameLayout, getCenterX() - nameLayout.width
				/ 2, getY() - nameLayout.height);

		switch (ai.getAIObserver().getStatus()) {
		case ALIVE:
			break;
		case SLOWEST:
			statusColor.set(1, 0, 0, 1);
			drawSlowStatus(batch);
			break;
		case SLOWER:
			statusColor.set(1, 1, 0, 1);
			drawSlowStatus(batch);
			break;
		case SLOW:
			statusColor.set(1, 1, 1, 1);
			drawSlowStatus(batch);
			break;
		case CRASHED:
			Draw.sprite(batch, CRASH, getCenterX() - CRASH.getWidth() / 2,
					getY() + getHeight() + 3, 8, 8, 0);
			break;
		case INITIALIZING:
			break;
		case KILLED:
			break;
		default:
			break;

		}

	}

	private void drawSlowStatus(SpriteBatch batch) {
		Draw.sprite(batch, SLOW, getCenterX() - SLOW.getWidth() / 2, getY()
				+ getHeight() + 3, 8, 8, 0, statusColor, false);
	}

	public int compareTo(PlayerFish that) {
		return Float.compare(that.getScale(), this.getScale());
	}

	public int getScore() {
		return score;
	}

	public void incrementScore() {
		score++;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
