package com.demoboot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Configuration
public class Student {
	private Integer id;
	private String name;
	private String course;
	private String mobileNo;
	
	@PostConstruct
	public void init() {
		System.out.println("Init method in Student Entity.");
	}
	
}
