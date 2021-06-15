package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;
import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Stale;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Wyrazenie extends Dzialanie{

    // Klasa wyrażenie odpowiada za przechowywanie nieuporządkowanego działania zawierającego zmienne oraz liczby. Działanie takie dzięki tej klasie można
    // Przetowrzyć do działania, które zawieraa tylko
    final private HashMap<String,Double> zmienne;
    HashMap<TypDzialania,String> baza = new LinkedHashMap<>();

    public Wyrazenie(HashMap<String,Double> zmienne){
        super();
        this.zmienne = zmienne;
    }

    public void add(TypDzialania typ, String obj){
        baza.put(typ,obj);
    }

    public String toString(){
        // Aby złożyć stringa z działania tworzę StringBuildera
        StringBuilder build = new StringBuilder();

        boolean znakpierwszy = false;

        for( TypDzialania keys : baza.keySet()) {
            if (!znakpierwszy) {
                // Analizuję pierwszy znak działania aby niepotrzebnie nie pisać plusa

                if(!keys.getZnakDzialania().equals("+")){

                    // Nie mogę pominąć znaku:
                    build.append(keys.getZnakDzialania());

                }

                build.append(baza.get(keys));
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

    public DzialanieNieuporzadkowane toDzialanieNieuporzadkowane() throws MyError{

        // Rozpoczynam przetwarzanie klasy z Wyrażenia do działaniaNieuporządkowanego
        DzialanieNieuporzadkowane odp = new DzialanieNieuporzadkowane();

        try {

            for (TypDzialania dzialania : baza.keySet()) {


                if (baza.get(dzialania).equalsIgnoreCase("pi")) {
                    // Gdy stała PI
                    odp.add(dzialania, Stale.PI);
                } else if (baza.get(dzialania).equalsIgnoreCase("e")) {
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

            // Gdy zapis liczbowy nie jest prawidłowy
            throw new MyError("Błąd zapisu dla wyrażenia: "+this.toString());

        }
        return odp;
    }
}
