package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.Przetwarzanie.*;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.util.HashMap;
import java.util.Scanner;

public class Kalkulator {

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

            if(!wyrazenie.equals("do")) zbior.add(wyrazenie);
        }

        System.out.println("");
        System.out.println("Wykonuje działania. Raport poniżej");
        System.out.println("");

        zbior.rozwiaz();




    }

    public static void main(String[] args){
        //Kalkulator calc = new Kalkulator();
        GUI oknoGUI = new GUI("Kalkulator");
        oknoGUI.pokazGUI();

        Kalkulator calc = new Kalkulator();
    }
}
