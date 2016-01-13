package screens;

import game.EatFishAndAI;
import gameobjects.fish.PlayerFish;
import graphics.gui.PlayerList;

import java.util.List;

import spawners.DummySpawner;
import spawners.Spawner;
import tween.CommonTweens;
import tween.GlobalTween;
import ai.loader.AIFactory;
import assets.Assets;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import configuration.GameConfiguration;

public class PlayScreen extends AbstractScreen {

	Spawner spawner;
	private final GameConfiguration conf;

	GlyphLayout gameNameLayout;

	float playerListX = 0;
	float playerListY = EatFishAndAI.HEIGHT;
	float centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;

	private static enum State {
		INITIALIZING, STARTING, PLAYING, WINNER, OVER
	}

	State state;

	public PlayScreen(Game game, GameConfiguration conf) {
		super(game);
		this.conf = conf;
		state = State.INITIALIZING;
	}

	@Override
	public void show() {
		setBackground(Assets.bg);

		spawner = new DummySpawner();
		spawner.setGameContext(getGameContext());

		gameNameLayout = new GlyphLayout();
		gameNameLayout.setText(Assets.font30, conf.gamename);

		// Preset predator ai
		// int offset = 100;
		// getGameContext().spawn(
		// new PredatorFish(EatFishAndAI.WIDTH - offset,
		// EatFishAndAI.HEIGHT - offset));
		// getGameContext().spawn(
		// new PredatorFish(EatFishAndAI.WIDTH - offset, offset));
		// getGameContext().spawn(
		// new PredatorFish(offset, EatFishAndAI.HEIGHT - offset));
		// getGameContext().spawn(new PredatorFish(offset, offset));
		List<AIFactory<?>> factories = conf.aiconf.getAIs();
		int count = factories.size(), radius = 150;
		float angle = (float) (2 * Math.PI / count);
		for (int i = 0; i < conf.aiconf.getAIs().size(); i++) {
			PlayerFish player = new PlayerFish((float) (centerX
					- PlayerFish.WIDTH / 2 + Math.cos(i * angle) * radius),
					(float) (centerY - PlayerFish.HEIGHT / 2 + Math.sin(i
							* angle)
							* radius));

			getGameContext().spawn(player);
			player.setGameContext(getGameContext());
			player.attachAI(factories.get(i).newInstance());
			conf.players.add(player);
		}

		getGameContext().spawn(new PlayerList(conf, playerListX, playerListY));

		getGameContext().setPaused(true);
		getGameContext().update(1);

		state = State.STARTING;

		Timeline all = Timeline.createSequence();
		for (PlayerFish player : conf.players) {
			all.push(CommonTweens.zoomAtGameObject(player, getCamera(), 3.0f));
		}
		all.push(CommonTweens.zoomAtPoint(centerX, centerY, getCamera(), 1.0f));
		all.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				if (type == TweenCallback.COMPLETE) {
					state = State.PLAYING;
					getGameContext().setPaused(false);
				}
			}
		});
		GlobalTween.add(all);
	}

	@Override
	public void resume() {
		if (state == State.PLAYING) {
			setPaused(false);
		}
	}

	protected void update(float delta) {
		switch (state) {
		case INITIALIZING:
			break;
		case STARTING:
			break;
		case PLAYING:
			spawner.update(delta);
			for (int i = 0; i < conf.players.size(); i++) {
				PlayerFish player = conf.players.get(i);
				if (!player.isAlive()) {
					conf.players.remove(player);
					i--;
				}
			}

			if (conf.players.size() == 1) {

			}

			break;
		case OVER:
			break;
		case WINNER:
			break;
		default:
			break;
		}
	}

	@Override
	protected void drawScreen(SpriteBatch batch) {
		super.drawScreen(batch);
		Assets.font30.draw(batch, gameNameLayout, centerX
				- gameNameLayout.width / 2, EatFishAndAI.HEIGHT);
	}

}
