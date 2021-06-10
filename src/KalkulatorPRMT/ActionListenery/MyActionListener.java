package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;

import javax.swing.*;
import javax.swing.text.BadLocationException;
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
        int polozeniekrusora = poletekstowe.getNumerZnaku();
        try {
            poletekstowe.setLinijka(tekstzguzika, polozeniekrusora);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }

    }
}
