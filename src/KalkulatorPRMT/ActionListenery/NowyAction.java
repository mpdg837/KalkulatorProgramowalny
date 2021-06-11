package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowyAction implements ActionListener {


    final ListaKalkulatorowa wpisz;
    final JTextField sciezka;

    public NowyAction(ListaKalkulatorowa wpisz,JTextField sciezka) {

        this.wpisz = wpisz;
        this.sciezka = sciezka;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        wpisz.clearAll();
        wpisz.dodajLinijke();

        sciezka.setText("");
    }
}
