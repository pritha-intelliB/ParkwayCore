package com.parkway.core.txnhandler.connector;

import com.parkway.core.exception.ProcessingException;

public abstract interface EConnection {
	
	  public abstract void close();

	  public abstract Object post(Object paramObject) throws ProcessingException;

}
