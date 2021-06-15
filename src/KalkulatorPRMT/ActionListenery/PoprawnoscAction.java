package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PoprawnoscAction implements ActionListener {

    final ListaKalkulatorowa wpisz;


    public PoprawnoscAction(ListaKalkulatorowa wpisz) {

        this.wpisz = wpisz;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ZbiorWyrazen zbior = new ZbiorWyrazen();
        String[] dzialanie = wpisz.getZawartoscLini();

        for(String dzial:dzialanie) {
            zbior.add(dzial);
        }
        zbior.sprawdzPoprawnosc();
        wpisz.repaint();
    }
}
