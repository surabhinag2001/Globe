package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteVisitedListDialogBox extends DeleteDialogBox {

    private GlobeApp globe;
    private JButton yes;

    public DeleteVisitedListDialogBox(Frame parent, GlobeApp gb) {
        super(parent, gb);
        globe = gb;
        yes = super.getYes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yes) {
            globe.deleteSelectedRow('v');
        }
        dispose();
    }
}
