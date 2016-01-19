package graphics.gui;

import java.util.Collections;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import configuration.GameConfiguration;
import gameobjects.AbstractGameObject;
import gameobjects.fish.PlayerFish;

public class RoundScore extends AbstractGameObject {
	GameConfiguration conf;
	GlyphLayout listLabelLayout;
	GlyphLayout[] playerNameLayouts;
	BitmapFont listFont;

	public RoundScore(GameConfiguration conf, float x, float y) {
		super(x, y, 0, 0);
		this.conf = conf;
		listFont = Assets.font30;
		listLabelLayout = new GlyphLayout();
		listLabelLayout.setText(listFont, "Round score");

	}

	@Override
	public void update(float delta) {
		Collections.sort(conf.players);
	}

	@Override
	public void draw(SpriteBatch batch) {
		listFont.draw(batch, listLabelLayout, x, y);
		for (int i = 0; i < conf.players.size(); i++) {
			GlyphLayout layout = conf.players.get(i).getNameLayout();
			PlayerFish.playerNameFont.draw(batch, layout, x, y - (i + 2)
					* (layout.height + 2));
			
			GlyphLayout score = conf.players.get(i).getMassScoreLayout();
			PlayerFish.playerNameFont.draw(batch, score, x + PlayerFish.NAME_LENGTH_LIMIT, y - (i + 2)
					* (layout.height + 2));
		}
	}

}
