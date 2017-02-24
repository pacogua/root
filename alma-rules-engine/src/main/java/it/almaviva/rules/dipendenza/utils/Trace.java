package it.almaviva.rules.dipendenza.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trace {



	public static void error(Object obj, String msg, Throwable t,
			Object... args) {
		getLogger(obj).error(msg, t, args);
	}

	public static void debug(Object obj, String msg, Object... args) {
		Logger logger = getLogger(obj);
		if (logger.isDebugEnabled())
			logger.debug(msg, args);
	}

	public static void info(Object obj, String msg, Object... args) {
		Logger logger = getLogger(obj);
		if (logger.isInfoEnabled())
			logger.info(msg, args);
	}

	public static void warn(Object obj, String msg, Object... args) {
		Logger logger = getLogger(obj);
		logger.warn(msg);
	}




	public static void error(Object obj, String msg, Object... args) {
		
		Logger logger = getLogger(obj);
		logger.error(msg, args);
	}

	

	private static Logger getLogger(Object o) {
		return LoggerFactory.getLogger(o.getClass());
	}
	

	
	
}
