package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Dzialania.*;

import java.util.HashMap;

public class Convert {

    public static TypDzialania getZnak(String znak,int n){
        switch (znak){
            case "+":
                return new Dodawanie(n);

            case "-":
                return new Odejmowanie(n);

            case "*":
                return new Mnozenie(n);

            case "/":
                return new Dzielenie(n);

            default:
                return new Dodawanie(n);

        }
    }
    public static Wyrazenie fromStringtoWyrazenie( HashMap<String,Double> zmienne,String ciag){
        Wyrazenie odp = new Wyrazenie(zmienne);

        String znak = "+";
        StringBuilder wartosc = new StringBuilder();

        char[] znaki = ciag.toCharArray();
        int n=0;

        for(char zna : znaki){
            switch (zna+""){
                case "+":
                case "-":
                case "*":
                case "/":

                    odp.add(getZnak(znak,n),wartosc.toString());


                    n ++;
                    znak = zna+"";
                    wartosc = new StringBuilder();
                    break;
                default:
                    wartosc.append(zna);
                    break;

            }
        }

        odp.add(getZnak(znak,n),wartosc.toString());

        return odp;
    }
}
