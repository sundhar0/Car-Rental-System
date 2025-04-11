package com.api.carrental.Config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.carrental.Exception.InvalidUserNameException;
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
}
