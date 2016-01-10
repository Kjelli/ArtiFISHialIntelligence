package ai.loader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import ai.AI;

public class AILoader {
	private static final Map<String, AIFactory> facmap = new HashMap<>();

	public static AI load(String filename) throws FileNotFoundException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, MaliciousAICodeException {
		if (!facmap.containsKey(filename)) {
			facmap.put(filename, AIRetriever.compileAndLoadAI(filename));
		}
		return facmap.get(filename).newInstance();
	}
}
