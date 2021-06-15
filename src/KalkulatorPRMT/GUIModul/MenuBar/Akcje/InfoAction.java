package KalkulatorPRMT.GUIModul.MenuBar.Akcje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(new JLabel(),"Kalkulator 1.0. Wersja prezentacyjna. \n Projekt zaliczeniowy w ramach przedmiotu PRM2T, w semestrze 21L (rok akademicki 2020/2021) \n na kierunku Telekomunikacja na Wydziale Elektorniki i Technik Informacyjnych Politechniki Warszawskiej. \n \n Autorzy \n \n Michał Podgajny \n Jędrzej Joniec \n Michał Kamiński \n \n Czerwiec 2021","Informacje",JOptionPane.PLAIN_MESSAGE);
    }
}
