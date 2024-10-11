package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import DTO.PhieuNhapDTO;
import DTO.ChiTietPhieuNhapDTO;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;

public class SuaPhieuNhap extends JPanel {
    private JTextField maPhieuNhapField, ngayField, maNhanVienField;
    private JComboBox<String> nhaCungCapComboBox;
    private String[] suppliers = { "NCC01", "NCC02", "NCC03", "NCC04" };
    private JTable bookTable;
    private JTextField tongTienField;
    private JButton xacNhanButton, huyButton, chonSachButton;

    PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO();
    PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();

    public void setMaPhieuNhap(String maPhieuNhap) {
        maPhieuNhapField.setText(maPhieuNhap);
        phieuNhapDTO.setMaPN(maPhieuNhap);
        loadChiTietPhieuNhap(maPhieuNhap); // Load details and update table
    }

    public void setNhaCungCap(String nhaCungCap) {
        nhaCungCapComboBox.setSelectedItem(nhaCungCap);
        phieuNhapDTO.setMaNCC(nhaCungCap);
    }

    public void setMaNhanVien(String maNhanVien) {
        maNhanVienField.setText(maNhanVien);
        phieuNhapDTO.setTenDN(maNhanVien);
    }

    public void setNgay(Date ngayLap) {
        ngayField.setText(ngayLap.toString());
        phieuNhapDTO.setNgayNhap(ngayLap);
    }

    public void setTongTien(Double tongTien) {
        tongTienField.setText(String.valueOf(tongTien));
        phieuNhapDTO.setTongTien(tongTien);
    }

    public SuaPhieuNhap() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Mã phiếu nhập
        addLabel("Mã phiếu nhập:", 0, 0, gbc);
        maPhieuNhapField = addTextField(phieuNhapDTO.getMaPN(), 1, 0, gbc);

        // Ngày
        addLabel("Ngày:", 2, 0, gbc);
        ngayField = addTextField(phieuNhapDTO.getNgayNhap() != null ? phieuNhapDTO.getNgayNhap().toString() : "", 3, 0,
                gbc);

        // Mã nhân viên
        addLabel("Mã nhân viên:", 0, 1, gbc);
        maNhanVienField = addTextField(phieuNhapDTO.getTenDN(), 1, 1, gbc);

        // Nhà cung cấp
        nhaCungCapComboBox = new JComboBox<>(suppliers);
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
        String[] columnNames = { "Tên sách", "Số lượng", "Giá nhập", "Thành tiền", "" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel); // Initialize bookTable with tableModel
        bookTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
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
        tongTienField = addTextField(String.valueOf(phieuNhapDTO.getTongTien()), 3, 3, gbc);

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

        // Event handlers
        huyButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        });

        xacNhanButton.addActionListener(e -> {
            phieuNhapDTO.setMaPN(maPhieuNhapField.getText());
            phieuNhapDTO.setMaNCC(nhaCungCapComboBox.getSelectedItem().toString());
            phieuNhapDTO.setTenDN(maNhanVienField.getText());
            phieuNhapDTO.setTongTien(Double.parseDouble(tongTienField.getText()));
            phieuNhapDTO.setTrangThai(1); // Set trạng thái to 1

            phieuNhapBUS.suaPhieuNhap(
                    phieuNhapDTO.getMaPN(),
                    phieuNhapDTO.getMaNCC(),
                    phieuNhapDTO.getTenDN(),
                    phieuNhapDTO.getNgayNhap(),
                    phieuNhapDTO.getTongTien(),
                    phieuNhapDTO.getTrangThai());
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
            PhieuNhapGUI phieuNhapGUI = new PhieuNhapGUI();
            phieuNhapGUI.loadData();
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

    private void loadChiTietPhieuNhap(String maPhieuNhap) {
        List<ChiTietPhieuNhapDTO> chiTietList = phieuNhapBUS.LayChiTietPhieuNhap(maPhieuNhap);
        DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();
        tableModel.setRowCount(0); // Clear existing data

        for (ChiTietPhieuNhapDTO chiTiet : chiTietList) {
            Object[] rowData = {
                    chiTiet.getMASACH(),
                    chiTiet.getSOLUONG(),
                    chiTiet.getGIANHAP(),
                    chiTiet.getTONGTIEN(),
                    "Xóa"
            };
            tableModel.addRow(rowData);
        }
    }

    private void xoaChiTietPhieuNhap(int row) {
        DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();
        String maSach = (String) tableModel.getValueAt(row, 0);
        int soLuong = (int) tableModel.getValueAt(row, 1);
        phieuNhapBUS.xoaChiTietPhieuNhap(phieuNhapDTO.getMaPN(), maSach);
        SanPhamBUS sanPhamBUS = new SanPhamBUS();
        int currentStock = PhieuNhapBUS.getSoLuongSP(maSach);
        sanPhamBUS.CapNhatSoLuongSP(maSach, currentStock + soLuong);

        double currentTotal = Double.parseDouble(tongTienField.getText());
        double rowTotal = (double) tableModel.getValueAt(row, 3);
        double newTotal = currentTotal - rowTotal;
        tongTienField.setText(String.valueOf(newTotal));
        tableModel.removeRow(row);
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

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                fireEditingStopped();
                int row = bookTable.getSelectedRow();
                if ("Xóa".equals(label)) {
                    xoaChiTietPhieuNhap(row);
                }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sửa Phiếu Nhập");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new SuaPhieuNhap());
            frame.setVisible(true);
        });
    }
}
