package com.ksolves.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    CustomAuthenticationProvider provider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = provider.authenticate(authentication);
        return auth;
    }
}
