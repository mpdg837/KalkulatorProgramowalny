package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    Image simg;
    private String name;

    public ImageButton(Image img,String name){
        super(" ");
        this.setFocusable(false);
        this.setBackground(Color.lightGray);
        this.name = name;
        simg = img;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image scaledInstance = simg.getScaledInstance(24,24,Image.SCALE_SMOOTH);

        g.drawImage(scaledInstance,this.getWidth()/2-12,this.getHeight()/2-12,24,24,this);
    }

    public void setText(String nazwa){
        name= nazwa;
    }

    public String getText(){
        return name;
    }
}
