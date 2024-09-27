/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author nhatm
 */

import GUI.Dialog.TacGia.ThemTacGiaDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TacGiaGUI extends JPanel {
    private JTable tacGiaTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton addButton, editButton, deleteButton;
    private JPanel editButtonPanel;
    private JPanel searchPanel;
    
    public TacGiaGUI() {
        setLayout(new BorderLayout());
        
        String[] columnNames = {"Mã tác giả", "Tên tác giả"};
        
        tableModel = new DefaultTableModel(columnNames, 0);
        
        tacGiaTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tacGiaTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Tùy chỉnh renderer cho tiêu đề
        tacGiaTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(Color.decode("#98DCE2"));
                return label;
            }
        });
        
        // Chỉnh chiều rộng ô và chiều cao hàng
        TableColumnModel columnModel = tacGiaTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(300);
        tacGiaTable.setRowHeight(30);
        
        tacGiaTable.setShowGrid(false);
        tacGiaTable.setFont(new Font("Arial", Font.PLAIN, 14));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tacGiaTable.setDefaultRenderer(Object.class, centerRenderer);
        
        JTableHeader tableHeader = tacGiaTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(100, 40));
        
        // Dữ liệu mẫu
        tableModel.addRow(new Object[]{"NCC01", "Nguyễn Phong"});
        tableModel.addRow(new Object[]{"NCC02", "Nguyễn Nhật Ánh"});

        // Panel chức năng và tìm kiếm
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(Color.WHITE);
        
        editButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        editButtonPanel.setBackground(Color.WHITE);
        searchPanel.setBackground(Color.WHITE);

        
        controlPanel.add(editButtonPanel, BorderLayout.WEST);
        controlPanel.add(searchPanel, BorderLayout.EAST);
        
        addButton = new JButton("Thêm tác giả");
        addButton.setBackground(Color.decode("#A6E3A1"));
        
        editButton = new JButton("Sửa tác giả");
        editButton.setBackground(Color.decode("#B4BEFE"));
        
        deleteButton = new JButton("Xóa tác giả");
        deleteButton.setBackground(Color.decode("#EBA0AC"));
        
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Tìm kiếm");

        editButtonPanel.add(addButton);
        editButtonPanel.add(editButton);
        editButtonPanel.add(deleteButton);
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        add(controlPanel, BorderLayout.NORTH);

        // "Thêm tác giả"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemTacGiaDialog dialog = new ThemTacGiaDialog(SwingUtilities.getWindowAncestor(TacGiaGUI.this), TacGiaGUI.this);
                dialog.setVisible(true);
            }
        });

        // "Tìm kiếm"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                tacGiaTable.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
            }
        });
    }

    public void addTacGia(String ma, String ten) {
        tableModel.addRow(new Object[]{ma, ten});
    }
}