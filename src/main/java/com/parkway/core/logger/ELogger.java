package com.parkway.core.logger;

public interface ELogger {

	public abstract boolean isDebugEnabled();

	public abstract boolean isInfoEnabled();

	public abstract boolean isWarnEnabled();

	public abstract boolean isErrorEnabled();

	public abstract boolean isFatalEnabled();

	public abstract void debug(String s);

	public abstract void debug(String s, Throwable throwable);

	public abstract void info(String s);

	public abstract void info(String s, Throwable throwable);

	public abstract void warn(String s);

	public abstract void warn(String s, Throwable throwable);

	public abstract void error(String s);

	public abstract void error(String s, Throwable throwable);
	
	public abstract void error(Throwable throwable);
	
	public abstract void monitorError(String s, Throwable throwable);
	
	public abstract void monitorError(String s);
	
	public abstract void monitorError(Throwable throwable);

	public abstract void fatal(String s);

	public abstract void fatal(String s, Throwable throwable);

	public static final String DEFAULT_CONTEXT = "com.dbs";
}
