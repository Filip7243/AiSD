public class IceCreamProblem {

    private static final int SET_SIZE = 6; // licza elementów zbioru
    private static int[] permutation = new int[SET_SIZE + 1];
    private static final int[][] ICE_CREAM_MACHINE = {
            {0, 7, 20, 21, 12, 23},
            {27, 0, 13, 16, 46, 5},
            {53, 15, 0, 25, 27, 6},
            {16, 2, 35, 0, 47, 10},
            {31, 29, 5, 18, 0, 4},
            {28, 24, 11, 17, 5, 0}
    };
    private static int bestValue = Integer.MAX_VALUE;
    private static String bestCombination = "";

    public static void main(String[] args) {
        createPermutation(0);
        System.out.println(bestValue);
        System.out.println(bestCombination);
    }

    private static void createPermutation(int n) { // n - element listy od któreg zaczynamy

        if (n == SET_SIZE) { // jeśli zwraca true, to znaczy, że została stworzona kolejna permutacja
            int currentValue = 0;
            var currentCombination = new StringBuilder();

            for (int i = 0; i < SET_SIZE - 1; i++) {
                currentValue += ICE_CREAM_MACHINE[permutation[i] - 1][permutation[i+1] - 1];
                currentCombination.append(permutation[i]);
            }

            currentValue += ICE_CREAM_MACHINE[permutation[SET_SIZE - 1] - 1][permutation[0] - 1];
            currentCombination.append(permutation[SET_SIZE - 1]);
            currentCombination.append(permutation[0]);

            if(currentValue < bestValue) {
                bestValue = currentValue;
                bestCombination = currentCombination.toString();
            }
        } else {
            for (int i = 1; i <= SET_SIZE; i++) { // i - element, który wstawiamy do listy

                int j;
                for (j = 0; j < n; j++) { // j - indeks, na który wstawiamy element i do listy
                    if (permutation[j] == i) {
                        break;
                    }
                }
                if (j == n) {
                    permutation[j] = i;
                    createPermutation(n + 1);
                }
            }
        }
    }

}
