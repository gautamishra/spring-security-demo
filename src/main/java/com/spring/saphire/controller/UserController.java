package com.spring.saphire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.saphire.DTO.UserDTO;
import com.spring.saphire.events.OnRegistrationCompleteEvent;
import com.spring.saphire.modal.User;
import com.spring.saphire.service.IUserServcie;
import com.spring.saphire.validation.EmailExistsException;

@RestController
public class UserController {

	@Autowired
	private IUserServcie usrService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;


	@PostMapping("/user/register")
	public User registerUserAccount(@RequestBody @Valid UserDTO userInfo, HttpServletRequest request)
			throws EmailExistsException {
		User user = usrService.registerNewUserAccount(userInfo);
		System.out.println(user.getEmail());
		if(user != null) {
			eventPublisher
					.publishEvent(new OnRegistrationCompleteEvent(user, request.getContextPath(), request.getLocale()));
		}
		return user;
	}

	@GetMapping("/get_data")
	public String registerUserAccount1() {
		return "hhdfhjdvdjjdvs";
	}

}
