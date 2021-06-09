package KalkulatorPRMT.Obliczanie.Warunki.Rodzaje;

import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;

import java.util.HashMap;

public class Warunek {

    public String ciag;
    public HashMap<String,Double> zmienne;

    public Warunek(String ciag,HashMap<String,Double> zmienne){
        this.ciag = ciag;
        this.zmienne = zmienne;
    }


    public boolean toBoolean() throws MyError {

        char[] znaki = ciag.toCharArray();

        boolean drugaCzesc = false;

        StringBuilder wyraz1 = new StringBuilder();
        StringBuilder wyraz2 = new StringBuilder();

        String warunek = "";

        for(char c : znaki){

            switch (c+""){
                case " "->{}
                case "=",">","<"->{
                    warunek = c+"";
                    drugaCzesc = true;
                }

                default -> {

                    if(drugaCzesc){
                        wyraz2.append(c);
                    }else{
                        wyraz1.append(c);
                    }
                }
            }
        }

        Grupowanie grp1 = new Grupowanie(wyraz1.toString(),zmienne);
        double wynik1 = grp1.wynik();

        Grupowanie grp2 = new Grupowanie(wyraz2.toString(),zmienne);
        double wynik2 = grp2.wynik();

        if(Rowny.znak.equals(warunek)){
            return wynik1 == wynik2;
        }else if(Wiekszy.znak.equals(warunek)){
            return wynik1 > wynik2;
        }else  if(Mniejszy.znak.equals(warunek)){
            return wynik1 < wynik2;
        }else{
            throw new MyError("Zła postać warunku");
        }

    }
}
