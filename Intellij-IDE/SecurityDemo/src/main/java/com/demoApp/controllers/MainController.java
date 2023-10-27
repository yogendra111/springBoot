package com.demoApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome All";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String user(){
        return "Welcome user";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "Welcome Admin";
    }

}
