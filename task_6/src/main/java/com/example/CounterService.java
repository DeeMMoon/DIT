package com.example;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class CounterService {

    private final Map<String, Counter> counters;

    public CounterService(Map<String, Counter> counters) {
        this.counters = counters;
        counters.clear();
    }

    public Counter getCounterByName(String name){
        return counters.get(name);
    }

    public boolean addCounter(Counter counter){
        if (counter != null && !counters.containsKey(counter.getName())) {
            counters.put(counter.getName(), counter);
            return true;
        }
        return false;
    }

    public boolean deleteCounter(String name){
        if (name != null && counters.get(name) != null) {
            counters.remove(name);
            return true;
        }
        return false;
    }

    public Long getSummaryCount(){
        try {
            Long result = counters.values().stream().mapToLong(Counter::getCounter).sum();
            return result;
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return -1L;
    }

    public Set<String> getNames(){
        return  counters.keySet();
    }
}
