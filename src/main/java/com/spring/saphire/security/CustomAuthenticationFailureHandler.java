package com.spring.saphire.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.spring.saphire.validation.GenericException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private MessageSource messages;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		Locale locale = request.getLocale();
		String errorMessage = messages.getMessage("message.badCredentials", null, locale);
		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = messages.getMessage("auth.message.disabled", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = messages.getMessage("auth.message.expired", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = messages.getMessage("auth.message.blocked", null, locale);
		}

//		new ObjectMapper().writeValue(response.getOutputStream(), new ErrorDTO(errorMessage));
		throw new GenericException(errorMessage, HttpStatus.UNAUTHORIZED);
		
	}
}
