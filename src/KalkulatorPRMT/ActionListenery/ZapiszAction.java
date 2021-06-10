package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ZapiszAction implements ActionListener {
    ListaKalkulatorowa wpisz;
    JTextField sciezka;

    public ZapiszAction(JTextField sciezka, ListaKalkulatorowa wpisz){
        this.wpisz = wpisz;
        this.sciezka = sciezka;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileWriter myWriter;
        File file = new File(sciezka.getText());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            myWriter = new FileWriter(sciezka.getText(),true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            PrintWriter out = new PrintWriter(bw);
            out.println(wpisz.getLinijka());
            out.close();
            bw.close();
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}




