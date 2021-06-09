package KalkulatorPRMT.Obliczanie.Tablice;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;

public class DlugoscTablicy {
    public static int dlugosc(HashMap<String,Double> zmienne,String name) throws MyError {
        int k=0;
        while (true){
            String nazwa = name+"_"+k;
            if(!zmienne.containsKey(nazwa)){
                break;
            }
            k++;
        }

        if(k==0){
            throw new MyError("Nie wykryto tablicy");
        }
        return k;
    }
}
