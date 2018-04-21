package com.parkway.core.util;

import java.util.ArrayList;

import com.parkway.core.dataobject.Param;
import com.parkway.core.dataobject.Record;
import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;

public class Utilities {
	
	private static final ELogger log = ELoggerFactory.getLogger(Utilities.class);
	
	public static void printDebugRecordParams(String description, Record record) {
		
		if(record==null || (!log.isDebugEnabled()))
			return;
		
		ArrayList<Param> paramList =  record.getParams();
		for (int i = 0; i < paramList.size(); i++) {
			Param param = paramList.get(i);
			log.debug(new StringBuilder(description).append("Name>>>").append(param.getName()).toString());
			log.debug(new StringBuilder(description).append("Value>>").append(param.getValue()).toString());
		}
	}

}
