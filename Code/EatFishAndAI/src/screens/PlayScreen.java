package screens;

import game.EatFishAndAI;
import gameobjects.fish.PlayerFish;
import graphics.gui.OverallScore;
import graphics.gui.RoundScore;
import graphics.gui.buttons.ButtonAction;
import graphics.gui.buttons.ButtonListener;
import graphics.gui.buttons.CustomTextButton;
import graphics.gui.buttons.ButtonAction.TYPE;

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

	float centerX = EatFishAndAI.WIDTH / 2, centerY = EatFishAndAI.HEIGHT / 2;
	float playerListX = centerX / 3;
	float playerListY = EatFishAndAI.HEIGHT;

	private static enum State {
		INITIALIZING, STARTING, PLAYING, WINNER, OVER
	}

	State state;

	public PlayScreen(Game game, GameConfiguration conf) {
		super(game);
		this.conf = conf;

	}

	@Override
	public void show() {
		state = State.INITIALIZING;
		setBackground(Assets.bg);
		getBackgroundBounds().x = -100;
		getBackgroundBounds().y = -100;
		getBackgroundBounds().width = EatFishAndAI.WIDTH + 200;
		getBackgroundBounds().height = EatFishAndAI.HEIGHT + 200;

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
		float angleOffset = (float) (Math.PI / 4);
		for (int i = 0; i < conf.aiconf.getAIs().size(); i++) {
			PlayerFish player;
			if (conf.discardedPlayers.isEmpty()) {
				player = new PlayerFish(
						(float) (centerX - PlayerFish.WIDTH / 2 + Math.cos(i
								* angle + angleOffset)
								* radius), (float) (centerY - PlayerFish.HEIGHT
								/ 2 + Math.sin(i * angle + angleOffset)
								* radius));
			} else {
				player = conf.discardedPlayers.get(i);
				player.killAI();
				player.setX((float) (centerX - PlayerFish.WIDTH / 2 + Math
						.cos(i * angle + angleOffset) * radius));
				player.setY((float) (centerY - PlayerFish.HEIGHT / 2 + Math
						.sin(i * angle + angleOffset) * radius));
				player.setVelocityX(0);
				player.setVelocityY(0);
				player.setMaxSpeed(PlayerFish.MAX_SPEED);
				player.setScale(PlayerFish.STARTING_SCALE);
				player.setAlive(true);
			}
			conf.players.add(player);

			getGameContext().spawn(player);
			player.setGameContext(getGameContext());
			player.attachAI(factories.get(i).newInstance());
			player.start();
		}

		conf.discardedPlayers.clear();

		getGameContext().spawn(new RoundScore(conf, playerListX, playerListY));

		CustomTextButton stopButton = new CustomTextButton(EatFishAndAI.WIDTH
				- CustomTextButton.WIDTH, 0, "STOP");

		stopButton.setButtonListener(new ButtonListener() {

			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					game.setScreen(new MenuScreen((EatFishAndAI) game));
				}
			}
		});

		CustomTextButton killButton = new CustomTextButton(EatFishAndAI.WIDTH
				- 2 * CustomTextButton.WIDTH, 0, "KILL");

		killButton.setButtonListener(new ButtonListener() {

			@Override
			public void handle(ButtonAction ba) {
				if (ba.type == TYPE.RELEASE) {
					while (conf.players.size() > 1) {
						int randomIndex = (int) (Math.random() * conf.players
								.size());
						PlayerFish fish = conf.players.get(randomIndex);
						conf.players.remove(fish);
						conf.discardedPlayers.add(fish);
						fish.destroy();
					}
				}
			}
		});

		getGameContext().spawn(killButton);
		getGameContext().spawn(stopButton);

		state = State.STARTING;

		Timeline all = Timeline.createSequence();
		for (PlayerFish player : conf.players) {
			all.push(CommonTweens.zoomAtGameObject(player, getCamera(), 3.0f,
					1.0f));
		}
		all.push(CommonTweens.zoomAtPoint(centerX, centerY, getCamera(), 1.0f,
				1.0f));
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

		getGameContext().spawn(
				new OverallScore(conf, EatFishAndAI.WIDTH - 300,
						EatFishAndAI.HEIGHT));

		getGameContext().update(1);
		getGameContext().setPaused(true);
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
					conf.discardedPlayers.add(player);
					i--;
				}
			}

			if (conf.players.size() == 1 && conf.aiconf.getAIs().size() > 1) {
				final PlayerFish winner = conf.players.get(0);
				state = State.OVER;
				getGameContext().setPaused(true);
				GlobalTween.add(CommonTweens.zoomAtGameObject(winner,
						getCamera(), 3.0f, 3.0f).setCallback(
						new TweenCallback() {

							@Override
							public void onEvent(int type, BaseTween<?> source) {
								if (type == TweenCallback.COMPLETE) {
									conf.discardedPlayers.add(winner);
									winner.incrementScore();
									winner.destroy();
									conf.players.clear();
									getGameContext().setPaused(false);
									getGameContext().update(1f);
									if (winner.getScore() >= conf.winLimit) {
										game.setScreen(new WinnerScreen(game,
												winner.getName()));
									} else {
										game.setScreen(new PlayScreen(game,
												conf));
									}
								}
							}
						}));
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
