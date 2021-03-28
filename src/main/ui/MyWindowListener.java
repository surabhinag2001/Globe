package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//class for representing window listener
public class MyWindowListener implements WindowListener {
    GlobeApp gb;

    public MyWindowListener(GlobeApp gb) {
        this.gb = gb;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    //MODIFIES: gb
    //EFFECTS: changes the cross icon from pink to grey
    @Override
    public void windowClosing(WindowEvent e) {
        gb.setCrossIcon("images/crossD.png");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
