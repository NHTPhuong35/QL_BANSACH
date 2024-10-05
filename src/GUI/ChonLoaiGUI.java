package GUI;

import DTO.LoaiDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ChonLoaiGUI extends JFrame {

    private JPanel pnHeader;
    private JTable tbLoai;
    private JButton btnThem, btnTroVe;
    private JLabel exit;
    private JTextField txtTimKiem;
    ArrayList<LoaiDTO> dsLoai;
    ChucNangSanPhamGUI cnSPGUI;

    public ChonLoaiGUI(ChucNangSanPhamGUI cnSPGUI) {
        this.cnSPGUI = cnSPGUI;
        dsLoai = new ArrayList<>();
        dsLoai.add(new LoaiDTO("L01", "Kỹ năng sống"));
        dsLoai.add(new LoaiDTO("L02", "Tâm lý học"));
        dsLoai.add(new LoaiDTO("L03", "Quản trị - lãnh đạo"));
        init();
    }

    public void init() {
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        pnHeader = new JPanel();
        pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.Y_AXIS));

        //Tiêu đề
        JPanel pnTieuDe = new JPanel();
        pnTieuDe.setLayout(new BorderLayout());
        pnTieuDe.setPreferredSize(new Dimension(800, 36));
        pnTieuDe.setBackground(BASE.color_heaer);
        pnTieuDe.setOpaque(true);

        JLabel lblHeader = new JLabel("Chọn loại sản phẩm", JLabel.CENTER);
        lblHeader.setFont(BASE.font_title);
        lblHeader.setBorder(new EmptyBorder(0, 20, 0, 0));

        ImageIcon icon = new ImageIcon("./src/image/exit_icon.png");
        exit = new JLabel(icon, JLabel.CENTER);
        exit.setPreferredSize(new Dimension(36, 36));
        exit.setBackground(Color.RED);
        exit.setOpaque(true);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                ChonLoaiGUI.this.dispose();
                cnSPGUI.setVisible(true);
            }
            
        });
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTieuDe.add(lblHeader, BorderLayout.WEST);
        pnTieuDe.add(exit, BorderLayout.EAST);
        
        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));
        pnThaoTac.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnThem = new JButton("+ Lưu");
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
        btnTroVe.setBackground(Color.decode("#99CCFF"));
        btnTroVe.setFont(BASE.font);
        btnTroVe.setOpaque(true);
        btnTroVe.setBorderPainted(false);
        btnTroVe.setFocusPainted(false);
        btnTroVe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTroVe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChonLoaiGUI.this.dispose();
                cnSPGUI.setVisible(true);
            }
        });

        JLabel lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        lblTimKiem.setFont(BASE.font);
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(100, 20));

        pnThaoTac.add(btnThem);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(btnTroVe);
        pnThaoTac.add(Box.createRigidArea(new Dimension(100, 0)));
        pnThaoTac.add(lblTimKiem);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(txtTimKiem);

        pnHeader.add(pnTieuDe);
        pnHeader.add(Box.createVerticalStrut(10)); 
        pnHeader.add(pnThaoTac);
        
        // Table
        String[] headerTable = {"", "Mã loại", "Tên loại"};

        // Thiết lập dữ liệu cho JTable với DefaultTableModel tùy chỉnh
        DefaultTableModel df = new DefaultTableModel(headerTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột checkbox (cột đầu tiên)
                return column == 0;
            }
        };

        tbLoai = new JTable();
        tbLoai.setRowHeight(30);

        for (LoaiDTO loai : dsLoai) {
            boolean isSelected = isLoaiSelected(loai); // Kiểm tra loại đã được chọn trước đó
            df.addRow(new Object[]{isSelected, loai.getMaLoai(), loai.getTenLoai()});
        }
        tbLoai.setModel(df);

        // Đặt cell editor và renderer cho checkbox
        tbLoai.getColumnModel().getColumn(0).setCellRenderer(tbLoai.getDefaultRenderer(Boolean.class));
        tbLoai.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Canh giữa nội dung trong các cột còn lại
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tbLoai.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbLoai.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Đặt kích thước cho các cột
        TableColumnModel columnModel = tbLoai.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // "Checkbox"
        columnModel.getColumn(1).setPreferredWidth(100);  // "Mã tác giả"
        columnModel.getColumn(2).setPreferredWidth(200);  // "Tên tác giả"

        // Bỏ kẻ dọc
        tbLoai.setShowVerticalLines(false);
        JTableHeader tableHeader = tbLoai.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(BASE.color_table_heaer);  // Đặt màu nền cho tiêu đề là màu xám nhạt
        tableHeader.setFont(BASE.font_header);
        tbLoai.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jb = new JScrollPane(tbLoai);

        // Thêm sự kiện cho nút "Lấy tác giả được chọn"
        btnThem.addActionListener(e -> layDanhSachLoai(df));

        // Thêm sự kiện cho JTextField txtTimKiem để xử lý tìm kiếm
        txtTimKiem.addActionListener(e -> timKiemLoai());

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(jb, BorderLayout.CENTER);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // Hàm để lấy danh sách tác giả được chọn
    private void layDanhSachLoai(DefaultTableModel df) {
        cnSPGUI.dsLoai.clear(); // Xóa danh sách trước khi lấy dữ liệu mới
        for (int i = 0; i < df.getRowCount(); i++) {
            Boolean isSelected = (Boolean) df.getValueAt(i, 0); // Kiểm tra checkbox
            if (isSelected) {
                String maLoai = (String) df.getValueAt(i, 1); // Lấy mã tác giả
                String tenLoai = (String) df.getValueAt(i, 2); // Lấy tên tác giả
                LoaiDTO Loai = new LoaiDTO(maLoai, tenLoai); // Tạo đối tượng Loai
                cnSPGUI.dsLoai.add(Loai); // Thêm vào danh sách
            }
        }
        this.dispose();
        cnSPGUI.setVisible(true);
    }

    private boolean isLoaiSelected(LoaiDTO loai) {
        for (LoaiDTO selectedLoai : cnSPGUI.dsLoai) {
            if (selectedLoai.getMaLoai().equals(loai.getMaLoai())) {
                return true;
            }
        }
        return false;
    }

    // Hàm tìm kiếm tác giả theo tên hoặc mã tác giả
    private void timKiemLoai() {
        String keyword = txtTimKiem.getText().toLowerCase(); // Lấy từ khóa tìm kiếm và chuyển về chữ thường
        DefaultTableModel df = (DefaultTableModel) tbLoai.getModel(); // Lấy mô hình của JTable
        df.setRowCount(0); // Xóa toàn bộ dữ liệu hiện có trong bảng

        // Lọc dữ liệu dựa trên từ khóa tìm kiếm
        for (LoaiDTO loai : dsLoai) {
            if (loai.getMaLoai().toLowerCase().contains(keyword) || loai.getTenLoai().toLowerCase().contains(keyword)) {
                boolean isSelected = isLoaiSelected(loai);
                df.addRow(new Object[]{isSelected, loai.getMaLoai(), loai.getTenLoai()});
            }
        }
        // Thiết lập lại editor cho cột checkbox
        tbLoai.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

    }
}
