package KalkulatorPRMT.GUIModul.MenuBar.Akcje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class WklejAction implements ActionListener {

    public WklejAction(){

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
