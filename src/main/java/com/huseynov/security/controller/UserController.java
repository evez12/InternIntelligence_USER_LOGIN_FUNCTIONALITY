package com.huseynov.security.controller;

import com.huseynov.security.dto.*;
import com.huseynov.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private static final String SUCCESS_MESSAGE = "success";
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody CreateUserRequest request) {
        log.info("Register request: {}", request);

        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResults(userService.registerUser(request));
        response.setStatus(SUCCESS_MESSAGE);

        log.info("Register response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {

        log.info("Login request: {}", request);
        ApiResponse<LoginResponse> response = new ApiResponse<>();
        LoginResponse loginResponse = userService.authenticateUser(request);

        log.info("Login response: {}", loginResponse);
        response.setResults(loginResponse);
        response.setStatus(SUCCESS_MESSAGE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello request");
        return "HELLO EVERYBODY";
    }

    //    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public String user() {
        return "HELLO USER";
    }

    @GetMapping("/manager")
    public String manager() {
        return "HELLO Manager";
    }

    @GetMapping("/admin")
    public String admin() {
        return "HELLO ADMIN";
    }
}
