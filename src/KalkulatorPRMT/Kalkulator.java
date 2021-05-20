package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.*;
import KalkulatorPRMT.Obliczanie.Dzialania.Dodawanie;
import KalkulatorPRMT.Obliczanie.Dzialania.Mnozenie;

import java.util.HashMap;

public class Kalkulator {

    public Kalkulator(){

        // Przykład działania

        HashMap<String,Double> zmienne = new HashMap<>();
        String dzialanie = "2+2*2/e-PI+2/e";
        System.out.println(dzialanie);
        Wyrazenie dzial = Convert.fromStringtoWyrazenie(zmienne,dzialanie);


        System.out.println(dzial.toString());

        dzialanieNieuporzadkowane nieuporzadkowane = dzial.toDzialanieNieuporzadkowane();
        System.out.println(nieuporzadkowane.toString());

        dzialanieUporzadkowane  uporzadkowane = nieuporzadkowane.toDzialanieUporzadkowane();
        System.out.println(uporzadkowane.toString());
        System.out.println(uporzadkowane.wynik());
    }

    public static void main(String[] args){
        Kalkulator calc = new Kalkulator();
    }
}
