package com.epam.cdp.m2.hw3.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task 2
 * Create three threads:
 * 1st thread is infinitely writing random number to the collection;
 * 2nd thread is printing sum of the numbers in the collection;
 * 3rd is printing square root of sum of squares of all numbers in the collection.
 * Make these calculations thread-safe using synchronization block. Fix the possible deadlock.
 */
public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    private static final List<Integer> list = new ArrayList<>();
    private static final Random random = new Random();
    private static final ReentrantLock lock = new ReentrantLock();

    private static void run() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            lock.lock();
            list.add(random.nextInt(100));
            lock.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            int sum = 0;
            for (Integer integer : list) {
                sum += integer;
            }
            System.out.println(sum);
            lock.unlock();
        });

        Thread thread3 = new Thread(() -> {
            lock.lock();
            int squareSum = 0;

            for (Integer integer : list) {
                squareSum += integer * integer;
            }

            double result = Math.sqrt(squareSum);

            System.out.println(result);
            lock.unlock();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
