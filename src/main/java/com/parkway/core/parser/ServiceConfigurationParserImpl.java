package com.parkway.core.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parkway.core.config.Constants;
import com.parkway.core.exception.ProcessingException;
import com.parkway.core.logger.ELogger;
import com.parkway.core.logger.ELoggerFactory;
import com.parkway.core.parser.dataobject.ServiceConfiguration;

public class ServiceConfigurationParserImpl implements ServiceConfigurationParser{
	
	private static final ELogger log = ELoggerFactory.getLogger(ServiceConfigurationParserImpl.class);

	public Map<String, ServiceConfiguration> getServiceConfigurations() {
		String serviceConfigurationJSON = null;
		JSONArray jsonArray = null;
		Map<String, ServiceConfiguration> serviceConfigurationsMap = new HashMap<String, ServiceConfiguration>();
		try {
			String serviceConfigurationFile = Constants.SERVICE_CONFIG_KEY_FILE_PATH;
			serviceConfigurationJSON = readConfigurationFile(serviceConfigurationFile);
			
			if(serviceConfigurationJSON==null || serviceConfigurationJSON.trim().equals("")) {
				log.fatal("getServiceConfigurations Service Config file not found/ Invalid:::");
				throw new ProcessingException("Service Configuration Not Found");
			}
			
			jsonArray = new JSONArray(serviceConfigurationJSON);
			
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jsonObject = jsonArray.optJSONObject(i);
				
				ServiceConfiguration seviceConfig = new ServiceConfiguration(jsonObject);
				
				if(log.isDebugEnabled()) {
					log.debug("=======================================================SERVICE CONFIG=======================================================");
					log.debug(seviceConfig.toString());
					log.debug("=======================================================SERVICE CONFIG=======================================================");
				}
				
				String serviceName = seviceConfig.getName();
				
				serviceConfigurationsMap.put(serviceName, seviceConfig);
			}
		
			return serviceConfigurationsMap;
			
		}catch(JSONException e) {
			log.fatal("getServiceConfigurations Invalid Service Config file");
			throw new ProcessingException("Invalid Service Configuration File");
		}
		
	}
	
	private String readConfigurationFile(String filename) {
		StringBuffer records = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.append(line);
			}
			return records.toString();
		} catch (Exception e) {
			log.error("readConfigurationFile>>>", e);
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("readConfigurationFile>>FileClose>>", e);
				}
			}
		}
	}

}
