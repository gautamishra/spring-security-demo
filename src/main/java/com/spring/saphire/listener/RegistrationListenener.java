package com.spring.saphire.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.spring.saphire.events.OnRegistrationCompleteEvent;
import com.spring.saphire.modal.User;
import com.spring.saphire.service.IUserServcie;

@Component
public class RegistrationListenener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private IUserServcie service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		System.out.println("In Listener");
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		service.createVerifactionToken(user, token);
		String recipientAddress = user.getEmail();
		String subject = "Registration Confirmation";
		String confirmationUrl = "/regitrationConfirm.html?token=" + token;
		String message = messages.getMessage("message.regSucc", null, event.getLocale());
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
		mailSender.send(email);
	}
}
