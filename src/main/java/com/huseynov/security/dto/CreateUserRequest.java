package com.huseynov.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "username is required")
    String username;

    @NotBlank(message = "password is required")
    String password;

}
