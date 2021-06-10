package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {

    public static String konwersjaDoStringa(Double num) throws MyError{
        if(Math.abs(num)<0.0000001){
            return "0";
        }else{
            DecimalFormatSymbols symbole = new DecimalFormatSymbols(Locale.GERMAN);
            symbole.setDecimalSeparator('.');

            DecimalFormat dec = new DecimalFormat("##################################.#############################################",symbole);
            if(Math.abs(num)>2147483647){
                throw new MyError("Za duży wynik");
            }

            System.out.println(dec.format(num));
            return dec.format(num);
        }
    }
}
