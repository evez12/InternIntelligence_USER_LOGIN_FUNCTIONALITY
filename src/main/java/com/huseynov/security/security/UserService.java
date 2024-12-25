package com.huseynov.security.security;

import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.ResponseUser;

public interface UserService {

    ResponseUser createUser(CreateUserRequest request);
}
