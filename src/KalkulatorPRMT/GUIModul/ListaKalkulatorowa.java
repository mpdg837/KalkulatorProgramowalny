package KalkulatorPRMT.GUIModul;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ListaKalkulatorowa extends JPanel implements KeyListener {

    // Lista pól tekstowych
    ArrayList<JTextField> linie= new ArrayList<>();
    ArrayList<JLabel> numerki= new ArrayList<>();

    JScrollPane pane;
    JFrame update;

    public ListaKalkulatorowa(JFrame updt){

        // Deklaracja
        super(new GridBagLayout());
        update = updt;
        for(int n=0;n<1;n++) {
            dodajLinijke();
        }
    }
    public void clearAll(){

        for(int n=0;n<linie.size();n++){

            this.remove(linie.get(n));
            this.remove(numerki.get(n));

        }

        linie.clear();
        numerki.clear();

    }
    public String[] getZawartoscLini(){
        // Pobieranie zawartości linijki
        String[] zaw = new String[linie.size()];

        for(int n=0;n<zaw.length;n++){
            zaw[n] = linie.get(n).getText();
        }

        return zaw;
    }

    public void setZawartoscLini(String[] linieS){
        // Dodawanie linijek
        clearAll();

        for(String linia:linieS){
            //odrzucam ostatnia pusta linie
            if(!linia.equals("")) {
                //dodaje wszystkie linijki na poczatku


                dodajLinijke();


                linie.get(linie.size()-1).setText(linia);


            }
        }

        // Jeżeli wcyztano pusty plik
        if(linie.size()==0){
            dodajLinijke();
        }
    }
    public void setScrollPane(JScrollPane scroll){
        // Pobieranie paska
        pane = scroll;
    }
    public String getLinijka(){
        // Pobranie zawartości lini

        for(JTextField field : linie){
            if(field.isFocusOwner()){
                // Odnalazłem linie sfocusowaną
                return field.getText();
            }
        }

        return "";
    }

    public JTextField getWiersz(){
        // Pobranie zawartości lini

        for(JTextField field : linie){
            if(field.isFocusOwner()){
                // Odnalazłem linie sfocusowaną
                return field;
            }
        }

        return null;
    }

    public int getNumerZnaku(){
        // Pobranie zawartości lini

        for(JTextField field : linie){
            if(field.isFocusOwner()){
                // Odnalazłem linie sfocusowaną
                return field.getCaretPosition();
            }
        }

        return 0;
    }

    public void setLinijka(String ciag, int polozeniekursora,boolean cofnij) throws BadLocationException {
        // Pobeiram zawartość lini

        for(JTextField field : linie){
            // Znalazłem linie sfocusowaną
            if(field.isFocusOwner()){
                field.getDocument().insertString(polozeniekursora, ciag, null);
                int caret = field.getCaretPosition();
                if(cofnij) {
                    if (caret > 0) {
                        field.setCaretPosition(caret - 1);
                    }
                }
            }
        }

    }

    public void nastepnaLinia(boolean enter){

        // Przechodzę do następnej linijki

        boolean nastepnalinijka = false;
        boolean wykonano =false;
        boolean enterwyk = false;

        String memory = "";
        for(JTextField field : linie){

            if(nastepnalinijka){
                // Przejście do następnej linii

                if(enter){
                    memory = field.getText();
                    field.setText("");
                    enterwyk = true;
                }
                field.grabFocus();
                field.requestFocus();
                nastepnalinijka = false;
                wykonano = true;


            }else {

                if(enterwyk){
                    memory = field.getText();
                    field.setText(memory);

                }

                // Odnalezienie lini sfocusowanej
                if (field.isFocusOwner()) {
                    nastepnalinijka = true;
                }
            }
        }

        if(!wykonano || enter) {
            // Nie odnaleziono lini sfocusowanej -> trzeba utworzyć nową
            dodajLinijke();
            if (enter) {
                linie.get(linie.size() - 1).setText(memory);
            }
            if (!wykonano) {
                linie.get(linie.size() - 1).grabFocus();
                linie.get(linie.size() - 1).requestFocus();
            }
        }


    }

    public int getNumer(){
        // Pobranie zawartości lini

        int n=0;
        for(JTextField field : linie){
            if(field.isFocusOwner()){
                // Odnalazłem linie sfocusowaną
                return n;
            }
            n++;
        }

        return 0;
    }

    public void poprzedniaLinia(){

        // Przejście do poprzedniej linii
        int n=0;
        for(JTextField field : linie){
            // Odnalezienie lini sfocusowanej
            if (field.isFocusOwner()) {
                if (n - 1 >= 0) {
                    // Wracam do poprzedniej linii
                    linie.get(n - 1).grabFocus();
                    linie.get(n - 1).requestFocus();
                }
            }
            n++;
        }

    }
    public void dodajLinijke(){

        // Dodanie linijki
        GridBagConstraints consta = new GridBagConstraints();

        int n=linie.size();
        consta.gridx = 0;
        consta.gridy = n;

        int numer = n+1;
        JLabel lab = new JLabel(numer+"");
        lab.setFont(new Font("Courier", Font.PLAIN, 21));
        this.add(lab,consta);

        consta.gridx = 1;
        consta.weightx=1000;

        var tekst = new JTextField("");
        tekst.setBackground(Color.WHITE);
        tekst.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tekst.setPreferredSize(new Dimension(600,25));

        tekst.setBackground(Color.WHITE);
        tekst.setFont(new Font("Courier", Font.PLAIN, 21));
        tekst.addKeyListener(this);

        linie.add(tekst);
        this.add(tekst,consta);

        numerki.add(lab);
        // Aktualizacja
        update.pack();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Wykrywanie sterowania klawiaturą
        switch (e.getKeyCode()){
            case KeyEvent.VK_ENTER ->nastepnaLinia(true); //Enter
            case KeyEvent.VK_UP ->poprzedniaLinia(); // Do góry
            case KeyEvent.VK_DOWN -> nastepnaLinia(false); // Do dołu
            case KeyEvent.VK_BACK_SPACE -> {
                if(getLinijka().length()==0){
                    boolean usun = false;
                    if(getNumer() == linie.size()-1){
                        usun = true;
                    }
                    poprzedniaLinia(); // Backspace
                    if(linie.size()-1>0) {
                        if (usun) {
                            // Usuwanie z UI
                            this.remove(linie.get(linie.size() - 1));
                            this.remove(numerki.get(numerki.size() - 1));

                            // Usuwanie z list
                            linie.remove(linie.get(linie.size() - 1));
                            numerki.remove(numerki.get(numerki.size() - 1));
                            update.pack();
                            update.repaint();
                        }
                    }
                }
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
