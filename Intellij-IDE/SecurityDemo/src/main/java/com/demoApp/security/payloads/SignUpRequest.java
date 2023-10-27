package com.demoApp.security.payloads;

import com.demoApp.security.models.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> role;
}
