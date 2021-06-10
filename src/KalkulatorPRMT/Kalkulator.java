package KalkulatorPRMT;

import KalkulatorPRMT.GUIModul.ImageButton;
import KalkulatorPRMT.Obliczanie.Przetwarzanie.*;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Kalkulator {
    private static GUI oknoGUI = new GUI("Kalkulator");

    public Kalkulator(){



    }

    public static void main(String[] args){
        //Kalkulator calc = new Kalkulator();

        GUI gui = new GUI("Kalkulator");
        gui.pokazGUI();

        Kalkulator calc = new Kalkulator();
    }
}
