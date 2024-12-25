package com.huseynov.security.exception;

import com.huseynov.security.dto.ResponseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistsUsernameException.class)
    public ResponseEntity<ResponseUser> handlerExistsUsernameException(ExistsUsernameException exception) {
        ResponseUser response = new ResponseUser();
        response.setErrorMessage(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(NotFoundUsernameException.class)
    public ResponseEntity<ResponseUser> handlerNotFoundUsernameException(NotFoundUsernameException exception) {
        ResponseUser response = new ResponseUser();
        response.setErrorMessage(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
