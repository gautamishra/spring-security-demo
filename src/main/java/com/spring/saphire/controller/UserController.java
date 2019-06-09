package com.spring.saphire.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.saphire.DTO.UserDTO;
import com.spring.saphire.events.OnRegistrationCompleteEvent;
import com.spring.saphire.modal.User;
import com.spring.saphire.modal.VerificationToken;
import com.spring.saphire.service.IUserServcie;
import com.spring.saphire.utilities.MailUtility;
import com.spring.saphire.utilities.RequestUtility;
import com.spring.saphire.validation.EmailExistsException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private MailUtility mailUtility;

	@Autowired
	private IUserServcie usrService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private MailSender mailSernder;

	@Autowired
	private MessageSource messages;

	@PostMapping("/user/register")
	public User registerUserAccount(@RequestBody @Valid UserDTO userInfo, HttpServletRequest request)
			throws EmailExistsException {
		User user = usrService.registerNewUserAccount(userInfo);
		System.out.println(user.getEmail());
		if(user != null) {			
			Runnable r = () -> {
				eventPublisher
				.publishEvent(new OnRegistrationCompleteEvent(user, request.getContextPath(), request.getLocale()));
			};
			Thread emailThread = new Thread(r);
			emailThread.start();
		}
		return user;
	}

	@GetMapping("/registration-confirm")
	public String registrationConfirmation(@RequestParam("token") String token, HttpServletRequest request) {
		return usrService.confirmRegistration(token, request.getLocale());
	}

	@GetMapping("/get_data")
	public String registerUserAccount1(Principal principal) {
//		.out.println(principal.getName());
		return "dsadad";
	}

	@GetMapping("/user/resendRegistrationToken")
	public String resendVerificationMail(@RequestParam("token") String token, HttpServletRequest request) {
		VerificationToken newToken = usrService.generateNewVerficationToken(token);
		mailSernder.send(mailUtility.constructResendVerificationTokenEmail(RequestUtility.getAppUrl(request), request.getLocale(),
				newToken,
				newToken.getUser()));
		
		return messages.getMessage("message.resendToken", null, request.getLocale());
	}

}
