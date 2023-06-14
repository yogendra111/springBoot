package com.demoboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoboot.dto.AuthRequest;
import com.demoboot.entities.UserInfo;
import com.demoboot.services.JWTService;
import com.demoboot.services.UserService;

@RestController
public class AuthController {

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("authenticate")
	public String authAndGetToken(@RequestBody AuthRequest authRequest) {
		return jwtService.generateToken(authRequest.getUsername());
	}
	
	@PostMapping("/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(userInfo); 
	}
	
}
