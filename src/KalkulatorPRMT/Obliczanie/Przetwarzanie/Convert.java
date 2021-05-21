package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.Dzialania.*;

import java.util.HashMap;

public class Convert {

    // Klasa odpowiadająca za konwersję wyrażenia strnig (napisu) do klasy Wyrażenie,
    // Przechowującym działanie podzielone na znaki działań, liczby i nazwy zmiennych.

    public static TypDzialania getZnak(String znak, int n){

        // Przetworzenie znaków działań na klasy działań

        switch (znak){
            case "+":
                return new Dodawanie();

            case "-":
                return new Odejmowanie();

            case "*":
                return new Mnozenie();

            case "/":
                return new Dzielenie();

            default:
                return new Dodawanie();

        }
    }
    public static Wyrazenie fromStringtoWyrazenie( HashMap<String,Double> zmienne,String ciag){

        // Utworzenie pustego działania
        Wyrazenie odp = new Wyrazenie(zmienne);

        // Zakładam, że wstępnym działąniem jest 0 + dalszy ciąg wyrażenia o ile pierwszym znakiem ciągu nie jest minus

        String znak = "+";
        StringBuilder wartosc = new StringBuilder();

        // Tworzę tablicę znaków do analizy ciągu działania
        char[] znaki = ciag.toCharArray();
        int n=0;

        // Sprawdzam czy pierwszy znak jest minusem:

        if(znaki.length>0){
            // Pod tym względem nie ma sensu analizować działania nieposiadającego znaków

            String minus = (new Odejmowanie()).getZnakDzialania();
            if(minus.equals(znaki[0]+"")){
                // Wykryto minus zatem zmieniam pierwszy znak

                znak = minus;
            }
        }

        // Rozpoczynam właściwą analizę:

        for(char zna : znaki){
            switch (zna+""){
                case " " ->{}
                case "+" , "-" , "*" ,"/" -> {

                    // Dodaje wcześniej zgromadzony znak i wyrażenie/liczbę do klasy Wyrażenie
                    if(wartosc.length()==0 && (zna+"").equals("-")){
                        // Gdy wprowadzam minusa, może to oznaczać liczbę ujemną.

                        wartosc.append(zna);
                    }else {
                        odp.add(getZnak(znak, n), wartosc.toString());

                        // Wykrywam znak działania
                        // Dodaje go do klasy Wyrażenie:

                        // Resetuje zmienne kontrolujące analizę:
                        n++;
                        znak = zna + "";
                        wartosc = new StringBuilder();
                    }
                }
                default -> // Dodaje znaki do StringBuildera odpowiadającego za skompletowanie pojedyńczych zmiennych/liczb które będą skłądowymi wyrażenia:
                        wartosc.append(zna);

            }
        }

        // Dodaje niedodany jeszcze do tablicy znak wyrażenia wraz z zmienną/liczbą:

        odp.add(getZnak(znak,n),wartosc.toString());

        return odp;
    }
}
