package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//class for customising the headers of the tables
public class HeaderRenderer extends JLabel implements TableCellRenderer {

    //EFFECTS: sets the ui for the header of the tables
    public HeaderRenderer() {
        setFont(new Font("Nunito", Font.PLAIN,13));
        setOpaque(true);
        setForeground(Color.BLACK);
        setBackground(new Color(248, 248, 251));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(BorderFactory.createLineBorder(new Color(248, 248, 251)));
    }


    //EFFECTS: returns customised header component
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        setText(value.toString());
        table.getTableHeader().setPreferredSize(new Dimension(100,35));
        return this;
    }
}
