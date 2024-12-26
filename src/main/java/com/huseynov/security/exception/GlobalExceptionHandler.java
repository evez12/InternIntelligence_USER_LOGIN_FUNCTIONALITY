package com.huseynov.security.exception;

import com.huseynov.security.dto.ApiResponse;
import com.huseynov.security.dto.ErrorDTO;
import com.huseynov.security.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String FAILED_MESSAGE = "failed";

    @ExceptionHandler(ExistsUsernameException.class)
    public ResponseEntity<ApiResponse<UserResponse>> handlerExistsUsernameException(ExistsUsernameException exception) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setStatus(FAILED_MESSAGE);
        ErrorDTO errorDTO = new ErrorDTO("username", exception.getMessage());
        response.setErrors(Collections.singletonList(errorDTO));
        return ResponseEntity
                .badRequest()
                .body(response);
    }

    //    @ExceptionHandler(NotFoundUsernameException.class)
//    public ResponseEntity<ApiResponse<UserResponse>> handlerNotFoundUsernameException(NotFoundUsernameException exception) {
//        ApiResponse<UserResponse> response = new ApiResponse<>();
//        response.setStatus(FAILED_MESSAGE);
//        ErrorDTO errorDTO = new ErrorDTO("username", exception.getMessage());
//        response.setErrors(Collections.singletonList(errorDTO));
//        return ResponseEntity
//                .badRequest()
//                .body(response);
//    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<UserResponse>> handlerNotFoundUsernameException(UsernameNotFoundException exception) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setStatus(FAILED_MESSAGE);
        ErrorDTO errorDTO = new ErrorDTO("username", exception.getMessage());
        response.setErrors(Collections.singletonList(errorDTO));
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
