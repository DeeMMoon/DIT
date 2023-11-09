package com.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void inc() {
        Counter counter = new Counter(0, "Counter1");
        counter.inc();
        Assert.assertTrue(counter.getCounter() == 1);
        for (int i = 0; i < Integer.MAX_VALUE; i++)
            counter.inc();
        Assert.assertTrue(counter.getCounter() == Integer.MAX_VALUE);
        counter.inc();
        Assert.assertTrue(counter.getCounter() == Integer.MAX_VALUE);
    }
}