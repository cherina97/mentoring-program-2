package task1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Task 1
 * Create HashMap<Integer, Integer>. The first thread adds elements into the map, the other go along the given map
 * and sum the values. Threads should work before catching ConcurrentModificationException.
 * Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
 * What has happened after simple Map implementation exchanging? How it can be fixed in code?
 * Try to write your custom ThreadSafeMap with synchronization and without.
 * Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance.
 * Provide a simple report to your mentor.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //ConcurrentModificationException with using HashMap
        //there is no ConcurrentModificationException, but result is not true
        sumRace();

        //with using CountDownLatch no ConcurrentModificationException and true result
        sumRaceCountDownLatch();
    }

    private static final Map<Integer, Integer> map = new HashMap<>();
    private static final ConcurrentHashMap<Integer, Integer> mapConcurrent = new ConcurrentHashMap<>();
    private static final Map<Integer, Integer> mapCustom = new CustomThreadSafeMap<>();
    private static final Integer COUNT_OF_NUMBERS = 1_000;

    private static void sumRace() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
                map.put(i, i);
            }
        };
        Thread thread = new Thread(runnable);

        Runnable runnable1 = () -> {
            int sum = 0;

            for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
                for (Integer key : map.keySet()) {
                    sum += map.get(key);
                }
            }

            System.out.println(sum);
        };
        Thread thread1 = new Thread(runnable1);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }

    private static void sumRaceCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
                map.put(i, i);
            }
            countDownLatch.countDown();
        });

        Thread thread1 = new Thread(() -> {
            try {
                countDownLatch.await();
                int sum = 0;
                for (int i = 0; i < COUNT_OF_NUMBERS; i++) {
                    for (Integer key : map.keySet()) {
                        sum += map.get(key);
                    }
                }
                System.out.println(sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }
}
