package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Zakladki extends JTabbedPane {

    public JPanel historia;
    public JPanel zmienne;
    int x = 0;
    int y = 0;
    Set<String> nazwyzmiennych = new HashSet<>();
    HashMap<String ,JLabel > zmiennawjlabel = new HashMap<>();

    public Zakladki(){

        historia = new JPanel(null);
        historia.setBackground(Color.LIGHT_GRAY);
        zmienne = new JPanel(null);
        zmienne.setBackground(Color.LIGHT_GRAY);

        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        this.addTab("Historia",historia);
        this.addTab("Zmienne",zmienne);

        this.setBackground(Color.GRAY);
    }
    public void addWynikHistoria(String wynik){
        JLabel pole = new JLabel();
        pole.setLocation(0,y);
        y+=13;
        pole.setText(wynik + "\n");
        pole.setSize(pole.getPreferredSize());
        historia.add(pole);
        repaint();

    }
    public void addZmienne(HashMap<String,Double> slownik){
        JLabel pole;

        String zmienna = (String) slownik.keySet().toArray()[slownik.keySet().toArray().length -1];
        Double zmiennawartosc = (Double) slownik.values().toArray()[slownik.values().toArray().length -1];
        if(!nazwyzmiennych.contains(zmienna)) {
            nazwyzmiennych.add(zmienna);
            pole = new JLabel();
            pole.setText(zmienna + "= " + zmiennawartosc);
            pole.setLocation(0,x);
            zmiennawjlabel.put(zmienna,pole);
            x+=13;
            pole.setSize(pole.getPreferredSize());
            zmienne.add(pole);
            repaint();

        }
        else{
            zmiennawjlabel.get(zmienna).setText(zmienna + "= " + zmiennawartosc);



        }


    }
}
