package com.spring.saphire.service;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {
		if (emailExists(accountDto.getEmail())) {
	            throw new EmailExistsException(
					"There is an account with that email address:"  + accountDto.getEmail());
	        }

		User user = new User();
		user.setFirstName(accountDto.getFirstname());
		user.setLastName(accountDto.getLastname());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		if(accountDto.getDateOfBirth() != null) {
			user.setDateOfBirth(accountDto.getDateOfBirth());
		}
		return userRepository.save(user);
	}

	boolean emailExists(String email) {
		User user = userRepository.findByEmail(email);
		return (user == null) ? false : true;
	}

	@Override
	public void createVerifactionToken(User user, String token) {
		VerificationToken vertoken = new VerificationToken(user, token);
		tokenRepository.saveAndFlush(vertoken);
	}

	@Override
	public String confirmRegistration(String token, Locale locale) {
		VerificationToken verfictaionToken = tokenRepository.findByToken(token);
		User user = verfictaionToken.getUser();
		String message = "";
		Calendar cal = Calendar.getInstance();
		System.out.println("current time " + cal.getTime());
		System.out.println("expire time " + verfictaionToken.getExpiryDate());

		if ((verfictaionToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			message = messageSource.getMessage("auth.message.expired", null, locale) + "\n";
			message += "click on below to genrate confirmation ink again \n";
			message += "localhost:8080/user/resendRegistrationToken/" + token;
			return message;
		} else {
			user.setEnable(true);
			userRepository.saveAndFlush(user);
			return messageSource.getMessage("auth.message.confirmRegistration" , null, locale);
		}

	}

	@Override
	public VerificationToken generateNewVerficationToken(String token) {
		VerificationToken verificationToken = tokenRepository.findByToken(token);
		verificationToken.updateToken(UUID.randomUUID().toString());
		tokenRepository.saveAndFlush(verificationToken);
		return verificationToken;
	}

}
