package KalkulatorPRMT.Obliczanie.Tablice;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;

public class DlugoscTablicy {
    public static int dlugosc(HashMap<String,Double> zmienne,String name) throws MyError {
        int k=0;

        // Analizuję komórki pamieci o nazwach wskazujących na to że należą do tablicy o nazwie name

        while (true){

            // Tworze nazwę do analizy
            String nazwa = name+"_"+k;

            // Sprawdzam czy taka nazwa istnieje
            if(!zmienne.containsKey(nazwa)){
                break;
            }
            k++;
        }

        if(k==0){
            // Brak wykrycia tablicy
            throw new MyError("Nie wykryto tablicy");
        }
        return k;
    }
}
