package com.spring.saphire.service;

import java.util.Locale;

import com.spring.saphire.DTO.UserDTO;
import com.spring.saphire.modal.User;
import com.spring.saphire.validation.EmailExistsException;

public interface IUserServcie {

	User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;

	void createVerifactionToken(User user, String token);

	String confirmRegistration(String token, Locale locale);
}
