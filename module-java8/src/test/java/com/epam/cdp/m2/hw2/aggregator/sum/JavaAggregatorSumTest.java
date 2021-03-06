package com.epam.cdp.m2.hw2.aggregator.sum;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;

public abstract class JavaAggregatorSumTest {

    @Parameterized.Parameter(0)
    public List<Integer> numbers;

    @Parameterized.Parameter(1)
    public int expected;

    private Aggregator aggregator;

    public JavaAggregatorSumTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{asList(1, 2, 3, 4, 5, 6, 7, 8), 36});
        data.add(new Object[]{asList(10, -10, 3), 3});
        data.add(new Object[]{emptyList(), 0});

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10_000_000; i++) {
            list.add(i);
        }
        data.add(new Object[]{list, -2004260032});

        return data;
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        int actual = aggregator.sum(numbers);

        assertEquals(expected, actual);

        long finish = System.currentTimeMillis();

        System.out.println(aggregator.getClass() + "(Sum) - " + (finish - start) + " Millis");
    }
}