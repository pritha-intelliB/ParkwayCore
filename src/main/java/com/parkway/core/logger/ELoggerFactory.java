package com.parkway.core.logger;

import java.util.HashMap;
import java.util.Map;

import com.parkway.core.config.Constants;

public abstract class ELoggerFactory {

	private static final ELoggerFactory instance = createLoggerInstance();
	private static final ELogger fallbackLogger = new ConsoleLogger();

	@SuppressWarnings("rawtypes")
	protected Map loggerCache;

	protected abstract ELogger createLogger(String s);

	@SuppressWarnings("unchecked")
	protected synchronized ELogger getLoggerForSubsystem(String subsystem)
	{
		//System.out.println("subsystem : " + subsystem);
		ELogger cached = (ELogger) loggerCache.get(subsystem);
		if(cached == null)
		{
			cached = createLogger(subsystem);
			loggerCache.put(subsystem, cached);
		}
		return cached;
	}

	private static final ELoggerFactory createLoggerInstance()
	{
		ELoggerFactory eLoggerFactory =null;
		try{
			String loggerClassName = Constants.PARKWAY_LOG4_CONNECTION_FACTORY;
			System.out.println("Default logger configured : " + loggerClassName); 

			if(loggerClassName != null)
				eLoggerFactory = (ELoggerFactory)Class.forName(loggerClassName).newInstance();

		}catch(Exception e){
			e.printStackTrace();
		}
		return eLoggerFactory;
	}

	@SuppressWarnings("rawtypes")
	public static ELogger getLogger(Class subsystem)
	{
		//System.out.println("subsystem class : " + subsystem.getName());
		return getLogger(subsystem.getName());
	}

	public static ELogger getLogger(String subsystem)
	{
		if(instance == null)
			return fallbackLogger;
		else
			return instance.getLoggerForSubsystem(subsystem);
	}

	@SuppressWarnings("rawtypes")
	private final void initLoggerCache()
	{
		loggerCache = new HashMap();
	}

	protected ELoggerFactory()
	{
		initLoggerCache();
	}

}
