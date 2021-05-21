package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.MyError;

import java.util.ArrayList;
import java.util.HashMap;

public class Grupowanie {

    // Klasa odpowiada za obslugę nawiasów w wyrażeniach

    // Lista zmiennych używanych przez realizator
    public HashMap<String,Double> zmienne = new HashMap<String,Double>();

    private final String ciagDzilania;

    private double rozwiazSkladowa(String ciag) throws MyError {
        // Rozwiązywanie wnętrza nawiasu

        Wyrazenie dzial = Convert.fromStringtoWyrazenie(zmienne,ciag);
        DzialanieNieuporzadkowane nieuporzadkowane = dzial.toDzialanieNieuporzadkowane();
        DzialanieUporzadkowane uporzadkowane = nieuporzadkowane.toDzialanieUporzadkowane();

        return uporzadkowane.wynik();
    }

    public Grupowanie(String ciag,HashMap<String,Double> zmienne){
        // Inicjacja
        this.zmienne = zmienne;
        this.ciagDzilania = ciag;
    }

    public Double wynik(){
        try {
            // Zmienna infromująca o poziomie zagnieżgdżenia wyrażeń rozwiązywanych przez klasy nieobsługujące nawiasów
            int stopienzagniezdzenia = 0;

            // Inicjacja analizy ciągu wyrażenia
            StringBuilder build = new StringBuilder();
            ArrayList<StringBuilder> poziomy = new ArrayList<StringBuilder>();

            char[] znaki = ciagDzilania.toCharArray();

            // Rozpoczęcie
            poziomy.add(new StringBuilder());

            for (char c : znaki) {
                switch (c + "") {
                    // Nawias otwarty następny poziom zagnieżdżenia
                    case "(" -> {
                        stopienzagniezdzenia++;
                        if (stopienzagniezdzenia > poziomy.size() - 1) {
                            // Gdy nie powstał jeszcze poziom zagnieżdżenia

                            poziomy.add(new StringBuilder());
                        }
                    }
                    case ")" -> {
                        String zawartosc = poziomy.get(stopienzagniezdzenia).toString();
                        poziomy.set(stopienzagniezdzenia, new StringBuilder());
                        if (stopienzagniezdzenia > 0) {
                            // Obniżam poziom zagnieżdżenia
                            stopienzagniezdzenia--;
                        }else{

                            // Poziom zagnieżdżenia nie może być ujemny w prawidłowo opisanym działaniu
                            throw new MyError("Za dużo nawiasów domykających");
                        }

                        // Dodanie wyniku obliczania wnętrza nawiasu
                        StringBuilder nowazaw = poziomy.get(stopienzagniezdzenia);


                        nowazaw.append(rozwiazSkladowa(zawartosc));
                        poziomy.set(stopienzagniezdzenia, nowazaw);
                    }
                    default -> {

                        // Dopisywanie znaków niedotyczących podziału na nawiasy

                        StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                        builder.append(c);
                        poziomy.set(stopienzagniezdzenia, builder);
                    }
                }
            }

            // Sprawdzam czy wszystkie wyrażenia są domknięte
            if(stopienzagniezdzenia>0){
                throw new MyError("Brakuje nawiasu domykającego działanie");
            }

            // Wysyłam wynik
            return rozwiazSkladowa(poziomy.get(stopienzagniezdzenia).toString());
        }catch (MyError err){

            // Wykryto błąd podczas realizacji działania
            System.out.println(err.getMessage());
            return null;
        }
    }
}
