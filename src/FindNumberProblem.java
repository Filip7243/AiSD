import javax.swing.text.TabableView;
import java.util.Random;

public class FindNumberProblem {

    private static final Random RANDOM = new Random();
    private static final int NUMBER_TO_GUESS = RANDOM.nextInt(1000) + 1;
    private static int[] tab = new int[1000];

    public static void main(String[] args) {
        findNumberInRangeDynamicProgramming(0, 1000);
    }

    private static int findNumberInRangeDivideAndConquer(int a, int b) {
        if (b < a) {
            return -1;
        }
        int guess = (b + a) / 2;
        System.out.println("My guess is: " + guess);
        if (guess == NUMBER_TO_GUESS) {
            System.out.println("You win! Number you were finding is: " + NUMBER_TO_GUESS);
            return guess;
        } else if (guess < NUMBER_TO_GUESS) {
            System.out.println("Number is higher! Try again!");
            return findNumberInRangeDivideAndConquer(guess + 1, b);
        } else {
            System.out.println("Number is lower! Try again!");
            return findNumberInRangeDivideAndConquer(a, guess - 1);
        }
    }

    private static void findNumberInRangeDynamicProgramming(int a, int b) {
        int guess = (b + a) / 2;

        if (guess == NUMBER_TO_GUESS) {
            System.out.println("YOU GUESSED! NUMBER IS: " + guess);
        } else if (guess < NUMBER_TO_GUESS) {
            System.out.println("YOUR GUESS WAS: " + guess + " BUT NUMBER IS HIGHER");
            tab[0] = guess - 1;
            a = tab[0];
        } else {
            System.out.println("YOUR GUESS WAS: " + guess + " BUT NUMBER IS LOWER");
            tab[0] = guess + 1;
            b = tab[0];
        }

        int i = 1;
        while (guess != NUMBER_TO_GUESS) {
            guess = (b + a) / 2;
            tab[i] = guess;
            if (guess == NUMBER_TO_GUESS) {
                System.out.println("YOU GUESSED! NUMBER IS: " + guess);
            } else if (guess < NUMBER_TO_GUESS) {
                System.out.println("YOUR GUESS WAS: " + guess + " BUT NUMBER IS HIGHER");
                tab[i] = guess - 1;
                a = tab[i];
            } else {
                System.out.println("YOUR GUESS WAS: " + guess + " BUT NUMBER IS LOWER");
                tab[i] = guess + 1;
                b = tab[i];
            }
        }
    }
}
