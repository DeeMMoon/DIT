package com.company;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = initPlayer();
        Casino casino = initCasino();
        Strategy strategy = initStrategy();
        player.setStrategy(strategy);
        Roulette.startGame(player,casino);
    }

    private static Player initPlayer(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input player's amount");
            String amount = scanner.next();
            if (checkAmount(amount)){
                return new Player(Integer.parseInt(amount));
            } else System.out.println("Invalid input \n");
        }
    }

    private static Casino initCasino(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input casino's amount");
            String amount = scanner.next();
            if (checkAmount(amount)){
                return new Casino(Integer.parseInt(amount));
            } else System.out.println("Invalid input \n");
        }
    }

    private static Strategy initStrategy(){
        Scanner scanner = new Scanner(System.in);
        Strategy strategy = new Strategy();
        while (true){
            System.out.println("Choose strategy");
            strategy.showStrategy();
            String strategyNumber = scanner.next();
            if(checkStrategyNumber(strategyNumber)){
                strategy.setStrategyNumber(Integer.parseInt(strategyNumber));
                return strategy;
            } else System.out.println("Invalid input");
        }
    }

    private static boolean checkStrategyNumber(String strategyNumber){
        if (strategyNumber.isEmpty() || strategyNumber.isBlank()
                || strategyNumber.length() == 0 || strategyNumber.length() > 10)
            System.out.println("Invalid input");
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(strategyNumber, pos);
        return ((strategyNumber.length() == pos.getIndex())
                && Integer.parseInt(strategyNumber) >= 1
                && Integer.parseInt(strategyNumber) <= Strategy.COUNT_OF_STRATEGIES);
    }

    private static boolean checkAmount(String amount){
        if(amount.isEmpty() || amount.isBlank() || amount.length() == 0 || amount.length() > 10 || Long.parseLong(amount) > Integer.MAX_VALUE)
            return false;
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(amount, pos);
        return ((amount.length() == pos.getIndex()) && Integer.parseInt(amount) >= 0);
    }
}
