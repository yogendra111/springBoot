package com.ksolves.controllers;

import com.ksolves.payloads.AuthRequest;
import com.ksolves.payloads.RegisterRequest;
import com.ksolves.payloads.UpdateUserLockRequest;
import com.ksolves.payloads.UserResponse;
import com.ksolves.security.JwtUtils;
import com.ksolves.services.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody RegisterRequest registerRequest){
        userInfoService.saveUser(registerRequest);
        return ResponseEntity.accepted().body(UserResponse.builder()
                .user(registerRequest.getName())
                .response("User added Successfully")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody AuthRequest authRequest){
        //authenticationManager
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Authenticated User");
        return ResponseEntity.ok().body(
                UserResponse.builder()
                        .user(authRequest.getName())
                        .response("JWT Token: " + jwtUtils.generateJwtTokenByUsername(authRequest.getName()))
                        .build());

    }

    @PatchMapping("/update")
    public ResponseEntity updateUser(@Valid @RequestBody UpdateUserLockRequest updateRequest){
        userInfoService.changeUserLockSetting(updateRequest);
        return ResponseEntity.ok().body(
                UserResponse.builder()
                        .user(updateRequest.getUsername())
                        .response("user access Locked: " + updateRequest.getIsLocked())
                        .build());
    }

}
