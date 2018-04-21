package com.parkway.core.serviceconfig;

import java.util.Iterator;
import java.util.List;

import com.parkway.core.config.Constants;
import com.parkway.core.dataobject.Record;
import com.parkway.core.exception.InputValidationException;
import com.parkway.core.parser.dataobject.InputConfiguration;
import com.parkway.core.parser.dataobject.ServiceConfiguration;
import com.parkway.core.util.DataObjectUtil;

public class ServiceInputValidator {
	
	public static void validateServiceInputs (Record requestRecord) {
		String serviceId = DataObjectUtil.getStringParamValue(requestRecord, Constants.SERVICE_ID);
		
		if(serviceId==null || serviceId.trim().equals("")) {
			throw new InputValidationException("Invalid ServiceId or ServiceId Not in the request");
		}
			
		ServiceConfiguration serviceConfig = ServiceConfigurationManager.getServiceConfig(serviceId);
			
		if(serviceConfig==null) {
			throw new InputValidationException("No Service Config for the service id");
		}
		
		List<InputConfiguration> inputConfigurations = serviceConfig.getInputConfigurations();
		
		if(inputConfigurations != null && (!inputConfigurations.isEmpty())) {
			for (Iterator iterator = inputConfigurations.iterator(); iterator.hasNext();) {
				InputConfiguration inputConfiguration = (InputConfiguration) iterator.next();
				String paramName = inputConfiguration.getName();
				String requestValue = DataObjectUtil.getStringParamValue(requestRecord, paramName);
				if(inputConfiguration.getValidate()) {
					// Mandatory check
					if(inputConfiguration.getIsMandatory()) {
						if(requestValue==null || requestValue.trim().equals("")) {
							throw new InputValidationException("Mandatory Paramter missing/invalid "+paramName);
						}
					}
					String type = inputConfiguration.getType();
					if("N".equals(type)) {
						try {
							Long.parseLong(requestValue);
						}catch (NumberFormatException e) {
							throw new InputValidationException("Paramter type invalid "+paramName);
						}
					}else if("D".equals(type)) {
						try {
							Double.parseDouble(requestValue);
						}catch (NumberFormatException e) {
							throw new InputValidationException("Paramter type invalid "+paramName);
						}
					}
					int minimumLen = inputConfiguration.getMinLength();
					if(minimumLen != 0 && requestValue.length() < minimumLen) {
						throw new InputValidationException("Paramter value length too short "+paramName);
					}
					
					int maxLen = inputConfiguration.getMaxLength();
					if(maxLen != 0 && requestValue.length() > maxLen) {
						throw new InputValidationException("Paramter value length too long "+paramName);
					}
				}
			}
		}
		
	}

}
