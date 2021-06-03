package KalkulatorPRMT;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class GUI extends Frame {
    JTextField tekst;


    public GUI(String nazwa) {
        super(nazwa);
    }


    public void pokazGUI() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        //stworzenie paneli
        Panel panelCyfrowy = new Panel(new GridLayout(4,3));
        Panel panelDzialania1 = new Panel(new GridLayout(2,2));
        Panel panelDzialania2 = new Panel(new GridLayout(2,1));
        Panel panelDzialania3 = new Panel(new GridLayout(2,1));
        Panel panelDzialania123 = new Panel(new BorderLayout());
        Panel panelDzialania4 = new Panel(new GridLayout(4,4));
        Panel panelFinalny = new Panel(new BorderLayout());

        //stworzenie przyciskow, tekstu i umieszczanie ich na panelach
        tekst = new JTextField(5);

        JButton but1 = new JButton("1");
        JButton but2 = new JButton("2");
        JButton but3 = new JButton("3");
        JButton but4 = new JButton("4");
        JButton but5 = new JButton("5");
        JButton but6 = new JButton("6");
        JButton but7 = new JButton("7");
        JButton but8 = new JButton("8");
        JButton but9 = new JButton("9");
        JButton but0 = new JButton("0");
        JButton butZnak = new JButton("+/-");
        JButton butPrzecinek = new JButton(",");

        JButton butNawiasLewy = new JButton("(");
        JButton butNawiasPrawy = new JButton(")");
        JButton butMnozenie = new JButton("*");
        JButton butDzielenie = new JButton("/");
        JButton butOdejmowanie = new JButton("-");
        JButton butDodawanie = new JButton("+");
        JButton butRownosc = new JButton("=");

        JButton butPierwiastek = new JButton("sqrt");
        JButton butPi = new JButton("pi");
        JButton butE = new JButton("e");
        JButton butKasuj = new JButton("[x>");
        JButton butKwadrat = new JButton("()^2");
        JButton butSinus = new JButton("sin");
        JButton butLogarytmNaturalny = new JButton("ln");
        JButton butEnter = new JButton("enter");
        JButton butSzescian = new JButton("()^3");
        JButton butCosinus = new JButton("cos");
        JButton butEksponenta = new JButton("exp");
        JButton butPlay = new JButton("play");
        JButton butModul = new JButton("| |");
        JButton butTangens = new JButton("tan");
        JButton butLogarytmDziesietny = new JButton("log");
        JButton butSilnia = new JButton("n!");


        panelCyfrowy.add(but7);
        panelCyfrowy.add(but8);
        panelCyfrowy.add(but9);
        panelCyfrowy.add(but4);
        panelCyfrowy.add(but5);
        panelCyfrowy.add(but6);
        panelCyfrowy.add(but1);
        panelCyfrowy.add(but2);
        panelCyfrowy.add(but3);
        panelCyfrowy.add(butZnak);
        panelCyfrowy.add(but0);
        panelCyfrowy.add(butPrzecinek);

        panelDzialania1.add(butNawiasLewy);
        panelDzialania1.add(butNawiasPrawy);
        panelDzialania1.add(butMnozenie);
        panelDzialania1.add(butDzielenie);

        panelDzialania2.add(butRownosc);

        panelDzialania3.add(butOdejmowanie);
        panelDzialania3.add(butDodawanie);

        panelDzialania4.add(butPierwiastek);
        panelDzialania4.add(butPi);
        panelDzialania4.add(butE);
        panelDzialania4.add(butKasuj);
        panelDzialania4.add(butKwadrat);
        panelDzialania4.add(butSinus);
        panelDzialania4.add(butLogarytmNaturalny);
        panelDzialania4.add(butEnter);
        panelDzialania4.add(butSzescian);
        panelDzialania4.add(butCosinus);
        panelDzialania4.add(butEksponenta);
        panelDzialania4.add(butPlay);
        panelDzialania4.add(butModul);
        panelDzialania4.add(butTangens);
        panelDzialania4.add(butLogarytmDziesietny);
        panelDzialania4.add(butSilnia);

        //nakladanie paneli na siebie w celu stworzenia calej palety przyciskow
        panelDzialania123.add(panelDzialania1, BorderLayout.NORTH);
        panelDzialania123.add(panelDzialania3, BorderLayout.CENTER);
        panelDzialania123.add(butRownosc, BorderLayout.WEST);


        panelFinalny.add(tekst, BorderLayout.EAST);
        panelFinalny.add(panelDzialania4, BorderLayout.EAST);
        panelFinalny.add(panelDzialania123, BorderLayout.CENTER);
        panelFinalny.add(panelCyfrowy, BorderLayout.WEST);

        add(panelFinalny);
        pack();
        EventQueue.invokeLater(() -> setVisible(true));
    }










}
