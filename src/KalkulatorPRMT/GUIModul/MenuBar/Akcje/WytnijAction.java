package KalkulatorPRMT.GUIModul.MenuBar.Akcje;

import KalkulatorPRMT.GUI;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WytnijAction implements ActionListener {
    private GUI okno;

    public WytnijAction(GUI okno) {
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        StringSelection stringSelection = new StringSelection(okno.tekst.getWiersz().getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        okno.tekst.getWiersz().replaceSelection("");
    }
}
