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
    private JPanel panel;
    private JPanel message;
    private JLabel text;
    private JPanel namePanel;
    private JLabel name;
    private JPanel notesPanel;
    private JLabel notes;


    public AddToWishlistDialogBox(Frame parent,GlobeApp gb) {
        this.gb = gb;
        this.parent = parent;
        Point loc = parent.getLocation();
        setAddWishListDialogUI(parent, loc);



        setNameUI();

        panel.add(namePanel);
        panel.add(Box.createVerticalStrut(10));


        setNotesUI();

        panel.add(notesPanel);
        panel.add(Box.createVerticalStrut(15));


        setAddButtonUI();

        panel.add(addButton);
        panel.add(Box.createVerticalStrut(18));


        setErrorUI();
        panel.add(err);
        panel.add(Box.createVerticalStrut(20));

        dialog.getContentPane().add(panel);
        dialog.pack();
    }

    private void setNotesUI() {
        notesPanel = new JPanel();
        notes = new JLabel();

        notes.setText("Notes :    ");
        notes.setFont(new Font("Nunito", Font.PLAIN, 14));


        notesField = new JTextFieldHintUI("Enter notes");
        setTextFieldUI(notesField);

        notesPanel.add(Box.createHorizontalStrut(15));
        notesPanel.add(notes);
        notesPanel.add(notesField);
        notesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notesPanel.setBackground(null);
    }

    private void setNameUI() {
        namePanel = new JPanel();
        name = new JLabel();

        name.setText("Name :    ");
        name.setFont(new Font("Nunito", Font.PLAIN, 14));
        name.setForeground(new Color(247, 37, 133));

        nameField = new JTextFieldHintUI("Enter country");
        setTextFieldUI(nameField);

        namePanel.add(Box.createHorizontalStrut(15));
        namePanel.add(name);
        namePanel.add(nameField);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setBackground(null);
    }

    private void setAddWishListDialogUI(Frame parent, Point loc) {
        dialog = new JDialog(parent);
        dialog.setLocation(loc.x + 80, loc.y + 80);
        panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));

        message = new JPanel();
        text = new JLabel("Add country to wishlist");
//        text.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        text.setFont(new Font("Nunito", Font.PLAIN, 14));
//        text.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        message.setBackground(null);
        message.add(text);
        panel.add(message);
        panel.add(Box.createVerticalStrut(20));
    }

    private void setTextFieldUI(JTextField textField) {
        textField.setBorder(null);
        textField.setMargin(new Insets(5, 50, 5, 10));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(null);
    }

    private void setErrorUI() {
        err = new JLabel(" ");

        err.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        err.setFont(new Font("Nunito", Font.PLAIN, 12));
        err.setForeground(new Color(247, 37, 133));
        err.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setAddButtonUI() {
        addButton = new JButton("Add");

        addButton.setForeground(new Color(247, 37, 133));
        addButton.addActionListener(this);
//        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Nunito", Font.PLAIN, 14));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
