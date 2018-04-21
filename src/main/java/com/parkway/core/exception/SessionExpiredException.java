package com.parkway.core.exception;

public class SessionExpiredException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public SessionExpiredException(){
		super();
	}

	public SessionExpiredException(String message){
		super(message);
	}

	public SessionExpiredException(Throwable e){
		super(e);
	}
	
	public SessionExpiredException(String message, Throwable e){
		super(message, e);
	}

}
