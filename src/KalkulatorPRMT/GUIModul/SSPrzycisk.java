package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class SSPrzycisk extends JButton {
    public SSPrzycisk(String tytul){
        super(tytul);

        this.setBackground(new Color(180,180,180));

        this.setFont(new Font("Arial",Font.PLAIN,16));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.setPreferredSize(new Dimension(70,30));
    }
}
