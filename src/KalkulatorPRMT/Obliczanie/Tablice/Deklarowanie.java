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
        this.zmienne = zmienne;
        this.ciag = ciag;

    }

    public void analizuj() throws MyError {
        char[] znaki = ciag.toCharArray();

        StringBuilder build = new StringBuilder();
        StringBuilder nazwa = new StringBuilder();
        boolean wNawiasie = false;

        for(char znak : znaki){

            switch (znak+"") {
                case "[" -> {

                    wNawiasie = true;
                }
                case "]"->{
                    wNawiasie = false;
                    break;
                }
                default ->{
                    if(wNawiasie){
                        build.append(znak);
                    }else{
                        nazwa.append(znak);
                    }
                }
            }
        }

        String dzialanie = build.toString();
        Grupowanie grp = new Grupowanie(dzialanie,zmienne);

        name = nazwa.toString();
        size = (int)(double)grp.wynik();
        if(size<=0){
            throw new MyError("Rozmiar tablicy musi być dodatni");
        }

    }
    public void utworzZmienne(){

        for(int n=0;n<size;n++){
            zmienne.put(name+"_"+n,(double) 0);
        }
    }

    public HashMap<String,Double> getZmienne(){
        return zmienne;
    }
}
