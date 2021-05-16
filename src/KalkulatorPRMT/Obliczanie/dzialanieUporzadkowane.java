package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Dzialania.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class dzialanieUporzadkowane extends Dzialanie{
    HashMap<TypDzialania,Double> baza = new LinkedHashMap<>();

    public dzialanieUporzadkowane(){

    }

    public String toString(){
        StringBuilder build = new StringBuilder();

        for( TypDzialania keys : baza.keySet()){
            build.append(keys.getZnakDzialania()+""+baza.get(keys));
        }

        return build.toString();
    }

    public double wynik(){
        double wynik = 0;
        for(TypDzialania dzialania : baza.keySet()){
            if(dzialania.getClass() == Dodawanie.class ){

                wynik += baza.get(dzialania);

            }else  if(dzialania.getClass() == Odejmowanie.class ){

                wynik -= baza.get(dzialania);
            }
        }

        return wynik;
    }
    public void add(TypDzialania typ, Double obj){
        baza.put(typ,obj);
    }

}
