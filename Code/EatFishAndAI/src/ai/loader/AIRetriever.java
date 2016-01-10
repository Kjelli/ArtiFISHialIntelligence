package ai.loader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import ai.AI;

public class AIRetriever {
	File root;
	String pack;

	public AIRetriever() {
		root = new File("/java/mindyourfishai/");
	}

	/**
	 * Reads the source code from a given file (filename as string)
	 * 
	 * @param filename
	 *            the string containing the path of where the file is located
	 * @return the source code stored in a string
	 * @throws IOException
	 *             Throws potential exceptions if the file could not be found or
	 *             otherwise
	 */
	public String retrieveSourceCode(String filename) throws IOException {

		StringBuilder sourcecode = new StringBuilder();

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				filename)));
		boolean packLine = true;
		for (String line = reader.readLine(); line != null; line = reader
				.readLine()) {
			if (packLine) {
				pack = line.substring(7, line.length() - 1);
				packLine = false;
				continue;
			}
			sourcecode.append(line + "\r\n");
		}

		reader.close();

		return sourcecode.toString();
	}

	/**
	 * Given the sourcecode and a destination filepath, this method (creates a
	 * new file and)saves it as {filename}.java, storing the source code.
	 * 
	 * @param sourceCode
	 *            The source code to place inside the source file
	 * @param filename
	 *            the name of the file
	 * @return returns a reference to the newly generated file, or null if
	 *         unsuccessful
	 */

	public File saveToSourcefile(String sourceCode, String filename) {

		// Save source in .java file.
		File sourceFile;
		try {
			// On Windows running on C:\, this is
			// C:\java.
			sourceFile = new File(root, filename);
			sourceFile.getParentFile().mkdirs();

			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(sourceFile));
			writer.write("import ai.*;\r\n");
			for (String s : sourceCode.split("[\r\n]+")) {
				writer.write(s + "\r\n");
			}
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return sourceFile;
	}

	/**
	 * Takes the given sourcefile and compiles it using the system java
	 * compiler, and returns the class loaded from the compiled file.
	 * 
	 * @param sourceFile
	 *            The file of which to compile
	 * @return returns the class (which must extend AI interface)
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ObjectNotImplementingAIException
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends AI> compile(File sourceFile) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, ObjectNotImplementingAIException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, sourceFile.getPath());

		URLClassLoader classLoader = URLClassLoader
				.newInstance(new URL[] { root.toURI().toURL() });
		Class<?> cls = Class.forName(sourceFile.getName().split("[.]+")[0],
				true, classLoader);
		if (cls.newInstance() instanceof AI) {
			return (Class<? extends AI>) cls;
		} else {
			throw new ObjectNotImplementingAIException();
		}
	}

	public static AIFactory<?> compileAndLoadAI(String filepath)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, MaliciousAICodeException {
		AIRetriever ar = new AIRetriever();

		String filename = getFileName(filepath);
		System.out.println("Loading " + filename + " (" + filepath + ")");

		String sc = null;
		try {
			sc = ar.retrieveSourceCode(filepath);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		try {
			CodeAnalyzer.checkSourceCode(filename, sc);
		} catch (MaliciousAICodeException maice) {
			throw maice;
		}

		File sourceFile = ar.saveToSourcefile(sc, filename);

		Class<? extends AI> cls = null;
		try {
			cls = ar.compile(sourceFile);
		} catch (ObjectNotImplementingAIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AIFactory<>(cls);
	}

	private static String getFileName(String filename) {
		String[] elements = filename.split("[/]+");
		return elements[elements.length - 1];
	}
}
