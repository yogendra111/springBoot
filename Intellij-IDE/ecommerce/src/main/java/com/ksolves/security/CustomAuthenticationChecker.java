package com.ksolves.security;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

public class CustomAuthenticationChecker implements UserDetailsChecker {
    @Override
    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new LockedException("UserLocked");
        } else if (!user.isEnabled()) {
            throw new DisabledException("User is disabled");
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("User account has expired");
        }
    }
}
