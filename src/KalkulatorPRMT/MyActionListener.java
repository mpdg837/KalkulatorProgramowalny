package KalkulatorPRMT;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    private String tekstzguzika;
    private ListaKalkulatorowa poletekstowe;

    public MyActionListener(String tekstzguzika, ListaKalkulatorowa poletekstowe){
        this.tekstzguzika = tekstzguzika;
        this.poletekstowe = poletekstowe;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        poletekstowe.setLinijka(tekstzguzika);

    }
}
