package com.epam.exception;

public class GlobalApplicationException extends RuntimeException {

    public GlobalApplicationException(String message) {
        super(message);
    }
}
