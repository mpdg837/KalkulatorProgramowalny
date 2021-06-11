package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterAction implements ActionListener {
    final ListaKalkulatorowa listaKalkulatorowa;
    public EnterAction(ListaKalkulatorowa listaKalkulatorowa){
        this.listaKalkulatorowa = listaKalkulatorowa;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listaKalkulatorowa.dodajLinijke();

    }
}
