package graphics.gui;

import gameobjects.AbstractGameObject;
import gameobjects.fish.PlayerFish;

import java.util.Collections;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import configuration.GameConfiguration;

public class OverallScore extends AbstractGameObject {
	GameConfiguration conf;
	GlyphLayout listLabelLayout;
	GlyphLayout[] playerNameLayouts;
	BitmapFont listFont;

	Sprite fishSprite;

	public OverallScore(GameConfiguration conf, float x, float y) {
		super(x, y, 0, 0);
		this.conf = conf;
		listFont = Assets.font30;
		listLabelLayout = new GlyphLayout();
		listLabelLayout.setText(listFont, "Total Wins");

		fishSprite = new Sprite(Assets.predatorfish);
		fishSprite.setY(y);
		fishSprite.setX(x);
		fishSprite.setScale(1.0f);
		fishSprite.setBounds(x, y, 16, 12);

	}

	@Override
	public void update(float delta) {
		Collections.sort(conf.players);
	}

	@Override
	public void draw(SpriteBatch batch) {
		listFont.draw(batch, listLabelLayout, x, y);
		for (int i = 0; i < conf.players.size(); i++) {
			drawPlayerInformation(conf.players.get(i), batch, i);
		}
		for (int i = 0; i < conf.discardedPlayers.size(); i++) {
			drawPlayerInformation(conf.discardedPlayers.get(i), batch, i
					+ conf.players.size());
		}
	}

	private void drawPlayerInformation(PlayerFish player, SpriteBatch batch,
			int playerNo) {
		GlyphLayout layout = player.getNameLayout();
		PlayerFish.playerNameFont.draw(batch, layout, x, y - (playerNo + 2)
				* (layout.height + 2));
		for (int j = 0; j < player.getScore(); j++) {
			fishSprite.setY(y - (playerNo + 2) * (layout.height + 2) - layout.height);
			fishSprite.setX(x + layout.width + j * fishSprite.getWidth());
			fishSprite.draw(batch);
		}
	}

}
