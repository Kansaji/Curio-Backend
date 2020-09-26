package com.curio.curioapp.curioappbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.LoginRequest;
import com.curio.curioapp.curioappbackend.dto.RegisterRequest;
import com.curio.curioapp.curioappbackend.model.User;
import com.curio.curioapp.curioappbackend.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setTelephone(registerRequest.getTelephone());
		userRepository.save(user);
	}
	
	public void login(LoginRequest loginRequest) {
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
	}
	
	//password encoder
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
}
