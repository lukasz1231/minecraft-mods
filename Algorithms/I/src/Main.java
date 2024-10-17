import java.io.*;
import java.util.Arrays;


class Alg1 {
    private int licz;
    private int n;
    private int k;
    private int[][] pasc;
    private long factorial(long n) {
        long wynik=1;
        if(n == 0 || n == 1) {
            return 1;
        }
        else {
            for(int i = 2; i<=n; i++) {
                wynik=wynik*i;
                licz++;
            }
            return wynik;
        }
    }
    private void pascal(int n, int k)
    {

        pasc = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            pasc[i][0] = 1;  // Każdy pierwszy element w wierszu to 1

            for (int j = 1; j <= i; j++) {
                if(j>k)
                    break; //do obliczenia n nad k wystarczy że do tablicy dodamy kolumny nie wieksze niż k
                else {
                    pasc[i][j] = pasc[i - 1][j - 1] + pasc[i - 1][j];
                    licz++;
                }
            }
        }

    }
    private String SN1(int n, int k)
    {
        licz = 1; //dlatego ze jedną operacją jest zlozenie silni do wzoru
        int temp = n-k;
        try{
        long wynik = factorial(n) / (factorial(k) * factorial(temp));
        return String.valueOf(wynik)+"\n"+String.valueOf(licz);

        }
        catch (ArithmeticException e)
        {
            return "Błąd" + "\n" + String.valueOf(licz);
        }

    }
    private String SN5(int n, int k)
    {
        licz = 0;
        pascal(n,k);
        return String.valueOf(pasc[n][k]) + "\n" + String.valueOf(licz);
    }
    public Alg1()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("In0101.txt"))) {
            String linia = br.readLine();
            String[] liczby = linia.split(" ");
            this.n=Integer.parseInt(liczby[0]);
            this.k=Integer.parseInt(liczby[1]);
            br.close();
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0101.txt"))) {
            if(n<k||n<0||k<0)
                bw.write("podaj poprawne dane");
            else {
                bw.write("SN1 (nie wykona się poprawnie dla n lub k > 20 ze względu na ograniczony zakres longa)");
                bw.newLine();
                bw.write(SN1(n, k));  // zapis danych do pliku
                bw.newLine();    // dodanie nowej linii
                bw.write("SN5");
                bw.newLine();
                bw.write(SN5(n, k));
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }
    }
}

class Alg3 {
    private int MAX;
    private int j;
    private int pocz;
    public Alg3()
    {
        int max = 0;
        j=0;
        MAX=0;
        try (BufferedReader br = new BufferedReader(new FileReader("In0103.txt"))) {
            int N = Integer.parseInt(br.readLine());
            int[] liczby = new int[N];
            for(int i=0;i<N;i++)
            {
                liczby[i]= Integer.parseInt(br.readLine());
                if(liczby[i]+max<0)
                    max=0;
                else if (liczby[i]+max>=0)
                    max+=liczby[i];
                if(max>MAX)
                {
                    MAX = max;
                    j=i;
                }
            }
            max=MAX;
            int i=j;
            while(max!=0)
            {
                max-=liczby[i];
                pocz=i;
                i--;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0103.txt"))) {
            bw.write("Zad 3 nieoptymalne");
            bw.newLine();
            bw.write(String.format("%d %d %d ", pocz  , j  , MAX));  // zapis danych do pliku
            bw.close();
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }
    }
}
class Alg3opt {
    private int MAX;
    private int j;
    private int pocz;
    public Alg3opt()
    {
        int max = 0;
        j=0;
        MAX=0;
        int p = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("In0103.txt"))) {
            int N = Integer.parseInt(br.readLine());
            int[] liczby = new int[N];
            for(int i=0;i<N;i++)
            {
                liczby[i]= Integer.parseInt(br.readLine());
                if(liczby[i]+max<0)
                {
                    max=0;
                    p=i+1;
                }
                else if (liczby[i]+max>=0)
                    max+=liczby[i];
                if(max>MAX)
                {
                    MAX = max;
                    j=i;
                    pocz=p;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0103.txt"))) {
            bw.write("Zad 3 optymalne");
            bw.newLine();
            bw.write(String.format("%d %d %d ", pocz  , j  , MAX));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Alg5{
    private int a;
    private int b;
    private int j;
    private static int op =0;
    private int[] prime_numbers;

    public static boolean isPrime(int n) {
        if (n < 2) {
            op++;
            return false;
        }
        op++; //w kazdym przypadku dochodzi dodatkowe porownanie czy liczba jest <2
        for (int i = 2; i <= Math.sqrt(n); i++) {
            op++;
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public void Method1(int a, int b)
    {
        prime_numbers = new int[b-a+1];
        j = 0;
        op = 0;
        for(int i=a;i<=b;i++) {
            if (isPrime(i)) {
                prime_numbers[j] = i;
                j++; //tablica zaczyna indeksowanie od 0, ale j++ w tym miejscu sprawia ze ilosc liczb pierwszych jest o 1 wieksza od ostatniego indeksu w tablicy
            }
        }
    }
    public void Method2(int a, int b)
    {
        prime_numbers = new int[b-a+1];
        j = 0;
        op = 0;

        // Oblicz limit jako √b dla głównego sita
        int limit = (int) Math.sqrt(b);
        boolean[] basePrimes = sieve(limit);  // Generowanie liczb pierwszych do √b

        // Tworzymy tablicę dla przedziału [a, b]
        boolean[] isPrimeInRange = new boolean[b - a + 1];
        Arrays.fill(isPrimeInRange, true);  // Początkowo wszystkie liczby są traktowane jako pierwsze

        // Zaznaczamy wielokrotności liczb pierwszych z basePrimes
        for (int i = 2; i <= limit; i++) {
            if (basePrimes[i]) {
                int start = Math.max(i * i, (a + i - 1) / i * i);  // Znajdź pierwszą wielokrotność w zakresie [a, b]

                for (int j = start; j <= b; j += i) {
                    isPrimeInRange[j - a] = false;
                    op++;  // Operacja zaznaczenia
                }
            }
        }

        // Wypełniamy tablicę prime_numbers
        for (int i = 0; i < isPrimeInRange.length; i++) {
            if (isPrimeInRange[i] && (i + a) >= 2) {
                prime_numbers[j] = i + a;
                j++;
            }
        }
    }

    // Funkcja pomocnicza: Sito Eratostenesa do wygenerowania liczb pierwszych do √b
    private static boolean[] sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;  // 0 i 1 nie są pierwsze

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
    public Alg5()
    {

        try (BufferedReader br = new BufferedReader(new FileReader("In0105.txt"))) {
            String[] line = br.readLine().split(" ");
            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0105.txt"))) {
            bw.write("Zad 5");
            bw.newLine();
            bw.write(String.format("przedzial [%d, %d]",a,b));
            bw.newLine();
            bw.write("I metoda:");
            bw.newLine();
            Method1(a,b);
            for(int i = 0; i< j; i++)
                bw.write(String.format("%d ", prime_numbers[i]));
            bw.write(String.format("ilosc liczb pierwszych: %d ilosc wykonanych operacji: %d", j, op));
            bw.newLine();
            bw.write("II metoda:");
            bw.newLine();
            Method2(a,b);
            for(int i = 0; i< j; i++)
                bw.write(String.format("%d ", prime_numbers[i]));
            bw.write(String.format("ilosc liczb pierwszych: %d ilosc wykonanych operacji: %d", j, op));

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        //new Alg1();
        //new Alg3();
        //new Alg3opt();
        new Alg5();
    }

}
