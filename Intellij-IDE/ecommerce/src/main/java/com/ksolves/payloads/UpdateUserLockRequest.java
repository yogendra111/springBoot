package com.ksolves.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserLockRequest {
    private String username;
    private Boolean isLocked;
}
