package com.parkway.core.parser;

import java.util.Map;

import com.parkway.core.parser.dataobject.ServiceConfiguration;

public interface ServiceConfigurationParser {
	
	public Map<String, ServiceConfiguration> getServiceConfigurations();

}
