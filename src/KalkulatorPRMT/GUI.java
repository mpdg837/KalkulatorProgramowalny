package KalkulatorPRMT;

import KalkulatorPRMT.ActionListenery.*;
import KalkulatorPRMT.GUIModul.*;
import KalkulatorPRMT.GUIModul.MenuBar.MyMenuBar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame {
    public ListaKalkulatorowa tekst;
    public JTextField sciezka;

    public JTextField wynik = new JTextField("0");

    public void setWynik(String wynik) {
        this.wynik.setText(wynik);
    }

    public String[] getTekst() {
        return tekst.getZawartoscLini();
    }


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
        Zakladki panelBoczny= new Zakladki();

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
        tekst = new ListaKalkulatorowa(this);

        JScrollPane pane = new JScrollPane(tekst);
        tekst.setScrollPane(pane);
        //Pole do wyswietlania ściezki
        sciezka = new JTextField("");
        sciezka.setPreferredSize(new Dimension(100,20));



        pane.setPreferredSize(new Dimension(500,300));
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
        Przycisk butZera = new Przycisk("00");
        Przycisk butKropka = new Przycisk(".");


        JButton butNawiasLewy = new SPrzycisk("(");
        JButton butNawiasPrawy = new SPrzycisk(")");


        JButton butOdejmowanie = new SPrzycisk("-");
        JButton butDodawanie = new SPrzycisk("+");
        JButton butRownosc = new Przycisk("=");



        try {
            //jak zmienię jeszcze e, to się cały panel sypnie, więc na razie e zostawiam bez image, ja bym
            //tylko kolor zmienił pod e i będzie cacy, reszta przycisków wygląda gites

            ImageButton butPierwiastek = new ImageButton(ImageIO.read(new File("sqrt.png")),"sqrt()");
            ImageButton butPi = new ImageButton(ImageIO.read(new File("pi.png")),"pi");
            SSPrzycisk butE = new SSPrzycisk("e");
            //ImageButton butE = new ImageButton(ImageIO.read(new File("e.png")),"e");
            JPanel butPusty = new JPanel();
            butPusty.setBackground(Color.DARK_GRAY);
            ImageButton butKwadrat = new ImageButton(ImageIO.read(new File("kwadrat.png")),"pow2()");
            ImageButton butSinus = new ImageButton(ImageIO.read(new File("sin.png")),"sin()");
            ImageButton butLogarytmNaturalny = new ImageButton(ImageIO.read(new File("ln.png")),"ln()");
            ImageButton butEnter = new ImageButton(ImageIO.read(new File("enter.png")),"");
            ImageButton butSzescian = new ImageButton(ImageIO.read(new File("szescian.png")),"pow3()");
            ImageButton butCosinus = new ImageButton(ImageIO.read(new File("cos.png")),"cos()");
            ImageButton butEksponenta = new ImageButton(ImageIO.read(new File("ex.png")),"ex()");
            ImageButton butPlay = new ImageButton(ImageIO.read(new File("play1.png")),"");
            ImageButton butModul = new ImageButton(ImageIO.read(new File("mod.png")),"abs()");
            ImageButton butTangens = new ImageButton(ImageIO.read(new File("tan.png")),"tan()");
            ImageButton butLogarytmDziesietny = new ImageButton(ImageIO.read(new File("log.png")),"log()");
            ImageButton butSilnia = new ImageButton(ImageIO.read(new File("silnia.png")),"fact()");
            ImageButton pobierzplik = new ImageButton(ImageIO.read(new File("przegladaj.png")),"");
            ImageButton obliczzpliku = new ImageButton(ImageIO.read(new File("ok.png")),"");
            ImageButton zapiszdopliku = new ImageButton(ImageIO.read(new File("zapisz.png")),"");
            ImageButton nowyplik = new ImageButton(ImageIO.read(new File("new.png")),"");

            // przerzucone
            ImageButton butMnozenie = new ImageButton(ImageIO.read(new File("mnozenie.png")),"*");
            ImageButton butDzielenie = new ImageButton(ImageIO.read(new File("dzielenie.png")),"/");



            JPanel upbar = new JPanel(new BorderLayout());
            JPanel upbarbar = new JPanel();

            upbarbar.setPreferredSize(new Dimension(200,40));

            //dodaje przyciski do panelu bocznego
            upbarbar.add(nowyplik);
            upbarbar.add(pobierzplik);
            upbarbar.add(obliczzpliku);
            upbarbar.add(zapiszdopliku);

            sciezka.setEnabled(false);

            JPanel sPanel = new JPanel();

            sciezka.setPreferredSize(new Dimension(200,30));
            sPanel.add(sciezka);

            upbar.add(sPanel,BorderLayout.EAST);

            upbar.add(upbarbar,BorderLayout.WEST);

            //dodaj actionlistenery
            but1.addActionListener(new MyActionListener(but1.getText(),tekst,false));
            but2.addActionListener(new MyActionListener(but2.getText(),tekst,false));
            but3.addActionListener(new MyActionListener(but3.getText(),tekst,false));
            but4.addActionListener(new MyActionListener(but4.getText(),tekst,false));
            but5.addActionListener(new MyActionListener(but5.getText(),tekst,false));
            but6.addActionListener(new MyActionListener(but6.getText(),tekst,false));
            but7.addActionListener(new MyActionListener(but7.getText(),tekst,false));
            but8.addActionListener(new MyActionListener(but8.getText(),tekst,false));
            but9.addActionListener(new MyActionListener(but9.getText(),tekst,false));
            but0.addActionListener(new MyActionListener(but0.getText(),tekst,false));

            nowyplik.addActionListener(new NowyAction(tekst,sciezka));
            butPierwiastek.addActionListener(new MyActionListener(butPierwiastek.getName(),tekst,true));
            butPi.addActionListener(new MyActionListener(butPi.getName(),tekst,false));
            butE.addActionListener(new MyActionListener(butE.getText(),tekst,false));

            butKwadrat.addActionListener(new MyActionListener(butKwadrat.getName(),tekst,true));
            butSinus.addActionListener(new MyActionListener(butSinus.getName(),tekst,true));
            butLogarytmNaturalny.addActionListener(new MyActionListener(butLogarytmNaturalny.getName(),tekst,true));
            butEnter.addActionListener(new EnterAction(tekst));
            butSzescian.addActionListener(new MyActionListener(butSzescian.getName(),tekst,true));
            butCosinus.addActionListener(new MyActionListener(butCosinus.getName(),tekst,true));
            butEksponenta.addActionListener(new MyActionListener(butEksponenta.getName(),tekst,true));
            butPlay.addActionListener(new PlayAction(this, panelBoczny));
            butModul.addActionListener(new MyActionListener(butModul.getName(),tekst,true));
            butTangens.addActionListener(new MyActionListener(butTangens.getName(),tekst,true));
            butLogarytmDziesietny.addActionListener(new MyActionListener(butLogarytmDziesietny.getName(),tekst,true));
            butSilnia.addActionListener(new MyActionListener(butSilnia.getName(),tekst,true));

            butZera.addActionListener(new MyActionListener(butZera.getText(),tekst,false));
            butKropka.addActionListener(new MyActionListener(butKropka.getText(),tekst,false));
            butNawiasLewy.addActionListener(new MyActionListener(butNawiasLewy.getText(),tekst,false));
            butNawiasPrawy.addActionListener(new MyActionListener(butNawiasPrawy.getText(),tekst,false));
            butMnozenie.addActionListener(new MyActionListener(butMnozenie.getName(),tekst,false));
            butDzielenie.addActionListener(new MyActionListener(butDzielenie.getName(),tekst,false));
            butOdejmowanie.addActionListener(new MyActionListener(butOdejmowanie.getText(),tekst,false));
            butDodawanie.addActionListener(new MyActionListener(butDodawanie.getText(),tekst,false));
            butRownosc.addActionListener(new MyActionListener(butRownosc.getText(),tekst,false));
            pobierzplik.addActionListener(new WybierzAction(upbar,sciezka,tekst));
            obliczzpliku.addActionListener(new PoprawnoscAction(tekst ));
            zapiszdopliku.addActionListener(new ZapiszAction(sciezka, tekst,false));

            panelCyfrowy.add(but7);
            panelCyfrowy.add(but8);
            panelCyfrowy.add(but9);
            panelCyfrowy.add(but4);
            panelCyfrowy.add(but5);
            panelCyfrowy.add(but6);
            panelCyfrowy.add(but1);
            panelCyfrowy.add(but2);
            panelCyfrowy.add(but3);
            panelCyfrowy.add(butZera);
            panelCyfrowy.add(but0);
            panelCyfrowy.add(butKropka);

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
            panelDzialania4.add(butPusty);
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
            panelBoczny.setPreferredSize(new Dimension(200,600));


            gora.add(upbar,BorderLayout.NORTH);
            gora.add(pane,BorderLayout.CENTER);


            wynik.setFont(new Font("Arial",Font.BOLD,20));
            wynik.setForeground(Color.white);
            wynik.setEnabled(false);
            wynik.setBackground(Color.DARK_GRAY);


            gora.add(wynik,BorderLayout.SOUTH);

            panelFinalny.add(gora, BorderLayout.NORTH);
            panelFinalny.add(panelDzialania4, BorderLayout.EAST);
            panelFinalny.add(panelDzialania123, BorderLayout.CENTER);
            panelFinalny.add(panelCyfrowy, BorderLayout.WEST);



            add(panelFinalny,BorderLayout.CENTER);



            add(panelBoczny,BorderLayout.EAST);


        }catch (IOException ignore){

        }

        MyMenuBar bar = new MyMenuBar(this);
        this.setMenuBar(bar);

        JLabel lab = new JLabel("Kalkulator 1.0 beta. Projekt zaliczeniowy. Wersja prezentacyjna");
        lab.setFont(new Font("Arial",Font.PLAIN,12));

        panelFinalny.setPreferredSize(new Dimension(650,600));

        this.add(lab,BorderLayout.SOUTH);

        pack();
        EventQueue.invokeLater(() -> setVisible(true));
    }










}
