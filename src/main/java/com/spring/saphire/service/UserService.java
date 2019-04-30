package com.spring.saphire.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.saphire.DTO.UserDTO;
import com.spring.saphire.modal.User;
import com.spring.saphire.modal.VerificationToken;
import com.spring.saphire.repository.RoleRepository;
import com.spring.saphire.repository.TokenRepositiory;
import com.spring.saphire.repository.UserRepository;
import com.spring.saphire.validation.EmailExistsException;

@Service
public class UserService implements IUserServcie {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TokenRepositiory tokenRepository;

	@Transactional
	@Override
	public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {
		if (emailExists(accountDto.getEmail())) {
	            throw new EmailExistsException(
					"There is an account with that email address:  + accountDto.getEmail()");
	        }

		User user = new User();
		user.setFirstName(accountDto.getFirstname());
		user.setLastName(accountDto.getLastname());
		user.setPassword(accountDto.getPassword());
		user.setEmail(accountDto.getEmail());
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		return userRepository.save(user);
	}

	boolean emailExists(String email) {
		User user = userRepository.findByEmail(email);
		return (user == null) ? false : true;
	}

	@Override
	public void CreateVerifactionToken(User user, String token) {
		VerificationToken vertoken = new VerificationToken(user, token);
		tokenRepository.saveAndFlush(vertoken);
	}
}
