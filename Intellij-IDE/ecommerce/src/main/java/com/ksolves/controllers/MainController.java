package com.ksolves.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to our store");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("Welcome admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("Welcome user to our store");
    }

}
