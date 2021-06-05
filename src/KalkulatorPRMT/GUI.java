package KalkulatorPRMT;

import KalkulatorPRMT.GUIModul.Przycisk;
import KalkulatorPRMT.GUIModul.SPrzycisk;
import KalkulatorPRMT.GUIModul.SSPrzycisk;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class GUI extends JFrame {
    JTextArea tekst;


    public GUI(String nazwa) {
        super(nazwa);
    }


    public void pokazGUI() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(1);
            }
        });

        this.setLayout(new BorderLayout());
        //stworzenie paneli
        JPanel panelCyfrowy = new JPanel(new GridLayout(4,3,2,2));
        panelCyfrowy.setBackground(Color.DARK_GRAY);
        JPanel panelDzialania1 = new JPanel(new GridLayout(2,2,2,2));
        panelDzialania1.setBackground(Color.DARK_GRAY);
        JPanel panelDzialania2 = new JPanel(new BorderLayout());
        panelDzialania2.setBackground(Color.DARK_GRAY);
        JPanel panelDzialania3 = new JPanel(new GridLayout(2,1,2,2));
        panelDzialania3.setBackground(Color.DARK_GRAY);
        BorderLayout lay1 = new BorderLayout();
        lay1.setVgap(2);
        lay1.setHgap(2);
        JPanel panelDzialania123 = new JPanel(lay1);
        panelDzialania123.setBorder(BorderFactory.createEmptyBorder(1,2,2,2));
        panelDzialania123.setBackground(Color.DARK_GRAY);
        JPanel panelDzialania4 = new JPanel(new GridLayout(4,4,2,2));
        panelDzialania4.setBackground(Color.DARK_GRAY);
        BorderLayout lay = new BorderLayout();
        lay.setVgap(2);
        JPanel panelFinalny = new JPanel(lay);
        panelFinalny.setBackground(Color.DARK_GRAY);

        //stworzenie przyciskow, tekstu i umieszczanie ich na panelach
        tekst = new JTextArea("eee");

        tekst.setBackground(Color.WHITE);
        tekst.setFont(new Font("Courier",Font.PLAIN,21));
        tekst.setRows(25);
        tekst.setColumns(25);

        JScrollPane pane = new JScrollPane(tekst);

        pane.setPreferredSize(new Dimension(500,200));
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Przycisk but1 = new Przycisk("1");
        Przycisk but2 = new Przycisk("2");
        Przycisk but3 = new Przycisk("3");
        Przycisk but4 = new Przycisk("4");
        Przycisk but5 = new Przycisk("5");
        Przycisk but6 = new Przycisk("6");
        Przycisk but7 = new Przycisk("7");
        Przycisk but8 = new Przycisk("8");
        Przycisk but9 = new Przycisk("9");
        Przycisk but0 = new Przycisk("0");
        Przycisk butZnak = new Przycisk("+/-");
        Przycisk butPrzecinek = new Przycisk(",");

        JButton butNawiasLewy = new SPrzycisk("(");
        JButton butNawiasPrawy = new SPrzycisk(")");
        JButton butMnozenie = new SPrzycisk("*");
        JButton butDzielenie = new SPrzycisk("/");
        JButton butOdejmowanie = new SPrzycisk("-");
        JButton butDodawanie = new SPrzycisk("+");
        JButton butRownosc = new Przycisk("=");


        SSPrzycisk butPierwiastek = new SSPrzycisk("sqrt");
        SSPrzycisk butPi = new SSPrzycisk("pi");
        SSPrzycisk butE = new SSPrzycisk("e");
        SSPrzycisk butKasuj = new SSPrzycisk("[x>");
        SSPrzycisk butKwadrat = new SSPrzycisk("()^2");
        SSPrzycisk butSinus = new SSPrzycisk("sin");
        SSPrzycisk butLogarytmNaturalny = new SSPrzycisk("ln");
        SSPrzycisk butEnter = new SSPrzycisk("enter");
        SSPrzycisk butSzescian = new SSPrzycisk("()^3");
        SSPrzycisk butCosinus = new SSPrzycisk("cos");
        SSPrzycisk butEksponenta = new SSPrzycisk("exp");
        SSPrzycisk butPlay = new SSPrzycisk("play");
        SSPrzycisk butModul = new SSPrzycisk("| |");
        SSPrzycisk butTangens = new SSPrzycisk("tan");
        SSPrzycisk butLogarytmDziesietny = new SSPrzycisk("log");
        SSPrzycisk butSilnia = new SSPrzycisk("n!");


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

        panelDzialania2.add(butRownosc,BorderLayout.CENTER);

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

        JPanel gora = new JPanel(new BorderLayout());

        JPanel upbar = new JPanel();
        upbar.setPreferredSize(new Dimension(500,30));

        gora.add(upbar,BorderLayout.NORTH);
        gora.add(pane,BorderLayout.CENTER);

        JTextField wynik = new JTextField("0");
        wynik.setFont(new Font("Arial",Font.BOLD,20));
        wynik.setPreferredSize(new Dimension(500,30));
        wynik.setForeground(Color.white);
        wynik.setEnabled(false);
        wynik.setBackground(Color.DARK_GRAY);


        gora.add(wynik,BorderLayout.SOUTH);

        panelFinalny.add(gora, BorderLayout.NORTH);
        panelFinalny.add(panelDzialania4, BorderLayout.EAST);
        panelFinalny.add(panelDzialania123, BorderLayout.CENTER);
        panelFinalny.add(panelCyfrowy, BorderLayout.WEST);

        add(panelFinalny,BorderLayout.WEST);

        JPanel panelBoczny = new JPanel();
        panelBoczny.setPreferredSize(new Dimension(200,200));

        add(panelBoczny,BorderLayout.EAST);
        pack();
        EventQueue.invokeLater(() -> setVisible(true));
    }










}
