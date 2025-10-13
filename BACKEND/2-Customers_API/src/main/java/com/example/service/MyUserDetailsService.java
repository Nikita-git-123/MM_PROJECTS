package com.example.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entities.Customer;
import com.example.repo.CustomerRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer byEmail = customerRepo.findByEmail(email);
		return new User(byEmail.getEmail(), byEmail.getPassword(), Collections.emptyList());
	}

}
