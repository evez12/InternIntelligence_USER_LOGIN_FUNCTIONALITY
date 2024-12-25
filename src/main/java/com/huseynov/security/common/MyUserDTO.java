package com.huseynov.security.common;

import lombok.Data;

import java.util.Set;

@Data
public class MyUserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

//    @Override
//    public String toString() {
//        return "MyUserDTO{" +
//                "email='" + email + '\'' +
//                ", id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", roles=" + roles.forEach; +
//                '}';
//    }
}
