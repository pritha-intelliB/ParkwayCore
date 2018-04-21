package com.parkway.core.exception;

public class ProcessingException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ProcessingException(){
		super();
	}

	public ProcessingException(String message){
		super(message);
	}

	public ProcessingException(Throwable e){
		super(e);
	}
	
	public ProcessingException(String message, Throwable e){
		super(message, e);
	}

}
