package KalkulatorPRMT.Obliczanie.Warunki.Rodzaje;

import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Przetwarzanie.Grupowanie;

import java.util.HashMap;

public class Warunek {

    public String ciag;
    public HashMap<String,Double> zmienne;

    public Warunek(String ciag,HashMap<String,Double> zmienne){
        // Deklaracja
        this.ciag = ciag;
        this.zmienne = zmienne;
    }


    public boolean toBoolean() throws MyError {

        char[] znaki = ciag.toCharArray();

        boolean drugaCzesc = false;

        // Deklaruje dwa zdania do porównań
        StringBuilder wyraz1 = new StringBuilder();
        StringBuilder wyraz2 = new StringBuilder();

        // Deklaruje znak warunku
        String warunek = "";

        for(char c : znaki){

            switch (c+""){
                case " "->{}
                case "=",">","<"->{
                    // Wykryto znak warunku
                    warunek = c+"";
                    drugaCzesc = true;
                }

                default -> {
                    // Pobieram jeden z dwóch wyrazów do porównania
                    if(drugaCzesc){
                        wyraz2.append(c);
                    }else{
                        wyraz1.append(c);
                    }
                }
            }
        }

        // Przeliczanie zebranych wyrażeń obliczeniowych w celu porównania ich
        Grupowanie grp1 = new Grupowanie(wyraz1.toString(),zmienne);
        double wynik1 = grp1.wynik();

        Grupowanie grp2 = new Grupowanie(wyraz2.toString(),zmienne);
        double wynik2 = grp2.wynik();

        // Sprawdzenie spełnienia warunków

        if(Rowny.znak.equals(warunek)){

            // Równość
            return wynik1 == wynik2;
        }else if(Wiekszy.znak.equals(warunek)){
            // Większy
            return wynik1 > wynik2;
        }else  if(Mniejszy.znak.equals(warunek)){

            // Mniejszy
            return wynik1 < wynik2;
        }else{
            throw new MyError("Zła postać warunku");
        }

    }
}
