package com.huseynov.security.user;

import lombok.Data;

import javax.crypto.spec.PSource;
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
