package com.curio.curioapp.curioappbackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AuthenticationResponse;
import com.curio.curioapp.curioappbackend.dto.LoginRequest;
import com.curio.curioapp.curioappbackend.dto.RegisterRequest;
import com.curio.curioapp.curioappbackend.model.User;
import com.curio.curioapp.curioappbackend.repository.UserRepository;
import com.curio.curioapp.curioappbackend.security.JwtProvider;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	
	public boolean signup(RegisterRequest registerRequest) {
		
		Optional<User> tempUser=userRepository.findByUsername(registerRequest.getUsername());
		if(tempUser.isPresent() && tempUser.get().getUsername().equals(registerRequest.getUsername())) {
			return false;
		}else {
			User user = new User();
			user.setUsername(registerRequest.getUsername());
			user.setPassword(encodePassword(registerRequest.getPassword()));
			user.setEmail(registerRequest.getEmail());
			user.setTelephone(registerRequest.getTelephone());
			userRepository.save(user);
			return true;
		}
	}
	
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
	}
	
	//password encoder
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principle = (org.springframework.security.core.userdetails.User)
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(principle);
	}
}
