import java.sql.SQLOutput;

public class Main {
    private static final int NUMBER_OF_ITEMS = 4;
    private static final int[] VOLUME_OF_ITEMS = {7, 3, 4, 5};
    private static final int[] VALUE_OF_ITEMS = {42, 12, 40, 25};
    private static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_ITEMS);
    private static final int MAX_VOLUME = 10;

    public static void main(String[] args) {
//        final int N = 6;
//        int[] knapsack = new int[N + 1];
//        int s = (int) Math.pow(2, N);
//        for (int l = 0; l < s; l++) {
//
//            System.out.print("{ ");
//
//            for (int i = 0; i < N; i++)
//                if (knapsack[i] == 1){
//                    System.out.print(i + 1 + " ");
//                }
//
//            System.out.println("}");
//
//            int i = 0;
//            do {
//                if (knapsack[i] == 1) {
//                    knapsack[i] = 0;
//                    i++;
//                } else {
//                    knapsack[i] = 1;
//                    break;
//                }
//            } while (true);
//        }
        int[] knapsack = new int[NUMBER_OF_ITEMS + 1];
        int[] bestSubset = new int[NUMBER_OF_ITEMS];
        int bestVolume = Integer.MIN_VALUE;
        int bestValue = Integer.MIN_VALUE;
        int currentVolume = 0;
        int currentValue = 0;

        for (int i = 0; i < NUMBER_OF_SUBSETS; i++) {// iteruje przez wszystkie mozliwe pod zbiory zbioru n elementowego, n = NUMBER_OF_SUBSETS
            for (int j = 0; j < NUMBER_OF_ITEMS; j++) { // iteruje przez mój plecak
                if (knapsack[j] == 1) { // jeśli na którymś miejscu w plecaku pojawi się jeden to oznacza to, ze jednym z elementow podziboru bedzie element o j-tym indeksie, wiec:
                    currentVolume += VOLUME_OF_ITEMS[j];
                    currentValue += VALUE_OF_ITEMS[j];
                }
            }

            if(currentVolume <= MAX_VOLUME && currentValue > bestValue) {
                bestVolume = currentVolume;
                bestValue = currentValue;
            } else {
                currentVolume = 0;
                currentValue = 0;
            }


            //todo; zamienic na for
            int k = 0;
            do {
                if (knapsack[k] == 1) {
                    knapsack[k] = 0;
                    k++;
                } else {
                    knapsack[k] = 1;
                    break;
                }
            } while (true);
        }

        System.out.println("Best Volume Of Items = " + bestVolume);
        System.out.println("Best Value Of Items = " + bestValue);
        print(bestSubset);
    }

    private static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}

