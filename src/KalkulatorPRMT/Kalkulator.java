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

        while (!wyrazenie.equals("do")) {
            Scanner scan = new Scanner(System.in);
            wyrazenie = scan.nextLine();

            if(!wyrazenie.equals("do")) zbior.add(wyrazenie);
        }

        zbior.rozwiaz();




    }

    public static void main(String[] args){
        Kalkulator calc = new Kalkulator();
    }
}
