package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayAction implements ActionListener {
    private GUI gui;
    public PlayAction(GUI gui){
        this.gui = gui;

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
            System.out.println(wynik);
            gui.setWynik(wynik);
        }
    }
}
