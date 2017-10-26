package com.enterprise.common;

import com.enterprise.common.util.Constants;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * WAS log levels:
 * off
 * fatal
 * severe
 * warning
 * audit
 * info
 * config
 * detail
 * fine
 * finer
 * finest
 * all
 * 
 * @since  26 Jul 2013 1:55:20 PM
 * @author rafourie
 */
public class Logger {
	/* static final variable declarations */
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
	
	private static String buildMsg(Class<?> clasz, Exception e) {
		return "[" + (clasz == null ? Constants.EMPTY_STR : clasz.getSimpleName()) + "] " + 
			((e == null) ? Constants.EMPTY_STR : e.getMessage() + " - " + e.toString());
	}
	
	private static String buildMsg(Class<?> clasz, Throwable e) {
		return "[" + (clasz == null ? Constants.EMPTY_STR : clasz.getSimpleName()) + "] " + 
			((e == null) ? Constants.EMPTY_STR : e.getMessage() + " - " + e.toString());
	}
	
	private static String buildMsg(String msg, Class<?> clasz) {
		return "[" + (clasz == null ? Constants.EMPTY_STR : clasz.getSimpleName()) + "] " + msg;
	}
	
	public static void fatal(String msg, Class<?> clasz) {
		msg = Logger.buildMsg(msg, clasz);
		logger.fatal(msg);
		Logger.println(msg);
	}

	public static void fatal(Class<?> clasz, Exception e) {
		String msg = Logger.buildMsg(clasz, e);
		logger.fatal(msg);
		Logger.println(msg);
	}
	
	public static void error(Class<?> clasz, Exception e) {
		String msg = Logger.buildMsg(clasz, e);
		logger.error(msg);
		Logger.println(msg);
		if (e != null)
			e.printStackTrace();
	}
	
	public static void error(Class<?> clasz, Throwable e) {
		String msg = Logger.buildMsg(clasz, e);
		logger.error(msg);
		Logger.println(msg);
		if (e != null)
			e.printStackTrace();
	}
	
	public static void warning(String msg, Class<?> clasz) {
		msg = Logger.buildMsg(msg, clasz);
		logger.warn(msg);
		Logger.println(msg);
	}
	
	public static void info(String msg, Class<?> clasz) {
		msg = Logger.buildMsg(msg, clasz);
		logger.info(msg);
		Logger.println(msg);
	}
	
	public static void debug(String msg, Class<?> clasz) {
		msg = Logger.buildMsg(msg, clasz);
		logger.debug(msg);
		Logger.println(msg);
	}
	
	private static void println(String msg) {
		System.out.println(msg);
	}
	
	public void println(String msg, Class<?> clasz) {
		msg = Logger.buildMsg(msg, clasz);
		Logger.println(msg);
	}
}