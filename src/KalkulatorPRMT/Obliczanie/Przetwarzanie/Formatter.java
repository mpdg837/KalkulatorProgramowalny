package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.text.DecimalFormat;

public class Formatter {

    public static String konwersjaDoStringa(Double num) throws MyError{
        if(Math.abs(num)<0.0000001){
            return "0";
        }else{

            DecimalFormat dec = new DecimalFormat("##################################.#############################################");
            if(Math.abs(num)>2147483647){
                throw new MyError("Za duży wynik");
            }
            return dec.format(num);
        }
    }
}
