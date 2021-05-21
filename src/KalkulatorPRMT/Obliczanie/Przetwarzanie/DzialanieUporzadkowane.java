package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.Dzialania.Dodawanie;
import KalkulatorPRMT.Obliczanie.Dzialania.Odejmowanie;
import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class DzialanieUporzadkowane extends Dzialanie{
    HashMap<TypDzialania,Double> baza = new LinkedHashMap<>();

    public DzialanieUporzadkowane(){

    }

    public String toString(){
        // Aby złożyć stringa z działania tworzę StringBuildera
        StringBuilder build = new StringBuilder();

        boolean znakpierwszy = false;

        for( TypDzialania keys : baza.keySet()) {
            if (!znakpierwszy) {
                // Analizuję pierwszy znak działania aby niepotrzebnie nie pisać plusa

                if(keys.getZnakDzialania().equals("+")){
                    // Pomijam plusa
                    build.append(baza.get(keys));
                }else{
                    // Nie mogę pominąć znaku:
                    build.append(keys.getZnakDzialania());
                    build.append(baza.get(keys));
                }
                znakpierwszy = true;
            } else {
                // Nie mogę pominąć znaku
                build.append(keys.getZnakDzialania());
                build.append(baza.get(keys));
            }
        }

        // Zamieniam StringBuildera na Stringa

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
