package com.ekalavya.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {

	@Value("${application.userName}")
	private String userName;

	@Value("${application.password}")
	private String password;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userName.equals(username)) {
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//String password = passwordEncoder.encode(rawPassword);
			List<SimpleGrantedAuthority> roles = new ArrayList<>();
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin");
			roles.add(simpleGrantedAuthority);
			return new User(userName, password, roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
