package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    private String tekstzguzika;
    private ListaKalkulatorowa poletekstowe;
    private boolean nawias;
    public MyActionListener(String tekstzguzika, ListaKalkulatorowa poletekstowe,boolean nawias){
        this.tekstzguzika = tekstzguzika;
        this.poletekstowe = poletekstowe;
        this.nawias = nawias;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int polozeniekrusora = poletekstowe.getNumerZnaku();
        try {
            poletekstowe.setLinijka(tekstzguzika, polozeniekrusora,nawias);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }

    }
}
