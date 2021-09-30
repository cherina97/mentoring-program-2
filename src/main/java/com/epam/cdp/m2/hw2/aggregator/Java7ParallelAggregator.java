package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import javafx.util.Pair;

public class Java7ParallelAggregator implements Aggregator {

    private static final int THREADS = 5;
    private Integer sum = 0;

    @Override
    public int sum(List<Integer> numbers) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<Future<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            int step = i;

            tasks.add(executorService.submit(new Callable<Integer>() {
                public Integer call() {
                    int partSum = 0;
                    for (int j = step; j < numbers.size(); j += THREADS) {
                        partSum += numbers.get(j);
                    }
                    return partSum;
                }
            }));
        }

        for (Future<Integer> task : tasks) {
            try {
                sum += task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}
