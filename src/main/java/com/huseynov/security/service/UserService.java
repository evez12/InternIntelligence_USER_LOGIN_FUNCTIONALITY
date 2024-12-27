package com.huseynov.security.service;

import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.LoginRequest;
import com.huseynov.security.dto.LoginResponse;
import com.huseynov.security.dto.UserResponse;

public interface UserService  {

    UserResponse registerUser(CreateUserRequest request);

    LoginResponse authenticateUser(LoginRequest request);
}
