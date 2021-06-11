package KalkulatorPRMT.GUIModul.MenuBar;

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
        wklej.addActionListener(new WklejAction(frame));
        edycja.add(wklej);

        edycja.add(wklej);

        plik.add(new MenuItem("Nowy"));
        plik.add(new MenuItem("Otwórz"));
        plik.add(new MenuItem("Zapisz"));
        plik.add(new MenuItem("Zapisz jako"));

        MenuItem wyj =  new MenuItem("Wyjście");

        wyj.addActionListener(new KoniecAction());
        plik.add(wyj);
    }
}
