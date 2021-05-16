package KalkulatorPRMT.Obliczanie;

import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;
import KalkulatorPRMT.Stale;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Wyrazenie extends Dzialanie{
    private HashMap<String,Double> zmienne;
    HashMap<TypDzialania,String> baza = new LinkedHashMap<>();

    public Wyrazenie(HashMap<String,Double> zmienne){
        super();
        this.zmienne = zmienne;
    }

    public void add(TypDzialania typ, String obj){
        baza.put(typ,obj);
    }

    public String toString(){
        StringBuilder build = new StringBuilder();

        for( TypDzialania keys : baza.keySet()){
            build.append(keys.getZnakDzialania()+""+baza.get(keys));
        }

        return build.toString();
    }

    public dzialanieNieuporzadkowane toDzialanieNieuporzadkowane(){

        dzialanieNieuporzadkowane odp = new dzialanieNieuporzadkowane();

        try {

            for (TypDzialania dzialania : baza.keySet()) {
                if (baza.get(dzialania).equals("PI")) {
                    // Gdy stała PI
                    odp.add(dzialania, Stale.PI);
                } else if (baza.get(dzialania).equals("e")) {
                    // Gdy stała Eulera
                    odp.add(dzialania, Stale.eulerConstant);
                } else if (zmienne.containsKey(baza.get(dzialania))) {
                    // Wyszukuje wartości:

                    odp.add(dzialania, zmienne.get(baza.get(dzialania)));

                } else {
                    // Dodaję:
                    double value = Double.parseDouble(baza.get(dzialania));

                    odp.add(dzialania,value);
                }
            }
        }catch (NumberFormatException err){
            System.out.println("Błąd zapisu dla wyrażenia: "+this.toString());
            odp.clearAll();
        }
        return odp;
    }
}
