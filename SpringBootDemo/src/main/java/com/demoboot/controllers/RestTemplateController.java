package com.demoboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoboot.services.RestTemplateService;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {
	
	@Autowired
	private RestTemplateService restTemplateService;
	
	@GetMapping("/getbooks")
	public ResponseEntity<String> getAllBooks(){
		return restTemplateService.allBook();
	}

}
