package com.demoboot.interceptors;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demoboot.exceptionhandler.InvalidSysIPException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BookServiceInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Request will be send to controller.");
		
//		String sysip = request.getHeader("sysip");
//		
//		if(sysip==null || sysip.length()==0) {
//			throw new InvalidSysIPException("Provided sysIP cannot be empty.");
//		}
//		
//		String arr[] = sysip.split("\\.");
//		for(String s: arr) {
//			if(s.length()<2 || s.length()>3) {
//				throw new InvalidSysIPException("sysIp is not well formatted");
//			}
//		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		System.out.println("Reponse will be send to the client.");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		System.out.println("Request and Response is completed.");
	}

}
