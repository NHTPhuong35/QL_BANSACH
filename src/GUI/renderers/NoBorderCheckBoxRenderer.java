/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.renderers;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author nhatm
 */
public class NoBorderCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    public NoBorderCheckBoxRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
        setBorderPainted(false);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected((value != null && (Boolean) value));
        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return this;
    }
}
