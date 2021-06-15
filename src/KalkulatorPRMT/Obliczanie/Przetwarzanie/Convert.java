package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.Dzialania.*;

import java.util.HashMap;

public class Convert {

    // Klasa odpowiadająca za konwersję wyrażenia strnig (napisu) do klasy Wyrażenie,
    // Przechowującym działanie podzielone na znaki działań, liczby i nazwy zmiennych.

    public static TypDzialania getZnak(String znak){

        // Przetworzenie znaków działań na klasy działań

        return switch (znak) {
            case "-" -> new Odejmowanie();
            case "*" -> new Mnozenie();
            case "/" -> new Dzielenie();
            default -> new Dodawanie();
        };
    }
    public static Wyrazenie fromStringtoWyrazenie( HashMap<String,Double> zmienne,String ciag){

        // Utworzenie pustego działania
        Wyrazenie odp = new Wyrazenie(zmienne);

        // Zakładam, że wstępnym działąniem jest 0 + dalszy ciąg wyrażenia o ile pierwszym znakiem ciągu nie jest minus

        String znak = "+";
        StringBuilder wartosc = new StringBuilder();

        // Tworzę tablicę znaków do analizy ciągu działania
        char[] znaki = ciag.toCharArray();


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

        boolean sprawdzMinus=false;
        for(char zna : znaki){
            switch (zna+""){
                case " " ->{}
                case "-" ->{
                    if (sprawdzMinus) {
                        // Działania na minusach
                        char[] znaki2 = wartosc.toString().toCharArray();
                        StringBuilder zmianaciagu = new StringBuilder();

                        if(znaki2.length>0){
                            if((znaki2[0]+"").equals("-")){
                                for(int n=1;n<znaki2.length;n++){
                                    zmianaciagu.append(znaki2[n]);
                                }
                            }else{
                                zmianaciagu.append("-");
                                for (char c : znaki2) {
                                    zmianaciagu.append(c);
                                }
                            }

                            wartosc = zmianaciagu;
                        }else{
                            wartosc.append("-");
                        }


                    }else
                    if(wartosc.length()!=0 || !(zna+"").equals("-")) {

                            odp.add(getZnak(znak), wartosc.toString());

                            znak = zna + "";
                            wartosc = new StringBuilder();


                    }
                    sprawdzMinus = true;
                }
                case "*" ,"/","+" -> {

                    // Dodaje wcześniej zgromadzony znak i wyrażenie/liczbę do klasy Wyrażenie
                    if(wartosc.length()!=0){
                        sprawdzMinus = true;
                        odp.add(getZnak(znak), wartosc.toString());

                        // Wykrywam znak działania
                        // Dodaje go do klasy Wyrażenie:

                        // Resetuje zmienne kontrolujące analizę:

                        znak = zna + "";
                        wartosc = new StringBuilder();
                    }
                }
                default -> {// Dodaje znaki do StringBuildera odpowiadającego za skompletowanie pojedyńczych zmiennych/liczb które będą skłądowymi wyrażenia:
                    sprawdzMinus = false;
                    wartosc.append(zna);
                }
            }
        }

        // Dodaje niedodany jeszcze do tablicy znak wyrażenia wraz z zmienną/liczbą:

        odp.add(getZnak(znak),wartosc.toString());

        return odp;
    }
}
