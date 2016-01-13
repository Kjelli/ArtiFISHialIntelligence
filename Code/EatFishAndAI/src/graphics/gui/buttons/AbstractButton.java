package graphics.gui.buttons;

import gameobjects.AbstractGameObject;
import graphics.gui.buttons.ButtonAction.TYPE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class AbstractButton extends AbstractGameObject implements
		Button {

	protected Actor actor;
	protected Texture idle, hover, pressed;

	private ButtonListener listener;

	public static final int WIDTH = 150, HEIGHT = 50;

	public AbstractButton(float x, float y, Texture idle, Texture hover,
			Texture pressed) {
		super(x, y, WIDTH, HEIGHT);
		this.idle = idle;
		this.hover = hover;
		this.pressed = pressed;
		setSprite(new Sprite(idle));
		actor = new Actor();
		actor.setBounds(x, y, width, height);
		actor.addListener(new InputListener() {

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				return true;
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				if (pointer == -1) {
					getSprite().setTexture(hover);
				}
				if (listener != null) {
					listener.handle(new ButtonAction(TYPE.ENTER, x, y, -1));
				}
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (button == 0) {
					getSprite().setTexture(pressed);
				}
				if (listener != null) {
					listener.handle(new ButtonAction(TYPE.PRESS, x, y, button));
				}

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (button == 0) {
					if (x >= 0 && x <= actor.getWidth() && y >= 0
							&& y <= actor.getHeight()) {
						getSprite().setTexture(hover);
					} else {
						getSprite().setTexture(idle);
					}
				}
				if (listener != null) {
					listener.handle(new ButtonAction(TYPE.RELEASE, x, y, button));
				}

			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer,
					Actor toActor) {
				if (pointer == -1) {
					getSprite().setTexture(idle);
				}
				if (listener != null) {
					listener.handle(new ButtonAction(TYPE.EXIT, x, y, pointer));
				}

			}
		});

	}

	@Override
	public void onSpawn() {
		getGameContext().getStage().addActor(actor);
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void setX(float x) {
		super.setX(x);
		actor.setBounds(x, y, width, height);
	}

	@Override
	public void setY(float y) {
		super.setY(y);
		actor.setBounds(x, y, width, height);
	}

	@Override
	public void onDespawn() {
		actor.clear();
	}

	public ButtonListener getButtonListener() {
		return listener;
	}

	public void setButtonListener(ButtonListener listener) {
		this.listener = listener;
	}

}
