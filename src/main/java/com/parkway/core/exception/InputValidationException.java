package com.parkway.core.exception;

public class InputValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InputValidationException(){
		super();
	}

	public InputValidationException(String message){
		super(message);
	}

	public InputValidationException(Throwable e){
		super(e);
	}
	
	public InputValidationException(String message, Throwable e){
		super(message, e);
	}

}
