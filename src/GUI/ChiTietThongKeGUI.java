/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nhatm
 */
public class ChiTietThongKeGUI extends JPanel{
    private JTable ThongKeTable;
    private DefaultTableModel tableModel;
    
    public ChiTietThongKeGUI(){
        init();
    }
    public void init(){
        setLayout(new BorderLayout());
        
        String[] columnNames = {"Khách hàng", "Thời gian", "Ngày", "Tên sách", "Số lượng bán", "Giá bán", "Tổng"};
        
        tableModel = new DefaultTableModel(columnNames, 0);
        
        ThongKeTable = new JTable(tableModel);
        ThongKeTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(ThongKeTable);
        add(scrollPane, BorderLayout.CENTER);
        
        ThongKeTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(Color.decode("#E0AC69"));
                return label;
            }
        });
        TableColumnModel columnModel = ThongKeTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(300);
        ThongKeTable.setRowHeight(30);
        ThongKeTable.setShowGrid(false);
        ThongKeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ThongKeTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = ThongKeTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(100, 40));
        setVisible(true);
    }
    
    public void addKH(String ten, LocalTime thoigian, LocalDate ngay, String tensach, int soluongban, String giaban, String tong) {
        tableModel.addRow(new Object[]{ten, thoigian, ngay, tensach, soluongban, giaban, tong});
    }
    
    public DefaultTableModel getTableModel(){
        return tableModel;
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 500);
        f.add(new ChiTietThongKeGUI());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
        
}
