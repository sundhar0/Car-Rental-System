package com.api.carrental.Config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

	@ExceptionHandler(InvalidUserNameException.class)
	public ErrorResponse invalidUsernameExceptionHandler(InvalidUserNameException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse invalidIDExceptionHandler(InvalidIDException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(LicenseNoAlreadyAssigned.class)
	public ErrorResponse licenseNoAlreadyAssignedHandler(LicenseNoAlreadyAssigned e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(DriverNotAvailable.class)
	public ErrorResponse driverNotAvailableHandler(DriverNotAvailable e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
}
