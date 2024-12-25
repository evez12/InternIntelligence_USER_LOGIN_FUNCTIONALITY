package com.huseynov.security.exception;

public class ExistsUsernameException extends RuntimeException {
    public ExistsUsernameException(String message) {
        super(message);
    }
}
