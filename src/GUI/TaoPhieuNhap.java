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
        String[] columnNames = { "Tên sách", "Số lượng", "Giá nhập", "Thành tiền", "" };
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
            JPanel panel = new JPanel(new GridLayout(3, 2));
            JTextField soLuongField = new JTextField();
            JTextField donGiaField = new JTextField();

            panel.add(new JLabel("Tên sách:"));
            JComboBox<String> tenSachComboBox = new JComboBox<>(PhieuNhapBUS.getAllTenSach().toArray(new String[0]));
            panel.add(tenSachComboBox);
            panel.add(new JLabel("Số lượng:"));
            panel.add(soLuongField);
            panel.add(new JLabel("Đơn giá:"));
            panel.add(donGiaField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm sách", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String tenSach = (String) tenSachComboBox.getSelectedItem();
                    int soLuong = Integer.parseInt(soLuongField.getText());
                    float donGia = Float.parseFloat(donGiaField.getText());

                    if (tenSach == null || tenSach.isEmpty() || soLuong <= 0 || donGia <= 0) {
                        throw new NumberFormatException();
                    }

                    float thanhTien = soLuong * donGia;
                    model.addRow(new Object[] { tenSach, soLuong, donGia, thanhTien, "Xóa" });

                    // Update tổng tiền
                    float tongTien = Float.parseFloat(tongTienField.getText());
                    tongTien += thanhTien;
                    tongTienField.setText(String.valueOf(tongTien));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin và các giá trị phải lớn hơn 0",
                            "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                }
            }
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

            for (int i = 0; i < model.getRowCount(); i++) {
                String tenSach = (String) model.getValueAt(i, 0);
                int soLuong = (int) model.getValueAt(i, 1);
                float donGia = (float) model.getValueAt(i, 2);
                float thanhTien = (float) model.getValueAt(i, 3);

                phieuNhapBUS.ThemChiTietPhieuNhapByTenSach(maPN, tenSach, soLuong, thanhTien, donGia);
            }
            phieuNhapBUS.ThemPhieuNhap(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);

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
        private DefaultTableModel model;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
            super(checkBox);
            this.model = model;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                int row = bookTable.getSelectedRow();
                if (row != -1) {
                    // Update tổng tiền
                    float thanhTien = (float) model.getValueAt(row, 3);
                    float tongTien = Float.parseFloat(tongTienField.getText());
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
