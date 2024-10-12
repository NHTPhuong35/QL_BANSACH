package GUI;

import BUS.PhieuNhapBUS; // Import the PhieuNhapBUS class
import DTO.PhieuNhapDTO; // Import the PhieuNhapDTO class

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;

public class PhieuNhapGUI extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public PhieuNhapGUI() {
        // Tạo các nút chức năng với màu sắc như trong hình
        JButton addButton = new JButton("+ Thêm phiếu nhập");
        addButton.setBackground(BASE.btnThem); // Màu xanh lá

        JButton editButton = new JButton("+ Sửa phiếu nhập");
        editButton.setBackground(BASE.btnSua); // Màu tím

        JButton deleteButton = new JButton("+ Xóa phiếu nhập");
        deleteButton.setBackground(BASE.btnXoa); // Màu đỏ

        // Bố trí các nút theo dạng FlowLayout (căn ngang)
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Căn trái và có khoảng cách
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);

        // Add action listener to the addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the TaoPhieuNhap panel
                JFrame taoPhieuNhapFrame = new JFrame("Tạo Phiếu Nhập");
                taoPhieuNhapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                taoPhieuNhapFrame.setSize(800, 600);
                taoPhieuNhapFrame.add(new TaoPhieuNhap()); // Assuming TaoPhieuNhap is a JPanel
                taoPhieuNhapFrame.setVisible(true);
            }
        });

        // Add action listener to the editButton
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Get data from the selected row
                String maPN = (String) table.getValueAt(selectedRow, 0);
                String tenNV = (String) table.getValueAt(selectedRow, 1);
                String nhaCC = (String) table.getValueAt(selectedRow, 2);
                Date ngayLap = (Date) table.getValueAt(selectedRow, 3);
                Double tongTien = (Double) table.getValueAt(selectedRow, 4);

                // Show the SuaPhieuNhap panel with data
                JFrame suaPhieuNhapFrame = new JFrame("Sửa Phiếu Nhập");
                suaPhieuNhapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                suaPhieuNhapFrame.setSize(800, 600);
                    SuaPhieuNhap suaPhieuNhapPanel = new SuaPhieuNhap();
                    suaPhieuNhapPanel.setMaPhieuNhap(maPN);
                    suaPhieuNhapPanel.setMaNhanVien(tenNV);
                    suaPhieuNhapPanel.setNhaCungCap(nhaCC);
                    suaPhieuNhapPanel.setNgay(ngayLap);
                    suaPhieuNhapPanel.setTongTien(tongTien);

                
                suaPhieuNhapFrame.add(suaPhieuNhapPanel);
                suaPhieuNhapFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để sửa.");
            }
            }
        });

        // Add action listener to the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String maPN = (String) table.getValueAt(selectedRow, 0);
                        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
                        phieuNhapBUS.XoaPhieuNhap(maPN);
                        model.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để xóa.");
                }
            }
        });
        String[] columns = { "Mã PN", "Tên nhân viên", "Nhà cung cấp", "Ngày lập", "Tổng tiền", "Chi tiết" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            // Custom render cho cột nút "XEM"
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Chỉ cho phép cột cuối cùng là có thể nhấn nút
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (column == 5) {
                    JButton button = new JButton("XEM");
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null, "Xem chi tiết cho dòng: " + (row + 1));
                        }
                    });
                    return button;
                }
                return comp;
            }
        };

        // Load data into the table
        loadData();

        // Select the first row
        if (model.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }

        // Tạo thanh tìm kiếm
        JTextField searchField = new JTextField(15);
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Căn phải
        searchPanel.add(new JLabel("Tìm kiếm"));
        searchPanel.add(searchField);

        // Tạo bảng cuộn cho bảng dữ liệu
        JScrollPane scrollPane = new JScrollPane(table);

        table.getTableHeader().setBackground(BASE.color_table_header); // Màu xanh tiêu đề bảng
        table.getTableHeader().setBackground(BASE.color_table_heaer); // Màu xanh tiêu đề bảng
        table.setBackground(Color.WHITE);

        // Bố cục tổng thể
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(toolBar, BorderLayout.WEST); // Đưa các nút về phía trái
        topPanel.add(searchPanel, BorderLayout.EAST); // Thanh tìm kiếm ở phía phải

        // Đặt layout cho JPanel
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH); // Thanh công cụ và tìm kiếm ở trên cùng
        add(scrollPane, BorderLayout.CENTER); // Bảng dữ liệu ở giữa với dữ liệu mẫu
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phiếu Nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 400);
        frame.add(new PhieuNhapGUI());
        frame.setVisible(true);
    }

    public void loadData() {
        model.setRowCount(0);
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        List<PhieuNhapDTO> phieuNhapList = phieuNhapBUS.getAllPhieuNhap();
        for (PhieuNhapDTO phieuNhap : phieuNhapList) {
            model.addRow(new Object[] {
                    phieuNhap.getMaPN(),
                    phieuNhap.getTenDN(),
                    phieuNhap.getMaNCC(),
                    phieuNhap.getNgayNhap(),
                    phieuNhap.getTongTien(),
                    "XEM"
            });
        }
    }
}
