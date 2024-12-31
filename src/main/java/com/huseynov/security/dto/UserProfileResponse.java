package com.huseynov.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserProfileResponse {
    private String username;
    private List<String> roles;
}
