package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.AuthRepository;

@Service
public class MyUserService implements UserDetailsService{
	
	@Autowired
	private AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authRepository.findByUsername(username);
	}

}
