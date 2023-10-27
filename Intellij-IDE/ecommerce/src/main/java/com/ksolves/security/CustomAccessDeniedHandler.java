package com.ksolves.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("AccessDenied/");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        writer.println("AccessDenied " + accessDeniedException.getMessage());
    }
}

//@Component
//class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException, ServletException {
//
////        response.addHeader("WWW-Authenticate", "Basic realm=");
//        System.out.println("Helloshaug");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        PrintWriter writer = response.getWriter();
//        writer.println("HTTP Status 401 - " + authException.getMessage());
//    }
//}