package gameobjects.fish;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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

	public static final float NAME_LENGTH_LIMIT = 150;

	public static final float STARTING_SCALE = 1.2f;

	private int score = 1;
	private GlyphLayout nameLayout;
	private GlyphLayout massScoreLayout;
	public static BitmapFont playerNameFont;
	private String name;

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
			// TODO change ?
			playerNameFont = Assets.font20;
		}

		setName(name);

		nameLayout.setText(playerNameFont, getName());

		massScoreLayout = new GlyphLayout();
		massScoreLayout.setText(playerNameFont,
				String.format("%.0f", getScale()));
		System.out.println("Starting " + ai );
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
