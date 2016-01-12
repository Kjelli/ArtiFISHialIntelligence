package graphics.gui.buttons;

import assets.Assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractTextButton extends AbstractButton implements
		TextButton {

	protected final GlyphLayout layout;
	protected final BitmapFont font;
	private String text;

	public AbstractTextButton(float x, float y, BitmapFont font, String text) {
		super(x, y, Assets.button_empty, Assets.button_empty_hover,
				Assets.button_empty_pressed);
		layout = new GlyphLayout();
		this.font = font;
		setText(text);
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		font.draw(batch, layout, x + WIDTH / 2 - layout.width / 2, y + HEIGHT
				/ 2 + layout.height / 2);
	}

	@Override
	public void setText(String s) {
		text = s;
		layout.setText(font, text);
		if (layout.width > getWidth()) {
			setText(s.substring(0, s.length() - 2) + ".");
		}
	}

	@Override
	public String getText() {
		return text;
	}

}
