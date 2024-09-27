/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Admin
 */
public class DeleteButtonRenderer extends JButton implements TableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    setText("Xóa"); // Set the button text
    setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
    return this;
  }
}
