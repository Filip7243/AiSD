import java.util.Random;

public class Field {

    private static final int DRAW_LIMIT = 100; // max number of draws
    private static final Random RANDOM = new Random();
//    liczba losowych punktow / calkowita licza puntkow

    public static void main(String[] args) {
        countTheField();
    }

    private static void countTheField() {
        int numberOfPoints = 0;
        for (int i = 0; i < DRAW_LIMIT; i++) {
            double randomX = RANDOM.nextInt(1001) / 1000.0;
            double randomY = RANDOM.nextInt(1001) / 1000.0;
            if (randomY <= Math.sin(randomX)) {
                numberOfPoints++;
            }
        }

        double result = (double) numberOfPoints / DRAW_LIMIT;
        System.out.println("POLE = " + result);
    }
}
