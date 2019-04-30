package com.spring.saphire.events;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.spring.saphire.modal.User;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private User user;
	private final String appUrl;
	private Locale locale;

	public OnRegistrationCompleteEvent(User user, String url, Locale locale) {
		super(user);
		System.out.println("In Event");
		this.user = user;
		this.appUrl = url;
		this.locale = locale;
		System.out.println(user + " " + user + " " + locale.getCountry());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
