package KalkulatorPRMT.Obliczanie.Przetwarzanie;

import KalkulatorPRMT.Obliczanie.DodatekMatematyczny;
import KalkulatorPRMT.Obliczanie.MyError;
import KalkulatorPRMT.Obliczanie.Tablice.DlugoscTablicy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupowanie {

    // Klasa odpowiada za obslugę nawiasów w wyrażeniach

    // Lista zmiennych używanych przez realizator
    public HashMap<String,Double> zmienne;


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


    // Metoda służy do wykonywania działań oraz funkcji

    public StringBuilder dodajWynik(StringBuilder nowazaw,String zawartosc) throws MyError{

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

        if(komenda2.toString().equalsIgnoreCase("ln")){

            nowazaw = new StringBuilder();

            // Czyszczę StringBuildera z zbędnych znaków
            for(int n=0;n<znaki2.length-2;n++){
                nowazaw.append(znaki2[n]);
            }

            // Wykonuję logarytm liniowy
            nowazaw.append(Formatter.konwersjaDoStringa(DodatekMatematyczny.ln(rozwiazSkladowa(zawartosc))));

            czyWykrytoKomende = true;
        }
        // Analiza komend 3 znakowych
        switch (komenda3.toString()) {
            case "sin", "cos", "tan", "abs", "exp", "log","pow","len" -> {
                nowazaw = new StringBuilder();

                // Czyszczę stringbuildera z niepotrzebnych znaków
                for (int n = 0; n < znaki2.length - 3; n++) {
                    nowazaw.append(znaki2[n]);
                }

                // Wykonuję działanie
                switch (komenda3.toString().toLowerCase()) {
                    case "sin" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.sin(rozwiazSkladowa(zawartosc))));
                    case "cos" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.cos(rozwiazSkladowa(zawartosc))));
                    case "tan" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.tan(rozwiazSkladowa(zawartosc))));
                    case "abs" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.abs(rozwiazSkladowa(zawartosc))));
                    case "exp" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.exp(rozwiazSkladowa(zawartosc))));
                    case "log" -> nowazaw.append(Formatter.konwersjaDoStringa(DodatekMatematyczny.log(rozwiazSkladowa(zawartosc))));
                    case "pow" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.pow(rozwiazSkladowa(zawartosc), 2)));
                    case "len" ->{

                        int k = DlugoscTablicy.dlugosc(zmienne,zawartosc);

                        nowazaw.append(k);
                    }
                }
                czyWykrytoKomende = true;
            }
        }

        // Analiza komend 4 znakowych
        switch (komenda4.toString()) {
            case "sqrt", "pow2", "pow3", "fact" ,"show","inpu"-> {

                // Czyszczę StringBuildera z zbędnych znaków
                nowazaw = new StringBuilder();
                for (int n = 0; n < znaki2.length - 4; n++) {
                    nowazaw.append(znaki2[n]);
                }

                // Wykonuję działanie
                switch (komenda4.toString().toLowerCase()) {
                    case "sqrt" -> nowazaw.append(Formatter.konwersjaDoStringa(DodatekMatematyczny.sqrt(rozwiazSkladowa(zawartosc))));
                    case "pow2" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.pow(rozwiazSkladowa(zawartosc), 2)));
                    case "pow3" -> nowazaw.append(Formatter.konwersjaDoStringa(Math.pow(rozwiazSkladowa(zawartosc), 3)));
                    case "fact" -> nowazaw.append(DodatekMatematyczny.fact(rozwiazSkladowa(zawartosc)));
                    case "show" -> {

                        KalkulatorowyString zaw = new KalkulatorowyString(zawartosc,zmienne);

                        JOptionPane.showMessageDialog(new JPanel(),zaw.convert());
                        nowazaw.append("0");
                    }
                    case "inpu" ->{



                        try{
                            KalkulatorowyString zaw = new KalkulatorowyString(zawartosc,zmienne);

                            String num = JOptionPane.showInputDialog(new JPanel(), zaw.convert());

                            double numer = Double.parseDouble(num);
                            zawartosc = "";
                            nowazaw.append(Formatter.konwersjaDoStringa(numer));
                        }catch (NumberFormatException err){
                            throw new MyError("Wprowadzono nieprawidłowo liczbę");
                        }catch (NullPointerException err){
                            throw new MyError("Wprowadzono pustą wartość");
                        }
                    }

                }

                czyWykrytoKomende = true;
            }
        }

        if(!czyWykrytoKomende) {

            // Gdy nie wykryto komendy
            nowazaw.append(Formatter.konwersjaDoStringa(rozwiazSkladowa(zawartosc)));
        }

        return nowazaw;

    }


    public Double wynik() throws MyError{

            // Zmienna infromująca o poziomie zagnieżgdżenia wyrażeń rozwiązywanych przez klasy nieobsługujące nawiasów
            int stopienzagniezdzenia = 0;

            // Inicjacja analizy ciągu wyrażenia
            ArrayList<StringBuilder> poziomy = new ArrayList<>();

            var znaki = ciagDzilania.toCharArray();

            // Rozpoczęcie
            poziomy.add(new StringBuilder());

            boolean trybTekstowy = false;

            // Enumeracja ewentualnych tablic
            boolean trybEnumeracjiTablicy = false;
            StringBuilder zawEnumeracji = new StringBuilder();


            for (char c : znaki) {

                switch (c + "") {
                    case "=" -> throw new MyError("Wykryto znak = w nieporządanym miejscu");
                    case "[" -> trybEnumeracjiTablicy = true;
                    case "]" ->{

                        String zawEnum = zawEnumeracji.toString();

                        // Dopisywanie znaków niedotyczących podziału na nawiasy

                        StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                        builder.append("_");
                        builder.append((int)rozwiazSkladowa(zawEnum));

                        poziomy.set(stopienzagniezdzenia, builder);

                        zawEnumeracji = new StringBuilder();
                        trybEnumeracjiTablicy = false;
                    }
                    case " " ->{
                        if(trybTekstowy){
                            // Dopisywanie znaków niedotyczących podziału na nawiasy

                            StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                            builder.append(c);

                            poziomy.set(stopienzagniezdzenia, builder);
                        }
                    }
                    // Nawias otwarty następny poziom zagnieżdżenia
                    case "(" -> {
                        if(trybEnumeracjiTablicy){
                            zawEnumeracji.append(c);
                        }else
                        if(!trybTekstowy) {
                            // Wykrywanie komend;

                            // Przejście do następnego
                            stopienzagniezdzenia++;

                            if (stopienzagniezdzenia > poziomy.size() - 1) {
                                // Gdy nie powstał jeszcze poziom zagnieżdżenia

                                poziomy.add(new StringBuilder());
                            }
                        }else{
                            StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                            builder.append(c);

                            poziomy.set(stopienzagniezdzenia, builder);
                        }
                    }
                    case ")" -> {
                        if(trybEnumeracjiTablicy){
                            zawEnumeracji.append(c);
                        }else
                        if(!trybTekstowy) {
                            String zawartosc = poziomy.get(stopienzagniezdzenia).toString();
                            poziomy.set(stopienzagniezdzenia, new StringBuilder());

                            if (stopienzagniezdzenia > 0) {
                                // Obniżam poziom zagnieżdżenia
                                stopienzagniezdzenia--;
                            } else {

                                // Poziom zagnieżdżenia nie może być ujemny w prawidłowo opisanym działaniu
                                throw new MyError("Za dużo nawiasów domykających");
                            }

                            // Dodanie wyniku obliczania wnętrza nawiasu
                            StringBuilder nowazaw = poziomy.get(stopienzagniezdzenia);

                            nowazaw = dodajWynik(nowazaw, zawartosc);

                            poziomy.set(stopienzagniezdzenia, nowazaw);
                        }else{
                            StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                            builder.append(c);

                            poziomy.set(stopienzagniezdzenia, builder);
                        }
                    }
                    default -> {
                        if(trybEnumeracjiTablicy){
                            zawEnumeracji.append(c);
                        }else {
                            if ((c + "").equals("'")) {
                                trybTekstowy = !trybTekstowy;
                            }
                            // Dopisywanie znaków niedotyczących podziału na nawiasy

                            StringBuilder builder = poziomy.get(stopienzagniezdzenia);
                            builder.append(c);

                            poziomy.set(stopienzagniezdzenia, builder);
                        }
                    }
                }
            }

            // Sprawdzam czy wszystkie wyrażenia są domknięte
            if(stopienzagniezdzenia>0){
                throw new MyError("Brakuje nawiasu domykającego działanie");
            }

            // Wysyłam wynik
            return rozwiazSkladowa(poziomy.get(stopienzagniezdzenia).toString());

    }
}
