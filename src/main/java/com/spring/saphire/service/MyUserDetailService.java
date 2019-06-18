package com.spring.saphire.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.saphire.modal.Role;
import com.spring.saphire.modal.User;
import com.spring.saphire.repository.UserRepository;

public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + email);
		}
		System.out.println(user);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnable(), true, true, true, getAuthorities(user.getRoles()));
	}

	private final Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
