package com.parkway.core.txnhandler.connector;

import java.util.Properties;
import java.util.StringTokenizer;

import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;

public class PropertiesHelper {

	private static final ELogger logger = ELoggerFactory.getLogger(PropertiesHelper.class);

	public static Properties parseProperties(String properties) {
		Properties propTable = new Properties();
		StringTokenizer tokenizer = new StringTokenizer(properties, ";=");

		while (tokenizer.hasMoreTokens()) {
			propTable.put(tokenizer.nextToken(), tokenizer.nextToken());
		}

		return propTable;
	}

	public static String getStringProperty(Properties properties, String key, String defaultValue, String errorMsg) {
		String value = properties.getProperty(key);
		if (value == null) {
			value = defaultValue;
			logger.info("PropertiesHelper>>>errorMsg" + errorMsg);
		}
		return value;
	}

	public static String getStringProperty(Properties properties, String key, String errorMsg) {
		return getStringProperty(properties, key, "", errorMsg);
	}

	public static Integer getIntegerProperty(Properties properties, String key, int defaultValue) {
		Integer value = new Integer(defaultValue);
		try {
			Integer temp = Integer.valueOf(properties.getProperty(key));
			if (temp != null)
				value = temp;
		} catch (Exception ex) {
			logger.error("PropertiesHelper>>>getIntegerProperty>>>", ex);
		}
		return value;
	}

	public static Integer getIntegerProperty(Properties properties, String key) {
		return getIntegerProperty(properties, key, 0);
	}
}
