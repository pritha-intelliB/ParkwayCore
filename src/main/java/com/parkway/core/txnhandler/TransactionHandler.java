package com.parkway.core.txnhandler;

public interface TransactionHandler {
	
	public Object processRequest (Object httprequest, Object requestRecord, String serviceId) ;		

}
