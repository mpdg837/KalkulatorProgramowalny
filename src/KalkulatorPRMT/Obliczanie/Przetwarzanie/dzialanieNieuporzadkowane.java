package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.Dzialania.Dzielenie;
import KalkulatorPRMT.Obliczanie.Dzialania.Mnozenie;
import KalkulatorPRMT.Obliczanie.Dzialania.TypDzialania;
import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class dzialanieNieuporzadkowane extends Dzialanie {

    HashMap<TypDzialania,Double> baza = new LinkedHashMap<>();

    public dzialanieNieuporzadkowane(){

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

    public void add(TypDzialania typ, Double obj){
        baza.put(typ,obj);
    }

    public void clearAll(){
        baza.clear();
    }

    public dzialanieUporzadkowane toDzialanieUporzadkowane() throws MyError {
        double valmem = 0;

        TypDzialania dziala = null;
        dzialanieUporzadkowane uporzadkowane = new dzialanieUporzadkowane();

        for(TypDzialania dzialania : baza.keySet()){

            if(dzialania.getClass() == Mnozenie.class ){
                // Mnoże poprzedni zbiorczy wynik z zberaną liczbą
                valmem *= baza.get(dzialania);

            }else  if(dzialania.getClass() == Dzielenie.class ){
                // Dzielę poprzedni zbiorczy wynik z zberaną liczbą
                if(baza.get(dzialania) == 0){
                    throw new MyError("Nie dzielimy przez 0");
                }else {
                    valmem /= baza.get(dzialania);
                }
            }else{
                if(dziala !=null) {
                    // Inicujuję działanie
                    uporzadkowane.add(dziala, valmem);
                }

                dziala = dzialania;
                valmem = baza.get(dzialania);
            }

        }
        if(dziala !=null) {
            // Jeżeli nie zainicjowano to dodaję niedodane wcześniej dane:

            uporzadkowane.add(dziala, valmem);
        }

        return uporzadkowane;
    }

}
