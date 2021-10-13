package com.epam.cdp.m2.hw3.multithreading.task5.utilities;

import java.io.FileNotFoundException;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
