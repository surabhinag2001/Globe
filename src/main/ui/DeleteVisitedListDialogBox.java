package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// class representing deletion dialog box for visited list
public class DeleteVisitedListDialogBox extends DeleteDialogBox {

    private GlobeApp globe;
    private JButton yes;
    private JDialog dialog;

    public DeleteVisitedListDialogBox(Frame parent, GlobeApp gb) {
        super(parent, gb);
        globe = gb;
        yes = super.getYes();
        dialog = super.getDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yes) {
            globe.deleteSelectedRow('v');
        }
        dialog.dispose();
    }
}
