package com.parkway.core.logger;

public class ConsoleLogger implements ELogger{

	protected String prependContext(String msg)
	{
		StringBuffer buf = new StringBuffer();
		if(msg != null)
		{
			buf.append(' ');
			buf.append(msg);
		}
		return buf.toString();
	}

	
	public boolean isDebugEnabled()
	{
		return true;
	}

	
	public void debug(String msg)
	{
		if(isDebugEnabled())
			prependContext(msg);
	}

	
	public void debug(String s, Throwable throwable)
	{
		if(isDebugEnabled())
		{
			prependContext(s);
			throwable.printStackTrace(System.out);
		}
	}

	
	public void info(String s)
	{
		prependContext(s);
	}

	
	public void info(String s, Throwable throwable)
	{
		prependContext(s);
		throwable.printStackTrace(System.out);
	}

	
	public void warn(String s)
	{
		prependContext(s);
	}

	
	public void warn(String s, Throwable throwable)
	{
		prependContext(s);
		throwable.printStackTrace(System.out);
	}

	
	public void error(String s)
	{
		prependContext(s);
	}

	
	public void error(String s, Throwable throwable)
	{
		prependContext(s);
		throwable.printStackTrace(System.out);
	}

	
	public void fatal(String s)
	{
		prependContext(s);
	}

	
	public void fatal(String s, Throwable throwable)
	{
		prependContext(s);
		throwable.printStackTrace(System.out);
	}
	
	public boolean isErrorEnabled()
	{
		return true;
	}

	
	public boolean isFatalEnabled()
	{
		return true;
	}

	
	public boolean isInfoEnabled()
	{
		return true;
	}

	
	public boolean isWarnEnabled()
	{
		return true;
	}

	
	public void error(Throwable throwable) {
		error("", throwable);        
	}

	
	public void monitorError(String s, Throwable throwable) {
		error("APPLICATION_MONITOR " + s, throwable);
	}

	
	public void monitorError(String s) {
		error("APPLICATION_MONITOR " + s);
	}

	
	public void monitorError(Throwable throwable) {
		error("APPLICATION_MONITOR ", throwable);
	}
}
