package com.ksolves.security;

import com.ksolves.exceptionhandler.CustomAuthenticationEntryPoint;
import com.ksolves.exceptionhandler.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CustomSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;
    private AuthenticationEntryPoint authenticationEntryPoint;

//    HandlerExceptionResolver handlerExceptionResolver;

    public CustomSecurityFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

//    @Autowired
//    PasswordEncoder passwordEncoder;

//    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
//    private AuthenticationEntryPoint authenticationEntryPoint;
//    private RememberMeServices rememberMeServices = new NullRememberMeServices();
//    private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
//            String authType = request.getHeader("Authorization");
//            System.out.println("Authentication: " + authType);
        try {
            String authType = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authType == null) {
                filterChain.doFilter(request, response);
                return;
            }

            if (authType.startsWith("Bearer")) {
                String jwtToken = authType.substring(7);
                if (jwtToken == null || !jwtUtils.isValidToken(jwtToken)) {     //To Ask
                    filterChain.doFilter(request, response);
                    return;
                }

                String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
                System.out.println("Username: " + username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("user: " + userDetails.getUsername() + " " + userDetails.isAccountNonLocked()
                        + " " + userDetails.getPassword() + " " + userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
//                        userDetails.getPassword(), userDetails.getAuthorities());

                Authentication newauthAuthentication = authenticationManager.authenticate(authToken);
                System.out.println("newuser: " + newauthAuthentication.getName() + " " + newauthAuthentication.getCredentials() + " " + newauthAuthentication.getAuthorities());
                if (newauthAuthentication.isAuthenticated()) {
                    System.out.println("user authenticated successss v1");
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

//                SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
//                context.setAuthentication(newauthAuthentication);
//                this.securityContextHolderStrategy.setContext(context);
//
//                this.rememberMeServices.loginSuccess(request, response, newauthAuthentication);
//                this.securityContextRepository.saveContext(context, request, response);

//                SecurityContextHolder.getContext().setAuthentication(newauthAuthentication);
                }
            } else if (authType.startsWith("Basic")) {
                String token = authType.substring(6);
                byte[] authByte = Base64.getDecoder().decode(token);
                String credential = new String(authByte, StandardCharsets.UTF_8);
                System.out.println(credential);
                String[] credentials = credential.split(":");
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
                Authentication authentication = authenticationManager.authenticate(auth);
                System.out.println("isAuth : " + authentication.isAuthenticated());
                if (authentication.isAuthenticated()) {
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }catch (AuthenticationException ex){
            System.out.println("Auth Exception " + ex.getMessage());
            this.authenticationEntryPoint.commence(request, response, ex);
        }
//        catch (Exception ex){
//            handlerExceptionResolver.resolveException(request, response, null, ex);
//        }
    }

}
