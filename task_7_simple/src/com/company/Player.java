package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int amount;
    private Strategy strategy;

    public Player(int amount) {
        this.amount = amount;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
