package com.parkway.core.datahandler;

public interface BaseDataHandler {
	
	public Object requestPreProcessor(Object httprequest, Object requestRecord);
	
	public Object formatRequest(Object requestRecord);
	
	public Object formatResponse(Object responseResult);
	
	public Object responsePostProcessor(Object httprequest, Object responseResult);

}
