package KalkulatorPRMT.ActionListenery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PobierzAction implements ActionListener  {
    JFileChooser oknoplikow;
    JPanel panel;
    JTextField sciezka;
    public PobierzAction(JPanel panel, JTextField sciezka){
        oknoplikow = new JFileChooser();
        this.panel = panel;
        this.sciezka = sciezka;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        oknoplikow.setApproveButtonText("Wybierz");
        oknoplikow.showOpenDialog(panel);
        File file = oknoplikow.getSelectedFile();
        //zapobiegam nullpointerexception
        if(file != null) {
            String filename = file.getPath();
            sciezka.setText(filename);
        }

    }
}
