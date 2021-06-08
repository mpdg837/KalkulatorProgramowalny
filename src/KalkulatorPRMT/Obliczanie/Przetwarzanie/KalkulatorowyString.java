package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.HashMap;
import java.util.zip.DeflaterOutputStream;

public class KalkulatorowyString {

    private String ciagwejsciowy;
    private HashMap<String,Double> zmienne = new HashMap<>();

    public KalkulatorowyString(String ciagWejsciowy, HashMap<String,Double> zmienne){

        // Deklaracja
        this.ciagwejsciowy = ciagWejsciowy;
        this.zmienne = zmienne;
    }

    private String pobierzZmienna(String nazwa) throws MyError{
        String tresc = "";

        // Pobieranie zmiennej z tablicy zmiennych

        if(zmienne.containsKey(nazwa)){
            // WYrkyto więc wypisuję
            tresc = zmienne.get(nazwa)+"";
        }else{
            // Nie wykryto więc próbuje parsować
            try{
                double tryx = Double.parseDouble(nazwa);
            }catch (NumberFormatException err){
                // Błąd wartości
                throw  new MyError("Podano zły format liczby lub zmiennej");
            }
            tresc = nazwa;
        }

        return tresc;
    }

    public String convert() throws MyError{
        // Konwersja
        // Inicjacja

        StringBuilder builder = new StringBuilder();

        // Pobranie ciągu wejściowego do analizy
        char[] znaki = ciagwejsciowy.toCharArray();

        boolean trybTekstowy = false;

        // Utworzene pol na treść i nazwę ew zmiennej

        StringBuilder tresc = new StringBuilder();
        StringBuilder nazwazmiennej = new StringBuilder();

        for(char c : znaki){

            switch (c+""){
                case "'" ->{
                    // Początek/Koniec stringa
                    trybTekstowy = !trybTekstowy;

                    }
                case "+" -> {
                    // Sumowanie stringa
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
                        // Dodawanie do treści stringa
                        tresc.append(c);
                    }else{
                        // Dodawanie do ew nazwy zmiennej
                        nazwazmiennej.append(c);
                    }
                }
            }
        }

        // Dokończenie
        if(nazwazmiennej.length()>0){
            builder.append(pobierzZmienna(nazwazmiennej.toString()));
        }else{
            builder.append(tresc.toString());
        }
        return builder.toString();
    }
}
