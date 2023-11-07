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

    public void addCounter(Counter counter){
        if (counter != null)
            counters.put(counter.getName(), counter);
    }

    public void deleteCounter(String name){
        counters.remove(name);
    }

    public Long getSummaryCount(){
       return counters.values().stream().mapToLong(Counter::getCounter).sum();
    }

    public Set<String> getNames(){
        return  counters.keySet();
    }
}
