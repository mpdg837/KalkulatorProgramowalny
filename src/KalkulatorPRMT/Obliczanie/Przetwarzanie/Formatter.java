package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import java.text.DecimalFormat;

public class Formatter {

    public static String konwersjaDoStringa(Double num){
        if(Math.abs(num)<0.0000001){
            return "0";
        }else{


            return num+"";
        }
    }
}
