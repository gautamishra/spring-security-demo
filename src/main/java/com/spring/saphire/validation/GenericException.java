package com.spring.saphire.validation;

public class GenericException extends Exception {
	private String message;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}


}
