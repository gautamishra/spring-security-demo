package com.spring.saphire.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password;
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enable;
	private long userid;

	public ApplicationUser() {
		// Default constructor
	}

	public ApplicationUser(String username, String password, boolean enable, long userId,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.authorities = authorities;
		this.userName = username;
		this.password = password;
		this.userid = userId;
		this.enable = enable;
	}

	// Overriding clone() method of Object class
	public Object clone() throws CloneNotSupportedException {
		return (ApplicationUser) super.clone();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public void setPassword() {
		password = null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enable;
	}

	public long getUserid() {
		return userid;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
