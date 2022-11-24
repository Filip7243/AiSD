import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ChangeProblem {

    public static void main(String[] args) throws IOException {
        InputStreamReader str = new InputStreamReader(System.in);
        BufferedReader wejscie = new BufferedReader(str);
        String tekst;
        Random losuj = new Random();
        final int[] MONETY = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        final int LIMIT_WYDANYCH_MONET = 1000; // limit wydanych monet + 1
        final int LICZBA_LOSOWAN = 100;
        int minLiczbaWydanychMonet = LIMIT_WYDANYCH_MONET;
        int[] wydaneMonety = new int[LIMIT_WYDANYCH_MONET];
        int[] minWydaneMonety = new int[LIMIT_WYDANYCH_MONET];
        int moneta, zl, gr, r;

        System.out.println("Podaj reszte..");
        System.out.print("zlotych: ");
        tekst = wejscie.readLine();
        zl = Integer.parseInt(tekst);
        System.out.print("groszy: ");
        tekst = wejscie.readLine();
        gr = Integer.parseInt(tekst);
        for (int i = 0; i < LICZBA_LOSOWAN; i++) {
            r = zl * 100 + gr;
            int liczbaWydanychMonet = 0;
            while (r > 0 && liczbaWydanychMonet < LIMIT_WYDANYCH_MONET) {
                moneta = MONETY[losuj.nextInt(MONETY.length)];
                if (r >= moneta) {
                    wydaneMonety[liczbaWydanychMonet] = moneta;
                    r = r - moneta;
                    liczbaWydanychMonet++;
                }
            }
            if (minLiczbaWydanychMonet > liczbaWydanychMonet) {
                minLiczbaWydanychMonet = liczbaWydanychMonet;
                for (int j = 0; j < liczbaWydanychMonet; j++)
                    minWydaneMonety[j] = wydaneMonety[j];
            }
        }
        if (minLiczbaWydanychMonet < LIMIT_WYDANYCH_MONET) {
            System.out.print("Reszta: ");
            for (int i = 0; i < minLiczbaWydanychMonet; i++)
                System.out.print(minWydaneMonety[i] / 100.0 + " ");
        } else
            System.out.print("Nie znaleziono rozwiazania.");
        System.out.println();
    }
}
