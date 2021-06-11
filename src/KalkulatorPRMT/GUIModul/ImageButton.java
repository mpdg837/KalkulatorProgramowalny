package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {

    BufferedImage simg;
    private String name;

    public ImageButton(BufferedImage img, String name){
        super("");
        this.setPreferredSize(new Dimension(32,32));
        this.setFocusable(false);
        this.setBackground(Color.lightGray);
        this.name = name;
        simg = img;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = 24*simg.getWidth()/simg.getHeight();
        Image scaledInstance = simg.getScaledInstance((24*simg.getWidth()/simg.getHeight()),24,Image.SCALE_SMOOTH);

        g.drawImage(scaledInstance,this.getWidth()/2-width/2,this.getHeight()/2-12,this);
    }

    public void setName(String nazwa){
        name= nazwa;
    }

    public String getName(){
        return name;
    }
}
