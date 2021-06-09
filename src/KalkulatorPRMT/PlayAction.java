package KalkulatorPRMT;

import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayAction implements ActionListener {
    private GUI gui;
    public PlayAction(GUI gui){
        this.gui = gui;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ZbiorWyrazen zbior = new ZbiorWyrazen();
        String dzialanie = gui.getTekst();
        zbior.add(dzialanie);
        zbior.rozwiaz();
        ArrayList<String> wyniki = zbior.getWyniki();

        for(String wynik: wyniki){
            System.out.println(wynik);
            gui.setWynik(wynik);
        }
    }
}
