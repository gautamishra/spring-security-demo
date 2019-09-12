package com.spring.saphire.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.saphire.security.ApplicationAuthenticationSuccessHandler;
import com.spring.saphire.security.CustomAuthenticationFailureHandler;
import com.spring.saphire.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SetSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private ApplicationAuthenticationSuccessHandler applicationAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	private AuthenticationEntryPoint restAuthenticationEntryPoint;

	
	@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        // @formatter:off
	        http
	        .cors()
	        .and()
	        .csrf()
	        .disable()
				.authorizeRequests()
				.antMatchers("/api/register", "/api/registration-confirm", "/api/resendRegistrationToken").permitAll()
				.antMatchers("/api/user/*").hasRole("USER")
				.and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.formLogin()
				.successHandler(applicationAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
	            .and()
	            .logout();
	 }


//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * register custom user detail service and password encoder into
	 * AuthenticationManagerBuilder
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	/**
	 * register custom user detail service and password encoder into
	 * AuthenticationManagerBuilder
	 */
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(
				Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
