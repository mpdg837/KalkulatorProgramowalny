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

