import java.util.ArrayList;

public class ShipCrewProblem {

    public static final int NUMBER_OF_CREW_MEMBERS = 5; // power of set
    public static final int[][] QUALIFICATIONS = {
            {1, 4}, {2, 4}, {2, 3}, {1, 2}, {3, 4} // 1 = A; 2 = B; 3 = C; 4 = D, pierwsz para odpowaida 1 kosmonaucie druga para 2 kosmonaucie itd....
    };
    public static final int NUMBER_OF_SUBSETS = (int) Math.pow(2, NUMBER_OF_CREW_MEMBERS);
    public static final int NEEDED_QUALIFICATIONS_VALUE = 10; // A+B+C+D=10, bo A=1;B=2 itd...

    public static void main(String[] args) {
        shipCrewProblemGreedyAlgorithm();
    }

    private static void shipCrewProblemGreedyAlgorithm() {
        int[][] qualifications = {{3, 2, 5}, {3, 5}, {1, 4}, {2, 3, 4}};
        int[] astronauts = {1, 2, 3, 4, 5};
        int i = 0; // indeks do poruszania się po astronautach
        ArrayList<Integer> myCrew = new ArrayList<>(); // załoga, która bęzie na statku

        while (i < astronauts.length) { // wykonuj, dopóki nie przekroczy liczby astronautów
            int currentAstronaut = astronauts[i]; // idziemy po kolei przez każdego astronaute, zaczynając od pierwszego

            int astronautSkills = 0;
            // iterujemy przez wszystkie kwalifikacje i patrzymy czy nasz currentAstronaut posiada daną kwalifikacje
            for (int j = 0; j < qualifications.length; j++) {
                for (int k = 0; k < qualifications[j].length; k++) {
                    if (qualifications[j][k] == currentAstronaut) {
                        astronautSkills++;
                    }
                }
            }

            // teraz patrzymy czy nasz currentAstronaut ma przynajmniej jedną kwalifikacje, jeśli tak to bierzemy go na statek,
            // ponieważ na tym polega AZ(bierzemy pierwszy lepszy wynik, który pasuje do rozwiązania)
            if (astronautSkills >= 1) {
                myCrew.add(currentAstronaut);
            } else { // w przeciwnym wypadku idziemy do kolejnego astronauty
                i++;
                continue;
            }

            int matchingQualifications = 0;
            // iterujemy po raz kolejny przez wszystkie kwalifikacje i patrzymy, czy jakis astronuta posiada daną kwalifikacje
            for (int l = 0; l < qualifications.length; l++) {
                for (int m = 0; m < qualifications[l].length; m++) {
                    // jeśli znajdzie się jakikolwiek astronauta z daną kwalifikacją, to inkrementujemy licznik kwalifikacji CAŁEJ ZAŁOGI
                    // i poprzez break wychodzimy z tej pretli, przez co przechodzimy do sprawdzenia kolejnej kwalifikacji
                    if (myCrew.contains(qualifications[l][m])) {
                        matchingQualifications++;
                        break;
                    }

                }
            }

            // jeśli licznik kwalifikacji CAŁEJ ZAŁOGI wynosi 4 (liczba zależna od długości tabeli kwalifikacji)
            // kończymy dobieranie załogi, jeśli nie, przechozdimy do kolejnego astronauty
            if (matchingQualifications == qualifications.length) {
                break;
            }
            i++;

        }

        // sprawzamy, czy udało nam się dobrać załogę
        if (i == astronauts.length) {
            System.out.println("NO RESULT");
        } else {
            System.out.println("CREW: " + myCrew);
        }
    }

    private static void shipCrewProblemBrutalForce() {
        int[] crew = new int[NUMBER_OF_CREW_MEMBERS + 1]; // tutaj przechowuje kazdy mozliwy podzbior
        int[] bestCrew = crew.clone();
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

        System.out.println("Best Number Of Crew Members: " + bestCrewMembers);
        System.out.println("Best Crew That Meet An Expectations: ");
        print(bestCrew);
    }

    private static void print(int[] arr) {
        for (int i =0; i < arr.length - 1; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
