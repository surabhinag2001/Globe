package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class HeaderRenderer extends JLabel implements TableCellRenderer {

    public HeaderRenderer() {
        setFont(new Font("Nunito", Font.PLAIN,13));
        setOpaque(true);
        setForeground(Color.BLACK);
        setBackground(new Color(229, 229, 229));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229)));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        setText(value.toString());
        table.getTableHeader().setPreferredSize(new Dimension(100,35));
        return this;
    }
}
