package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Zakladki extends JTabbedPane {

    public JPanel historia;
    public JPanel zmienne;
    private int x = 0;
    private int y = 0;
    private final List<String> nazwyzmiennych = new ArrayList<>();
    private final List<Double> wartoscizmiennych= new ArrayList<>();
    private final HashMap<String,JLabel> zmiennawjlabel = new HashMap<>();
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
    public void addZmienne(HashMap<String,Double> slownik) {
        JLabel pole;
        if(!(slownik.size() == 0)) {
            for(String klucz:slownik.keySet()){
                nazwyzmiennych.add(klucz);
                wartoscizmiennych.add(slownik.get(klucz));
                pole = new JLabel();

                if(!zmiennawjlabel.containsKey(klucz)) {
                    zmiennawjlabel.put(klucz, pole);
                    zmiennawjlabel.get(klucz).setText(nazwyzmiennych.get(nazwyzmiennych.indexOf(klucz)) + "= " + wartoscizmiennych.get(wartoscizmiennych.indexOf(slownik.get(klucz))));
                    pole.setLocation(0, x);
                    x += 13;
                    pole.setSize(pole.getPreferredSize());
                    zmienne.add(pole);
                }
                else{
                    zmiennawjlabel.get(klucz).setText(nazwyzmiennych.get(nazwyzmiennych.indexOf(klucz)) + "= " + wartoscizmiennych.get(wartoscizmiennych.indexOf(slownik.get(klucz))));
                }
                repaint();

            }
            nazwyzmiennych.clear();
            wartoscizmiennych.clear();
        }

    }
}
