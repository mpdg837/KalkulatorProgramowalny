package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;

public class KalkulatorowyString {

    private final String ciagwejsciowy;
    private final HashMap<String,Double> zmienne;

    public KalkulatorowyString(String ciagWejsciowy, HashMap<String,Double> zmienne){
        this.ciagwejsciowy = ciagWejsciowy;
        this.zmienne = zmienne;
    }

    private String pobierzZmienna(String nazwa) throws MyError{
        String tresc;

        if(zmienne.containsKey(nazwa)){
            tresc = zmienne.get(nazwa)+"";
        }else{
            try{
                Double.parseDouble(nazwa);
            }catch (NumberFormatException err){
                throw  new MyError("Podano zły format liczby lub zmiennej");
            }
            tresc = nazwa;
        }

        return tresc;
    }

    public String convert() throws MyError{
        StringBuilder builder = new StringBuilder();

        char[] znaki = ciagwejsciowy.toCharArray();

        boolean trybTekstowy = false;

        StringBuilder tresc = new StringBuilder();
        StringBuilder nazwazmiennej = new StringBuilder();

        for(char c : znaki){

            switch (c+""){
                case "'" ->trybTekstowy = !trybTekstowy;

                case "+" -> {

                    if (!trybTekstowy) {
                        if (nazwazmiennej.length() > 0) {
                            builder.append(pobierzZmienna(nazwazmiennej.toString()));
                        } else {
                            builder.append(tresc.toString());
                        }

                        nazwazmiennej = new StringBuilder();
                        tresc = new StringBuilder();
                    }
                }
                default -> {
                    if(trybTekstowy){
                        tresc.append(c);
                    }else{
                        nazwazmiennej.append(c);
                    }
                }
            }
        }

        if(nazwazmiennej.length()>0){
            builder.append(pobierzZmienna(nazwazmiennej.toString()));
        }else{
            builder.append(tresc.toString());
        }
        return builder.toString();
    }
}
