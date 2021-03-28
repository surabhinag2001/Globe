package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//abstract class representing deletion dialog box
public abstract class DeleteDialogBox implements ActionListener {

    private JButton yes;
    private JButton no;
    private JDialog dialog;
    private JPanel panel;
    private JLabel text;
    private JPanel options;

    //EFFECTS: constructor for DeleteDialogBox that also sets UI for dialog
    public DeleteDialogBox(Frame parent,GlobeApp gb) {
        Point loc = parent.getLocation();
        setDeleteDialogUI(parent, loc);
        yes = new JButton("Yes");
        yes.setForeground(new Color(247, 37, 133));
        yes.addActionListener(this);

        no = new JButton("No");
        no.addActionListener(this);

        yes.setContentAreaFilled(false);
        yes.setFocusPainted(false);
        yes.setFont(new Font("Nunito", Font.PLAIN, 14));

        no.setContentAreaFilled(false);
        no.setFocusPainted(false);
        no.setFont(new Font("Nunito", Font.PLAIN, 14));

        options.add(no);
        options.add(yes);

        panel.add(options);
        panel.add(Box.createVerticalStrut(20));

        dialog.getContentPane().add(panel);
        dialog.pack();
    }

    //EFFECTS: sets up the ui for this dialog box
    private void setDeleteDialogUI(Frame parent, Point loc) {
        dialog = new JDialog(parent);
        dialog.setLocation(loc.x + 80, loc.y + 80);
        panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        text = new JLabel("Are you sure you want to delete the selected item?");
        text.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        text.setFont(new Font("Nunito", Font.PLAIN, 14));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(text);
        panel.add(Box.createVerticalStrut(20));
        options = new JPanel();
        options.setBackground(null);
    }

    public JButton getYes() {
        return yes;
    }

    public JDialog getDialog() {
        return dialog;
    }

    //EFFECTS: displays deletion dialog
    public void display() {
        dialog.setVisible(true);
    }
}
