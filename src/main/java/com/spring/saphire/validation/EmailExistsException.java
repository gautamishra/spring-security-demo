package com.spring.saphire.validation;

public class EmailExistsException extends Exception {
	public EmailExistsException(String message) {
		super(message);
	}
}
