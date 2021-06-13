package KalkulatorPRMT.GUIModul.MenuBar;

import KalkulatorPRMT.ActionListenery.NowyAction;
import KalkulatorPRMT.ActionListenery.WybierzAction;
import KalkulatorPRMT.ActionListenery.ZapiszAction;
import KalkulatorPRMT.GUI;
import KalkulatorPRMT.GUIModul.MenuBar.Akcje.*;

import javax.swing.*;
import java.awt.*;

public class MyMenuBar extends MenuBar {

    public Menu plik = new Menu("Plik");
    public Menu edycja = new Menu("Edycja");
    public Menu info = new Menu("Informacje");

    public MyMenuBar(GUI frame)
    {

        this.add(plik);
        this.add(edycja);
        this.add(info);

        MenuItem item = new MenuItem("O programie");
        item.addActionListener(new InfoAction());
        info.add(item);



        MenuItem kopiu = new MenuItem("Kopiuj   (Ctrl + C)");
        kopiu.addActionListener(new KopiujAction(frame));
        edycja.add(kopiu);

        MenuItem wytij = new MenuItem("Wytnij   (Ctrl + X)");
        wytij.addActionListener(new WytnijAction(frame));
        edycja.add( wytij);

        MenuItem kopiuL = new MenuItem("Kopiuj linijke");
        kopiuL.addActionListener(new KopiujLinijkeAction(frame));
        edycja.add(kopiuL);

        MenuItem wklej = new MenuItem("Wklej    (Ctrl + V)");
        wklej.addActionListener(new WklejAction());
        edycja.add(wklej);


        MenuItem nowy = new MenuItem("Nowy");
        nowy.addActionListener(new NowyAction(frame.tekst,frame.sciezka));
        plik.add(nowy);

        MenuItem otw = new MenuItem("Otwórz");
        otw.addActionListener(new WybierzAction(new JPanel(),frame.sciezka,frame.tekst));
        plik.add(otw);

        MenuItem zap= new MenuItem("Zapisz");
        zap.addActionListener(new ZapiszAction(frame.sciezka,frame.tekst,false));
        plik.add(zap);

        MenuItem zapj= new MenuItem("Zapisz jako");
        zapj.addActionListener(new ZapiszAction(frame.sciezka,frame.tekst,true));
        plik.add(zapj);


        MenuItem wyj =  new MenuItem("Wyjście");

        wyj.addActionListener(new KoniecAction());
        plik.add(wyj);
    }
}
