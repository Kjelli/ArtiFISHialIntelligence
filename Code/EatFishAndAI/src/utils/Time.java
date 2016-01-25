package utils;

/**
 * Utility class for whitelisting methods otherwise banned
 * 
 * @author Kjell Arne Hellum
 *
 */
public class Time {
	/**
	 * Equivalent to System.nanoTime().
	 * 
	 * @return
	 */
	public static double nanoTime() {
		return System.nanoTime();
	}

	/**
	 * Equivalent to System.currentTimeMillis().
	 * 
	 * @return
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
}
