package com.parkway.core.config;

public class Constants {
	
	public static final String SYSTEM_PROPERTY_LOGGER = "com.parkway.logger"; // need to configure in jvm options
	public static final String SYSTEM_PROPERTY_CONFIG_DIRECTORY = "com.parkway.config.directory"; // need to configure in jvm options
	
	public static final String PARKWAY_LOG4_CONNECTION_FACTORY = "com.parkway.core.logger.ParkwayLog4jLoggerFactory";  // System.getProperty(Constants.SYSTEM_PROPERTY_LOGGER);
	public static final String CONFIG_DIRECTORY_LOCATION = "C:\\temp\\";  // System.getProperty(Constants.SYSTEM_PROPERTY_CONFIG_DIRECTORY);

	public static final String LOG4J_CONFIG_KEY_FILE_PATH = CONFIG_DIRECTORY_LOCATION + "log4j.properties";
	public static final String SERVICE_CONFIG_KEY_FILE_PATH = CONFIG_DIRECTORY_LOCATION + "service_config.json";
	
	public static final String LOG4J_CONFIG_KEY_REFRESH_INTERVAL = "10";
	
	public static final String SERVICE_ID = "serviceId";
	public static final String PARKWAY_REQUEST_CONTEXT = "PARKWAY_REQUEST_CONTEXT";
	
	public static java.util.Properties loadProperties(java.net.URL configURL) {
		String path = null;

		if (configURL != null) {
			path = configURL.getPath();
		}

		System.out.println("Environment Specific Properties Loading from URL : " + path);

		return loadProperties(path);
	}
	
	public static java.util.Properties loadProperties(String path) {
		java.util.Properties props = new java.util.Properties();
		java.io.BufferedReader fis = null;
		props.clear();

		try {
			fis = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(path), "UTF-8"));
			props.load(fis);
		} catch (Exception fe) {
			System.out.println("com.parkway.core.config.Constants>loadProperties>>FileNotFoundException>>" + path + fe.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (java.io.IOException ex) {
				System.out.println("ITM_APPLICATION_MONITOR Encountered error while reading " + path + ex.getMessage());
			}
		}
	  return props;
	}

}
