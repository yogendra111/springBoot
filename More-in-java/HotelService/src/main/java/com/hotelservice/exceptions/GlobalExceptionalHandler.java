package com.hotelservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

	public ResponseEntity<Map<String, Object>> resourceNotFoundExceptionHandler(Exception e){
		Map<String, Object> map  = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
	
}
