package ai.loader;

@SuppressWarnings("serial")
public class InvalidAIException extends Exception {
	public InvalidAIException(String reason) {
		super("Invalid AI: " + reason);
	}
}
