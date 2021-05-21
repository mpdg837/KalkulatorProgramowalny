package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.ArrayList;
import java.util.HashMap;

public class Grupowanie {

    public HashMap<String,Double> zmienne = new HashMap<String,Double>();

    private final String ciagDzilania;

    private double rozwiazSkladowa(String ciag) throws MyError {

        Wyrazenie dzial = Convert.fromStringtoWyrazenie(zmienne,ciag);
        dzialanieNieuporzadkowane nieuporzadkowane = dzial.toDzialanieNieuporzadkowane();
        dzialanieUporzadkowane  uporzadkowane = nieuporzadkowane.toDzialanieUporzadkowane();

        return uporzadkowane.wynik();
    }

    public Grupowanie(String ciag,HashMap<String,Double> zmienne){

        this.zmienne = zmienne;
        this.ciagDzilania = ciag;
    }

    public Double wynik(){
        try {
            int stopienzagniezdzenia = 0;

            StringBuilder build = new StringBuilder();
            ArrayList<StringBuilder> poziomy = new ArrayList<StringBuilder>();

            char[] znaki = ciagDzilania.toCharArray();

            poziomy.add(new StringBuilder());

            for (char c : znaki) {
                switch (c + "") {
                    case "(" -> {
                        stopienzagniezdzenia++;
                        if (stopienzagniezdzenia > poziomy.size() - 1) {
                            poziomy.add(new StringBuilder());
                        }
                    }
                    case ")" -> {
                        String zawartosc = poziomy.get(stopienzagniezdzenia).toString();
                        poziomy.set(stopienzagniezdzenia, new StringBuilder());
                        if (stopienzagniezdzenia > 0) {
                            stopienzagniezdzenia--;
                        }else{
                            throw new MyError("Za dużo nawiasów domykających");
                        }

                        StringBuilder nowazaw = poziomy.get(stopienzagniezdzenia);


                        nowazaw.append(rozwiazSkladowa(zawartosc));
                        poziomy.set(stopienzagniezdzenia, nowazaw);
                    }
                    default -> {
                        StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                        builder.append(c);
                        poziomy.set(stopienzagniezdzenia, builder);
                    }
                }
            }

            if(stopienzagniezdzenia>0){
                throw new MyError("Brakuje nawiasu domykającego działanie");
            }
            return rozwiazSkladowa(poziomy.get(stopienzagniezdzenia).toString());
        }catch (MyError err){
            System.out.println(err.getMessage());
            return null;
        }
    }
}
