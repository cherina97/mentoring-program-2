package com.epam.cdp.m2.hw3.multithreading.task5.utilities;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException() {
        super();
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
