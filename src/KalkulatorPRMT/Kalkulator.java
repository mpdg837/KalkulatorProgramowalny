package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.*;

import java.util.HashMap;
import java.util.Scanner;

public class Kalkulator {

    public Kalkulator(){

        Scanner scan = new Scanner(System.in);
        String wyrazenie = scan.nextLine();
        HashMap<String,Double> zmienne = new HashMap<>();

        Grupowanie grp = new Grupowanie(wyrazenie,zmienne);

        System.out.println(grp.wynik());
    }

    public static void main(String[] args){
        Kalkulator calc = new Kalkulator();
    }
}
