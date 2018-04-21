package com.parkway.core.logger;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.spi.LoggerRepository;

import com.parkway.core.config.Constants;

public class ParkwayPropertyConfigurator extends PropertyConfigurator {

	/**
	 * Read the configuration file <code>configFilename</code> if it exists.
	 * Moreover, a thread will be created that will periodically check if
	 * <code>configFilename</code> has been created or modified. The period is
	 * determined by the <code>delay</code> argument. If a change or file
	 * creation is detected, then <code>configFilename</code> is read to
	 * configure log4j.
	 * 
	 * @param configFilename
	 *            A file in key=value format.
	 * @param delay
	 *            The delay in milliseconds to wait between each check.
	 */
	static public void configureAndWatch(String configFilename, long delay) {
		ParkwayPropertyWatchdog pdog = new ParkwayPropertyWatchdog(configFilename);
		pdog.setDelay(delay);
		pdog.start();
	}

	/**
	 * Read configuration options from url <code>configURL</code>.
	 */
	@Override
	public void doConfigure(java.net.URL configURL, LoggerRepository hierarchy) {
		Properties envSpecificProps = Constants.loadProperties(configURL);
		super.doConfigure(envSpecificProps, hierarchy);
	}
}

/**
 * This class extends the default log4j FileWatchDog class The purpose is to
 * allow each appserver in the cluster their own log files This is achieved by
 * reading the properties from the log4j properties file Then these properties
 * are analysed replacing the <appserver>attribute in the properties file with
 * the serverName retrieved from the system properties
 */

class ParkwayPropertyWatchdog extends FileWatchdog {

	ParkwayPropertyWatchdog(String filename) {
		super(filename);
	}

	/**
	 * Call {@link PropertyConfigurator#configure(String)}with the
	 * <code>filename</code> to reconfigure log4j.
	 */
	@Override
	public void doOnChange() {
		Properties envSpecificProps = Constants.loadProperties(filename);
		new ParkwayPropertyConfigurator().doConfigure(envSpecificProps, LogManager.getLoggerRepository());
	}
}