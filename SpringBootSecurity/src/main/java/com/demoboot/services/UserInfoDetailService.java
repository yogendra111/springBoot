package com.demoboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demoboot.dto.UserInfoDetail;
import com.demoboot.entities.UserInfo;
import com.demoboot.repositories.UserInfoRepo;

@Component
public class UserInfoDetailService implements UserDetailsService {
	
	@Autowired
	UserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userInfoRepo.findByName(username);
		return userInfo.map(UserInfoDetail::new).orElseThrow(()->new UsernameNotFoundException("UserNotFound"+ username));
	}

}
