package com.spring.saphire.service;

import com.spring.saphire.DTO.UserDTO;
import com.spring.saphire.modal.User;
import com.spring.saphire.validation.EmailExistsException;

public interface IUserServcie {

	User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;

	void CreateVerifactionToken(User user, String token);
}
