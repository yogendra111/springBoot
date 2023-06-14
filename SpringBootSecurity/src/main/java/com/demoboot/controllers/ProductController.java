package com.demoboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoboot.services.ProductService;

@RestController
@RequestMapping("/router")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/welcome")
	public String Welcome() {
		return "Welcome Bro";
	}
	
}
