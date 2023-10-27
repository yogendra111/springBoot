package com.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userservice.payloads.ApiReponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiReponse> handleResourceNotFoundException(Exception e) {
		ApiReponse response = ApiReponse.builder().message(e.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiReponse>(response, HttpStatus.NOT_FOUND);
	}
	
}
