import java.util.Random;
import java.util.Scanner;

public class ChangeProblem {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final int[] COINS = {500, 200, 100, 50, 20, 10, 5, 2, 1}; // grosze
    private static final int[] amountOfCoins = {1000, 1, 5, 0, 0, 0, 0, 0, 1}; // index 0 -> 5, so we have five 5zl, two 2zl, etc...
    private static final int COIN_SPENDING_LIMIT = 1000; // 999 + 1
    private static final int DRAW_LIMIT = 100; // it is number of draws(losowań)

    private static int bestAmountOfCoinsSpent = COIN_SPENDING_LIMIT; // the fewer the better
    private static int[] coinsSpent = new int[COIN_SPENDING_LIMIT];
    private static int[] bestCombinationOfCoinsSpent = new int[COIN_SPENDING_LIMIT];

    public static void main(String[] args) {
        keepYourChangeMonteCarlo();
    }

    private static void keepYourChangeMonteCarlo() {
        System.out.println("Give a change: ");

        System.out.print("Zlotych: ");
        int zl = SCANNER.nextInt();
        System.out.print("Groszy: ");
        int gr = SCANNER.nextInt();
        int change = 0;

        for(int i = 0; i < DRAW_LIMIT; i++) {
            change = (zl*100) + gr;
            int amountOfCoinsSpent = 0;
            int[] currentAmountOfCoins = amountOfCoins.clone();

            while ((change > 0 && amountOfCoinsSpent < COIN_SPENDING_LIMIT)) {
                int randomCoinIndex = RANDOM.nextInt(COINS.length);
                int coin = COINS[randomCoinIndex];
                int amountOfCoin = currentAmountOfCoins[randomCoinIndex];
                int lowestAvailableCoin = findLowest(currentAmountOfCoins);
                if(lowestAvailableCoin > change) {
                    break;
                }
                if(coin <= change && amountOfCoin > 0) {
                    coinsSpent[amountOfCoinsSpent] = coin;
                    currentAmountOfCoins[randomCoinIndex]--;
                    change -= coin;
                    amountOfCoinsSpent++;
                }
            }
            if(amountOfCoinsSpent < bestAmountOfCoinsSpent && change == 0.0) {
                bestAmountOfCoinsSpent = amountOfCoinsSpent;
                for(int j = 0; j < bestAmountOfCoinsSpent; j++) {
                    bestCombinationOfCoinsSpent[j] = coinsSpent[j];
                }
            }
        }

        if(bestAmountOfCoinsSpent < COIN_SPENDING_LIMIT) {
            System.out.println("Change: ");
            for(int i = 0; i < bestAmountOfCoinsSpent; i++) {
                System.out.print(bestCombinationOfCoinsSpent[i]/100.0 + " ");
            }
        } else {
            System.out.println("There is no solution");
        }
    }

    private static int findLowest(int[]amountOfCoins) {
        int lowest = Integer.MAX_VALUE;
        for(int i = 0; i < COINS.length; i++) {
            if(amountOfCoins[i] != 0) {
                if(COINS[i] < lowest) {
                    lowest = COINS[i];
                }
            }
        }

        return lowest;
    }
}
