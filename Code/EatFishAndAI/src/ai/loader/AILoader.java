package ai.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ai.AI;

public class AILoader {
	private static final Map<String, AIFactory<? extends AI>> facmap = new HashMap<>();

	public static AIFactory<? extends AI> load(String filename)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, InvalidAIException, IOException {
		if (!facmap.containsKey(filename)) {
			facmap.put(filename, AIRetriever.compileAndLoadAI(filename));
		}
		return facmap.get(filename);
	}
}
