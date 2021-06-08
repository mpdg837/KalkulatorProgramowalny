package KalkulatorPRMT.Obliczanie.Warunki;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;

public class InstrukcjaWarunkowa {

    private String ciag;
    private HashMap<String,Double> zmienne;

    public String etykieta;

    public InstrukcjaWarunkowa(String ciag, HashMap<String,Double> zmienne){

        // Deklaracja
        this.ciag = ciag;
        this.zmienne = zmienne;
    }

    public void analizuj(boolean ifnot) throws MyError {

        // Rozpoczynam analizę ciągu warunków oraz komend związanychh z instrukcją warunkową
        char[] znaki = ciag.toCharArray();

        // Tworzę pola zawierające poszczególne elementy funkcji warunkowej

        StringBuilder build = new StringBuilder(); // Ciąg warunkowy
        StringBuilder etykieta = new StringBuilder(); // nazwa edykiety do której ma odnieść funkcja gdy spełniony będzie warunek
        StringBuilder command = new StringBuilder(); // komenda : ma być 'goto'

        // Rozpoczęcie procesu
        int poziomzag = 0;

        boolean waruneko= false;

        for(char c : znaki){

            switch (c+""){
                case "("->{
                    // Sprawdzenie czy ilość nawiasów domykacjących zgadza sie z nawiasami zamykającymi
                    poziomzag++;
                    if(poziomzag!=1) {
                        build.append(c);
                    }
                }
                case ")"->{
                    // Sprawdzenie czy ilość nawiasów domykacjących zgadza sie z nawiasami zamykającymi
                    poziomzag--;
                    if(poziomzag>0){

                        build.append(c);
                    }else{
                        waruneko = true;
                    }
                }
                default -> {
                    if(poziomzag>0 && !waruneko){
                        // pobieram warunek
                        build.append(c);
                    }else if(poziomzag>0 && waruneko){

                        // pobieram etykiete
                        etykieta.append(c);
                    }else{
                        if (!" ".equals(c + "")) {
                            // Pomijając spację poberam komende : jedyna dostępna w tej speycfikacji 'goto'
                            command.append(c);
                        }

                    }
                }
            }
        }

        ZbiorWarunkow warunki = new ZbiorWarunkow(build.toString(),zmienne);

        if(command.toString().equals("goto")) {

            if(warunki.toBoolean()){
                this.etykieta = etykieta.toString();
            }else{
                this.etykieta = "";
            }


        }else if(command.toString().equals("notgoto")) {

            if(warunki.toBoolean()){
                this.etykieta = "";
            }else{
                this.etykieta = etykieta.toString();
            }


        }else{
            throw new MyError("Zła komenda");
        }




    }
}
