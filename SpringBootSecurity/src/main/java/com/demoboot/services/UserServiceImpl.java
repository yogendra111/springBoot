package com.demoboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demoboot.entities.UserInfo;
import com.demoboot.repositories.UserInfoRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserInfoRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepo.save(userInfo);
		return "User Added to DB";
	}
	
}
