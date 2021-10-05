package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;

import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> wordsFrequencies = new TreeMap<>();
        for (String word : words) {
            wordsFrequencies.put(word, wordsFrequencies.containsKey(word) ? wordsFrequencies.get(word) + 1 : 1);
        }

        List<Pair<String, Long>> listOfWordFrequencies = new ArrayList<>();
        for (Map.Entry<String, Long> map : wordsFrequencies.entrySet()) {
            if (limit > 0) {
                listOfWordFrequencies.add(new Pair<>(map.getKey(), map.getValue()));
                limit--;
            }
        }
        return listOfWordFrequencies;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> duplicates = new ArrayList<>();
        for (String word : words) {

            String str = word.toUpperCase();
            String newString = str.replaceAll(String.valueOf(str.charAt(0)), " ").trim();

            if (!duplicates.contains(str) && str.length() > 1 && newString.length() > 0) {
                duplicates.add(str);
            }
        }

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
