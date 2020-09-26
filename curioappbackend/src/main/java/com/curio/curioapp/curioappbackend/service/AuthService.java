package com.curio.curioapp.curioappbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.RegisterRequest;
import com.curio.curioapp.curioappbackend.model.User;
import com.curio.curioapp.curioappbackend.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	UserRepository userRepository;
	
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUserName());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());
		user.setTelephone(registerRequest.getTelephone());
		userRepository.save(user);
	}

}
