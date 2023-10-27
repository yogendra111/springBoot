package com.ksolves.services;

import com.ksolves.entities.UserInfo;
import com.ksolves.exceptionhandler.UserNotFoundException;
import com.ksolves.models.UserDetailsImpl;
import com.ksolves.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException()
        );
//        System.out.println("user = " + user);
        return UserDetailsImpl.build(user);
    }
}
