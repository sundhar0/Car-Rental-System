package com.api.carrental.Exception;

public class InvalidBrandException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidBrandException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
