package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.Formatter;
import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;
import KalkulatorPRMT.Obliczanie.Tablice.Deklarowanie;
import KalkulatorPRMT.Obliczanie.Warunki.InstrukcjaWarunkowa;
import KalkulatorPRMT.Obliczanie.Warunki.Skok;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZbiorWyrazen {

    List<String> wyrazenia = new ArrayList<>();
    List<Double> wyniki= new ArrayList<>();
    HashMap<String,Double> zmienne= new HashMap<>();

    public List<String> wynikiStringi= new ArrayList<>();
    public List<String> getListaStringow(){
        return wynikiStringi;
    }

    public ZbiorWyrazen(){

    }


    public HashMap<String, Double> getZmienne() {
        return zmienne;
    }

    public void add(String wyrazenia){
        // Dodawanie wyrażeń do realizacji w ciągu
        this.wyrazenia.add(wyrazenia);
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
                case "[" -> wykrytoEnumeracje = true;

                case "]" ->{

                    String enu = enumeracja.toString();
                    wyrazenie.append("_");

                    Grupowanie grp = new Grupowanie(enu, zmienne);
                    int wynik = (int)(double)grp.wynik();

                    wyrazenie.append(wynik);

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

    public String[] getPolecenie(String ciag){
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

                    case "if","goto",":","dim","gosub","return","//" -> wykrytoPolecenie = true;


                }
            }
        }

        return new String[]{polecenie.toString(),build.toString()};
    }

    public int getNumerIndeksu(String nazwa) throws MyError{

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

    private void analizuj() throws MyError{
        wyniki.clear();
        zmienne.clear();

        List<Integer> powrotyGOSUB= new ArrayList<>();
        int zagn = 0;

        for (int n=0;n<wyrazenia.size();n++) {

            String wyrazenie = wyrazenia.get(n);

            // Rozbijam zmienną na nazwę i wyrażenie

            String[] polecenie = getPolecenie(wyrazenie);

            switch (polecenie[0]){
                case "new" -> zmienne = new HashMap<>();
                case "do","",":","//" -> {

                }
                case "end"-> n=wyrazenia.size();


                case "gosub"->{
                    Skok skok = new Skok(wyrazenie);

                    String etykieta = skok.analizuj();
                    if (!etykieta.equals("")) {
                        zagn ++;
                        int mem = n;
                        n = getNumerIndeksu(etykieta);

                        if(powrotyGOSUB.size()>zagn){
                            powrotyGOSUB.set(zagn,mem);
                        }else{
                            powrotyGOSUB.add(mem);

                        }
                    }
                }
                case "return"->{
                    n = powrotyGOSUB.get(zagn-1);
                    zagn --;

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

                    n = getNumerIndeksu(etykieta);
                }
                case "ifnot"-> {

                    InstrukcjaWarunkowa war = new InstrukcjaWarunkowa(polecenie[1], zmienne);

                    war.analizuj(true);
                    if (!war.etykieta.equals("")) {
                        n = getNumerIndeksu(war.etykieta);
                    }
                }
                case "if"-> {

                    InstrukcjaWarunkowa war = new InstrukcjaWarunkowa(polecenie[1], zmienne);

                    war.analizuj(false);
                    if (!war.etykieta.equals("")) {
                        n = getNumerIndeksu(war.etykieta);
                    }
                }
                default -> {

                    String[] rozbicie = rozbijNaZmiennaIWyrazenie(wyrazenie);

                    if(rozbicie[0].length() == 0){

                        //Gdy nie mamy doczynienia z przypisywaniem zmiennych
                        Grupowanie grp = new Grupowanie(wyrazenie, zmienne);

                        Double wynik = grp.wynik();

                        if (wynik != null) {

                            wynikiStringi.add("="+Formatter.konwersjaDoStringa(wynik));
                            wyniki.add(wynik);
                        }
                    }else {

                        // Gdy mamy przypisywanie zmiennych

                        Grupowanie grp = new Grupowanie(rozbicie[1], zmienne);

                        Double wynik = grp.wynik();
                        if (wynik != null) {


                            wynikiStringi.add(rozbicie[0] + "=" + Formatter.konwersjaDoStringa(wynik));
                            zmienne.put(rozbicie[0], wynik);
                        }
                    }
                }
            }

        }
    }

    public void sprawdzPoprawnosc(){
        try {

            // Zwracam błąd

            analizuj();
            JOptionPane.showMessageDialog(new JPanel(),"Skrypt kalkulatora wykonał się poprawnie","Poprawność zapisu",JOptionPane.INFORMATION_MESSAGE);
        }catch (MyError err){

            // Zwracam błąd
            JOptionPane.showMessageDialog(new JPanel(),err.getMessage(),"Błąd",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void rozwiaz() {

        // Skoro nowe rozwiązanie to czyszczę zawartość zmiennych i wyników

        try {

            analizuj();
        }catch (MyError err){

            // Zwracam błąd
            JOptionPane.showMessageDialog(new JPanel(),err.getMessage(),"Błąd",JOptionPane.ERROR_MESSAGE);

        }
    }
}
