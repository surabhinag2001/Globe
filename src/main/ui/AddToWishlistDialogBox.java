package ui;

import exceptions.CountryAlreadyPresentException;
import exceptions.InvalidCountryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToWishlistDialogBox implements ActionListener {

    private JDialog dialog;
    private JTextField nameField;
    private JTextField notesField;
    private JButton addButton;
    private GlobeApp gb;
    private Frame parent;
    private JLabel err;


    public AddToWishlistDialogBox(Frame parent,GlobeApp gb) {
        this.gb = gb;
        this.parent = parent;
        Point loc = parent.getLocation();
        dialog = new JDialog(parent);
        dialog.setLocation(loc.x + 80, loc.y + 80);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        JPanel message = new JPanel();
        JLabel text = new JLabel("Add country to wishlist");
//        text.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        text.setFont(new Font("Nunito", Font.PLAIN, 14));
//        text.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        message.setBackground(null);
        message.add(text);
        panel.add(message);
        panel.add(Box.createVerticalStrut(20));

        JPanel namePanel = new JPanel();
        JLabel name = new JLabel();
        name.setText("Name :    ");
        name.setFont(new Font("Nunito", Font.PLAIN, 14));
        name.setForeground(new Color(247, 37, 133));

        nameField = new JTextFieldHintUI("Enter country");
        nameField.setBorder(null);
        nameField.setMargin(new Insets(5, 50, 5, 10));
        nameField.setPreferredSize(new Dimension(200, 30));
        nameField.setBackground(null);
        namePanel.add(Box.createHorizontalStrut(15));
        namePanel.add(name);
        namePanel.add(nameField);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setBackground(null);

        panel.add(namePanel);
        panel.add(Box.createVerticalStrut(10));

        JPanel notesPanel = new JPanel();
        JLabel notes = new JLabel();
        notes.setText("Notes :    ");
        notes.setFont(new Font("Nunito", Font.PLAIN, 14));


        notesField = new JTextFieldHintUI("Enter notes");
        notesField.setBorder(null);
        notesField.setMargin(new Insets(5, 50, 5, 10));
        notesField.setPreferredSize(new Dimension(200, 30));
        notesField.setBackground(null);
        notesPanel.add(Box.createHorizontalStrut(15));
        notesPanel.add(notes);
        notesPanel.add(notesField);
        notesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notesPanel.setBackground(null);

        panel.add(notesPanel);
        panel.add(Box.createVerticalStrut(15));

        addButton = new JButton("Add");
        addButton.setForeground(new Color(247, 37, 133));
        addButton.addActionListener(this);
//        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Nunito", Font.PLAIN, 14));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(addButton);
        panel.add(Box.createVerticalStrut(18));

        err = new JLabel(" ");
        err.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        err.setFont(new Font("Nunito", Font.PLAIN, 12));
        err.setForeground(new Color(247, 37, 133));
        err.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(err);
        panel.add(Box.createVerticalStrut(20));

        dialog.getContentPane().add(panel);
        dialog.pack();
    }

    //EFFECTS: displays add dialog
    public void display() {
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addButton) {
            try {
                gb.addToWishlist(nameField.getText().trim().toUpperCase(),notesField.getText().trim());
                Object[] row = {nameField.getText().trim().toUpperCase(),notesField.getText().trim()};
                gb.getTableModel2().addRow(row);
                dialog.dispose();
            } catch (CountryAlreadyPresentException countryAlreadyPresentException) {
                countryAlreadyPresentException.printStackTrace();
                err.setText("Country already present");
            } catch (InvalidCountryException invalidCountryException) {
                invalidCountryException.printStackTrace();
                err.setText("Invalid country");

            }
        }
    }

}
