package com.epam.cdp.m2.hw2.aggregator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println(thread.getState());
        synchronized (obj) {
            obj.notify();
        }
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }
}
