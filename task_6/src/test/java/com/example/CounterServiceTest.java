package com.example;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CounterServiceTest {

    private static final Map<String, Counter> counters = new HashMap<>();
    private static final CounterService counterService = new CounterService(counters);

    @BeforeEach
     void initCounters(){
        counters.clear();
        Counter counter1 = new Counter(0, "Counter1");
        Counter counter2 = new Counter(0, "Counter2");
        Counter counter3 = new Counter(0, "Counter3");
        counters.put("Counter1", counter1);
        counters.put("Counter2", counter2);
        counters.put("Counter3", counter3);
    }

    @Test
    void getCounterByName() {
        assertNotNull(counterService.getCounterByName("Counter1"));
        assertSame(counters.get("Counter1"), counterService.getCounterByName("Counter1"));
        assertNotNull(counterService.getCounterByName("Counter2"));
        assertSame(counters.get("Counter2"), counterService.getCounterByName("Counter2"));
        assertNotNull(counterService.getCounterByName("Counter3"));
        assertSame(counters.get("Counter3"), counterService.getCounterByName("Counter3"));
        assertNull(counterService.getCounterByName("Counter4"));
    }

    @Test
    void addCounter() {
        Counter counter4 = new Counter(12, "Counter4");
        counterService.addCounter(counter4);
        assertSame(counter4, counters.get("Counter4"));
        counterService.addCounter(null);
        assertTrue(counters.size() == 4);
        assertFalse(counterService.addCounter(counter4));
    }

    @Test
    void deleteCounter() {
        assertTrue(counterService.deleteCounter("Counter3"));
        assertNull(counters.get("Counter3"));
        assertFalse(counterService.deleteCounter(null));
        assertFalse(counterService.deleteCounter("Counter5"));
    }

    @Test
    void getSummaryCount() {
        Long result = (long) (counters.get("Counter1").getCounter()
                + counters.get("Counter2").getCounter()
                + counters.get("Counter3").getCounter());
        assertEquals(result, counterService.getSummaryCount());
        counters.get("Counter1").inc();
        assertNotEquals(result, counterService.getSummaryCount());
        result = (long) (counters.get("Counter1").getCounter()
                + counters.get("Counter2").getCounter()
                + counters.get("Counter3").getCounter());
        assertEquals(result, counterService.getSummaryCount());
        counters.get("Counter1").setCounter(10000);
        counters.get("Counter2").setCounter(10000);
        counters.get("Counter3").setCounter(10000);
        assertEquals(Long.valueOf(30000), counterService.getSummaryCount());
    }

    @Test
    void getNames() {
        Set<String> names = new HashSet<>();
        names.add("Counter1");
        names.add("Counter2");
        names.add("Counter3");
        assertEquals(names, counterService.getNames());
        names.remove("Counter3");
        assertNotEquals(names, counterService.getNames());
        names.add("Counter3");
        names.add("Counter4");
        assertNotEquals(names, counterService.getNames());
    }
}