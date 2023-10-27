package com.ksolves.exceptionhandler;

public class ProductAlreadyExistsException extends RuntimeException{
    ProductAlreadyExistsException(String msg){
        super(msg);
    }
    ProductAlreadyExistsException(){
        super("Product already exists");
    }
}
