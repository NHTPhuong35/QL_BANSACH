package GUI;

import DTO.SanPhamDTO;
import DTO.TacGiaDTO;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ChonTacGiaGUI extends JFrame {

    private JPanel pnHeader;
    private JTable tbTacGia;
    private JButton btnThem, btnTroVe;
    private JTextField txtTimKiem;
    ArrayList<TacGiaDTO> dsTG;
    ArrayList<TacGiaDTO> selectedListTG = new ArrayList<>();

    public ChonTacGiaGUI() {
        dsTG = new ArrayList<>();
        dsTG.add(new TacGiaDTO("TG1", "Tác giả 1"));
        dsTG.add(new TacGiaDTO("TG2", "Tác giả 2"));
        dsTG.add(new TacGiaDTO("TG3", "Tác giả 3"));
        dsTG.add(new TacGiaDTO("TG4", "Tác giả 4"));
        dsTG.add(new TacGiaDTO("TG5", "Tác giả 5"));
        init();
    }

    public void init() {
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnHeader = new JPanel();
        pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.X_AXIS));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnThem = new JButton("+ Thêm tác giả");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setMaximumSize(new Dimension(150, 30));
        btnThem.setBackground(BASE.btnThem);
        btnThem.setFont(BASE.font);
        btnThem.setOpaque(true);
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnTroVe = new JButton("+ Trở về");
        btnTroVe.setPreferredSize(new Dimension(150, 30));
        btnTroVe.setMaximumSize(new Dimension(150, 30));
        btnTroVe.setBackground(BASE.color_heaer);
        btnTroVe.setFont(BASE.font);
        btnTroVe.setOpaque(true);
        btnTroVe.setBorderPainted(false);
        btnTroVe.setFocusPainted(false);
        btnTroVe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTroVe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChonTacGiaGUI.this.dispose();
            }
        });

        JLabel lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        lblTimKiem.setFont(BASE.font);
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(100, 20));

        pnHeader.add(btnThem);
        pnHeader.add(Box.createRigidArea(new Dimension(10, 0)));
        pnHeader.add(btnTroVe);
        pnHeader.add(Box.createRigidArea(new Dimension(100, 0)));
        pnHeader.add(lblTimKiem);
        pnHeader.add(Box.createRigidArea(new Dimension(10, 0)));
        pnHeader.add(txtTimKiem);

        // Table
        String[] headerTable = {"", "Mã tác giả", "Tên tác giả"};

        // Thiết lập dữ liệu cho JTable với DefaultTableModel tùy chỉnh
        DefaultTableModel df = new DefaultTableModel(headerTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột checkbox (cột đầu tiên)
                return column == 0;
            }
        };

        tbTacGia = new JTable();
        tbTacGia.setRowHeight(30);

        for (TacGiaDTO tg : dsTG) {
            df.addRow(new Object[]{false, tg.getMaTG(), tg.getTenTG()});
        }
        tbTacGia.setModel(df);

        // Đặt cell editor và renderer cho checkbox
        tbTacGia.getColumnModel().getColumn(0).setCellRenderer(tbTacGia.getDefaultRenderer(Boolean.class));
        tbTacGia.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Canh giữa nội dung trong các cột còn lại
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tbTacGia.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTacGia.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Đặt kích thước cho các cột
        TableColumnModel columnModel = tbTacGia.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // "Checkbox"
        columnModel.getColumn(1).setPreferredWidth(100);  // "Mã tác giả"
        columnModel.getColumn(2).setPreferredWidth(200);  // "Tên tác giả"

        // Bỏ kẻ dọc
        tbTacGia.setShowVerticalLines(false);
        JTableHeader tableHeader = tbTacGia.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(BASE.color_table_heaer);  // Đặt màu nền cho tiêu đề là màu xám nhạt
        tableHeader.setFont(BASE.font_header);
        tbTacGia.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jb = new JScrollPane(tbTacGia);

        // Thêm sự kiện cho nút "Lấy tác giả được chọn"
        btnThem.addActionListener(e -> layDanhSachTacGia(df));

        // Thêm sự kiện cho JTextField txtTimKiem để xử lý tìm kiếm
        txtTimKiem.addActionListener(e -> timKiemTacGia());

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(jb, BorderLayout.CENTER);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // Hàm để lấy danh sách tác giả được chọn
    private void layDanhSachTacGia(DefaultTableModel df) {
        selectedListTG.clear(); // Xóa danh sách trước khi lấy dữ liệu mới
        for (int i = 0; i < df.getRowCount(); i++) {
            Boolean isSelected = (Boolean) df.getValueAt(i, 0); // Kiểm tra checkbox
            if (isSelected) {
                String maTacGia = (String) df.getValueAt(i, 1); // Lấy mã tác giả
                String tenTacGia = (String) df.getValueAt(i, 2); // Lấy tên tác giả
                TacGiaDTO tacGia = new TacGiaDTO(maTacGia, tenTacGia); // Tạo đối tượng TacGia
                selectedListTG.add(tacGia); // Thêm vào danh sách
            }
        }
        this.dispose();
    }

    // Hàm tìm kiếm tác giả theo tên hoặc mã tác giả
    private void timKiemTacGia() {
        String keyword = txtTimKiem.getText().toLowerCase(); // Lấy từ khóa tìm kiếm và chuyển về chữ thường
        DefaultTableModel df = (DefaultTableModel) tbTacGia.getModel(); // Lấy mô hình của JTable
        df.setRowCount(0); // Xóa toàn bộ dữ liệu hiện có trong bảng

        // Lọc dữ liệu dựa trên từ khóa tìm kiếm
        for (TacGiaDTO tg : dsTG) {
            if (tg.getMaTG().toLowerCase().contains(keyword) || tg.getTenTG().toLowerCase().contains(keyword)) {
                df.addRow(new Object[]{false, tg.getMaTG(), tg.getTenTG()});
            }
        }
    }

    public static void main(String[] args) {
        new ChonTacGiaGUI();
    }
}
