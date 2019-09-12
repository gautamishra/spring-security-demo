package com.spring.saphire.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class ApiExceptionHandler {

	/**
	 * Exception handler for APIException
	 */
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ErrorDTO> handleInvalidDataException(GenericException e) {
		System.out.println("printing exceptio");
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(e.getMessage()), e.getHttpStatus());
	}

	/**
	 * Exception handler for any Exception or NullPointerException
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> handleException(Exception e) {
		String errorMessage = e.getMessage();
		if (e instanceof NullPointerException) {
			errorMessage = errorMessage != null ? errorMessage : "INTERNAL_SERVER_ERROR";
		}
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
