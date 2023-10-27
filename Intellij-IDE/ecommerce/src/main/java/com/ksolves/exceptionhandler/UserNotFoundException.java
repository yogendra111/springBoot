package com.ksolves.exceptionhandler;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

    UserNotFoundException(String msg){
        super(msg);
    }
    public UserNotFoundException(){
        super("Given user is not registered.");
    }
}
