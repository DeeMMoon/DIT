package com.company;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

import static com.company.Main.baseInputCheck;

public class Strategy {

    public static final Set<Integer> RED_NUMBERS = new HashSet<>();
    public static final Set<Integer> BLACK_NUMBERS = new HashSet<>();

    public static final Set<Integer> ODD_NUMBERS = new HashSet<>();
    public static final Set<Integer> EVEN_NUMBERS = new HashSet<>();
    static {
        RED_NUMBERS.addAll(Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36));
        BLACK_NUMBERS.addAll(Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35));
        ODD_NUMBERS.addAll(Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35));
        EVEN_NUMBERS.addAll(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36));
    }
    private static final int RATE_FOR_ONE_SECTOR = 35;
    private static final int RATE_FOR_PAIR = 17;
    private static final int RATE_FOR_LINE = 11;
    private static final int RATE_FOR_COLORED_SECTOR = 1;
    private static final int RATE_FOR_EVEN_AND_ODD_SECTOR = 1;

    public static final int COUNT_OF_STRATEGIES = 7;

    private Map<String, Exec> items;
    private Map<Integer, String> strategiesList;
    private int strategyNumber;
    private int playerNumber;


    public Strategy() {
        this.items = new HashMap<>();
        strategiesList = new HashMap<>();
        initStrategiesList();
        initItems();
    }

    private void initStrategiesList(){
        strategiesList.put(1, "Put on red");
        strategiesList.put(2, "Put on black");
        strategiesList.put(3, "Put on odd");
        strategiesList.put(4, "Put on even");
        strategiesList.put(5, "Put on number");
        strategiesList.put(6, "Put on pair");
        strategiesList.put(7, "Put on line");
    }

    private void initItems(){
        items.put(strategiesList.get(1), Strategy::putOnRed);
        items.put(strategiesList.get(2), Strategy::putOnBlack);
        items.put(strategiesList.get(3), Strategy::putOnOdd);
        items.put(strategiesList.get(4), Strategy::putOnEven);
        items.put(strategiesList.get(5), Strategy::putOnNumber);
        items.put(strategiesList.get(6), Strategy::putOnPair);
        items.put(strategiesList.get(7), Strategy::putOnThree);
    }

    private interface Exec {
        int exec(int sector, int bet, int playerNumber);
    }

    public int getStrategyNumber() {
        return strategyNumber;
    }

    public void setStrategyNumber(int strategyNumber) {
        this.strategyNumber = strategyNumber;
        if (strategyNumber > 4)
            setPlayerNumber();
    }

    private void setPlayerNumber(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select number and input it here: ");
            String yourNumber = scanner.next();
            if (checkInputNumber(yourNumber)) {
                playerNumber = Integer.parseInt(yourNumber);
                break;
            } else System.out.println("Invalid input");
        }
    }

    public void showStrategy(){
        for (int i = 1; i < strategiesList.size() + 1; i++){
            System.out.println(i + "." + strategiesList.get(i));
        }
    }

    public int useStrategy(int sector, int bet){
        return items.get(strategiesList.get(strategyNumber)).exec(sector, bet, playerNumber);
    }

    private static int putOnRed(int sector, int bet, int playerNumber){
        if (RED_NUMBERS.contains(sector))
            return bet * RATE_FOR_COLORED_SECTOR;
        return -bet;
    }

    private static int putOnBlack(int sector, int bet, int playerNumber){
        if (BLACK_NUMBERS.contains(sector))
            return bet * RATE_FOR_COLORED_SECTOR;
        return -bet;
    }

    private static int putOnOdd(int sector, int bet, int playerNumber){
        if (ODD_NUMBERS.contains(sector))
            return bet * RATE_FOR_EVEN_AND_ODD_SECTOR;
        return -bet;
    }

    private static int putOnEven(int sector, int bet, int playerNumber){
        if (EVEN_NUMBERS.contains(sector))
            return bet * RATE_FOR_EVEN_AND_ODD_SECTOR;
        return -bet;
    }


    private static int putOnNumber(int sector, int bet, int playerNumber){
        if (sector == playerNumber){
            return bet * RATE_FOR_ONE_SECTOR;
        } else return -bet;
    }

    private static int putOnPair(int sector, int bet, int playerNumber){
        int secondNumber;
        if (playerNumber % 3 == 0)
            secondNumber = playerNumber - 1;
        else secondNumber = playerNumber + 1;
        if (sector == playerNumber || sector == secondNumber){
            return bet/2 * RATE_FOR_PAIR;
        } else return -bet;
    }

    private static int putOnThree(int sector, int bet, int playerNumber){
        if (playerNumber % 3 == 0) {
            if (sector == playerNumber || sector == playerNumber - 1 || sector == playerNumber - 2) {
                return bet/3 * RATE_FOR_LINE;
            } else return -bet;
        } else {
            while (playerNumber % 3 != 0)
                playerNumber ++;
            if (sector == playerNumber || sector == playerNumber - 1 || sector == playerNumber - 2) {
                return bet/3 * RATE_FOR_LINE;
            } else return -bet;
        }
    }

    private static boolean checkInputNumber(String number){
        if (!baseInputCheck(number))
            return false;
        if(!(Long.parseLong(number) >= 0 && Long.parseLong(number) <= 37))
            return false;
        return true;
    }
}
