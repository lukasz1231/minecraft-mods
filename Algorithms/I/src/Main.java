import java.io.*;


class Alg1 {
    private int licz;
    private int n;
    private int k;
    private long factorial(long n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        else {
            licz++;
            return factorial(n - 1) * n;
        }
    }
    private long pascal(int n, int k)
    {
        if (k > n - k) {
            k = n - k;
        }

        long wynik = 1;

        for (int i = 0; i < k; i++) {
            wynik = wynik * (n - i) / (i + 1);
            licz++;
        }

        return wynik;
    }
    private String SN1(int n, int k)
    {
        licz = 1;
        long numerator = factorial(n); // factorial(n)
        long denominator = factorial(k) * factorial(n - k); // factorial(k) * factorial(n-k)

        // Sprawdzamy, czy nie dochodzi do dzielenia przez zero
        if (denominator == 0) {
            return "Błąd: nie można dzielić przez zero!";
        }

        // Obliczamy wynik
        long wynik = numerator / denominator;
        return String.valueOf(wynik)+"\n"+String.valueOf(licz);
    }
    private String SN5(int n, int k)
    {
        licz = 1;
        return String.valueOf(pascal(n,k)) + "\n" + String.valueOf(licz);
    }
    public Alg1() // tu bedzie plik
    {
        try (BufferedReader br = new BufferedReader(new FileReader("In0101.txt"))) {
            String linia = br.readLine();
            String[] liczby = linia.split(" ");
            this.n=Integer.parseInt(liczby[0]);
            this.k=Integer.parseInt(liczby[1]);
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0101.txt"))) {
            bw.write("SN1");
            bw.newLine();
            bw.write(SN1(n,k));  // zapis danych do pliku
            bw.newLine();    // dodanie nowej linii
            bw.write("SN5");
            bw.newLine();
            bw.write(SN5(n,k));
        } catch (IOException e) {
            e.printStackTrace(); // obsługa błędu
        }
    }
}

public class Main {
    public static void main(String[] args) {

        new Alg1();
    }
}