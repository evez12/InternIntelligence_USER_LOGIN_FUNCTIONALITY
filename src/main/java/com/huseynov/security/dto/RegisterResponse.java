package com.huseynov.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RegisterResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;

    public RegisterResponse(String username, String jwtToken, List<String> roles) {
        this.roles = roles;
        this.jwtToken = jwtToken;
        this.username = username;
    }
}
