package com.huseynov.security.dto;

import com.huseynov.security.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class MyUserDTO {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

}
