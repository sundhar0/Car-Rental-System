package com.api.carrental.Config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.carrental.Exception.InvalidFuelException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidModelException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.InvalidYearException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

	@ExceptionHandler(InvalidUserNameException.class)
	public ErrorResponse invalidUsernameExceptionHandler(InvalidUserNameException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(LicenseNoAlreadyAssigned.class)
	public ErrorResponse licenseNoAlreadyAssignedHandler(LicenseNoAlreadyAssigned e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(InvalidModelException.class)
	public ErrorResponse InvalidModelException(InvalidModelException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(InvalidYearException.class)
	public ErrorResponse InvalidYearException(InvalidYearException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(InvalidFuelException.class)
	public ErrorResponse InvalidFuelException(InvalidFuelException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse InvalidIDException(InvalidIDException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
}
