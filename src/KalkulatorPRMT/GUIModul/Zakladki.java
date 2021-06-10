package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class Zakladki extends JTabbedPane {

    public JPanel historia;
    public JPanel zmienne;
    public Zakladki(){

        historia = new JPanel();
        historia.setBackground(Color.LIGHT_GRAY);
        zmienne = new JPanel();
        zmienne.setBackground(Color.LIGHT_GRAY);

        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        this.addTab("Historia",historia);
        this.addTab("Zmienne",zmienne);

        this.setBackground(Color.GRAY);
    }
}
