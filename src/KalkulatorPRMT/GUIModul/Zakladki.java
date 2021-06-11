package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class Zakladki extends JTabbedPane {

    public JPanel historia;
    public JPanel zmienne;


    int x = 0;
    int y = 0;

    public Zakladki(){

        historia = new JPanel(null);
        historia.setBackground(Color.LIGHT_GRAY);
        zmienne = new JPanel();
        zmienne.setBackground(Color.LIGHT_GRAY);

        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        this.addTab("Historia",historia);
        this.addTab("Zmienne",zmienne);

        this.setBackground(Color.GRAY);

    }
    public void addWynikHistoria(String wynik){
        JLabel pole = new JLabel();
        pole.setLocation(x,y+20);
        y+=13;
        pole.setText(wynik + "\n");
        pole.setSize(pole.getPreferredSize());
        historia.add(pole);

        repaint();

    }
}
