package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Dzialania.Dzielenie;
import KalkulatorPRMT.Obliczanie.Dzialania.Mnozenie;
import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;
import KalkulatorPRMT.Obliczanie.Dzialanie;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class dzialanieNieuporzadkowane extends Dzialanie {

    HashMap<TypDzialania,Double> baza = new LinkedHashMap<>();

    public dzialanieNieuporzadkowane(){

    }
    public String toString(){
        StringBuilder build = new StringBuilder();

        for( TypDzialania keys : baza.keySet()){
            build.append(keys.getZnakDzialania()+""+baza.get(keys));
        }

        return build.toString();
    }

    public void add(TypDzialania typ, Double obj){
        baza.put(typ,obj);
    }

    public void clearAll(){
        baza.clear();
    }

    public dzialanieUporzadkowane toDzialanieUporzadkowane(){
        double valmem = 0;

        TypDzialania dziala = null;
        dzialanieUporzadkowane uporzadkowane = new dzialanieUporzadkowane();

        for(TypDzialania dzialania : baza.keySet()){

            if(dzialania.getClass() == Mnozenie.class ){

                valmem *= baza.get(dzialania);

            }else  if(dzialania.getClass() == Dzielenie.class ){

                valmem /= baza.get(dzialania);
            }else{
                if(dziala !=null) {
                    uporzadkowane.add(dziala, valmem);
                }

                dziala = dzialania;
                valmem = baza.get(dzialania);
            }

        }
        if(dziala !=null) {
            uporzadkowane.add(dziala, valmem);
        }

        return uporzadkowane;
    }

}
