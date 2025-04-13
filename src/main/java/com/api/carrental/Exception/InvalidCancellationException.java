package com.api.carrental.Exception;

public class InvalidCancellationException extends Exception{
	private static final long serialVersionUID = 1L;
	 
 	private String message;

	public InvalidCancellationException(String message) {
		super();
		this.message = message;
	}
 	
	@Override
 	public String getMessage() {
 		return message;
 	}

}
