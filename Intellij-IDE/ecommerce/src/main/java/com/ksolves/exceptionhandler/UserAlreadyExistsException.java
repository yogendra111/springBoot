package com.ksolves.exceptionhandler;

public class UserAlreadyExistsException extends RuntimeException{

    UserAlreadyExistsException(String msg){
        super(msg);
    }
    public UserAlreadyExistsException(){
        super("Given user name already being taken.");
    }
}
