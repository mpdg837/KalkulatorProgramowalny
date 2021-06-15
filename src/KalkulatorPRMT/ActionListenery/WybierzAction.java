package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
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

    JPanel panel;
    JTextField sciezka;
    ListaKalkulatorowa wpisz;
    public WybierzAction(JPanel panel, JTextField sciezka, ListaKalkulatorowa wpisz){

        this.panel = panel;
        this.sciezka = sciezka;
        this.wpisz = wpisz;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser oknoplikow = new JFileChooser();
        oknoplikow.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
        oknoplikow.setApproveButtonText("Wybierz");
        oknoplikow.setAcceptAllFileFilterUsed(true);

        oknoplikow.showOpenDialog(panel);


        File file = oknoplikow.getSelectedFile();

        //zapobiegam nullpointerexception
        if(file != null) {
            String filename = file.getPath();
            sciezka.setText(filename);

            //wczytuje plik

            if(!sciezka.getText().isEmpty()) {

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


                        br.close();

                    } catch (IOException fileNotFoundException) {
                        JPanel blad = new JPanel();
                        JOptionPane.showMessageDialog(blad, "Plik nie został odnaleziony","Wczytywanie pliku",JOptionPane.ERROR_MESSAGE);
                        sciezka.setText("");


                    }catch (Exception err){
                        JPanel blad = new JPanel();
                        JOptionPane.showMessageDialog(blad, "Nieokreślony błąd","Wczytywanie pliku",JOptionPane.ERROR_MESSAGE);
                        sciezka.setText("");
                    }




            }
            else {
                JPanel blad = new JPanel();
                JOptionPane.showMessageDialog(blad, "Błąd! Nie podano nazwy pliku","Wczytywanie pliku",JOptionPane.ERROR_MESSAGE);
            }

        }
        wpisz.repaint();
    }
}
