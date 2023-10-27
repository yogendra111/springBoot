package com.demoApp.controllers;

import com.demoApp.security.dao.RoleRepository;
import com.demoApp.security.dao.UserRepository;
import com.demoApp.security.jwt.JwtUtils;
import com.demoApp.security.models.ERole;
import com.demoApp.security.models.Role;
import com.demoApp.security.models.User;
import com.demoApp.security.payloads.LoginRequest;
import com.demoApp.security.payloads.MessageResponse;
import com.demoApp.security.payloads.SignUpRequest;
import com.demoApp.security.payloads.UserInfoResponse;
import com.demoApp.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpReq){
        if(userRepository.existsByUsername(signUpReq.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already taken!"));
        }
        if(userRepository.existsByEmail(signUpReq.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already in use!"));
        }
        User user = new User(signUpReq.getUsername(), signUpReq.getEmail(), encoder.encode(signUpReq.getPassword()));
        Set<String> strRoles = signUpReq.getRole();
        Set<Role> roles = new HashSet<>();
        if(strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()->new RuntimeException("Error: Role not found"));
        }else {
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminrole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(()->new RuntimeException("Error: Role not found"));
                        roles.add(adminrole);
                        break;
                    default:
                        Role userrole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(()->new RuntimeException("Error: Role not found"));
                        roles.add(userrole);
                }
            });
        }
        user.setRoles(roles);
//        System.out.println("User "+user);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered Successfully"));
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest signInReq){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInReq.getUsername(), signInReq.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }
    @PostMapping("/signout")
    public ResponseEntity<?> signOut(){
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}
