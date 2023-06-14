package com.demoboot.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class BookServiceInterceptorAppConfig implements WebMvcConfigurer{
	
	@Autowired
	BookServiceInterceptor bookServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(bookServiceInterceptor);
	}
	
}
