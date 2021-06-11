package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PoprawnoscAction implements ActionListener {

    ListaKalkulatorowa wpisz;


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
    }
}
