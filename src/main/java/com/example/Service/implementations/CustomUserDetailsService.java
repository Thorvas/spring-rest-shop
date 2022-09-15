package com.example.Service.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.Entities.Users;
import com.example.Repositories.UserRepository;
import com.example.Security.configuration.MyUserDetails;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Bean
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		Users user = userRepository.findByUsername(username);
		System.out.println("Found username is: "+username);
		return new MyUserDetails(user);
	}
	
}
