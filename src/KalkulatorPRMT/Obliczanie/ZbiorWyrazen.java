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
        char[] znaki = ciag.toCharArray();

        StringBuilder build = new StringBuilder();
        StringBuilder polecenie = new StringBuilder();

        boolean wykrytoPolecenie = false;

        for(char c : znaki){

            if(wykrytoPolecenie || !(c+"").equals(" ")){

                if (!wykrytoPolecenie) {
                    polecenie.append(c);
                } else {
                    build.append(c);
                }

                switch (polecenie.toString()) {

                    case "if","goto",":","dim" -> {
                        wykrytoPolecenie = true;
                    }

                }
            }
        }

        String[] odp = {polecenie.toString(),build.toString()};
        return odp;
    }

    public int getNumerIndeksu(String nazwa,int nx) throws MyError{

        for(int n=0;n<wyrazenia.size();n++){
            String wyrazenie = wyrazenia.get(n);

            StringBuilder etykieta = new StringBuilder();

            char[] znaki = wyrazenie.toCharArray();
            if(znaki.length>2) {
                if((znaki[0]+"").equals(":")) {
                    for (int x = 1; x < znaki.length; x++) {

                        etykieta.append(znaki[x]);

                    }
                }
            }

            if(etykieta.toString().equals(nazwa)){
                return n;
            }
        }

        throw new MyError("Nie odnaleziono rządanej etykiety");
    }

    public void rozwiaz() {

        // Skoro nowe rozwiązanie to czyszczę zawartość zmiennych i wyników

        wyniki.clear();
        zmienne.clear();
        try {
            for (int n=0;n<wyrazenia.size();n++) {

                String wyrazenie = wyrazenia.get(n);

                // Rozbijam zmienną na nazwę i wyrażenie

                String[] polecenie = getPolecenie(wyrazenie);

                switch (polecenie[0]){
                    case ":","//" -> {

                    }
                    case "dim" ->{
                        String zaw = polecenie[1].replaceAll(" ","");
                        Deklarowanie dek = new Deklarowanie(zaw,zmienne);

                        dek.analizuj();
                        dek.utworzZmienne();

                        zmienne = dek.getZmienne();

                    }
                    case "goto"->{
                        Skok skok = new Skok(wyrazenie);

                        String etykieta = skok.analizuj();

                        n = getNumerIndeksu(etykieta,n);
                    }
                    case "if"->{

                        InstrukcjaWarunkowa war = new InstrukcjaWarunkowa(polecenie[1],zmienne);

                        war.analizuj();

                        n = getNumerIndeksu(war.etykieta,n);
                    }
                    default -> {

                        String[] rozbicie = rozbijNaZmiennaIWyrazenie(wyrazenie);

                        if(rozbicie[0].length() == 0){

                            //Gdy nie mamy doczynienia z przypisywaniem zmiennych
                            Grupowanie grp = new Grupowanie(wyrazenie, zmienne);

                            Double wynik = grp.wynik();

                            if (wynik != null) {
                                System.out.println(wynik);

                                wyniki.add(wynik);
                            }
                        }else {

                            // Gdy mamy przypisywanie zmiennych

                            Grupowanie grp = new Grupowanie(rozbicie[1], zmienne);

                            Double wynik = grp.wynik();
                            if (wynik != null) {



                                System.out.println(rozbicie[0] + "=" + wynik);

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
