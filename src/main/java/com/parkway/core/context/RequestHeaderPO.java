package com.parkway.core.context;

import java.io.Serializable;

public class RequestHeaderPO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId = "";
	private String clientIP = "";
	
	public RequestHeaderPO(){		
	}
	
	public RequestHeaderPO(String userId, String clientIP){
		setClientIP(clientIP);
		setUserId(userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	@Override
	public String toString() {
		return "RequestHeaderPO [userId=" + userId + ", clientIP=" + clientIP + "]";
	}
	
	

}
