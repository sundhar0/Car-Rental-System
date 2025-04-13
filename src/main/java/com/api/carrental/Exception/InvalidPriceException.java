package com.api.carrental.Exception;

public class InvalidPriceException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidPriceException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}

}
