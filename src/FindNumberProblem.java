import java.util.Random;

public class FindNumberProblem {

    private static final Random RANDOM = new Random();
    private static final int NUMBER_TO_GUESS = RANDOM.nextInt(1000) + 1;

    public static void main(String[] args) {
        System.out.println("NUMBER TO GUESS = " + NUMBER_TO_GUESS);
        findNumberInRange(0, 1000);
    }

    private static int findNumberInRange(int a, int b) {
        if (b < a) {
            return -1;
        }
        int guess = (b+a) / 2;
        System.out.println("My guess is: " + guess);
        if(guess == NUMBER_TO_GUESS) {
            System.out.println("You win! Number you were finding is: " + NUMBER_TO_GUESS);
            return guess;
        } else if(guess < NUMBER_TO_GUESS) {
            System.out.println("Number is higher! Try again!");
            return findNumberInRange(guess+1, b);
        } else {
            System.out.println("Number is lower! Try again!");
            return findNumberInRange(a, guess-1);
        }
    }
}
