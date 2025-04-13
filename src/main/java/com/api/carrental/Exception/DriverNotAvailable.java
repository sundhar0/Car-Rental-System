package com.api.carrental.Exception;

public class DriverNotAvailable extends Exception{
	private static final long serialVersionUID = 1L;

	private String message;

	

	public DriverNotAvailable(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}
}
