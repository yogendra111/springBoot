package com.ksolves.mapper;

import com.ksolves.entities.Role;
import com.ksolves.entities.UserInfo;
import com.ksolves.payloads.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo convertRegisterRequesttoUserInfo(RegisterRequest registerRequest){
        UserInfo user = new UserInfo();
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        user.setRole(Role.valueOf(registerRequest.getRole()));
        user.setRoles(registerRequest.getRoles().stream().map(Role::valueOf).collect(Collectors.toList()));
        user.setIsUserLocked(false);
        return user;
    }

}
