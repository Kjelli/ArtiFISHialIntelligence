package ai.loader;

public class CodeAnalyzer {

	private static final String[] banned = new String[] { "System", "exec",
			"Runtime", "exit", "ClassLoader", "File", "Process",
			"java.awt.Robot", "reflection", "getdeclaredmethod",
			"getdeclaredfield", "script", "java.beans", "java.net" };

	public static final boolean checkSourceCode(String filename, String sc)
			throws MaliciousAICodeException {
		int lineNo = 0;
		for (String line : sc.split("[\r\n]+")) {
			if (!whitelisted(filename, line, lineNo)) {
				return false;
			}
			lineNo++;
		}

		return true;
	}

	public static final boolean whitelisted(String filename, String line,
			int lineNo) throws MaliciousAICodeException {
		String linecp = new String(line).toLowerCase();

		// Comments are safe
		if (linecp.startsWith("//")) {
			return true;

		}
		// Banned keywords
		for (String bannedKeyword : banned) {
			if (linecp.contains(bannedKeyword.toLowerCase())) {
				throw new MaliciousAICodeException(filename, line.trim(),
						lineNo, "Banned keyword detected: " + bannedKeyword);
			}
		}
		return true;
	}
}
