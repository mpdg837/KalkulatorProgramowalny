package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import KalkulatorPRMT.Obliczanie.ZbiorWyrazen;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WybierzAction implements ActionListener  {
    JFileChooser oknoplikow;
    JPanel panel;
    JTextField sciezka;
    ListaKalkulatorowa wpisz;
    public WybierzAction(JPanel panel, JTextField sciezka, ListaKalkulatorowa wpisz){
        oknoplikow = new JFileChooser();
        this.panel = panel;
        this.sciezka = sciezka;
        this.wpisz = wpisz;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        oknoplikow.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
        oknoplikow.setApproveButtonText("Wybierz");
        oknoplikow.showOpenDialog(panel);

        File file = oknoplikow.getSelectedFile();

        //zapobiegam nullpointerexception
        if(file != null) {
            String filename = file.getPath();
            sciezka.setText(filename);

            //wczytuje plik
            int polozeniekrusora = wpisz.getNumerZnaku();
            if(!sciezka.getText().isEmpty()) {

                if (file.exists()) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        //robie liste do przechowywania linii
                        List<String> lista = new ArrayList<>();
                        String st;

                        int n = 0;
                        while ((st = br.readLine()) != null) {
                            lista.add(st);
                            n++;
                        }
                        String[] tablica = new String[n];

                        for(int i = 0; i<n;i++){
                            //uzupelniam tablice liniami i podaje argument do metody
                            tablica[i] = lista.get(i);

                        }
                        wpisz.setZawartoscLini(tablica);



                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    //obliczam i ustawiam wynik
                    ZbiorWyrazen zbior = new ZbiorWyrazen();
                    String[] dzialanie = wpisz.getZawartoscLini();

                    for (String dzial : dzialanie) {
                        zbior.add(dzial);
                    }

                }
                else{JPanel blad = new JPanel();
                    JOptionPane.showMessageDialog(blad, "Błąd! Podany plik nie istnieje");

                }
            }
            else {
                JPanel blad = new JPanel();
                JOptionPane.showMessageDialog(blad, "Błąd! Nie podano nazwy pliku");
            }

        }

    }
}
