public class NewtonBinomial {

    private static int[][] tab;

    public static void main(String[] args) {
        int n = 17;
        int k = 3;
        System.out.println("Recursion = " + doNewtonBinomial(n,k));
        tab = new int[n+1][k+1];
        System.out.println("Iteratively = " + doNewtonBinomial(tab, n+1, k+1));
    }

    private static int doNewtonBinomial(int n, int k) {
        if(n < k) {
            return -1;
        }
        if(k == 0 || n == k) {
            return 1;
        }
        return doNewtonBinomial(n-1, k-1) + doNewtonBinomial(n-1, k);
    }

    private static int doNewtonBinomial(int[][] tab, int n, int k) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < k; j++) {
                if(i < j) {
                    continue;
                }
                else if(i == j || j == 0) {
                    tab[i][j] = 1;
                } else {
                    tab[i][j] = tab[i-1][j-1] + tab[i-1][j];
                }
            }
        }

        return tab[n-1][k-1];
    }


}
