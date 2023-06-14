package com.demoboot.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({BookNotFoundException.class})
	public ResponseEntity<ErrorDetails> bookNotFound(Exception exception){
		ErrorDetails errorDetails = new ErrorDetails("1001", exception.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({InvalidSysIPException.class})
	public ResponseEntity<ErrorDetails> sysIpNotFound(Exception exception){
		ErrorDetails errorDetails = new ErrorDetails("1002", exception.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	
}
