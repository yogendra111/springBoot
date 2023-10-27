package com.ksolves.models;

import com.ksolves.entities.Role;
import com.ksolves.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean isUserNonLocked;
    private final String ROLE_PREFIX = "ROLE_";

    public static UserDetailsImpl build(UserInfo user){
        return new UserDetailsImpl(user.getName(), user.getPassword(), user.getRoles(), user.getIsUserLocked());
    }

    public UserDetailsImpl(String name, String password, List<Role> roles, Boolean isUserLocked){
        this.name = name;
        this.password = password;
        this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.name()))
                .collect(Collectors.toList());
        this.isUserNonLocked = !isUserLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isUserNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
