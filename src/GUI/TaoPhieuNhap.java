package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.PhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;

public class TaoPhieuNhap extends JPanel {
    private JTextField maPhieuNhapField, ngayField, maNhanVienField, loiNhuanField;
    private JComboBox<String> nhaCungCapComboBox;
    private List<String> suppliers = PhieuNhapBUS.getAllMaNCC();
    private JTable bookTable;
    private JTextField tongTienField;
    private JButton xacNhanButton, huyButton, chonSachButton;

    PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO();
    private TaiKhoanDTO tkUSER;

    public void setMaNV(String maNV) {
        phieuNhapDTO.setTenDN(maNV);
        maNhanVienField.setText(maNV);
    }

    @SuppressWarnings("static-access")
    public TaoPhieuNhap(TaiKhoanDTO tkUSER) {
        this.tkUSER = tkUSER;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Mã phiếu nhập
        addLabel("Mã phiếu nhập:", 0, 0, gbc);
        String maPN = PhieuNhapBUS.getLatestMaPN();
        System.out.println(maPN);
        maPhieuNhapField = addTextField(maPN, 1, 0, gbc);
        maPhieuNhapField.setEditable(false);

        // Ngày
        addLabel("Ngày:", 2, 0, gbc);
        ngayField = addTextField(java.time.LocalDate.now().toString(), 3, 0, gbc);
        ngayField.setEditable(false);

        // Mã nhân viên
        addLabel("Mã nhân viên:", 4, 0, gbc);
        maNhanVienField = addTextField(tkUSER.getTenDN(), 5, 0, gbc);
        maNhanVienField.setEditable(false);

        // Nhà cung cấp
        addLabel("Nhà cung cấp:", 0, 1, gbc);
        nhaCungCapComboBox = new JComboBox<String>(suppliers.toArray(new String[0]));
        nhaCungCapComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nhaCungCapComboBox, gbc);

        // Lợi nhuận
        addLabel("Lợi nhuận:", 2, 1, gbc);
        loiNhuanField = addTextField("0", 3, 1, gbc);

        // Ensure Lợi nhuận field is a number
        loiNhuanField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField textField = (JTextField) input;
                try {
                    String text = textField.getText();
                    if (text == null || text.trim().isEmpty()) {
                        textField.setText("0");
                    } else {
                        Double.parseDouble(text);
                    }
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho lợi nhuận.", "Lỗi nhập liệu",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        loiNhuanField.addActionListener(e -> {
            try {
                double loiNhuan = Double.parseDouble(loiNhuanField.getText()) / 100;
                for (int i = 0; i < bookTable.getRowCount(); i++) {
                    double giaNhap = Double.parseDouble(bookTable.getValueAt(i, 2).toString());
                    double donGia = giaNhap * (1 + loiNhuan);
                    double giaBia = PhieuNhapBUS.getGiaBia(bookTable.getValueAt(i, 0).toString());
                    if (donGia >= giaBia) {
                        donGia = giaBia;
                    }
                    bookTable.setValueAt(donGia, i, 3);
                    int soLuong = Integer.parseInt(bookTable.getValueAt(i, 1).toString());
                    double thanhTien = soLuong * giaNhap;
                    bookTable.setValueAt(thanhTien, i, 4);
                }
                // Update tổng tiền
                double tongTien = 0;
                for (int i = 0; i < bookTable.getRowCount(); i++) {
                    tongTien += Double.parseDouble(bookTable.getValueAt(i, 4).toString());
                }
                tongTienField.setText(String.valueOf(tongTien));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho lợi nhuận.", "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Chọn sách button
        chonSachButton = new JButton("+ Chọn sách");
        chonSachButton.setBackground(Color.decode("#249171"));
        chonSachButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(chonSachButton, gbc);

        // Book table
        String[] columnNames = { "Mã sách", "Số lượng", "Giá nhập", "Giá bán", "Thành tiền", "" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing of the "Số lượng" and "Giá nhập" columns
                return column == 1 || column == 2 || column == 5;
            }
        };
        bookTable = new JTable(model);
        bookTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), model));
        bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        bookTable.getColumnModel().getColumn(2).setCellRenderer(numberRenderer);
        bookTable.getColumnModel().getColumn(3).setCellRenderer(numberRenderer);
        bookTable.getColumnModel().getColumn(4).setCellRenderer(numberRenderer);

        // Custom table header and table appearance
        bookTable.getTableHeader().setBackground(BASE.color_table_heaer);
        bookTable.getTableHeader().setFont(BASE.font_header_frame);
        bookTable.setBackground(Color.WHITE);
        bookTable.setFont(BASE.font_frame);
        bookTable.setRowHeight(40); // thiết lập chiều cao các cột

        JScrollPane scrollPane = new JScrollPane(bookTable);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
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
        tongTienField.setEditable(false);

        // Center the Tổng tiền field
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tongTienField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        xacNhanButton = new JButton("Xác nhận");
        xacNhanButton.setBackground(Color.decode("#56B7C0"));
        xacNhanButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        huyButton = new JButton("Hủy");
        huyButton.setBackground(Color.decode("#56B7C0"));
        huyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(xacNhanButton);
        buttonPanel.add(huyButton);

        // Center the buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Event listeners
        chonSachButton.addActionListener(e -> {
            ChonSanPhamPhieuNhapGUI chonSanPhamPhieuNhapGUI = new ChonSanPhamPhieuNhapGUI(this);
            chonSanPhamPhieuNhapGUI.setVisible(true);
        });

        bookTable.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 1 || column == 2) { // Only update if quantity or price is changed
                    try {
                        int soLuong = Integer.parseInt(model.getValueAt(row, 1).toString());
                        double giaNhap = Double.parseDouble(model.getValueAt(row, 2).toString());
                        double loiNhuan = Double.parseDouble(loiNhuanField.getText()) / 100;
                        double donGia = giaNhap * (1 + loiNhuan);
                        // Get giá bìa
                        double giaBia = PhieuNhapBUS.getGiaBia(model.getValueAt(row, 0).toString());
                        if (giaNhap > giaBia) {
                            JOptionPane.showMessageDialog(this, "Giá nhập cần bé hơn hoặc bằng giá bìa: " + giaBia,
                                    "Cảnh báo",
                                    JOptionPane.WARNING_MESSAGE);
                            // Đặt lại giá nhập thành giá bìa
                            model.setValueAt(giaBia, row, 2);
                            giaNhap = giaBia; // Cập nhật giá trị biến để tiếp tục tính toán
                            return;
                        }
                        if (donGia >= giaBia) {
                            donGia = giaBia;
                        }
                        double thanhTien = soLuong * giaNhap;

                        model.setValueAt(donGia, row, 3);
                        model.setValueAt(thanhTien, row, 4);

                        // Update tổng tiền
                        double tongTien = 0;
                        for (int i = 0; i < model.getRowCount(); i++) {
                            tongTien += Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
                        }
                        tongTienField.setText(String.valueOf(tongTien));
                        tongTienField.setText(new DecimalFormat("#,###").format(tongTien));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số lượng và giá nhập.",
                                "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        List<ChiTietPhieuNhapDTO> chiTietPhieuNhapDTOs = new ArrayList<>();

        xacNhanButton.addActionListener(e -> {
            String maNCC = (String) nhaCungCapComboBox.getSelectedItem();
            String tenDN = maNhanVienField.getText();
            String ngayNhapStr = ngayField.getText();
            java.util.Date ngayNhap = java.sql.Date.valueOf(ngayNhapStr);
            double tongTien = Double.parseDouble(tongTienField.getText().replace(",", ""));
            int trangThai = 1;

            PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();

            for (int i = 0; i < model.getRowCount(); i++) {
                String tenSach = (String) model.getValueAt(i, 0);
                int soLuong = Integer.parseInt(model.getValueAt(i, 1).toString());
                double giaNhap = Double.parseDouble(model.getValueAt(i, 2).toString());
                if (soLuong <= 0 || giaNhap <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải lớn hơn 0.", "Lỗi nhập liệu",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double thanhTien = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", ""));
                if (thanhTien <= 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại số lượng và giá nhập.", "Cảnh báo",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                chiTietPhieuNhapDTOs.add(new ChiTietPhieuNhapDTO(maPN, tenSach, soLuong, thanhTien, giaNhap));
            }
            PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO(maPN, maNCC, tenDN, ngayNhap, tongTien, trangThai);
            phieuNhapBUS.addPhieuNhapWithDetails(phieuNhapDTO, new ArrayList<>(chiTietPhieuNhapDTOs));

            for (int j = 0; j < model.getRowCount(); j++) {
                String maSP = model.getValueAt(j, 0).toString();
                double giaNhap = Double.parseDouble(model.getValueAt(j, 2).toString());
                double loiNhuan = Double.parseDouble(loiNhuanField.getText()) / 100;
                double donGia = giaNhap * (1 + loiNhuan);
                // Get giá bìa
                double giaBia = PhieuNhapBUS.getGiaBia(model.getValueAt(j, 0).toString());
                if (donGia >= giaBia) {
                    donGia = giaBia;
                }
                phieuNhapBUS.checkGiaBan(maSP, donGia);
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
                int row = bookTable.convertRowIndexToModel(bookTable.getSelectedRow());
                if (row != -1) {
                    // Update tổng tiền
                    double thanhTien = Double.parseDouble(model.getValueAt(row, 4).toString());
                    double tongTien = Double.parseDouble(tongTienField.getText());
                    tongTien -= thanhTien;
                    tongTienField.setText(String.valueOf(tongTien));
                    // Remove row
                    SwingUtilities.invokeLater(() -> model.removeRow(row));
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

    public void receiveSelectedProduct(String maSach) {
        // Add the selected book details to the table with proper decimal formatting
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.addRow(new Object[] {
                maSach,
                0,
                0,
                0,
                0,
                "Xóa"
        });
    }

    // Custom cell renderer for numeric columns
    private DefaultTableCellRenderer numberRenderer = new DefaultTableCellRenderer() {
        @Override
        public void setValue(Object value) {
            if (value instanceof Number) {
                if (value instanceof Integer) {
                    setText(String.valueOf(value));
                } else {
                    setText(new DecimalFormat("#,###").format(((Number) value).doubleValue()));
                }
            } else {
                super.setValue(value);
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tạo Phiếu Nhập");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            QuyenDTO q = new QuyenDTO("QL", "Quản lý");
            TaiKhoanDTO tkDTO = new TaiKhoanDTO("NV07", "Phương123", "Quận 8", "0983456789", "Phuong579@gmail.com",
                    "55345678", q, 0);
            frame.add(new TaoPhieuNhap(tkDTO));
            frame.setVisible(true);
        });
    }
}
