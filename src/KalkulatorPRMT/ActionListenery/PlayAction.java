package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUI;
import KalkulatorPRMT.GUIModul.Zakladki;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayAction implements ActionListener {
    private GUI gui;
    Zakladki zakladki;
    public PlayAction(GUI gui, Zakladki zakladki){
        this.gui = gui;
        this.zakladki = zakladki;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ZbiorWyrazen zbior = new ZbiorWyrazen();
        String[] dzialanie = gui.getTekst();

        for(String dzial:dzialanie) {
            zbior.add(dzial);
        }
        zbior.rozwiaz();
        List<String> wyniki = zbior.getListaStringow();

        for(String wynik: wyniki){
            zakladki.addWynikHistoria(wynik);
            gui.setWynik(wynik);
        }
    }
}
