package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.DodatekMatematyczny;
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

            var znaki = ciagDzilania.toCharArray();

            // Rozpoczęcie
            poziomy.add(new StringBuilder());


            for (char c : znaki) {
                switch (c + "") {
                    // Nawias otwarty następny poziom zagnieżdżenia
                    case "(" -> {
                        // Wykrywanie komend;

                        // Przejście do następnego
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

                        // Definiowanie miejsc na komendy
                        char[] znaki2 = nowazaw.toString().toCharArray();

                        StringBuilder komenda2 = new StringBuilder();
                        StringBuilder komenda3 = new StringBuilder();
                        StringBuilder komenda4 = new StringBuilder();

                        // Wydobywanie komend dwuznakowych
                        for(int n=znaki2.length-2;n<znaki2.length;n++)
                            if (n >= 0) {
                                komenda2.append(znaki2[n]);
                            }

                        // Trzyznakowych
                        for(int n=znaki2.length-3;n<znaki2.length;n++)
                            if (n >= 0) {
                                komenda3.append(znaki2[n]);
                            }


                        // Czteroznakowych
                        for(int n=znaki2.length-4;n<znaki2.length;n++)
                            if (n >= 0) {
                                komenda4.append(znaki2[n]);
                            }

                        boolean czyWykrytoKomende = false;

                        if(komenda2.toString().toLowerCase().equals("ln")){

                            nowazaw = new StringBuilder();

                            // Czyszczę StringBuildera z zbędnych znaków
                            for(int n=0;n<znaki2.length-2;n++){
                                nowazaw.append(znaki2[n]);
                            }

                            // Wykonuję logarytm liniowy
                            nowazaw.append(DodatekMatematyczny.ln(rozwiazSkladowa(zawartosc)));

                            czyWykrytoKomende = true;
                        }
                        // Analiza komend 3 znakowych
                        switch (komenda3.toString()) {
                            case "sin", "cos", "tan", "abs", "exp", "log" -> {
                                nowazaw = new StringBuilder();

                                // Czyszczę stringbuildera z niepotrzebnych znaków
                                for (int n = 0; n < znaki2.length - 3; n++) {
                                    nowazaw.append(znaki2[n]);
                                }

                                // Wykonuję działanie
                                switch (komenda3.toString().toLowerCase()) {
                                    case "sin" -> nowazaw.append(Math.sin(rozwiazSkladowa(zawartosc)));
                                    case "cos" -> nowazaw.append(Math.cos(rozwiazSkladowa(zawartosc)));
                                    case "tan" -> nowazaw.append(Math.tan(rozwiazSkladowa(zawartosc)));
                                    case "abs" -> nowazaw.append(Math.abs(rozwiazSkladowa(zawartosc)));
                                    case "exp" -> nowazaw.append(Math.exp(rozwiazSkladowa(zawartosc)));
                                    case "log" -> nowazaw.append(DodatekMatematyczny.log(rozwiazSkladowa(zawartosc)));
                                    case "pow" -> nowazaw.append(Math.pow(rozwiazSkladowa(zawartosc), 2));
                                }
                                czyWykrytoKomende = true;
                            }
                        }

                        // Analiza komend 4 znakowych
                        switch (komenda4.toString()) {
                            case "sqrt", "pow2", "pow3", "fact" -> {

                                // Czyszczę StringBuildera z zbędnych znaków
                                nowazaw = new StringBuilder();
                                for (int n = 0; n < znaki2.length - 4; n++) {
                                    nowazaw.append(znaki2[n]);
                                }

                                // Wykonuję działanie
                                switch (komenda4.toString().toLowerCase()) {
                                    case "sqrt" -> nowazaw.append(DodatekMatematyczny.sqrt(rozwiazSkladowa(zawartosc)));
                                    case "pow2" -> nowazaw.append(Math.pow(rozwiazSkladowa(zawartosc), 2));
                                    case "pow3" -> nowazaw.append(Math.pow(rozwiazSkladowa(zawartosc), 3));
                                    case "fact" -> nowazaw.append(DodatekMatematyczny.fact(rozwiazSkladowa(zawartosc)));


                                }

                                czyWykrytoKomende = true;
                            }
                        }

                        if(!czyWykrytoKomende) {

                            // Gdy nie wykryto komendy
                            nowazaw.append(rozwiazSkladowa(zawartosc));
                        }

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
