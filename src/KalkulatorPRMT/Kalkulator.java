package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.*;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Kalkulator {
    private static GUI oknoGUI = new GUI("Kalkulator");

    public Kalkulator(){

        // Program testowy

        String wyrazenie = "";

        ZbiorWyrazen zbior = new ZbiorWyrazen();

        System.out.println("Aby dodać wyrażenia lub przypisania zmiennych po prostu je napisz i zatwierdź enterem");
        System.out.println("Jeżeli chcesz wykonać wyrażenie to wpisz komende 'do' i zatwierdź enterem");
        System.out.println("");
        while (!wyrazenie.equals("do")) {

            Scanner scan = new Scanner(System.in);
            wyrazenie = scan.nextLine();
            zbior.add(wyrazenie);
        }



        System.out.println("");
        System.out.println("Wykonuje działania. Raport poniżej");
        System.out.println("");

        zbior.rozwiaz();

        List<String> wyniki = zbior.getListaStringow();

        for(String wynik: wyniki){
            System.out.println(wynik);
        }

    }

    public static void main(String[] args){
        //Kalkulator calc = new Kalkulator();

        GUI gui = new GUI("Kalkulator");
        gui.pokazGUI();
        Kalkulator calc = new Kalkulator();
    }
}
