package ui;

import exceptions.CountryAlreadyPresentException;
import exceptions.FutureDateException;
import exceptions.InvalidCountryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;

public class AddToVisitsDialogBox implements ActionListener {

    private JDialog dialog;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField notesField;
    private JButton addButton;
    private GlobeApp gb;
    private Frame parent;
    private JLabel err;
    private JPanel panel;
    private JPanel message;
    private JLabel text;
    private JPanel namePanel;
    private Object[] row;
    private JPanel datePanel;
    private JPanel notesPanel;
    private JLabel notes;
    private JLabel date;
    private JLabel name;

    public AddToVisitsDialogBox(Frame parent, GlobeApp gb) {
        this.gb = gb;
        this.parent = parent;
        Point loc = parent.getLocation();
        setAddVisitDialogUI(parent, loc);



        setNameUI();

        panel.add(namePanel);
        panel.add(Box.createVerticalStrut(10));



        setDateUI();

        panel.add(datePanel);
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
        notes.setForeground(new Color(247, 37, 133));


        notesField = new JTextFieldHintUI("Enter notes");
        setTextFieldUI(notesField);

        notesPanel.add(Box.createHorizontalStrut(15));
        notesPanel.add(notes);
        notesPanel.add(notesField);
        notesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notesPanel.setBackground(null);
    }

    private void setDateUI() {
        datePanel = new JPanel();
        date = new JLabel();

        date.setText("Date :    ");
        date.setFont(new Font("Nunito", Font.PLAIN, 14));


        dateField = new JTextFieldHintUI("yyyy-mm-dd");
        setTextFieldUI(dateField);

        datePanel.add(Box.createHorizontalStrut(15));
        datePanel.add(date);
        datePanel.add(dateField);
        datePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        datePanel.setBackground(null);

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

    private void setAddVisitDialogUI(Frame parent, Point loc) {
        dialog = new JDialog(parent);
        dialog.setLocation(loc.x + 80, loc.y + 80);
        panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));

        message = new JPanel();
        text = new JLabel("Add visit");

        text.setFont(new Font("Nunito", Font.PLAIN, 14));

        message.setBackground(null);
        message.add(text);
        panel.add(message);
        panel.add(Box.createVerticalStrut(20));
    }

    private void setErrorUI() {
        err = new JLabel(" ");

        err.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        err.setFont(new Font("Nunito", Font.PLAIN, 12));
        err.setForeground(new Color(247, 37, 133));
        err.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setTextFieldUI(JTextField textField) {
        textField.setBorder(null);
        textField.setMargin(new Insets(5, 50, 5, 10));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(null);
    }

    private void setAddButtonUI() {
        addButton = new JButton("Add");

        addButton.setForeground(new Color(247, 37, 133));
        addButton.addActionListener(this);
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
                addOnClick();
                dialog.dispose();
            } catch (CountryAlreadyPresentException countryAlreadyPresentException) {
                countryAlreadyPresentException.printStackTrace();
                err.setText("Country already present");
            } catch (InvalidCountryException invalidCountryException) {
                invalidCountryException.printStackTrace();
                err.setText("Invalid country");
            } catch (FutureDateException futureDateException) {
                futureDateException.printStackTrace();
                err.setText("Date in future");
            } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                ex.printStackTrace();
                err.setText("Incorrect date format");
            } catch (DateTimeException dateTimeException) {
                dateTimeException.printStackTrace();
                err.setText("Invalid date");
            }
        }
    }

    private void addOnClick() throws CountryAlreadyPresentException, InvalidCountryException, FutureDateException {
        gb.addToVisitedList(nameField.getText().trim().toUpperCase(), dateField.getText().trim(),
                notesField.getText().trim());
        row = new Object[]{nameField.getText().trim().toUpperCase(), dateField.getText().trim(),
               notesField.getText().trim()};
        gb.getTableModel3().addRow(row);
    }
}
