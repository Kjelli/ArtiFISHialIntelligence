package screens;

import game.EatFishAndAI;
import gameobjects.fish.DummyFish;
import gameobjects.fish.Fish;
import gameobjects.fish.PlayerFish;
import graphics.gui.buttons.ButtonAction;
import graphics.gui.buttons.ButtonAction.TYPE;
import graphics.gui.buttons.ButtonListener;
import graphics.gui.buttons.CustomTextButton;
import graphics.particles.Fireworks;
import loading.LoadTask;
import tween.GlobalTween;
import utils.TweenableFloat;
import ai.WinnerPredatorAI;
import assets.Assets;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinnerScreen extends AbstractScreen {

	GlyphLayout labelTop, winnerLabel, labelBottom;
	BitmapFont bigFont, smallFont;
	private float centerX = EatFishAndAI.WIDTH / 2,
			centerY = EatFishAndAI.HEIGHT / 2;
	private TweenableFloat winnerX, winnerY, labelTransparency,
			backgroundTransparency;
	Color fontColor;

	String winnerName;

	boolean shootStars = false;

	public WinnerScreen(Game game, String winnerName) {
		super(game);
		this.winnerName = winnerName;
		bigFont = Assets.font50;
		smallFont = Assets.font30;

		fontColor = new Color(1, 1, 1, 0);

		labelTop = new GlyphLayout(smallFont, "CONGRATULATIONS", fontColor, 0,
				0, false);
		winnerLabel = new GlyphLayout(bigFont, winnerName);
		labelBottom = new GlyphLayout(smallFont, "You are the champion!",
				fontColor, 0, 0, false);

		winnerX = new TweenableFloat();
		winnerY = new TweenableFloat();
		winnerX.setValue(centerX - winnerLabel.width / 2);
		winnerY.setValue(centerY + EatFishAndAI.HEIGHT / 4 - winnerLabel.height
				/ 2);
		labelTransparency = new TweenableFloat();
		labelTransparency.setValue(0.0f);
		backgroundTransparency = new TweenableFloat();
		backgroundTransparency.setValue(0.0f);

	}

	@Override
	public void show() {
		setBackground(Assets.bg2);
		setPaused(false);
		setBackgroundColorTint(new Color(Color.WHITE));
		GlobalTween.add(Timeline
				.createSequence()
				.push(Tween.from(winnerY, -1, 2.0f)
						.target(EatFishAndAI.HEIGHT + 100f)
						.ease(TweenEquations.easeOutBounce))
				.push(Tween.to(labelTransparency, -1, 3.0f).target(1.0f))
				.push(Tween.to(backgroundTransparency, -1, 3.0f).target(1.0f))
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int type, BaseTween<?> source) {
						if (type == COMPLETE) {
							shootStars = true;
							Fish lol = new DummyFish(-DummyFish.WIDTH,
									centerY / 3);
							lol.setVelocityX(1);
							getGameContext().spawn(lol);

							PlayerFish rofl = new PlayerFish(
									EatFishAndAI.WIDTH, centerY / 3);
							rofl.setVelocityX(-1);
							rofl.setScale(4.0f);
							getGameContext().spawn(rofl);
							rofl.setGameContext(getGameContext());
							rofl.attachAI(new WinnerPredatorAI());
							rofl.setName(winnerName);
							rofl.start();

							CustomTextButton back = new CustomTextButton(
									centerX - CustomTextButton.WIDTH / 2,
									centerY - 2 * CustomTextButton.HEIGHT,
									"Back");
							back.setButtonListener(new ButtonListener() {

								@Override
								public void handle(ButtonAction ba) {
									if (ba.type == TYPE.RELEASE) {
										game.setScreen(new LoadingScreen(
												game,
												new LoadTask[] { new LoadTask() {

													@Override
													public void load() {
														try {
															Thread.sleep(500);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
													}
												} }, new MenuScreen(
														(EatFishAndAI) game)));
									}
								}
							});

							getGameContext().spawn(back);
						}
					}
				}));

	}

	@Override
	protected void update(float delta) {
		fontColor.a = labelTransparency.getValue();
		labelTop = new GlyphLayout(smallFont, "CONGRATULATIONS", fontColor, 0,
				0, false);
		labelBottom = new GlyphLayout(smallFont, "You are the champion!",
				fontColor, 0, 0, false);
		getBackgroundColorTint().a = backgroundTransparency.getValue();

		if (shootStars) {
			if (Math.random() < 0.02f) {
				float randomX = (float) (Math.random() * EatFishAndAI.WIDTH);
				float randomY = (float) ((Math.random() * 0.5f + 0.5f) * EatFishAndAI.HEIGHT);
				getGameContext().spawn(new Fireworks(randomX, randomY));
			}
		}

	}

	@Override
	protected void drawScreen(SpriteBatch batch) {
		bigFont.draw(batch, winnerLabel, winnerX.getValue(), winnerY.getValue());

		smallFont.draw(batch, labelTop, centerX + labelTop.width / 2,
				EatFishAndAI.HEIGHT - labelTop.height * 7);
		smallFont.draw(batch, labelBottom, centerX + labelBottom.width / 2,
				labelBottom.height * 20);
	}

}
