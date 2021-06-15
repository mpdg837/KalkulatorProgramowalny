package KalkulatorPRMT.ActionListenery;

import KalkulatorPRMT.GUIModul.ListaKalkulatorowa;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ZapiszAction implements ActionListener {
    ListaKalkulatorowa wpisz;
    JTextField sciezka;

    final private boolean zapiszJako;
    public ZapiszAction(JTextField sciezka, ListaKalkulatorowa wpisz,boolean zapiszJako){
        this.wpisz = wpisz;
        this.sciezka = sciezka;
        this.zapiszJako = zapiszJako;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileWriter myWriter;
        File file = new File(sciezka.getText());

        boolean end = false;

        if(!file.exists()|| zapiszJako) {

            JFileChooser oknoplikow = new JFileChooser();

            boolean run =true;

            oknoplikow.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            oknoplikow.setApproveButtonText("Zapisz");

            oknoplikow.setAcceptAllFileFilterUsed(true);


            while(run) {


                int odp = oknoplikow.showSaveDialog(sciezka);

                file = oknoplikow.getSelectedFile();
                if(odp == JFileChooser.APPROVE_OPTION) {
                    if (file == null) {
                        run = false;
                        end = true;
                    } else {
                        if (file.exists()) {
                            int ok = JOptionPane.showConfirmDialog(sciezka, "Czy na pewno chcesz nadpisać ten plik", "Zapisz plik", JOptionPane.YES_NO_CANCEL_OPTION);


                            if (ok == JOptionPane.YES_OPTION || ok == JOptionPane.CANCEL_OPTION) {
                                run = false;

                                if(ok == JOptionPane.CANCEL_OPTION) {
                                    end = true;
                                }
                            }
                        } else {
                            run = false;

                        }
                    }
                }else{
                    run = false;
                    end = true;
                }
            }
        }

        if(!end) {
            try {

                    if (!file.exists()) {
                        boolean utworzyl = file.createNewFile();

                        if (!utworzyl) {
                            throw new IOException();
                        }
                    } else {
                        boolean ok = file.delete();
                        if (!ok) {
                            throw new IOException();
                        }
                    }
                    myWriter = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(myWriter);
                    PrintWriter out = new PrintWriter(bw);

                    String[] linijki = wpisz.getZawartoscLini();

                    for (String elementy : linijki) {
                        out.println(elementy);
                    }


                    out.close();
                    bw.close();
                    myWriter.close();

                    sciezka.setText(file.getPath());


            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(sciezka, "Odmowa dostępu");
            }

        }
    }
}




