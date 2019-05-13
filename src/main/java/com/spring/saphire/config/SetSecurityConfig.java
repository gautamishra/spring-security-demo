package com.spring.saphire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SetSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        // @formatter:off
	        http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/user/register", "/registration-confirm", "/user/resendRegistrationToken")
	            .permitAll();
	 }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
