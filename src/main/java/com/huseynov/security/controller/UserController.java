package com.huseynov.security.controller;

import com.huseynov.security.dto.*;
import com.huseynov.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin()
public class UserController {

    private static final String SUCCESS_MESSAGE = "success";
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody CreateUserRequest request) {
        log.debug("Register request: {}", request);

        ApiResponse<RegisterResponse> response = new ApiResponse<>();
        RegisterResponse registerResponse = userService.registerUser(request);
        response.setResults(registerResponse);
        response.setStatus("Successfully registered");

        log.debug("Register response: {}", response);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {

        log.debug("Login request: {}", request);
        ApiResponse<LoginResponse> response = new ApiResponse<>();
        LoginResponse loginResponse = userService.authenticateUser(request);

        log.debug("Login response: {}", loginResponse);
        response.setResults(loginResponse);
        response.setStatus(SUCCESS_MESSAGE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> profile(@RequestHeader(value = "Authorization", required = false) String authorization) {
        log.info("Profile request received ");
        log.info("Authorization: {}", authorization);
        UserProfileResponse profileResponse = userService.getProfileDetails();

        ApiResponse<UserProfileResponse> response = new ApiResponse<>();
        response.setResults(profileResponse);
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
        log.info("User request");
        return "HELLO USER";
    }

    @GetMapping("/manager")
    public String manager() {
        log.info("Manager request");
        return "HELLO Manager";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("Admin request");
        return "HELLO ADMIN";
    }
}
