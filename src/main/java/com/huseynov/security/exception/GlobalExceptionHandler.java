package com.huseynov.security.exception;

import com.huseynov.security.dto.ApiResponse;
import com.huseynov.security.dto.ErrorDTO;
import com.huseynov.security.dto.LoginResponse;
import com.huseynov.security.dto.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String FAILED_MESSAGE = "failed";

    @ExceptionHandler(ExistsUsernameException.class)
    public ResponseEntity<ApiResponse<RegisterResponse>> handlerExistsUsernameException(ExistsUsernameException exception) {
        log.warn("ExistsUsernameException : {}", exception.getMessage());
        ApiResponse<RegisterResponse> response = new ApiResponse<>();
        response.setStatus(FAILED_MESSAGE);
        ErrorDTO errorDTO = new ErrorDTO("username", exception.getMessage());
        response.setErrors(errorDTO);
        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<ApiResponse<LoginResponse>> handleCustomerAuthenticationException(CustomAuthenticationException exception) {
        log.warn("CustomAuthenticationException: {}", exception.getMessage());
        ApiResponse<LoginResponse> response = new ApiResponse<>();

        response.setStatus(FAILED_MESSAGE);
        ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());
        response.setErrors(errorDTO);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<LoginResponse>> handlerNotFoundUsernameException(UsernameNotFoundException exception) {
        log.warn("UsernameNotFoundException: {}", exception.getMessage());
        ApiResponse<LoginResponse> response = new ApiResponse<>();
        response.setStatus(FAILED_MESSAGE);
        ErrorDTO errorDTO = new ErrorDTO("username", exception.getMessage());
        response.setErrors(errorDTO);
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
