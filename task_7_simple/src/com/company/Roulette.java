package com.company;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Roulette{

    private static final int COUNT_OF_SECTORS = 37;

    public static void startGame(Player player, Casino casino){
        int bet = initBet(player.getAmount());
        int rouletteResult = 0;

        for(int i = 0; i < 100; i++){
            if (player.getAmount() == 0 || casino.getAmount() == 0)
                break;
            rouletteResult = ThreadLocalRandom.current().nextInt(0, COUNT_OF_SECTORS + 1);
            int result = player.getStrategy().useStrategy(rouletteResult, bet);
            if (result > 0) {
                player.setAmount(player.getAmount() + result);
                casino.setAmount(casino.getAmount() - result);
            } else {
                player.setAmount(player.getAmount() + result);
                casino.setAmount(casino.getAmount() - result);
            }
        }
        System.out.println("Casino amount: " + casino.getAmount());
        System.out.println("Player amount: " + player.getAmount());
    }

    private static int initBet(int amount){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input your bet: ");
            String bet = scanner.next();
            if (checkBet(bet, amount)){
                return Integer.parseInt(bet);
            } else System.out.println("Invalid input \n");
        }
    }

    private static boolean checkBet(String bet, int amount){
        if(bet.isEmpty() || bet.isBlank() || bet.length() == 0 || bet.length() > 10)
            System.out.println("Invalid input \n");
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(bet, pos);
        return ((bet.length() == pos.getIndex()) && Integer.parseInt(bet) >= 0 && Integer.parseInt(bet) <= amount);
    }
}
