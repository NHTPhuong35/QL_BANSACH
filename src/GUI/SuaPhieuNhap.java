package GUI;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private List<String> suppliers = PhieuNhapBUS.getAllMaNCC();
    private JTable bookTable;
    private JButton xacNhanButton, huyButton;
    private ArrayList<String> befoArrayList = new ArrayList<>();
    private ArrayList<String> afterArrayList = new ArrayList<>();

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

    public void setTongTien(double tongTien) {
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
        addLabel("Nhà cung cấp:", 2, 1, gbc);
        nhaCungCapComboBox = new JComboBox<String>(suppliers.toArray(new String[0]));
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(nhaCungCapComboBox, gbc);


        // Book table
        String[] columnNames = { "Tên sách", "Số lượng"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel); // Initialize bookTable with tableModel
        bookTable.setFont(BASE.font);
        bookTable.setRowHeight(40); // thiết lập chiều cao các cột
        JScrollPane scrollPane = new JScrollPane(bookTable);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);


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
            phieuNhapDTO.setTrangThai(1); // Set trạng thái to 1

            phieuNhapBUS.suaPhieuNhap(
                    phieuNhapDTO.getMaPN(),
                    phieuNhapDTO.getMaNCC(),
                    phieuNhapDTO.getTenDN(),
                    phieuNhapDTO.getNgayNhap(),
                    phieuNhapDTO.getTongTien(),
                    phieuNhapDTO.getTrangThai());
            
            // Add data to afterArrayList
            DefaultTableModel updatedTableModel = (DefaultTableModel) bookTable.getModel();
            int rowCount = updatedTableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSach = updatedTableModel.getValueAt(i, 0).toString();
                String soLuong = updatedTableModel.getValueAt(i, 1).toString();
                afterArrayList.add(maSach + "/" + soLuong);
            }
            
            // Calculate the difference between afterArrayList and befoArrayList
            ArrayList<String> diffArrayList = new ArrayList<>();
            for (String after : afterArrayList) {
                String[] afterParts = after.split("/");
                String afterMaSach = afterParts[0];
                int afterSoLuong = Integer.parseInt(afterParts[1]);

                for (String before : befoArrayList) {
                    String[] beforeParts = before.split("/");
                    String beforeMaSach = beforeParts[0];
                    int beforeSoLuong = Integer.parseInt(beforeParts[1]);

                    if (afterMaSach.equals(beforeMaSach)) {
                        int diffSoLuong = afterSoLuong - beforeSoLuong;
                        diffArrayList.add(afterMaSach + "/" + diffSoLuong);
                        break;
                    }
                }
            }

            System.out.println(diffArrayList);

            // Update the database
            for (String diff : diffArrayList) {
                String[] parts = diff.split("/");
                String maSach = parts[0];
                int soLuong = Integer.parseInt(parts[1]);
                phieuNhapBUS.capNhatChiTietPhieuNhap(phieuNhapDTO.getMaPN(), maSach, soLuong);
            }

            


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
                    chiTiet.getSOLUONG()
            };
            tableModel.addRow(rowData);
        }
        // Add data to beforeArrayList
        for (ChiTietPhieuNhapDTO chiTiet : chiTietList) {
            befoArrayList.add(chiTiet.getMASACH() + "/" + chiTiet.getSOLUONG());
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sửa Phiếu Nhập");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.add(new SuaPhieuNhap());
            frame.setVisible(true);
        });
    }
}
