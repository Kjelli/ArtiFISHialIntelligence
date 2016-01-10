package ai.loader;

import ai.AI;

@SuppressWarnings("serial")
public class MaliciousAICodeException extends InvalidAIException {

	public MaliciousAICodeException(String aiclass, String reason) {
		super("Potentially malicious code detected in " + aiclass + ": "
				+ reason);
	}

	public MaliciousAICodeException(String aiclass, String line, int lineNo,
			String reason) {
		super("Potentially malicious code detected in " + aiclass + " at line "
				+ lineNo + ": " + line + " (Reason: " + reason + ")");
	}

}
