package ai.loader;

import ai.AI;

public class AIFactory<T extends AI> {
	private final Class<T> cls;
	private final String filename;

	public AIFactory(Class<T> cls, String filename) {
		this.filename = filename;
		this.cls = cls;
	}

	public T newInstance() {
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFilename() {
		return filename;
	}

}
