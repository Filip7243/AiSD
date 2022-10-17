public class Main {
    private static final int NUMBER_OF_ITEMS = 6;
    private static final int[] VOLUME_OF_ITEMS = {6, 2, 3, 2, 3, 1};
    private static final int[] VALUE_OF_ITEMS = {6, 4, 5, 7, 10, 2};
    private static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_ITEMS);
    private static final int MAX_VOLUME = 10;

    public static void main(String[] args) {
        int[] knapsack = new int[NUMBER_OF_ITEMS + 1];
        int[] bestSubset = new int[NUMBER_OF_ITEMS + 1];
        int bestVolume = Integer.MIN_VALUE;
        int bestValue = Integer.MIN_VALUE;
        int currentVolume = 0;
        int currentValue = 0;

        for (int i = 0; i < NUMBER_OF_SUBSETS; i++) {// iteruje przez wszystkie mozliwe pod zbiory zbioru n elementowego, n = NUMBER_OF_SUBSETS
            for (int j = 0; j < NUMBER_OF_ITEMS; j++) { // iteruje przez podzbior
                if (knapsack[j] == 1) { // jeśli na którymś miejscu w podzbiorze pojawi się 1 to oznacza to, ze jednym z elementow podziboru bedzie element o j-tym indeksie, wiec:
                    currentVolume += VOLUME_OF_ITEMS[j]; // biore objetosc j -tego itemu i przypisuje do zmiennej currentVolume
                    currentValue += VALUE_OF_ITEMS[j]; // biore wartosc j -tego itemu i przypisuje do zmiennej currentValue
                }
            }

            if (currentVolume <= MAX_VOLUME && currentValue > bestValue) { // sprawdzam czy objetosc dnaego podzbioru nie przekracza MAX_VOLUME
                // i czy wartość tego podzbioru jest wieksza niz wartość ostatniego najlepszego podzbioru, jesli oba czynniki sa prawidzwe, to:
                bestVolume = currentVolume; // przypisuje nowa wartosc dla bestVolume
                bestValue = currentValue; // przypisuje nowa wartosc dla bestValue
                bestSubset = knapsack.clone(); // przypisuje dla najlepszego podzbioru zbior ktory w danym momencie ma najoptymalniejsze rozwiazanie
            } else { // jesli nie, to zeruje obie zmienne
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
        System.out.println("Best subset of all, number 1 means index of item(indexing from 0): ");
        print(bestSubset);
    }

    private static void print(int[] arr) {
        for (int j : arr) {
            System.out.print(j);
        }
        System.out.println();
    }
}

