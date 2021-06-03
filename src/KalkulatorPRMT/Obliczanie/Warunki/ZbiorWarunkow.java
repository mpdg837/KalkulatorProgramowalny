package KalkulatorPRMT.Obliczanie.Warunki;

import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Warunki.Rodzaje.Warunek;

import java.util.HashMap;

public class ZbiorWarunkow {

    private String ciag;
    private HashMap<String,Double> zmienne = new HashMap<>();

    public ZbiorWarunkow(String ciag,HashMap<String,Double> zmienne){
        this.ciag = ciag;
        this.zmienne = zmienne;
    }

    public boolean toBoolean() throws MyError {
        char[] znaki = ciag.toCharArray();

        boolean odp = false;

        StringBuilder zdanie = new StringBuilder();

        boolean zaczal = false;
        String dzialanie = "";

        for(char c : znaki){

            switch (c+""){

                case "&","|" ->{

                    if(zdanie.length()>0 ){

                        Warunek war = new Warunek(zdanie.toString(),zmienne);
                        boolean czyOk = war.toBoolean();

                        if(zaczal){
                            switch (dzialanie){
                                case "&" ->{
                                    odp = odp & czyOk;
                                }
                                case "|" ->{
                                    odp = odp | czyOk;
                                }
                            }
                        }else{
                            odp = czyOk;
                        }

                        zdanie = new StringBuilder();

                        zaczal = true;
                        dzialanie = c+"";
                    }



                }
                default -> {

                    zdanie.append(c);

                }
            }

        }

        Warunek war = new Warunek(zdanie.toString(),zmienne);

        if(!zaczal){
            odp = war.toBoolean();


        }else{
            boolean czyOk = war.toBoolean();

            switch (dzialanie){
                case "&" ->{
                    odp = odp & czyOk;
                }
                case "|" ->{
                    odp = odp | czyOk;
                }
            }
        }

        return odp;
    }
}
