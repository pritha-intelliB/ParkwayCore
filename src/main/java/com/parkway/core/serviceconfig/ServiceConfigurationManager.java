package com.parkway.core.serviceconfig;

import java.util.HashMap;
import java.util.Map;

import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;
import com.parkway.core.parser.ServiceConfigurationParser;
import com.parkway.core.parser.ServiceConfigurationParserImpl;
import com.parkway.core.parser.dataobject.ServiceConfiguration;

public class ServiceConfigurationManager {
	
	private static final ELogger log = ELoggerFactory.getLogger(ServiceConfigurationManager.class);
	
	private static Map<String, ServiceConfiguration> serviceConfigurationMap = new HashMap<String, ServiceConfiguration>();

	public static void loadServiceConfigurations() {
		if(serviceConfigurationMap.isEmpty()) {
			log.info("loadServiceConfigurations::Started");
			ServiceConfigurationParser parser = new ServiceConfigurationParserImpl();
			Map<String, ServiceConfiguration> serviceConfigurationsRefreshed = parser.getServiceConfigurations();
			serviceConfigurationMap.clear();
			serviceConfigurationMap.putAll(serviceConfigurationsRefreshed);
			log.info("loadServiceConfigurations::Done");
		}		
	}
	
	public static void refreshServiceConfigurations() {
		serviceConfigurationMap.clear();
		loadServiceConfigurations();
	}
	
	public static ServiceConfiguration getServiceConfig (String serviceId) {
		return serviceConfigurationMap.get(serviceId);
	}
	
	public boolean isValidServiceId (String serviceId) {
		boolean isValid = false;
		if(getServiceConfig(serviceId)!=null) {
			isValid = true;
		}
		return isValid;
	}
}
