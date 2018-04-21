package com.parkway.core.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class Log4jLogger implements ELogger {

	protected Logger logger;

	
	public void debug(String msg) {
		logger.debug(msg);
	}

	
	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	
	public void info(String msg) {
		logger.info(msg);
	}

	
	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	
	public void warn(String msg) {
		logger.warn(msg);
	}

	
	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	
	public void error(String msg) {
		logger.error(msg);
	}

	
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	
	public void fatal(String msg) {
		logger.fatal(msg);
	}

	
	public void fatal(String msg, Throwable t) {
		logger.fatal(msg, t);
	}

	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	
	@SuppressWarnings("deprecation")
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Priority.ERROR);
	}

	
	@SuppressWarnings("deprecation")
	public boolean isFatalEnabled() {
		return logger.isEnabledFor(Priority.FATAL);
	}

	
	@SuppressWarnings("deprecation")
	public boolean isInfoEnabled() {
		return logger.isEnabledFor(Priority.INFO);
	}

	
	@SuppressWarnings("deprecation")
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Priority.WARN);
	}

	public Log4jLogger(Logger logger) {
		this.logger = logger;
	}

	
	public void error(Throwable throwable) {
		error("", throwable);

	}

	
	public void monitorError(String s, Throwable throwable) {
		error("ITM_APPLICATION_MONITOR " + s, throwable);
		
	}

	
	public void monitorError(String s) {
		error("ITM_APPLICATION_MONITOR " + s);
		
	}

	
	public void monitorError(Throwable throwable) {
		error("ITM_APPLICATION_MONITOR " , throwable);
		
	}
}