package KalkulatorPRMT.GUIModul.MenuBar.Akcje;

import KalkulatorPRMT.GUI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class WklejAction implements ActionListener {
    private GUI okno;
    public WklejAction(GUI okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
        }catch (AWTException ignore){

        }
    }
}
