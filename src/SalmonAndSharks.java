import static java.lang.Math.min;

public class SalmonAndSharks {

    private static final double EULER = Math.exp(0.003);
    private static final int POPULATION = 1_000_000;

    public static void main(String[] args) {

//        System.out.println(populationDivideAndConquer(18)); // mniej wiecej w tej minucie zostanie osiagneta 1/2 populacji
//        System.out.println(populationDivideAndConquer(23)); // mniej wiecej w tej minucie zostanie osiagneta 1/3 populacji
//        System.out.println(populationDivideAndConquer(33)); // mniej wiecej w tej minucie zostanie osiagneta 1/10 populacji
//        System.out.println(populationDivideAndConquer(47)); // mniej wiecej w tej minucie zostanie osiagneta 1/100 populacji
//        System.out.println(populationDivideAndConquer(65)); // mniej wiecej w tej minucie wyginie gatunek
//
//        populationDynamicProgramming(18);
//        populationDynamicProgramming(23);
//        populationDynamicProgramming(33);
//        populationDynamicProgramming(47);
        populationDynamicProgramming(65);
    }

    private static int populationDivideAndConquer(int t) { // t - time
        if (t == 0) {
            return POPULATION;
        }
        int population = populationDivideAndConquer(t - 1);
        double permil = population * 0.001;
        return (int) (population * EULER - (4 * t * permil));
    }

    private static void populationDynamicProgramming(int t) {
        int[] population = new int[100];
        population[0] = 1_000_000;

        for (int i = 1; i <= t; i++) {
            population[i] = (int) ((population[i - 1] * EULER) - (population[i - 1] * (4 * i * 0.001)));
        }

        for (int i = 0; i <= t; i++) {
            System.out.print(population[i] + " ");
        }
        System.out.println();
    }

}
