package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZbiorWyrazen {

    List<String> wyrazenia = new ArrayList<String>();
    List<Double> wyniki= new ArrayList<Double>();
    HashMap<String,Double> zmienne= new HashMap<String, Double>();

    public ZbiorWyrazen(){

    }

    public List<String> getWyrazenia() {
        return wyrazenia;
    }

    public List<Double> getWyniki() {
        return wyniki;
    }

    public HashMap<String, Double> getZmienne() {
        return zmienne;
    }

    public void add(String wyrazenia){
        // Dodawanie wyrażeń do realizacji w ciągu
        this.wyrazenia.add(wyrazenia);
    }

    public void clearAll(){
        wyrazenia.clear();
        zmienne.clear();
        wyniki.clear();
    }

    public String[] rozbijNaZmiennaIWyrazenie(String ciag) throws MyError{

        // Próbuję rozdzielić na nazwę potencjalnie przypisywanej zminnej i wyrażenie

        String[] zbitka = new String[2];

        char[] znaki = ciag.toCharArray();

        // Inicjacja builderów wyrażenia i nazwy
        StringBuilder wyrazenie = new StringBuilder();
        StringBuilder nazwa = new StringBuilder();

        // Inicjacja analizy stringa
        boolean wykrytoRownaSie = false;

        for(char znak : znaki){
            switch (znak+""){
                // Gdy znajduje równa się
                case "=" ->{
                    if(!wykrytoRownaSie) {
                        // Skoro to będzie zmienna to trzeba przenieść nazwę z wyrażenia do nazwy
                        wykrytoRownaSie = true;
                        nazwa = wyrazenie;

                        // Wyzerowanie wyrażenia
                        wyrazenie = new StringBuilder();
                    }else{
                        // Za dużo znaków równa się
                        throw new MyError("Za duża ilość znaków '=' dla wyrażenia");
                    }
                }
                default -> {
                    // W każdym innym przypadku dopisuje znak do wyrażenia
                    wyrazenie.append(znak);
                }
            }
        }

        // Zamiana builderów na Stringi

        zbitka[0] = nazwa.toString();
        zbitka[1] = wyrazenie.toString();

        return zbitka;
    }
    public void rozwiaz() {

        // Skoro nowe rozwiązanie to czyszczę zawartość zmiennych i wyników

        wyniki.clear();
        zmienne.clear();
        try {
            for (String wyrazenie : wyrazenia) {

                // Rozbijam zmienną na nazwę i wyrażenie
                String[] rozbicie = rozbijNaZmiennaIWyrazenie(wyrazenie);

                if(rozbicie[0].length() == 0){

                    //Gdy nie mamy doczynienia z przypisywaniem zmiennych
                    Grupowanie grp = new Grupowanie(wyrazenie, zmienne);
                    if (grp.wynik() != null) {
                        double wynik = grp.wynik();

                        System.out.println(wynik);

                        wyniki.add(wynik);
                    }
                }else {

                    // Gdy mamy przypisywanie zmiennych

                    Grupowanie grp = new Grupowanie(rozbicie[1], zmienne);
                    if (grp.wynik() != null) {
                        double wynik = grp.wynik();

                        System.out.println(rozbicie[0] + "=" + wynik);

                        zmienne.put(rozbicie[0], wynik);
                    }
                }

            }
        }catch (MyError err){

            // Zwracam błąd
            System.out.println(err.getMessage());
        }
    }
}
