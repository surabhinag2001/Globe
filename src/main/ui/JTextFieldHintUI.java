package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//class for customising the input hints displayed in the text fields
public class JTextFieldHintUI extends JTextField {

    Font gainFont = new Font("Nunito", Font.PLAIN, 16);
    Font lostFont = new Font("Nunito", Font.PLAIN, 16);

    //EFFECTS: constructor for JTextFieldHintUI
    public JTextFieldHintUI(final String hint) {

        setLost(hint, lostFont, Color.GRAY);

        //MODIFIES: this
        //EFFECTS: changes the text,font and color of the hint depending on thr focus
        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                } else {
                    setText(getText());
                    setFont(gainFont);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint) || getText().length() == 0) {
                    setLost(hint, lostFont, Color.GRAY);
                } else {
                    setLost(getText(), gainFont, Color.BLACK);
                }
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: changes the text,font and color of the hint when focs is lost
    private void setLost(String hint, Font lostFont, Color gray) {
        setText(hint);
        setFont(lostFont);
        setForeground(gray);
    }
}