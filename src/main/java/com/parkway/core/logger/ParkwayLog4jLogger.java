
package com.parkway.core.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

import com.parkway.core.config.Constants;
import com.parkway.core.context.RequestContext;
import com.parkway.core.context.RequestHeaderPO;


public class ParkwayLog4jLogger extends Log4jLogger implements ELogger{

	public ParkwayLog4jLogger(Logger logger) {
		super(logger);
	}

	@Override
	public void debug(String msg) {
		appendContext();
		logger.debug(msg);
		NDC.pop();
	}

	@Override
	public void debug(String msg, Throwable t) {
		appendContext();
		logger.debug(msg, t);
		NDC.pop();
	}

	@Override
	public void info(String msg) {
		appendContext();
		logger.info(msg);
		NDC.pop();
	}

	@Override
	public void info(String msg, Throwable t) {
		appendContext();
		logger.info(msg, t);
		NDC.pop();
	}

	@Override
	public void warn(String msg) {
		appendContext();
		logger.warn(msg);
		NDC.pop();
	}

	@Override
	public void warn(String msg, Throwable t) {
		appendContext();
		logger.warn(msg, t);
		NDC.pop();
	}

	@Override
	public void error(String msg) {
		appendContext();
		logger.error(msg);
		NDC.pop();
	}

	@Override
	public void error(String msg, Throwable t) {
		appendContext();
		logger.error(msg, t);
		NDC.pop();
	}

	@Override
	public void fatal(String msg) {
		appendContext();
		logger.fatal(msg);
		NDC.pop();
	}

	@Override
	public void fatal(String msg, Throwable t) {
		appendContext();
		logger.fatal(msg, t);
		NDC.pop();
	}

	public void appendContext(){
		StringBuffer buffer = new StringBuffer();

		if(RequestContext.get() != null){
			RequestHeaderPO requestHeaderPO = (RequestHeaderPO) RequestContext.get().get(Constants.PARKWAY_REQUEST_CONTEXT);
			if(requestHeaderPO!=null) {
				buffer.append(requestHeaderPO.getUserId()).append(",").append(requestHeaderPO.getClientIP());
			}
		}

		NDC.push(buffer.toString());
	}
}
