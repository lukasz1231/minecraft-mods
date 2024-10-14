import java.io.*;


class Alg1 {
    private int licz;
    private int n;
    private int k;
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
    private long pascal(int n, int k)
    {
        if (k > n - k) {
            k = n - k;
        }

        long wynik = n;

        for (int i = 1; i < k; i++) {  // zaczynam od 1 aby pominąć jedną operacje
            wynik = wynik * (n - i) / (i + 1);
            licz++;
        }
        // wzór iteracyjny to SNK = n(n-1)...(n-k+1) / (1*2*3*4*...*k)
        return wynik;
    }
    private String SN1(int n, int k)
    {
        licz = 1; //dlatego ze jedną operacją jest zlozenie silni do wzoru

        try{
        long wynik = factorial(n) / (factorial(k) * factorial(n - k));
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
        return String.valueOf(pascal(n,k)) + "\n" + String.valueOf(licz);
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
            bw.write("SN1");
            bw.newLine();
            bw.write(SN1(n,k));  // zapis danych do pliku
            bw.newLine();    // dodanie nowej linii
            bw.write("SN5");
            bw.newLine();
            bw.write(SN5(n,k));
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
    public Alg5()
    {

        try (BufferedReader br = new BufferedReader(new FileReader("In0105.txt"))) {
            int N = Integer.parseInt(br.readLine());

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Out0105.txt"))) {
            bw.write("Zad 5 nieoptymalne");
            bw.newLine();
            //bw.write(String.format("%d %d %d ", pocz  , j  , MAX));
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
    }
}