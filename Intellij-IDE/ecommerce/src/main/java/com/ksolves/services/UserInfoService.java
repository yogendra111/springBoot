package com.ksolves.services;

import com.ksolves.payloads.RegisterRequest;
import com.ksolves.payloads.UpdateUserLockRequest;
import com.ksolves.payloads.UserResponse;

public interface UserInfoService {
    void saveUser(RegisterRequest registerRequest);
    void changeUserLockSetting(UpdateUserLockRequest updateRequest);
}
