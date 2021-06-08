package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    Image simg;

    public ImageButton(Image img){
        super(" ");
        this.setFocusable(false);
        this.setBackground(new Color(200,200,200));

        simg = img;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(simg,this.getWidth()/2-12,this.getHeight()/2-12,24,24,this);
    }
}
