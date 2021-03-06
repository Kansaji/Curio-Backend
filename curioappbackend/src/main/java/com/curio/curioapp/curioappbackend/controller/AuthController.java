package com.curio.curioapp.curioappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curio.curioapp.curioappbackend.dto.AuthenticationResponse;
import com.curio.curioapp.curioappbackend.dto.LoginRequest;
import com.curio.curioapp.curioappbackend.dto.RegisterRequest;
import com.curio.curioapp.curioappbackend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) {
		boolean success = authService.signup(registerRequest);
		if(success) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
		
	}

}
