package com.ksolves.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 8, message = "Password must have 8 characters.")
    private String password;
//    private String role;
    private Set<String> roles;
}
