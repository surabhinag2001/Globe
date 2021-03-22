package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class DeleteDialogBox extends JDialog implements ActionListener {

    private JButton yes;
    private JButton no;

    public DeleteDialogBox(Frame parent,GlobeApp gb) {
        Point loc = parent.getLocation();
        setLocation(loc.x + 80, loc.y + 80);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(248, 248, 251));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        JLabel text = new JLabel("Are you sure you want to delete the selected item?");
        text.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 15, new Color(248, 248, 251)));
        text.setFont(new Font("Nunito", Font.PLAIN, 14));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(text);
        panel.add(Box.createVerticalStrut(20));
        JPanel options = new JPanel();
        options.setBackground(null);
        yes = new JButton("Yes");
        yes.setForeground(new Color(247, 37, 133));
        yes.addActionListener(this);

        no = new JButton("No");
        no.addActionListener(this);

        yes.setBorderPainted(false);
        yes.setContentAreaFilled(false);
        yes.setFocusPainted(false);
        yes.setFont(new Font("Nunito", Font.PLAIN, 14));

        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        no.setFocusPainted(false);
        no.setFont(new Font("Nunito", Font.PLAIN, 14));

        options.add(no);
        options.add(yes);

        panel.add(options);
        panel.add(Box.createVerticalStrut(20));

        getContentPane().add(panel);
        pack();
    }

    public JButton getYes() {
        return yes;
    }

    public void display() {
        this.setVisible(true);
    }
}
