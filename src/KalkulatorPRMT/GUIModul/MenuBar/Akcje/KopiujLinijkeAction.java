package KalkulatorPRMT.GUIModul.MenuBar.Akcje;

import KalkulatorPRMT.GUI;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KopiujLinijkeAction implements ActionListener {
    private GUI okno;
    public KopiujLinijkeAction(GUI okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        StringSelection stringSelection = new StringSelection(okno.tekst.getLinijka());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

    }
}
