package com.parkway.core.parser.dataobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceConfiguration {
	
	private String name;
	private String description;
	private String sessionCategory;
	private List<InputConfiguration> inputConfigurations = new ArrayList<InputConfiguration>();
	
	public ServiceConfiguration() {
	}
	
	public ServiceConfiguration(JSONObject jsonServiceConfig) throws JSONException {
		this();
		
		String name = jsonServiceConfig.getString("name");
		String sessionCategory = jsonServiceConfig.getString("sessionCategory");
		String description = jsonServiceConfig.optString("description", "");
		
		JSONArray inputsArray = jsonServiceConfig.optJSONArray("inputs");
		ArrayList<InputConfiguration> tempList = new ArrayList<InputConfiguration>();
		for (int i = 0; i < inputsArray.length(); i++) {
			JSONObject jsonInput = inputsArray.optJSONObject(i);
			InputConfiguration inputConfiguration = new InputConfiguration(jsonInput);
			tempList.add(inputConfiguration);
		}
		
		setName(name);
		setDescription(description);
		setSessionCategory(sessionCategory);
		setInputConfigurations(tempList);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSessionCategory() {
		return sessionCategory;
	}
	public void setSessionCategory(String sessionCategory) {
		this.sessionCategory = sessionCategory;
	}
	public List<InputConfiguration> getInputConfigurations() {
		return inputConfigurations;
	}
	public void setInputConfigurations(List<InputConfiguration> inputConfigurations) {
		this.inputConfigurations = inputConfigurations;
	}
	
	@Override
	public String toString() {
		return "ServiceConfiguration [name=" + name + ", description=" + description + ", sessionCategory="
				+ sessionCategory + ", inputConfigurations=" + inputConfigurations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceConfiguration other = (ServiceConfiguration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
