package ai.loader;

@SuppressWarnings("serial")
public class ObjectNotImplementingAIException extends InvalidAIException {

	public ObjectNotImplementingAIException() {
		super("Object not implementing AI interface!");

	}

	public ObjectNotImplementingAIException(String reason) {
		super("Object not implementing AI interface: " + reason);

	}

}
