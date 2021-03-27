package ui;

import exceptions.MaxDateBeforeMinDateException;
import model.VisitedCountry;
import model.VisitedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.util.List;

// class representing dialog box to display filtered list
public class FilterListDialog implements ActionListener {

    private  Frame parent;
    private  JLabel err;
    private JDialog dialog;
    private GlobeApp gb;
    private JPanel panel;
    private JPanel topPanel;
    private JLabel to;
    private JLabel from;
    private JTextField fromDateField;
    private JPanel fromPanel;
    private JTextField toDateField;
    private JPanel toPanel;
    private JButton filterButton;
    private JPanel message;
    private JLabel text;


    public FilterListDialog(Frame parent, GlobeApp gb) {
        this.gb = gb;
        this.parent = parent;
        Point loc = parent.getLocation();
        
        setFilterListDialogUI(parent,loc);

        setFromUI();
        panel.add(fromPanel);
        panel.add(Box.createVerticalStrut(10));

        setToUI();
        panel.add(toPanel);
        panel.add(Box.createVerticalStrut(15));

        setFilterButtonUI();
        panel.add(filterButton);
        panel.add(Box.createVerticalStrut(18));

        setErrorUI();
        panel.add(err);
        panel.add(Box.createVerticalStrut(20));

        dialog.getContentPane().add(panel);
        dialog.pack();
    }

    private void setErrorUI() {
        err = new JLabel(" ");

        err.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        err.setFont(new Font("Nunito", Font.PLAIN, 12));
        err.setForeground(new Color(247, 37, 133));
        err.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setToUI() {
        toPanel = new JPanel();
        to = new JLabel();
        to.setText("To :    ");
        to.setFont(new Font("Nunito", Font.PLAIN, 14));

        toDateField = new JTextFieldHintUI("yyyy-mm-dd");
        setTextFieldUI(toDateField);

        toPanel.add(Box.createHorizontalStrut(15));
        toPanel.add(to);
        toPanel.add(toDateField);
        toPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        toPanel.setBackground(null);
    }

    private void setFromUI() {
        fromPanel = new JPanel();
        from = new JLabel();
        from.setText("From :    ");
        from.setFont(new Font("Nunito", Font.PLAIN, 14));
        from.setForeground(new Color(247, 37, 133));

        fromDateField = new JTextFieldHintUI("yyyy-mm-dd");
        setTextFieldUI(fromDateField);

        fromPanel.add(Box.createHorizontalStrut(15));
        fromPanel.add(from);
        fromPanel.add(fromDateField);
        fromPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fromPanel.setBackground(null);
    }

    private void setFilterButtonUI() {
        filterButton = new JButton("Filter");

        filterButton.setForeground(new Color(247, 37, 133));
        filterButton.addActionListener(this);
        filterButton.setContentAreaFilled(false);
        filterButton.setFocusPainted(false);
        filterButton.setFont(new Font("Nunito", Font.PLAIN, 14));
        filterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setTextFieldUI(JTextField textField) {
        textField.setBorder(null);
        textField.setMargin(new Insets(5, 50, 5, 10));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(null);
    }

    private void setFilterListDialogUI(Frame parent, Point loc) {
        dialog = new JDialog(parent);
        dialog.setLocation(loc.x + 80, loc.y + 80);
        panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));

        message = new JPanel();
        text = new JLabel("Enter dates");

        text.setFont(new Font("Nunito", Font.PLAIN, 14));

        message.setBackground(null);
        message.add(text);
        panel.add(message);
        panel.add(Box.createVerticalStrut(20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == filterButton) {
            try {
                gb.createFilterTable(fromDateField.getText().trim(),toDateField.getText().trim());
                gb.getTbPanel3().remove(gb.getSp3());
                gb.getTbPanel3().add(gb.getSp4());
                gb.getTbPanel3().repaint();
                gb.getTbPanel3().revalidate();
                dialog.dispose();
            } catch (MaxDateBeforeMinDateException maxDateBeforeMinDateException) {
                maxDateBeforeMinDateException.printStackTrace();
                err.setText("Dates not correctly entered");
            } catch (DateTimeException dateTimeException) {
                dateTimeException.printStackTrace();
                err.setText("Invalid date");
            } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                ex.printStackTrace();
                err.setText("Incorrect date format");
            }
        }
    }

    //EFFECTS: displays add dialog
    public void display() {
        dialog.setVisible(true);
    }
}
