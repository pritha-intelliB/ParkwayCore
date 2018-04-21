package com.parkway.core.logger;



import org.apache.log4j.Logger;

import com.parkway.core.config.Constants;


public class ParkwayLog4jLoggerFactory extends ELoggerFactory{

	protected static String getConfigFile() {
		String configFile = Constants.LOG4J_CONFIG_KEY_FILE_PATH;		
		System.out.println("getConfigFile = "+configFile);
		return configFile;
	}
	
	protected static long getConfigRefreshInterval() {
		long refreshInterval = Long.parseLong(Constants.LOG4J_CONFIG_KEY_REFRESH_INTERVAL);
		return refreshInterval * 1000;
	}

	public ParkwayLog4jLoggerFactory(){
		super();
	}

	static {
		configure();
	}

	protected static void configure() {
		ParkwayPropertyConfigurator.configureAndWatch(
				getConfigFile(),
				getConfigRefreshInterval());
	}

	protected ELogger createLogger(String subsystem) {
		return new ParkwayLog4jLogger(Logger.getLogger(subsystem));
	}	

}
