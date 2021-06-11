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
    final private GUI gui;
    final Zakladki zakladki;
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

        if(wyniki.size()>30){
            for(int n=wyniki.size()-30;n<wyniki.size();n++){
                zakladki.addWynikHistoria(wyniki.get(n));
                gui.setWynik(wyniki.get(n));
            }
        }else{
            for(String wynik: wyniki){
                zakladki.addWynikHistoria(wynik);
                gui.setWynik(wynik);
            }
        }

    }
}
