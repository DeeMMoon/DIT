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
            String amount = scanner.nextLine();
            if (checkAmount(amount)){
                return new Player(Integer.parseInt(amount));
            } else System.out.println("Invalid input \n");
        }
    }

    private static Casino initCasino(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input casino's amount");
            String amount = scanner.nextLine();
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

    public static boolean baseInputCheck(String num){
        if (num.isEmpty() || num.isBlank() || num.length() > 10)
            return false;
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(num, pos);
        if (!(num.length() == pos.getIndex()))
            return false;
        return true;
    }

    private static boolean checkStrategyNumber(String strategyNumber){
        if (!baseInputCheck(strategyNumber))
            return false;
        if (!(Long.parseLong(strategyNumber) >= 1 && Long.parseLong(strategyNumber) <= Strategy.COUNT_OF_STRATEGIES))
            return false;
        return true;
    }

    private static boolean checkAmount(String amount){
        if (!baseInputCheck(amount))
            return false;
        if (!(Long.parseLong(amount) >= 0 && Long.parseLong(amount) <= Integer.MAX_VALUE))
            return false;
        return true;
    }
}
