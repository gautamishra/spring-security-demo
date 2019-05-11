package com.spring.saphire.utilities;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.spring.saphire.modal.User;
import com.spring.saphire.modal.VerificationToken;

@Component
public class MailUtility {

	@Autowired
	private MessageSource messages;


	public SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale,
			final VerificationToken newToken, final User user) {
		final String confirmationUrl = contextPath + "/user/resendRegistrationToken?token=" + newToken.getToken();
		final String message = messages.getMessage("message.resendToken", null, locale);
		return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
	}


	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		return email;
	}
}
