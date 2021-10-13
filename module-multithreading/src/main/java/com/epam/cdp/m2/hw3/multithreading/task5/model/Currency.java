package com.epam.cdp.m2.hw3.multithreading.task5.model;

public enum Currency {

    UAH(1.0),
    USD(0.038),
    EUR(0.033);

    private final double rate;

    Currency(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
