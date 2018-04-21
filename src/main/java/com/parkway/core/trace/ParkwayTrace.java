package com.parkway.core.trace;

import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;

// Sample usage of class
//ParkwayTrace trace = null;
//trace = new ParkwayTrace();
//trace.setSubsystem("MIDDLE_WARE|Application Name");
//trace.start();
//trace.setDescription("FETCHING_FACILITIES|call method");
//trace.stopAndReport();

public class ParkwayTrace {

	private static final ELogger log = ELoggerFactory.getLogger(ParkwayTrace.class);

	protected long totalTime;
	protected long startTime;
	protected long stopTime;
	protected String description;
	protected String subsystem;

	protected String formatTime(long millis) {
		return Long.toString(millis);
	}

	public String getDescription() {
		return this.description;
	}

	public String getSubsystem() {
		return this.subsystem;
	}

	public long report() {

		StringBuffer outputText = new StringBuffer(description);
		outputText.append(",");
		outputText.append(formatTime(totalTime));

		log.info(outputText.toString());

		long time = totalTime;

		reset();

		return time;
	}

	public void reset() {
		this.startTime = 0L;
		this.stopTime = 0L;
		this.totalTime = 0L;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	public long stop() {
		this.stopTime = System.currentTimeMillis();
		long timeTaken = this.stopTime - this.startTime;
		this.totalTime += timeTaken;
		return timeTaken;
	}

	public long stopAndReport() {
		stop();
		return report();
	}

	public ParkwayTrace() {
		this.totalTime = 0L;
		this.startTime = 0L;
		this.stopTime = 0L;
		this.description = new String();
		this.subsystem = "PARKWAY.CUSTOM.TIMING";
	}

	public ParkwayTrace(String description) {
		this.totalTime = 0L;
		this.startTime = 0L;
		this.stopTime = 0L;
		this.subsystem = "PARKWAY.CUSTOM.TIMING";
		this.description = description;
	}

}
