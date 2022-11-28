public class Bacteria {

    private static int[] bacterias = new int[50];

    public static void main(String[] args) {
        int minute = 28;
        bacteriaDynamicProgramming(minute);
        for (int i = 0; i <= minute; i += 2) {
            System.out.print(bacterias[i] + " ");
        }
        System.out.println();
        System.out.println(bacteriaDivideAndConquer(28));
    }

    private static long bacteriaDivideAndConquer(int minute) {
        if (minute == 0) {
            return 1;
        }
        // jesli minuta jest nieparzysta to znaczy ze w tej minucie sie bakterie nie rozmnozyly
        // czyli ilosc bakterii jest taka jak z minuty poprzedniej gdy sie rozmnozyly
        if (minute % 2 != 0) {
            return bacteriaDivideAndConquer(minute - 1);
        }

        return (2 * bacteriaDivideAndConquer(minute - 2));
    }

    private static void bacteriaDynamicProgramming(int minute) {
        bacterias[0] = 1;

        for (int i = 1; i <= minute; i++) {
            if (i % 2 != 0) {
                bacterias[i] = bacterias[i - 1];
            } else {
                bacterias[i] = bacterias[i - 1] * 2;
            }
        }
    }
}
