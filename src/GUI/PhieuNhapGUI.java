package GUI;

import BUS.PhieuNhapBUS; // Import the PhieuNhapBUS class
import DTO.PhieuNhapDTO; // Import the PhieuNhapDTO class
// import java.util.ArrayList; // Import ArrayList for demonstration purposes
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PhieuNhapGUI extends JPanel {

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

        // Tạo bảng hiển thị dữ liệu với các cột
        String[] columns = { "Mã PN", "Tên nhân viên", "Nhà cung cấp", "Ngày lập", "Tổng tiền", "Chi tiết" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model) {
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

        // Fetch data from PhieuNhapBUS and populate the table
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
}
