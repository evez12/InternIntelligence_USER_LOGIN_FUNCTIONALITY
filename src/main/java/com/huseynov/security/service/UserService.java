package com.huseynov.security.service;

import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponse createUser(CreateUserRequest request);
}
