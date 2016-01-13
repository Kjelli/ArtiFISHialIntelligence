package utils;

public class Log {
	public static void println(String line){
		System.out.println(line);
	}
	
	public static void printf(String format, Object...args){
		System.out.printf(format, args);
	}
}
