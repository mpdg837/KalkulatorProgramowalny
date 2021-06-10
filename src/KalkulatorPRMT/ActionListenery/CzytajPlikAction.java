package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class CzytajPlikAction implements ActionListener  {
    JTextField sciezka;
    ListaKalkulatorowa wpisz;
    JTextField oblicz;

    public CzytajPlikAction(JTextField sciezka, ListaKalkulatorowa wpisz, JTextField oblicz){
        this.sciezka = sciezka;
        this.wpisz = wpisz;
        this.oblicz = oblicz;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //wczytuje plik
        int polozeniekrusora = wpisz.getNumerZnaku();
        if(!sciezka.getText().isEmpty()) {
            File file = new File(sciezka.getText());

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {
                    wpisz.setLinijka(st, polozeniekrusora,false);
                }
            } catch (IOException | BadLocationException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            //obliczam i ustawiam wynik
            ZbiorWyrazen zbior = new ZbiorWyrazen();
            String[] dzialanie = wpisz.getZawartoscLini();

            for (String dzial : dzialanie) {
                zbior.add(dzial);
            }
            zbior.rozwiaz();
            List<String> wyniki = zbior.getListaStringow();

            for (String wynik : wyniki) {
                System.out.println(wynik);
                oblicz.setText(wynik);
            }
        }
        else{
            JPanel blad = new JPanel();
            JOptionPane.showMessageDialog(blad,"Błąd! Nie podano nazwy pliku");
        }
    }
}
