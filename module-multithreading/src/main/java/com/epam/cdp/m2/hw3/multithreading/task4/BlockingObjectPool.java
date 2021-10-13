package com.epam.cdp.m2.hw3.multithreading.task4;

/**
 * Task 4
 * <p>
 * Create simple object pool with support for multithreaded environment. No any extra inheritance, polymorphism
 * or generics needed here, just implementation of simple class:
 */

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

/**
 * Pool that block when it has not any items, or it fulls
 */
public class BlockingObjectPool {

    private final List<Object> pool = new ArrayList<>();
    private static final Integer POOL_SIZE = 4;


    /**
     * Creates filled pool of passed size *
     * <p>
     * * @param size of pool
     */
    public BlockingObjectPool(int size) {
        synchronized (pool) {
            range(0, size)
                    .forEach(i -> pool.add(new Object()));
        }
    }

    /**
     * Gets object from pool or blocks if pool is empty *
     * <p>
     * * @return object from pool
     */
    public synchronized Object get() throws InterruptedException {
        while (pool.isEmpty()) {
            this.wait();
        }
        Object object = pool.remove(0);
        notifyAll();
        return object;

    }

    /**
     * * Puts object to pool or blocks if pool is full *
     * <p>
     * * @param object to be taken back to pool
     */
    public synchronized void take(Object object) throws InterruptedException {
        while (pool.size() >= POOL_SIZE) {
            wait();
        }

        pool.add(object);
        notifyAll();
    }
}
