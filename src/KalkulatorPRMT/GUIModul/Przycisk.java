package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class Przycisk extends JButton {
    public Przycisk(String tytul){
        super(tytul);

        this.setBackground(new Color(220,220,220));

        this.setFont(new Font("Arial",Font.BOLD,21));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.setPreferredSize(new Dimension(70,30));
    }
}
