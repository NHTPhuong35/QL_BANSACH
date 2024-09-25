/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PhanQuyenBUS;
import GUI.renderers.CustomCheckBoxRenderer;
import GUI.renderers.NoBorderCheckBoxRenderer;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nhatm
 */
public class PhanQuyenGUI extends JPanel {
    private JPanel headPanel;
    private JPanel headComponentsPanel;
    private JPanel phanquyenPanel;
    private JLabel timquyen;
    private JButton themquyen;
   
    public PhanQuyenGUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.white);
        
        headPanel = new JPanel();
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new GridBagLayout());
        GridBagConstraints headPanelgbc = new GridBagConstraints();
        
        headComponentsPanel = new JPanel();
        headComponentsPanel.setBackground(Color.white);
        headComponentsPanel.setLayout(new GridBagLayout());
        GridBagConstraints headComponentsPanelgbc = new GridBagConstraints();

        phanquyenPanel = new JPanel();
        phanquyenPanel.setBackground(Color.yellow);
        
        phanquyenPanel.setLayout(new GridBagLayout());
        GridBagConstraints phanquyenPanelgbc = new GridBagConstraints();
        
        themquyen = new JButton("Thêm quyền");
        themquyen.setFont(new Font("Arial", Font.PLAIN, 12));
        themquyen.setFocusPainted(false);
        themquyen.setBackground(Color.decode("#57B8C1"));
        themquyen.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                themquyen.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                themquyen.setBackground(Color.decode("#57B8C1"));
            }
        });
        timquyen = new JLabel("Tìm quyền");
        
        String[] options = { "Tất cả", "Tác giả", "Loại", "Nhà cung cấp", "Khách hàng", "Phiếu nhập", "Sản phẩm", "Hóa đơn", "Thống kê", "Tài khoản", "Phân quyền" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        
        headComponentsPanelgbc.gridx = 0;
        headComponentsPanelgbc.gridy = 0;
        headComponentsPanelgbc.weightx = 1;
        headComponentsPanelgbc.anchor = GridBagConstraints.WEST;
        headComponentsPanelgbc.insets = new Insets(0, 0, 0, 0); // Margin top left bottom right
        headComponentsPanel.add(timquyen,headComponentsPanelgbc);
        
        headComponentsPanelgbc.gridx = 1;
        headComponentsPanelgbc.gridy = 0;
        headComponentsPanelgbc.weightx = 1;
        headComponentsPanelgbc.anchor = GridBagConstraints.WEST;
        headComponentsPanelgbc.insets = new Insets(0, 30, 0, 0); // Margin top left bottom right
        headComponentsPanel.add(comboBox,headComponentsPanelgbc);
        
        headComponentsPanelgbc.gridx = 2;
        headComponentsPanelgbc.gridy = 0;
        headComponentsPanelgbc.weightx = 1;
        headComponentsPanelgbc.anchor = GridBagConstraints.WEST;
        headComponentsPanelgbc.insets = new Insets(0, 15, 0, 0); // Margin top left bottom right
        headComponentsPanel.add(themquyen,headComponentsPanelgbc);
        
        headPanelgbc.gridx = 0;
        headPanelgbc.gridy = 0;
        headPanelgbc.weightx = 1;
        headPanelgbc.anchor = GridBagConstraints.WEST;
        headPanelgbc.insets = new Insets(0, 10, 0, 0); // Margin top left bottom right
        headPanel.add(headComponentsPanel, headPanelgbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        add(headPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        add(phanquyenPanel, gbc);
        
        //Table
        String[] columnNames = {"", "Xem", "Thêm", "Sửa", "Xóa"};
        Object[][] data = {
            {"Tác giả", false, false, false, false},
            {"Loại", false, false, false, false},
            {"Nhà cung cấp", false, false, false, false},
            {"Khách hàng", false, false, false, false},
            {"Phiếu nhập", false, false, false, false},
            {"Sản phẩm", false, false, false, false},
            {"Hóa đơn", false, false, false, false},
            {"Thống kê", false, false, false, false},
            {"Tài khoản", false, false, false, false},
            {"Phân quyền", false, false, false, false}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // checkbox setting only
            }
        };
        JTable table = new JTable(model);

        CustomCheckBoxRenderer customCheckBoxRenderer = new CustomCheckBoxRenderer();

        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customCheckBoxRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        //Settings UI/UX table
        table.setRowHeight(45);
        scrollPane.setSize(100,200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(new Font("Arial", Font.BOLD, 15)); // Đặt font đậm cho cột đầu tiên

        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 15));
        headerRenderer.setBackground(Color.decode("#98DCE2"));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);
        tableHeader.setPreferredSize(new Dimension(100, 35));
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.setShowGrid(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền của JScrollPane
        table.setBorder(null);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setFont(new Font("Arial", Font.BOLD, 15));
        
        phanquyenPanelgbc.gridx = 0;
        phanquyenPanelgbc.gridy = 0;
        phanquyenPanelgbc.weightx = 1;
        phanquyenPanelgbc.weighty = 1;
        phanquyenPanelgbc.fill = GridBagConstraints.BOTH;
        phanquyenPanel.add(scrollPane, phanquyenPanelgbc);
        phanquyenPanel.setBackground(Color.white);
        
        phanquyenPanel.revalidate();
        phanquyenPanel.repaint();
        
        PhanQuyenBUS phanquyenBUS = new PhanQuyenBUS();
        
//        phanquyenBUS.loadPermissionsFromDatabase(model);
         
    }  
    public static void main(String[] args) {
        new PhanQuyenGUI();
    }
}
