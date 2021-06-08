package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;
import KalkulatorPRMT.Obliczanie.Tablice.Deklarowanie;
import KalkulatorPRMT.Obliczanie.Warunki.InstrukcjaWarunkowa;
import KalkulatorPRMT.Obliczanie.Warunki.Skok;
import KalkulatorPRMT.Obliczanie.Warunki.ZbiorWarunkow;

import javax.swing.*;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZbiorWyrazen {

    List<String> wyrazenia = new ArrayList<String>();
    ArrayList<String> wyniki= new ArrayList<String>();
    HashMap<String,Double> zmienne= new HashMap<String, Double>();

    public ZbiorWyrazen(){

    }

    public List<String> getWyrazenia() {
        return wyrazenia;
    }

    public ArrayList<String> getWyniki() {
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
        boolean wykrytoEnumeracje = false;

        StringBuilder enumeracja = new StringBuilder();

        for(char znak : znaki){
            switch (znak+""){
                // Gdy znajduje równa się
                case "[" ->{
                    wykrytoEnumeracje = true;
                }
                case "]" ->{

                    String enu = enumeracja.toString();
                    wyrazenie.append("_");

                    Grupowanie grp = new Grupowanie(enu, zmienne);
                    int wynik = (int)(double)grp.wynik();

                    wyrazenie.append(wynik+"");

                    wykrytoEnumeracje = false;
                    enumeracja = new StringBuilder();
                }
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
                    if(wykrytoEnumeracje){
                        enumeracja.append(znak);
                    }else {
                        // W każdym innym przypadku dopisuje znak do wyrażenia
                        wyrazenie.append(znak);
                    }
                }
            }
        }

        // Zamiana builderów na Stringi

        zbitka[0] = nazwa.toString().replace(" ","");
        zbitka[1] = wyrazenie.toString();

        return zbitka;
    }

    public String[] getPolecenie(String ciag) throws MyError{

        // Pobieram polecenie z ciągu

        char[] znaki = ciag.toCharArray();

        // Deklaruje pola na polecenie
        StringBuilder build = new StringBuilder();
        StringBuilder polecenie = new StringBuilder();

        boolean wykrytoPolecenie = false;

        for(char c : znaki){

            if(wykrytoPolecenie || !(c+"").equals(" ")){

                if (!wykrytoPolecenie) {
                    // Dodaje znaki do polecenia
                    polecenie.append(c);
                } else {
                    // Dodaje znaki do parametrów
                    build.append(c);
                }

                switch (polecenie.toString()) {

                    case "if","goto",":","//","dim","gosub","return","show","end","new" -> {

                        // Wykryto jedno z poleceń zdefiniowanych dla tego programu
                        wykrytoPolecenie = true;
                    }

                }
            }
        }

        String[] odp = {polecenie.toString(),build.toString()};
        return odp;
    }

    public int getNumerIndeksu(String nazwa,int nx) throws MyError{

        // Zamieniam nazwę etykety na numer komendy
        for(int n=0;n<wyrazenia.size();n++){
            String wyrazenie = wyrazenia.get(n);

            // Deklaruje pobieranie nazwy etykiety
            StringBuilder etykieta = new StringBuilder();

            char[] znaki = wyrazenie.toCharArray();
            if(znaki.length>2) {
                if((znaki[0]+"").equals(":")) {
                    // Szukam aż do znalezienia końca nazwy
                    for (int x = 1; x < znaki.length; x++) {

                        etykieta.append(znaki[x]);

                    }
                }
            }

            // Sprawdzam czy nazwa zgadza się z szukaną
            if(etykieta.toString().equals(nazwa)){
                return n;
            }
        }

        // Jeżeli nie znajdzie się etykiety należy wyświetlić błąd
        throw new MyError("Nie odnaleziono rządanej etykiety");
    }

    public void rozwiaz() {

        // Skoro nowe rozwiązanie to czyszczę zawartość zmiennych i wyników

        wyniki.clear();
        zmienne.clear();

        List<Integer> powrotyGOSUB= new ArrayList<Integer>();
        int zagn = 0;

        try {
            for (int n=0;n<wyrazenia.size();n++) {

                String wyrazenie = wyrazenia.get(n);

                // Rozbijam zmienną na nazwę i wyrażenie

                String[] polecenie = getPolecenie(wyrazenie);

                switch (polecenie[0]){
                    case "new" ->{
                        // Czyszczenie pamięci
                        zmienne = new HashMap<String,Double>();
                    }
                    case ":","//" -> {
                        // W analizie nazwy etykiet i komentarze pomijamy
                    }
                    case "end"->{
                        // Zakończenie skryptu kalkulatora
                        n=wyrazenia.size();
                    }

                    case "gosub"->{

                        // Skok do etkiety z zwrotem (return)
                        Skok skok = new Skok(wyrazenie);

                        String etykieta = skok.analizuj();
                        if (!etykieta.equals("")) {

                            // Określam zagnieżdzenie aby po wprowadzeniu return wrócić w to samo miejsce
                            zagn ++;
                            int mem = n;
                            n = getNumerIndeksu(etykieta, n);

                            // Sprawdzam czy nie muszę dodać nowego poziomu zagnieżdzenia
                            if(powrotyGOSUB.size()>zagn){
                                powrotyGOSUB.set(zagn,mem);
                            }else{
                                powrotyGOSUB.add(mem);

                            }
                        }
                    }
                    case "return"->{
                        // Wracam do etykiety poprzedniej na podstawie gosub
                        n = powrotyGOSUB.get(zagn-1);
                        powrotyGOSUB.set(zagn-1,0);
                        zagn --;

                    }
                    case "dim" ->{

                        // Deklarowanie tablicy
                        String zaw = polecenie[1].replaceAll(" ","");

                        // Dodanie talbicy na zaś
                        Deklarowanie dek = new Deklarowanie(zaw,zmienne);

                        dek.analizuj();
                        if(dek.getName().length()>0) {
                            // Tablica musi mieć jakąś nazwę

                            dek.utworzZmienne();

                            // Dodaje nowy wykaz zmiennych
                            zmienne = dek.getZmienne();
                        }else{
                            throw new MyError("Tablica musi mieć nazwę");
                        }
                    }
                    case "goto"-> {
                        // Skok do innej rtykiety
                        Skok skok = new Skok(wyrazenie);

                        String etykieta = skok.analizuj();
                        if (!etykieta.equals("")) {
                            n = getNumerIndeksu(etykieta, n);
                        }else{
                            throw new MyError("Nie podano nazwy etykiety do której ma odbyć się skok");
                        }
                    }

                    case "if"->{
                        // Instrukcja warunkowa
                        InstrukcjaWarunkowa war = new InstrukcjaWarunkowa(polecenie[1],zmienne);

                        war.analizuj(false);

                        if (!war.etykieta.equals("")) {
                            n = getNumerIndeksu(war.etykieta, n);
                        }else{
                            throw new MyError("Zawartość warunku nie może być pusta");
                        }
                    }
                    default -> {

                        String[] rozbicie = rozbijNaZmiennaIWyrazenie(wyrazenie);

                        if(rozbicie[0].length() == 0){

                            //Gdy nie mamy doczynienia z przypisywaniem zmiennych
                            Grupowanie grp = new Grupowanie(wyrazenie, zmienne);

                            Double wynik = grp.wynik();

                            if (wynik != null && !polecenie[0].equals("show")) {

                                wyniki.add(" = " + wynik);
                            }
                        }else {

                            // Gdy mamy przypisywanie zmiennych

                            Grupowanie grp = new Grupowanie(rozbicie[1], zmienne);

                            Double wynik = grp.wynik();
                            if (wynik != null && !polecenie[0].equals("show")) {



                                wyniki.add(rozbicie[0] + "=" + wynik);
                                zmienne.put(rozbicie[0], wynik);
                            }
                        }
                    }
                }


            }
        }catch (MyError err){

            // Zwracam błąd
            JOptionPane.showMessageDialog(new JPanel(),err.getMessage(),"Błąd",JOptionPane.ERROR_MESSAGE);

        }
    }
}
