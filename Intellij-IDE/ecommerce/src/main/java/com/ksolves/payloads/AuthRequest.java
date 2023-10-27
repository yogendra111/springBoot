package com.ksolves.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank
    private String name;
    @NotBlank
//    @Size(min = 8, message = "Password must have 8 characters.")
    private String password;
}
