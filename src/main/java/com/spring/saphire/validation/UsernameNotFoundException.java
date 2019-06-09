package com.spring.saphire.validation;

public class UsernameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	UsernameNotFoundException() {
		super();
	}

	UsernameNotFoundException(final String message) {
		super(message);
	}

}
