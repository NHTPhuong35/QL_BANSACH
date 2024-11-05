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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private PhanQuyenBUS phanquyenBUS;
    private JComboBox<String> comboBox;
    private GridBagConstraints headComponentsPanelgbc;
    private GridBagConstraints headPanelgbc;
    
    public PhanQuyenGUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.white);
        
        headPanel = new JPanel();
        headPanel.setBackground(Color.white);
        headPanel.setLayout(new GridBagLayout());
        headPanelgbc = new GridBagConstraints();
        
        headComponentsPanel = new JPanel();
        headComponentsPanel.setBackground(Color.white);
        headComponentsPanel.setLayout(new GridBagLayout());
        headComponentsPanelgbc = new GridBagConstraints();

        phanquyenPanel = new JPanel();
        phanquyenPanel.setBackground(Color.yellow);
        
        phanquyenPanel.setLayout(new GridBagLayout());
        GridBagConstraints phanquyenPanelgbc = new GridBagConstraints();
        
        themquyen = new JButton("Thêm");

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Image/btAdd.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        themquyen.setIcon(scaledIcon);

        themquyen.setFont(new Font("Arial", Font.PLAIN, 12));
        themquyen.setFocusPainted(false);
        themquyen.setBackground(Color.decode("#5DADE2"));
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
        phanquyenBUS = new PhanQuyenBUS();
        
        String[] options = phanquyenBUS.getTenPhanQuyenList();
        comboBox = new JComboBox<>(options);
        
        themquyen.addActionListener((ActionEvent e) -> {
            ThemPhanQuyenDialog phanquyendialog = new ThemPhanQuyenDialog(this);
            
            //Xử lý khi JDialog tắt
            phanquyendialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String[] updatedOptions = phanquyenBUS.getTenPhanQuyenList();
                    comboBox.removeAllItems();
                    for (String option : updatedOptions) {
                        comboBox.addItem(option); // Thêm các mục mới
                    }
                }
            });
            phanquyendialog.setVisible(true); // Hiển thị dialog   
        });
                
        headComponentsPanelgbc.gridx = 0;
        headComponentsPanelgbc.gridy = 0;
        headComponentsPanelgbc.weightx = 1;
        headComponentsPanelgbc.anchor = GridBagConstraints.EAST;
        headComponentsPanelgbc.insets = new Insets(0, 0, 0, 0); // Margin top left bottom right
        headComponentsPanel.add(timquyen,headComponentsPanelgbc);
        
        headComponentsPanelgbc.gridx = 1;
        headComponentsPanelgbc.gridy = 0;
        headComponentsPanelgbc.weightx = 1;
        headComponentsPanelgbc.anchor = GridBagConstraints.WEST;
        headComponentsPanelgbc.insets = new Insets(0, 30, 0, 0); // Margin top left bottom right
        headComponentsPanel.add(comboBox,headComponentsPanelgbc);
        
//        headComponentsPanelgbc.gridx = 2;
//        headComponentsPanelgbc.gridy = 0;
//        headComponentsPanelgbc.weightx = 1;
//        headComponentsPanelgbc.anchor = GridBagConstraints.WEST;
//        headComponentsPanelgbc.insets = new Insets(0, 15, 0, 0); // Margin top left bottom right
//        headComponentsPanel.add(themquyen,headComponentsPanelgbc);
        
        headPanelgbc.gridx = 1;
        headPanelgbc.gridy = 0;
        headPanelgbc.weightx = 1;
        headPanelgbc.anchor = GridBagConstraints.EAST;
        headPanelgbc.insets = new Insets(0, 0, 0, 30); // Margin top left bottom right
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
        
        String[] columnNames = {"","Xem", "Thêm", "Xóa", "Sửa"};
        
        String selectedRole = (String) comboBox.getSelectedItem();
        String maphanquyen = phanquyenBUS.getMaPhanQuyenByTenPhanQuyen(selectedRole);
        Object[][] data = phanquyenBUS.getPhanQuyenListByRole(maphanquyen);
        
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
        
//        ActionListener cho comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String selectedRole = (String) comboBox.getSelectedItem();
                String maphanquyen = phanquyenBUS.getMaPhanQuyenByTenPhanQuyen(selectedRole);
                Object[][] newData = phanquyenBUS.getPhanQuyenListByRole(maphanquyen);

                model.setDataVector(newData, columnNames);

                CustomCheckBoxRenderer customCheckBoxRenderer = new CustomCheckBoxRenderer();
                for (int i = 1; i < table.getColumnCount(); i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(customCheckBoxRenderer);
                }
                
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                
                table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

                table.repaint();
                table.revalidate();
            }
        });
        
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customCheckBoxRenderer);
}
        
        //tickbox update
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                String selectedRole = (String) comboBox.getSelectedItem();
                String maphanquyen = phanquyenBUS.getMaPhanQuyenByTenPhanQuyen(selectedRole);

                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                
                // Kiểm tra nếu click vào cột checkbox (các cột bắt đầu từ 0)
                if (column > 0 && row >= 0) { // Đảm bảo rằng hàng và cột hợp lệ
                    Boolean currentValue = (Boolean) table.getValueAt(row, column);

                    String tenphanquyen = (String) table.getValueAt(row, 0); // Cột 0 chứa mã quyền

                    try {
                        //Test
//                        System.out.println("Giá trị ô trả về " + currentValue);
//                        System.out.println("Giá trị hàng " + row);
//                        System.out.println("Giá trị cột " + column);
                        //
                        phanquyenBUS.updatePhanQuyen(maphanquyen, currentValue, row, column);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Xử lý ngoại lệ nếu có
                    }
                }
            }
        });
                
        //Settings UI/UX table
        table.setRowHeight(45);
        scrollPane.setSize(100,200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setFont(BASE.font_header); // Đặt font đậm cho cột đầu tiên

        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setFont(BASE.font_header);
        headerRenderer.setBackground(BASE.color_header_tbl);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);
        tableHeader.setPreferredSize(new Dimension(100, 35));
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.setShowGrid(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền của JScrollPane
        table.setBorder(null);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setFont(BASE.font);
        
        phanquyenPanelgbc.gridx = 0;
        phanquyenPanelgbc.gridy = 0;
        phanquyenPanelgbc.weightx = 1;
        phanquyenPanelgbc.weighty = 1;
        phanquyenPanelgbc.fill = GridBagConstraints.BOTH;
        phanquyenPanel.add(scrollPane, phanquyenPanelgbc);
        phanquyenPanel.setBackground(Color.white);
        
        phanquyenPanel.revalidate();
        phanquyenPanel.repaint();
        
//        PhanQuyenBUS phanquyenBUS = new PhanQuyenBUS();
        
//        phanquyenBUS.loadPermissionsFromDatabase(model);
         
    } 
    
    public JButton getBtnThemQuyen(){
        return themquyen;
    }
    
    public JPanel getHeadComponentsPanel(){
        return headComponentsPanel;
    }
    
    public JPanel getHeadPanel(){
        return headPanel;
    }
    
    public GridBagConstraints getHeadComponentsPanelgbc(){
        return headComponentsPanelgbc;
    }
    
    public GridBagConstraints getHeadPanelgbc(){
        return headPanelgbc;
    }
    
    public static void main(String[] args) {
        new PhanQuyenGUI();
    }
}
