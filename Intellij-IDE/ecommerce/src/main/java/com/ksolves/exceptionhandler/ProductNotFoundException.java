package com.ksolves.exceptionhandler;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg){
        super(msg);
    }
    public ProductNotFoundException(){
        super("Product not found.");
    }
}
