package KalkulatorPRMT.Obliczanie.Tablice;

import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;

import java.util.HashMap;

public class Deklarowanie {

    private String ciag;
    private HashMap<String,Double> zmienne;

    private int size;
    private String name;
    public Deklarowanie(String ciag, HashMap<String,Double> zmienne){

        // Deklaracja
        this.zmienne = zmienne;
        this.ciag = ciag;

    }

    public void analizuj() throws MyError {

        // Próba analizy nazwy zmiennej tablicowej wraz z numerem
        char[] znaki = ciag.toCharArray();

        // Iniciuję analizę

        StringBuilder build = new StringBuilder();
        StringBuilder nazwa = new StringBuilder();
        boolean wNawiasie = false;

        for(char znak : znaki){

            switch (znak+"") {
                case "[" -> {
                    // Początek numeru
                    wNawiasie = true;
                }
                case "]"->{
                    // Koniec numeru
                    wNawiasie = false;
                    break;
                }
                default ->{
                    if(wNawiasie){
                        // Dodaje do numeru komórki tablicy znak
                        build.append(znak);
                    }else{
                        // Dodaje do nazwy zmiennej znak
                        nazwa.append(znak);
                    }
                }
            }
        }

        String dzialanie = build.toString();
        Grupowanie grp = new Grupowanie(dzialanie,zmienne);

        // Zamieniam działanie wewnątrz pola numeru na liczbę całkowitą

        name = nazwa.toString();
        size = (int)(double)grp.wynik();

        if(size<=0){
            // Tablica nie ma ujemnych indeksów
            throw new MyError("Rozmiar tablicy musi być dodatni");
        }

    }
    public void utworzZmienne(){

        for(int n=0;n<size;n++){
            // Tworzenie komórek tablicy w pamięci
            zmienne.put(name+"_"+n,(double) 0);
        }
    }

    public HashMap<String,Double> getZmienne(){
        return zmienne;
    }
    public String getName(){return name;}
}
