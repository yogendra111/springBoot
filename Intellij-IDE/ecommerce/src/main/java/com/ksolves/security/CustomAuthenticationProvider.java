package com.ksolves.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;
    private UserDetailsChecker preAuthenticationChecks = new CustomAuthenticationChecker();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Auth started");
        String username = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        this.preAuthenticationChecks.check(userDetails);
        System.out.println("Checked");
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
