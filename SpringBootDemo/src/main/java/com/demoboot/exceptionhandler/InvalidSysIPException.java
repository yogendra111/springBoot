package com.demoboot.exceptionhandler;

public class InvalidSysIPException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidSysIPException(String msg) {
		super(msg);
	}
	
}
