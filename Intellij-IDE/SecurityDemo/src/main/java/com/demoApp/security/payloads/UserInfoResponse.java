package com.demoApp.security.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private long id;
    private String username;
    private String email;
    private List<String> roles;
}
