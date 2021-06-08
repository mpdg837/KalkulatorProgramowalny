package KalkulatorPRMT.Obliczanie.Warunki;

import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Warunki.Rodzaje.Warunek;

import java.util.HashMap;

public class ZbiorWarunkow {

    private String ciag;
    private HashMap<String,Double> zmienne = new HashMap<>();

    public ZbiorWarunkow(String ciag,HashMap<String,Double> zmienne){
        // Deklaracja
        this.ciag = ciag;
        this.zmienne = zmienne;
    }

    public boolean toBoolean() throws MyError {

        // Rozpoczynam analizę ciągu warunków tak aby zwrócić czy dany warunek jest spełniony czy nie

        char[] znaki = ciag.toCharArray();

        boolean odp = false;

        // Deklarowanie zmiennej zawierającej builder zdania analizowanego
        StringBuilder zdanie = new StringBuilder();

        boolean zaczal = false;
        String dzialanie = "";

        for(char c : znaki){

            switch (c+""){

                case "&","|" ->{
                    // Wykrycie działań na warunkach. Wykonywane są one od lewej do prawej.

                    if(zdanie.length()>0 ){

                        // Zwieram zapisanie zdanie do funkcji warunek, opisujący pojedyńczy warunek
                        Warunek war = new Warunek(zdanie.toString(),zmienne);
                        boolean czyOk = war.toBoolean();

                        if(zaczal){
                            switch (dzialanie){
                                case "&" ->{
                                    // Działanie AND
                                    odp = odp & czyOk;
                                }
                                case "|" ->{
                                    // Działąnie OR
                                    odp = odp | czyOk;
                                }
                            }
                        }else{
                            // Gdy jeszcze nie wykonano działań
                            odp = czyOk;
                        }

                        // Resetuje zdanie skoro dokonano analizy
                        zdanie = new StringBuilder();

                        zaczal = true;
                        dzialanie = c+"";
                    }



                }
                default -> {
                    // Dodaje znaki do zdania analizowanego
                    zdanie.append(c);

                }
            }

        }

        // Dopełnienie algorytmu
        Warunek war = new Warunek(zdanie.toString(),zmienne);

        if(!zaczal){
            // Jeżeli był tylko jeden warunek
            odp = war.toBoolean();


        }else{
            // Jeżeli były dwa dokańczam procedurę
            boolean czyOk = war.toBoolean();

            switch (dzialanie){
                case "&" ->{
                    // AND
                    odp = odp & czyOk;
                }
                case "|" ->{

                    // OR
                    odp = odp | czyOk;
                }
            }
        }

        return odp;
    }
}
