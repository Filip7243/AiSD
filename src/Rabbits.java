public class Rabbits {

    private static int[] rabbits = new int[30];

    public static void main(String[] args) {
        System.out.println(rabbitsPopulationDivideAndConquerDynamicProgramming(8));
    }

    private static int rabbitsPopulationDivideAndConquer(int month) {
        if(month == 1 || month == 2) {
            return 1;
        } else {
            return rabbitsPopulationDivideAndConquer(month - 1) + rabbitsPopulationDivideAndConquer(month - 2);
        }
    }

    private static int rabbitsPopulationDivideAndConquerDynamicProgramming(int month) {
        rabbits[1] = 1;
        rabbits[2] = 1;
        for(int i = 3; i <= month; i++) {
            rabbits[i] = rabbits[i-1] + rabbits[i-2];
        }

        return rabbits[month];
    }


}
