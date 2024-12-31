package com.huseynov.security.service;

import com.huseynov.security.dto.*;

public interface UserService {

    RegisterResponse registerUser(CreateUserRequest request);

    LoginResponse authenticateUser(LoginRequest request);

    UserProfileResponse getProfileDetails();
}
