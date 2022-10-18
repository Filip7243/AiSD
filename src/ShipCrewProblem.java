public class ShipCrewProblem {

    public static final int NUMBER_OF_CREW_MEMBERS = 5; // power of set
    public static final int[][] QUALIFICATIONS = {
            {1, 4}, {2, 4}, {2, 3}, {1, 2}, {3, 4} // 1 = A; 2 = B; 3 = C; 4 = D, pierwsz para odpowaida 1 kosmonaucie druga para 2 kosmonaucie itd....
    };
    public static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_CREW_MEMBERS);
    public static int NEEDED_QUALIFICATIONS_VALUE = 10; // A+B+C+D=10, bo A=1;B=2 itd...

    public static void main(String[] args) {

        int[] crew = new int[NUMBER_OF_CREW_MEMBERS + 1]; // tutaj przechowuje kazdy mozliwy podzbior
        int[] bestCrew = crew.clone();
        int bestValueOfQualification = Integer.MIN_VALUE;
        int currentValueOfQualification = 0; // ta zmienna sluzy do reprezentacji kwalifikacji zalogi
        int crewMembers = 0; // ilosc zalogi z potrzebnymi kawlifikacjami
        int bestCrewMembers = Integer.MAX_VALUE; // ilosc zalogi z potrzebnymi kawlifikacjami, im mniej tym lepiej

        for (int i = 0; i < NUMBER_OF_SUBSETS; i++) { // iteruje przez wszystkei mozliwe podzbiory zbiory astronautow

            for (int j = 0; j < NUMBER_OF_CREW_MEMBERS; j++) { // iteruje przez wszystkie elementy pozdbioru
                // jesli ktorys z elementow bedzie 1, to:
                if (crew[j] == 1) {
                    currentValueOfQualification += (QUALIFICATIONS[j][0] + QUALIFICATIONS[j][1]); // dodaj do zmiennej currentValueOfQualification wartosc kwalifikacji danego astronauty
                    crewMembers++; // zwieksz liczbe zalogi
                }
            }

            // tutaj sprawdzam czy wartosc zmiennej currentValueOfQualification osiaga 10(1+2+3+4) i czy liczba zalogi jest mniejsza od ostatniej najlepszej ilosci zalogi
            // jesli tak, to:
            if (currentValueOfQualification == NEEDED_QUALIFICATIONS_VALUE && crewMembers <= bestCrewMembers) {
                bestValueOfQualification = currentValueOfQualification; // do zmiennej bestValueOfQualification przypisujemy wartosc currentValueOfQualification
                bestCrewMembers = crewMembers; // do zmiennej bestCrewMembers przypisujemy wartosc crewMembers
                bestCrew = crew.clone(); // tutaj do zminnej bestCrew przypisujemy aktualny podzbior
            } else {
                // jesli if nie zostanie spelniony to zerujemy obie zmienne
                currentValueOfQualification = 0;
                crewMembers = 0;
            }

            // tutaj tworzone sa kolejne podzbiory, indeks na ktorym wystepuje 1 oznacza number astronauty(indeksujemy od 0)
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

        System.out.println("Best Value Of Qualifications: " + bestValueOfQualification);
        System.out.println("Best Number Of Crew Members: " + bestCrewMembers);
        System.out.println("Best Crew That Meet An Expectations: ");
        print(bestCrew);
    }

    private static void print(int[] arr) {
        for (int j : arr) {
            System.out.print(j);
        }
        System.out.println();
    }
}
