public class NewtonBinomial {

    private static int[][] tab = new int[50][50];

    public static void main(String[] args) {
        int n = 12;
        int k = 4;
        System.out.println("Recursion = " + doNewtonBinomialDivideAndConquer(n, k));
        System.out.println(newtonBinomialProgrammingDynamic(n, k));
    }

    private static int doNewtonBinomialDivideAndConquer(int n, int k) {
        if (n < k) {
            System.out.println("Niedozowlne, spróbuj ponownie");
            return -1;
        }
        if (k == 0 || n == k) {
            return 1;
        }
        return doNewtonBinomialDivideAndConquer(n - 1, k - 1) + doNewtonBinomialDivideAndConquer(n - 1, k);
    }
    private static int newtonBinomialProgrammingDynamic(int n, int k) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == j || j == 0) {
                    tab[i][j] = 1;
                } else {
                    tab[i][j] = tab[i - 1][j - 1] + tab[i - 1][j];
                }
            }
        }

        return tab[n][k];
    }


}
