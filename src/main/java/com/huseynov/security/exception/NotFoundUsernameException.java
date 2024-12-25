package com.huseynov.security.exception;

public class NotFoundUsernameException extends RuntimeException {
    public NotFoundUsernameException(String message) {
        super(message);
    }
}
