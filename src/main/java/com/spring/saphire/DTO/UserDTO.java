package com.spring.saphire.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spring.saphire.validation.ValidEmail;

public class UserDTO {

	@NotNull
	@NotEmpty
	private String firstname;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String lastname;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
