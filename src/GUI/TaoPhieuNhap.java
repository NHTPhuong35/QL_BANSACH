package GUI;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*; // Removed unused import
import javax.swing.table.TableCellRenderer;

public class TaoPhieuNhap extends JFrame {
    private JTextField maPhieuNhapField, ngayField, maNhanVienField;
    private JComboBox<String> nhaCungCapComboBox;
    private String[] suppliers = { "Supplier 1", "Supplier 2", "Supplier 3" };
    private JTable bookTable;
    private JTextField tongTienField;
    private JButton xacNhanButton, huyButton, chonSachButton;

    public TaoPhieuNhap() {
        setTitle("Tạo Phiếu Nhập");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        

        // Mã phiếu nhập
        addLabel("Mã phiếu nhập:", 0, 0, gbc);
        maPhieuNhapField = addTextField("PN03", 1, 0, gbc);

        // Ngày
        addLabel("Ngày:", 2, 0, gbc);
        ngayField = addTextField("22-09-2024", 3, 0, gbc);

        // Mã nhân viên
        addLabel("Mã nhân viên:", 0, 1, gbc);
        maNhanVienField = addTextField("NV01", 1, 1, gbc);

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
        String[] columnNames = {"Tên sách", "Số lượng", "Giá nhập", "Thành tiền", ""};
        Object[][] data = {
            {"Đắc nhân tâm", "Số lượng", "Giá nhập", "Thành tiền: 0", "Xóa"},
            {"Muôn kiếp nhân sinh", "Số lượng", "Giá nhập", "Thành tiền: 0", "Xóa"}
        };
        bookTable = new JTable(data, columnNames); // Initialize bookTable with data and columnNames
        bookTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        bookTable.getColumn("").setCellRenderer(new ButtonRenderer());
        bookTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
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

        setVisible(true);
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

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
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
        SwingUtilities.invokeLater(TaoPhieuNhap::new);
    }
}