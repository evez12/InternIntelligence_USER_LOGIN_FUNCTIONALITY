package com.huseynov.security.controller;

import com.huseynov.security.dto.ApiResponse;
import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.UserResponse;
import com.huseynov.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private static final String SUCCESS_MESSAGE = "success";
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody CreateUserRequest request) {

        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResults(userService.createUser(request));
        response.setStatus(SUCCESS_MESSAGE);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('USER')")
    public String hello() {
        return "HELLO EVERYBODY";
    }

    //    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public String user() {
        return "HELLO USER";
    }

    //    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/manager")
    public String manager() {
        return "HELLO Manager";
    }

    //    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "HELLO ADMIN";
    }
}
