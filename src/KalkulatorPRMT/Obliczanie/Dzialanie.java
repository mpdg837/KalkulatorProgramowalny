package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Dzialanie {
    HashMap<TypDzialania,Object> baza = new LinkedHashMap<>();

    public Dzialanie(){

    }

    public void add(TypDzialania typ, Object obj){
        baza.put(typ,obj);
    }

    public String toString(){
        StringBuilder build = new StringBuilder();

        for( TypDzialania keys : baza.keySet()){
            build.append(keys.getZnakDzialania()+""+baza.get(keys));
        }

        return build.toString();
    }
}
