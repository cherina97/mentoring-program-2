package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import javafx.util.Pair;

public class Java7ParallelAggregator implements Aggregator {

    private static final int THREADS = Runtime.getRuntime().availableProcessors() - 1;

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SumRecursive(numbers, 0, numbers.size()));
    }

    static class SumRecursive extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 5;
        private final List<Integer> list;
        private final int begin;
        private final int end;

        public SumRecursive(List<Integer> list, int begin, int end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if ((end - begin) < THRESHOLD) {
                int sum = 0;
                for (int i = begin; i < end; i++) {
                    sum += list.get(i);
                }
                return sum;
            } else {
                int mid = end + (begin - end) / 2;
                SumRecursive left = new SumRecursive(list, begin, mid);
                SumRecursive right = new SumRecursive(list, mid, end);
                left.fork();
                Integer rightAns = right.compute();
                Integer leftAns = left.join();

                return rightAns + leftAns;
            }
        }
    }


//        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
//        List<Future<Integer>> tasks = new ArrayList<>();
//
//        for (int i = 0; i < THREADS; i++) {
//            int step = i;
//
//            tasks.add(executorService.submit(new Callable<Integer>() {
//                public Integer call() {
//                    int partSum = 0;
//                    for (int j = step; j < numbers.size(); j += THREADS) {
//                        partSum += numbers.get(j);
//                    }
//                    return partSum;
//                }
//            }));
//        }
//
//        for (Future<Integer> task : tasks) {
//            try {
//                sum += task.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        return sum;


    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<Pair<String, Long>> pairList = Collections.synchronizedList(new ArrayList<>());
        ReentrantLock lock = new ReentrantLock();

        ConcurrentHashMap<String, Long> wordsFrequencies = new ConcurrentHashMap<>();

        for (int i = 0; i < THREADS; i++) {
            int step = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    for (int j = step; j < words.size(); j += THREADS) {
                        wordsFrequencies.put(words.get(j), wordsFrequencies.containsKey(words.get(j))
                                ? wordsFrequencies.get(words.get(j)) + 1
                                : 1);
                    }
                    lock.unlock();
                }
            });
        }

        executorService.shutdown();
        awaitTermination(executorService);

        for (Map.Entry<String, Long> map : wordsFrequencies.entrySet()) {
            if (limit > 0) {
                pairList.add(new Pair<>(map.getKey(), map.getValue()));
                limit--;
            }
        }
        return pairList;
    }

    private void awaitTermination(ExecutorService executorService) {
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }


    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<String> duplicates = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < THREADS; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (String word : words) {

                        String str = word.toUpperCase();
                        String newString = str.replaceAll(String.valueOf(str.charAt(0)), " ").trim();

                        lock.lock();
                        if (!duplicates.contains(str) && str.length() > 1 && newString.length() > 0) {
                            duplicates.add(str);
                        }
                        lock.unlock();

                    }
                }
            });
        }

        executorService.shutdown();
        awaitTermination(executorService);

        Collections.sort(duplicates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        if (duplicates.size() < limit) {
            return duplicates;
        } else return duplicates.subList(0, Math.toIntExact(limit));
    }
}
