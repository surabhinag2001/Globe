package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// class representing deletion dialog box for visited list
public class DeleteVisitedListDialogBox extends DeleteDialogBox {

    private GlobeApp globe;
    private JButton yes;
    private JDialog dialog;

    //EFFECTS: constructs a dialog box for deleting a country from the visited list
    public DeleteVisitedListDialogBox(Frame parent, GlobeApp gb) {
        super(parent, gb);
        globe = gb;
        yes = super.getYes();
        dialog = super.getDialog();
    }

    //MODIFIES: globe
    //EFFECTS: action listeners for the yes button
    // the selected visit gets deleted
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yes) {
            globe.deleteSelectedRow('v');
        }
        dialog.dispose();
    }
}
