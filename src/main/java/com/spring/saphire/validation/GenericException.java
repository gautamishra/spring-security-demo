package com.spring.saphire.validation;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {
	private static final long serialVersionUID = 100L;
	private String message;
	private HttpStatus httpStatus;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
		this.message = message;
	}

	public GenericException(String message, HttpStatus status) {
		this(message);
		this.message = message;
		this.httpStatus = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
