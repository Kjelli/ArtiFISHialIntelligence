package utils;

/**
 * Utility class for whitelisting methods otherwise banned
 * 
 * @author Kjell Arne Hellum
 *
 */
public class Log {
	/**
	 * Equivalent to System.out.println(...);
	 * 
	 * @param line
	 */
	public static void println(String line) {
		System.out.println(line);
	}

	/**
	 * Equivalent to System.out.printf(format, args);
	 * 
	 * @param format
	 * @param args
	 */
	public static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}
}
