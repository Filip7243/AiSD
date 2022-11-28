<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ChangeProblem {

    public static void main(String[] args) throws IOException {
        InputStreamReader str = new InputStreamReader(System.in);
        BufferedReader wejscie = new BufferedReader(str);
        String tekst;
        Random losuj = new Random();
        final int[] MONETY = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        final int LIMIT_WYDANYCH_MONET = 1000; // limit wydanych monet + 1
        final int LICZBA_LOSOWAN = 100;
        int minLiczbaWydanychMonet = LIMIT_WYDANYCH_MONET;
        int[] wydaneMonety = new int[LIMIT_WYDANYCH_MONET];
        int[] minWydaneMonety = new int[LIMIT_WYDANYCH_MONET];
        int moneta, zl, gr, r;

        System.out.println("Podaj reszte..");
        System.out.print("zlotych: ");
        tekst = wejscie.readLine();
        zl = Integer.parseInt(tekst);
        System.out.print("groszy: ");
        tekst = wejscie.readLine();
        gr = Integer.parseInt(tekst);
        for (int i = 0; i < LICZBA_LOSOWAN; i++) {
            r = zl * 100 + gr;
            int liczbaWydanychMonet = 0;
            while (r > 0 && liczbaWydanychMonet < LIMIT_WYDANYCH_MONET) {
                moneta = MONETY[losuj.nextInt(MONETY.length)];
                if (r >= moneta) {
                    wydaneMonety[liczbaWydanychMonet] = moneta;
                    r = r - moneta;
                    liczbaWydanychMonet++;
                }
            }
            if (minLiczbaWydanychMonet > liczbaWydanychMonet) {
                minLiczbaWydanychMonet = liczbaWydanychMonet;
                for (int j = 0; j < liczbaWydanychMonet; j++)
                    minWydaneMonety[j] = wydaneMonety[j];
            }
        }
        if (minLiczbaWydanychMonet < LIMIT_WYDANYCH_MONET) {
            System.out.print("Reszta: ");
            for (int i = 0; i < minLiczbaWydanychMonet; i++)
                System.out.print(minWydaneMonety[i] / 100.0 + " ");
        } else
            System.out.print("Nie znaleziono rozwiazania.");
        System.out.println();
=======
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
>>>>>>> e484f1f1c3d78aae907cc5cbf4996ed09ad9e5b9
    }
}
