package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;

public class TaoPhieuNhap extends JPanel {
    private JTextField maPhieuNhapField, ngayField, maNhanVienField;
    private JComboBox<String> nhaCungCapComboBox;
    private List<String> suppliers = PhieuNhapBUS.getAllMaNCC();
    private JTable bookTable;
    private JTextField tongTienField;
    private JButton xacNhanButton, huyButton, chonSachButton;

    PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO();

    public void setMaNV(String maNV) {
        phieuNhapDTO.setTenDN(maNV);
        maNhanVienField.setText(maNV);
    }

    @SuppressWarnings("static-access")
    public TaoPhieuNhap() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Mã phiếu nhập
        addLabel("Mã phiếu nhập:", 0, 0, gbc);
        maPhieuNhapField = addTextField(PhieuNhapBUS.getLatestMaPN(), 1, 0, gbc);

        // Ngày
        addLabel("Ngày:", 2, 0, gbc);
        ngayField = addTextField(java.time.LocalDate.now().toString(), 3, 0, gbc);

        // Mã nhân viên
        addLabel("Mã nhân viên:", 0, 1, gbc);
        maNhanVienField = addTextField("NV01", 1, 1, gbc);

        // Nhà cung cấp
        addLabel("Nhà cung cấp:", 2, 1, gbc);
        nhaCungCapComboBox = new JComboBox<String>(suppliers.toArray(new String[0]));
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(nhaCungCapComboBox, gbc);

        // Chọn sách button
        chonSachButton = new JButton("+ Chọn sách");
        chonSachButton.setBackground(Color.decode("#249171"));
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(chonSachButton, gbc);

        // Book table
        String[] columnNames = { "Mã sách", "Số lượng", "Giá nhập", "Thành tiền", "" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(model);
        bookTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), model));
        bookTable.setRowHeight(40);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Tổng tiền
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        addLabel("Tổng tiền:", 2, 3, gbc);
        tongTienField = addTextField("0", 3, 3, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        xacNhanButton = new JButton("Xác nhận");
        xacNhanButton.setBackground(Color.decode("#56B7C0"));
        huyButton = new JButton("Hủy");
        huyButton.setBackground(Color.decode("#56B7C0"));
        buttonPanel.add(xacNhanButton);
        buttonPanel.add(huyButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        add(buttonPanel, gbc);

        // Event listeners
        chonSachButton.addActionListener(e -> {
            ChonSanPhamPhieuNhapGUI chonSanPhamPhieuNhapGUI = new ChonSanPhamPhieuNhapGUI(this);
            chonSanPhamPhieuNhapGUI.setVisible(true);
        });

        xacNhanButton.addActionListener(e -> {
            String maPN = PhieuNhapBUS.getLatestMaPN();
            String maNCC = (String) nhaCungCapComboBox.getSelectedItem();
            String tenDN = maNhanVienField.getText();
            String ngayNhapStr = ngayField.getText();
            java.util.Date ngayNhap = java.sql.Date.valueOf(ngayNhapStr);
            double tongTien = Double.parseDouble(tongTienField.getText());
            int trangThai = 1;

            PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();

            phieuNhapBUS.ThemPhieuNhap(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);

            for (int i = 0; i < model.getRowCount(); i++) {
                String tenSach = (String) model.getValueAt(i, 0);
                int soLuong = (int) model.getValueAt(i, 1);
                float donGia = (float) model.getValueAt(i, 2);
                float thanhTien = (float) model.getValueAt(i, 3);
                phieuNhapBUS.checkGiaBan(tenSach, donGia);
                phieuNhapBUS.ThemChiTietPhieuNhap(maPN, tenSach, soLuong, thanhTien, donGia);
            }
            

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        });

        huyButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        });

    }

    private void addLabel(String text, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(new JLabel(text), gbc);
    }

    private JTextField addTextField(String text, int x, int y, GridBagConstraints gbc) {
        JTextField field = new JTextField(text, 10);
        gbc.gridx = x;
        gbc.gridy = y;
        add(field, gbc);
        return field;
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            if ("Xóa".equals(value)) {
                setBackground(Color.decode("#EBA0AC"));
            } else {
                setBackground(UIManager.getColor("Button.background"));
            }
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private JButton button;
        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                int row = bookTable.getSelectedRow();
                if (row != -1) {
                    // Update tổng tiền
                    double thanhTien = (double) model.getValueAt(row, 3);
                    double tongTien = Double.parseDouble(tongTienField.getText());
                    tongTien -= thanhTien;
                    tongTienField.setText(String.valueOf(tongTien));
                    // Remove row
                    model.removeRow(row);
                }
                fireEditingStopped();
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            if ("Xóa".equals(value)) {
                button.setBackground(Color.decode("#EBA0AC"));
            } else {
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }
        }

        public void receiveSelectedProduct(String maSach, int soLuong, double donGia) {
        // Calculate total price for the selected product
        double thanhTien = soLuong * donGia;

        // Add the selected book details to the table
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.addRow(new Object[] { maSach, soLuong, donGia, thanhTien, "Xóa" });

        // Update tổng tiền (total amount)
        float tongTien = Float.parseFloat(tongTienField.getText());
        tongTien += thanhTien;
        tongTienField.setText(String.valueOf(tongTien));
        }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tạo Phiếu Nhập");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new TaoPhieuNhap());
            frame.setVisible(true);
        });
    }

    
}
