package com.parkway.core.parser.dataobject;

import org.json.JSONException;
import org.json.JSONObject;

public class InputConfiguration {
	
	private String name;
	private String type;
	private int minLength;
	private int maxLength;
	private boolean isMandatory;
	private boolean validate;
	
	public InputConfiguration() {
		
	}
	
	public InputConfiguration(JSONObject jsonInputConfig) throws JSONException {
		// Mandatory fields
		String name = jsonInputConfig.getString("name");
		String type = jsonInputConfig.getString("type");
		String isMandatory = jsonInputConfig.optString("isMandatory", "false");
		String validate = jsonInputConfig.optString("validate", "false");
		
		// Optional fields
		int minLength = jsonInputConfig.optInt("minLength", 0);
		int maxLength = jsonInputConfig.optInt("maxLength", 0);		
		
		setName(name);
		setType(type);
		setValidate("true".equals(validate)? true : false);
		setIsMandatory("true".equals(isMandatory)? true : false);
		setMinLength(minLength);
		setMaxLength(maxLength);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public boolean getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	@Override
	public String toString() {
		return "InputConfiguration [name=" + name + ", type=" + type +", validate=" + validate + ", minLength=" + minLength + ", maxLength="
				+ maxLength + ", isMandatory=" + isMandatory + "]";
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
		InputConfiguration other = (InputConfiguration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
