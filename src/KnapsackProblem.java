public class KnapsackProblem {
    private static final int NUMBER_OF_ITEMS = 6;
    private static final int[] VOLUME_OF_ITEMS = {6, 2, 3, 2, 3, 1};
    private static final int[] VALUE_OF_ITEMS = {6, 4, 5, 7, 10, 2};

    private static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_ITEMS);
    private static final int MAX_VOLUME = 10;

    public static void main(String[] args) {
        knapsackProblemGreedyAlgorithmByValueToVolumeRatio();
    }


    private static void knapsackProblemGreedyAlgorithmByVolume() {
        // we take items by VOLUME - the fewer the better
        boolean[] result = new boolean[NUMBER_OF_ITEMS];
        for (boolean r : result) {
            r = false;
        }

        int sumValue = 0;
        int sumVolume = 0;

        while (true) {
            int minItemVolume = Integer.MAX_VALUE; // item
            int index = -1;
            for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
                if (!result[i]) {
                    if ((sumVolume + VOLUME_OF_ITEMS[i] <= MAX_VOLUME) && (VOLUME_OF_ITEMS[i] < minItemVolume)) {
                        minItemVolume = VOLUME_OF_ITEMS[i];
                        index = i;
                    }
                }
            }
            if (index > -1) {
                result[index] = true;
                sumValue += VALUE_OF_ITEMS[index];
                sumVolume += VOLUME_OF_ITEMS[index];
            } else {
                break;
            }
        }

        for (boolean r : result) {
            System.out.print(r + " ");
        }

        System.out.println("VOLUME: " + sumVolume);
        System.out.println("VALUE: " + sumValue);
    }

    private static void knapsackProblemGreedyAlgorithmByValue() {
        // we take items by VALUE - the more the better
        boolean[] result = new boolean[NUMBER_OF_ITEMS];
        for (boolean r : result) {
            r = false;
        }

        int sumValue = 0;
        int sumVolume = 0;

        while (true) {
            int maxItemValue = Integer.MIN_VALUE;
            int index = -1;

            for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
                if (!result[i]) {
                    if ((VOLUME_OF_ITEMS[i] + sumVolume <= MAX_VOLUME) && (VALUE_OF_ITEMS[i] > maxItemValue)) {
                        maxItemValue = VALUE_OF_ITEMS[i];
                        index = i;
                    }
                }
            }

            if (index > -1) {
                result[index] = true;
                sumVolume += VOLUME_OF_ITEMS[index];
                sumValue += VALUE_OF_ITEMS[index];
            } else {
                break;
            }
        }

        for (boolean r : result) {
            System.out.print(r + " ");
        }
        System.out.println();
        System.out.println("VALUE = " + sumValue);
        System.out.println("VOLUME = " + sumVolume);

    }

    private static void knapsackProblemGreedyAlgorithmByValueToVolumeRatio() {
        // we take items by VALUE TO VOLUME RATIO - the more the better
        boolean[] result = new boolean[NUMBER_OF_ITEMS];
        for (boolean r : result) {
            r = false;
        }

        int sumVolume = 0;
        int sumValue = 0;

        while (true) {
            int maxRatio = Integer.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
                if (!result[i]) {
                    if((VOLUME_OF_ITEMS[i] + sumVolume <= MAX_VOLUME) && (VALUE_OF_ITEMS[i]/VOLUME_OF_ITEMS[i]) > maxRatio) {
                        index = i;
                        maxRatio = VALUE_OF_ITEMS[i]/VOLUME_OF_ITEMS[i];
                    }
                }
            }

            if(index > -1) {
                result[index] = true;
                sumVolume += VOLUME_OF_ITEMS[index];
                sumValue += VOLUME_OF_ITEMS[index];
            } else {
                break;
            }
        }

        for(boolean r : result) {
            System.out.print(r + " ");
        }
        System.out.println();
        System.out.println("VALUE = " + sumValue);
        System.out.println("VOLUME = " + sumVolume);
    }

    private static int knapsackProblemDynamicProgramming() {
        int[][] knapsack = new int[NUMBER_OF_ITEMS][MAX_VOLUME + 1];

        for (int j = 0; j < MAX_VOLUME + 1; j++) {
            if (j < VOLUME_OF_ITEMS[0]) {
                knapsack[0][j] = 0;
            } else {
                knapsack[0][j] = VALUE_OF_ITEMS[0];
            }
        }

        for (int i = 1; i < NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j < MAX_VOLUME + 1; j++) {
                if (j < VOLUME_OF_ITEMS[i]) {
                    knapsack[i][j] = knapsack[i - 1][j];
                } else {
                    int w1 = knapsack[i - 1][j];
                    int w2 = VALUE_OF_ITEMS[i] + knapsack[i - 1][j - VOLUME_OF_ITEMS[i]];
                    if (w2 > w1) {
                        knapsack[i][j] = w2;
                    } else {
                        knapsack[i][j] = w1;
                    }
                }
            }
        }

        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j < MAX_VOLUME + 1; j++) {
                System.out.print(knapsack[i][j] + " ");
            }
            System.out.println();
        }
        return knapsack[NUMBER_OF_ITEMS - 1][MAX_VOLUME];
    }

    private static int knapsackProblemDivideAndConquer(int itemNumber, int maxVolume) {
        int value1;
        int value2;

        if (itemNumber == 0 && VOLUME_OF_ITEMS[itemNumber] > maxVolume) {
            return 0;
        }
        if (itemNumber == 0 && VOLUME_OF_ITEMS[itemNumber] <= maxVolume) {
            return VALUE_OF_ITEMS[itemNumber];
        }
        value1 = knapsackProblemDivideAndConquer(itemNumber - 1, maxVolume);
        if (itemNumber > 0 && VOLUME_OF_ITEMS[itemNumber] > maxVolume) {
            return value1;
        }
        value2 = VALUE_OF_ITEMS[itemNumber] + knapsackProblemDivideAndConquer(itemNumber - 1, maxVolume - VOLUME_OF_ITEMS[itemNumber]);
        if (value2 > value1) {
            return value2;
        } else {
            return value1;
        }
    }

    private static Knapsack knapsackProblemDivideAndConquerWithPrint(int itemNumber, int maxVolume) {
        Knapsack k1 = new Knapsack();
        Knapsack k2 = new Knapsack();

        if (itemNumber == 0 && VOLUME_OF_ITEMS[itemNumber] > maxVolume) {
            k1.getContent()[itemNumber] = 0;
            k1.setValue(0);
            return k1;
        }
        if (itemNumber == 0 && VOLUME_OF_ITEMS[itemNumber] <= maxVolume) {
            k2.getContent()[itemNumber] = 1;
            k1.setValue(VALUE_OF_ITEMS[itemNumber]);
            return k2;
        }
        Knapsack newKnapsack = knapsackProblemDivideAndConquerWithPrint(itemNumber - 1, maxVolume);
        k1.setContent(newKnapsack.getContent());
        k1.getContent()[itemNumber] = 0;
        k1.setValue(newKnapsack.getValue());
        if (itemNumber > 0 && VOLUME_OF_ITEMS[itemNumber] > maxVolume) {
            return k1;
        }
        newKnapsack = knapsackProblemDivideAndConquerWithPrint(itemNumber - 1, maxVolume - VOLUME_OF_ITEMS[itemNumber]);
        k2.setContent(newKnapsack.getContent());
        k2.getContent()[itemNumber] = 1;
        k2.setValue(VALUE_OF_ITEMS[itemNumber] + newKnapsack.getValue());
        if (k2.getValue() > k1.getValue()) {
            return k2;
        } else {
            return k1;
        }
    }

    private static void knapsackProblemBrutalForce() {
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

    static class Knapsack {
        private int[] content = new int[NUMBER_OF_ITEMS];
        private int value = 0;

        public int[] getContent() {
            return content;
        }

        public void setContent(int[] content) {
            this.content = content;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}



