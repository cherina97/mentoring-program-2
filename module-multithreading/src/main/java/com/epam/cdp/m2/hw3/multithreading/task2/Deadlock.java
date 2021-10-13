package com.epam.cdp.m2.hw3.multithreading.task2;

public class Deadlock {
    
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    
    private static class Thread1 extends Thread{
        @Override
        public void run() {
            
            synchronized (lock1){
                System.out.println("Thread 1: Holding lock 1...");
                sleep10();

                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2){
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread{
        @Override
        public void run() {

            synchronized (lock2){
                System.out.println("Thread 2: Holding lock 1...");
                sleep10();

                System.out.println("Thread 2: Waiting for lock 2...");
                synchronized (lock1){
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static void sleep10() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
