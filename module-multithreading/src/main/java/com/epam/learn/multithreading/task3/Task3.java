package com.epam.learn.multithreading.task3;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Task 3
 * Implement message bus using Producer-Consumer pattern.
 * <p>
 * Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
 * Implement producer, which will generate and post randomly messages to the queue.
 * Implement consumer, which will consume messages on specific topic and log to the console message payload.
 * (Optional) Application should create several consumers and producers that run in parallel.
 */
public class Task3 {

    private final Queue<Integer> massageBus = new PriorityQueue<>();
    private final static Integer CAPACITY = 10;
    private final static Integer COUNT_OF_MASSAGE = 10;
    private final Object object = new Object();

    public static void main(String[] args) {

        Runnable producer = () -> {
            for (int i = 0; i < COUNT_OF_MASSAGE; i++) {
//                System.out.println("Massage produce - " + new Task3().produce(random.nextInt(100)));
                System.out.println("Massage produce - " + new Task3().produce(ThreadLocalRandom.current().nextInt(100)));
            }
        };

        Runnable consumer = () -> {
            System.out.println("Massage consume - " + new Task3().consume());
        };

        Arrays.asList(new Thread(producer), new Thread(consumer))
                .forEach(Thread::start);
    }

    private Integer produce(Integer putValue) {
        synchronized (this) {
            while (massageBus.size() == CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            massageBus.add(putValue);
            notifyAll();
            return putValue;
        }
    }

    private Integer consume() {
        synchronized (this) {
            while (massageBus.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Integer removeValue = massageBus.poll();
            notifyAll();
            return removeValue;
        }
    }
}
