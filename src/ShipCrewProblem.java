public class ShipCrewProblem {

    public static final int NUMBER_OF_CREW_MEMBERS = 5; // power of set
    public static final int[] ASTRONAUTS = {1, 2, 3, 4, 5}; // k1,k2,...,k5
    public static final int[][] QUALIFICATIONS = {
            {1, 4}, {2, 4}, {2, 3}, {1, 2}, {3, 4} // 1 = A; 2 = B; 3 = C; 4 = D
    };
    public static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_CREW_MEMBERS);

    public static void main(String[] args) {

        int[] crew = new int[NUMBER_OF_CREW_MEMBERS + 1];
        int[] bestCrew = new int[NUMBER_OF_CREW_MEMBERS];

        for (int i = 0; i < NUMBER_OF_SUBSETS; i++) {

            System.out.print("{ ");
            for(int j = 0; j < NUMBER_OF_CREW_MEMBERS; j++) {
                if(crew[j] == 1) {
                    System.out.print(j+1);
                }
            }
            System.out.println(" }");

            int k = 0;
            do {

                if (crew[k] == 1) {
                    crew[k] = 0;
                    k++;
                } else {
                    crew[k] = 1;
                    break;
                }
            } while (true);
        }

    }
}
