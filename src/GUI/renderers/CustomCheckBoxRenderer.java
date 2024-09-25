/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.renderers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nhatm
 */
public class CustomCheckBoxRenderer extends JComponent implements TableCellRenderer {
    private boolean isChecked;
    
    public CustomCheckBoxRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        isChecked = (value != null && (Boolean) value);
        setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        return this;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        int width = getWidth();
        int height = getHeight();
        int size = (int)(Math.min(width, height) * 0.6);
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, size, size);
        
        if (isChecked) {
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(Color.BLACK);
            int[] xPoints = {x + 2, x + size / 3, x + size - 2};
            int[] yPoints = {y + size / 2, y + size - 2, y + 2};
            g2d.drawPolyline(xPoints, yPoints, 3);
        }
        
        g2d.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(18, 18); // Smaller preferred size
    }
}
