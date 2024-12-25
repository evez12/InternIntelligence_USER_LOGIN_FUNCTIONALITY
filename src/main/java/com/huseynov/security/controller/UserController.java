package com.huseynov.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO EVERYBODY";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "HELLO USER";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager")
    public String manager() {
        return "HELLO Manager";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "HELLO ADMIN";
    }
}
