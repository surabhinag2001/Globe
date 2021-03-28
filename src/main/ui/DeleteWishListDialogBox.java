package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// class representing deletion dialog box for wishlist
public class DeleteWishListDialogBox extends DeleteDialogBox {
    private GlobeApp globe;
    private JButton yes;
    private JDialog dialog;

    //EFFECTS: constructs a dialog box for deleting a country from the wishlist
    public DeleteWishListDialogBox(Frame parent, GlobeApp gb) {
        super(parent, gb);
        globe = gb;
        yes = super.getYes();
        dialog = super.getDialog();
    }

    //MODIFIES: globe
    //EFFECTS: action listeners for the yes button
    // the selected country gets deleted
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yes) {
            globe.deleteSelectedRow('w');
        }
        dialog.dispose();
    }
}
