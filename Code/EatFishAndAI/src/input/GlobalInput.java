package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class GlobalInput {
	private static InputMultiplexer mux;

	public static void init() {
		setMux(new InputMultiplexer());
		Gdx.input.setInputProcessor(GlobalInput.getInputMultiplexer());
	}

	public static void addInputProcessor(InputProcessor processor) {
		getInputMultiplexer().addProcessor(processor);
	}

	public static void removeInputProcessor(InputProcessor processor) {
		getInputMultiplexer().removeProcessor(processor);
	}

	public static void removeAllInputProcessing() {
		getInputMultiplexer().clear();
	}

	public static Array<InputProcessor> getInputProcessors() {
		return getInputMultiplexer().getProcessors();
	}

	public static InputMultiplexer getInputMultiplexer() {
		return mux;
	}

	public static void setMux(InputMultiplexer mux) {
		GlobalInput.mux = mux;
	}
}
