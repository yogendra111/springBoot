package com.demoboot.services;


import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demoboot.entities.Book;

@Service
public class RestTemplateService {
	
	private RestTemplate restTemplate = new RestTemplate();
	private static final String get_All_Books = "http://localhost:8080/getallbooks";
	
	public ResponseEntity<String> allBook() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> response = restTemplate.exchange(get_All_Books, HttpMethod.GET, entity, String.class);
		return response;
		
	}
	
}
