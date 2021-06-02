package KalkulatorPRMT.Obliczanie.Warunki;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;

public class InstrukcjaWarunkowa {

    private String ciag;
    private HashMap<String,Double> zmienne;

    public String etykieta;

    public InstrukcjaWarunkowa(String ciag, HashMap<String,Double> zmienne){
        this.ciag = ciag;
        this.zmienne = zmienne;
    }

    public void analizuj() throws MyError {

        char[] znaki = ciag.toCharArray();

        StringBuilder build = new StringBuilder();
        StringBuilder etykieta = new StringBuilder();
        StringBuilder command = new StringBuilder();

        int poziomzag = 0;

        boolean waruneko= false;

        for(char c : znaki){

            switch (c+""){
                case "("->{
                    poziomzag++;
                    if(poziomzag!=1) {
                        build.append(c);
                    }
                }
                case ")"->{
                    poziomzag--;
                    if(poziomzag>0){

                        build.append(c);
                    }else{
                        waruneko = true;
                    }
                }
                default -> {
                    if(poziomzag>0 && !waruneko){
                        build.append(c);
                    }else if(poziomzag>0 && waruneko){
                        etykieta.append(c);
                    }else{
                        if (!" ".equals(c + "")) {

                            command.append(c);
                        }

                    }
                }
            }
        }


        ZbiorWarunkow warunki = new ZbiorWarunkow(build.toString(),zmienne);

        if(warunki.toBoolean()){
            if(command.toString().equals("goto")) {
                this.etykieta = etykieta.toString();
            }else{
                throw new MyError("Podano złą etykiete");
            }
        }else{
            this.etykieta = "";
        }

    }
}
