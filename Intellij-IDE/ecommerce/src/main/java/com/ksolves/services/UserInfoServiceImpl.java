package com.ksolves.services;

import com.ksolves.entities.UserInfo;
import com.ksolves.exceptionhandler.UserAlreadyExistsException;
import com.ksolves.exceptionhandler.UserNotFoundException;
import com.ksolves.mapper.UserMapper;
import com.ksolves.payloads.RegisterRequest;
import com.ksolves.payloads.UpdateUserLockRequest;
import com.ksolves.payloads.UserResponse;
import com.ksolves.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(RegisterRequest registerRequest){
        if(userRepository.existsByName(registerRequest.getName())){
            throw new UserAlreadyExistsException();
        }
        userRepository.save(userMapper.convertRegisterRequesttoUserInfo(registerRequest));
//        return new UserResponse(registerRequest.getName(), "User added Successfully");
    }

    @Override
    public void changeUserLockSetting(UpdateUserLockRequest updateRequest) {
        if(!userRepository.existsByName(updateRequest.getUsername())){
            throw new UserNotFoundException();
        }
        UserInfo user = userRepository.findByName(updateRequest.getUsername()).get();
        user.setIsUserLocked(updateRequest.getIsLocked());
        userRepository.save(user);
    }

}
