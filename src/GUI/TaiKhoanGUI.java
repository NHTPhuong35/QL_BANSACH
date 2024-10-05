/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.TaiKhoanBUS;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TaiKhoanGUI extends JPanel implements MouseListener {

    private JPanel pnHeader;
    private JPanel pnTimKiem;
    private JTable tbTaiKhoan;
    private JScrollPane jpTaiKhoan;
    private DefaultTableModel df;
    private JButton btnThem, btnSua, btnXoa;
    private JTextField txtTimKiem;
    private JComboBox<String> cbxQuyen;
    private int width = 1000, height = 500;
    private ArrayList<QuyenDTO> dsQuyen;
    private ArrayList<TaiKhoanDTO> dsTK;
    TaiKhoanDTO selectedTK = new TaiKhoanDTO();
    private boolean isPasswordVisible = false; //Xem mật khẩu (false: ẩn)
    private TaiKhoanBUS tkBUS;

    public TaiKhoanGUI() {
        dsQuyen = new ArrayList<>();
        dsQuyen.add(new QuyenDTO("Admin", "Admin"));
        dsQuyen.add(new QuyenDTO("QL", "Quản lý"));
        dsQuyen.add(new QuyenDTO("QNV", "Nhân Viên"));

        tkBUS = new TaiKhoanBUS();
        dsTK = new ArrayList<>();
        dsTK = tkBUS.getDsTK();

        init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        pnHeader = new JPanel();
        pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.X_AXIS));

        //thanh thao tác thêm, sửa, xoá
        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));
        btnThem = new JButton("+ Thêm tài khoản");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setMaximumSize(new Dimension(150, 30));
        btnThem.setBackground(BASE.btnThem);
        btnThem.setFont(BASE.font);
        btnThem.setOpaque(true);
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.addMouseListener(this);

        btnSua = new JButton("+ Sửa tài khoản");
        btnSua.setPreferredSize(new Dimension(150, 30));
        btnSua.setMaximumSize(new Dimension(150, 30));
        btnSua.setBackground(BASE.btnSua);
        btnSua.setFont(BASE.font);
        btnSua.setOpaque(true);
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.addMouseListener(this);

        btnXoa = new JButton("+ Xoá tài khoản");
        btnXoa.setPreferredSize(new Dimension(150, 30));
        btnXoa.setMaximumSize(new Dimension(150, 30));
        btnXoa.setBackground(BASE.btnXoa);
        btnXoa.setFont(BASE.font);
        btnXoa.setOpaque(true);
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoa.addMouseListener(this);
        // Thêm các button và các khoảng trống có thể giãn nở
        pnThaoTac.add(btnThem);
        pnThaoTac.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa các nút
        pnThaoTac.add(btnSua);
        pnThaoTac.add(Box.createHorizontalStrut(20)); // Khoảng cách giữa các nút
        pnThaoTac.add(btnXoa);
        pnThaoTac.add(Box.createHorizontalGlue());

        //thanh Tìm kiếm
        pnTimKiem = new JPanel();
        pnTimKiem.setPreferredSize(new Dimension(460, 50));
        pnTimKiem.setMaximumSize(new Dimension(460, 50));
        pnTimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JLabel lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        lblTimKiem.setFont(BASE.font);
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(BASE.font);
        txtTimKiem.setPreferredSize(new Dimension(150, 25));
//        txtTimKiem.addActionListener(e -> timKiemTaiKhoan(dsTK));
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timKiemTaiKhoan(dsTK);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timKiemTaiKhoan(dsTK);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        JLabel lblQuyen = new JLabel("Quyền");
        lblQuyen.setFont(BASE.font);
        cbxQuyen = new JComboBox<>();
        cbxQuyen.setFont(BASE.font);
        cbxQuyen.setPreferredSize(new Dimension(150, 25));
        cbxQuyen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbxQuyen.addItem("Tất cả");
        for (int i = 0; i < dsQuyen.size(); i++) {
            cbxQuyen.addItem(dsQuyen.get(i).getTenQuyen());
        }

        cbxQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbxQuyen.getSelectedItem();

                ArrayList<TaiKhoanDTO> dsTimKiem = new ArrayList<>();
                // Thực hiện tìm kiếm theo giá trị đã chọn
                if (!selected.equals("Tất cả")) {
                    for (int i = 0; i < dsTK.size(); i++) {
                        if (dsTK.get(i).getQuyen().getTenQuyen().equals(selected)) {
                            dsTimKiem.add(dsTK.get(i));
                        }
                    }
                    timKiemTaiKhoan((dsTimKiem));
                } else {
                    timKiemTaiKhoan((dsTK));
                }
            }
        });

        pnTimKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(lblQuyen);
        pnTimKiem.add(cbxQuyen);

        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnHeader.add(pnThaoTac);
        pnHeader.add(Box.createVerticalStrut(30));
        pnHeader.add(pnTimKiem);

        //Bảng danh sách tài khoản
        tbTaiKhoan = initContent(dsTK);
        jpTaiKhoan = new JScrollPane(tbTaiKhoan);
//        jpTaiKhoan.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(jpTaiKhoan, BorderLayout.CENTER);
    }

    private JTable initContent(ArrayList<TaiKhoanDTO> dsTK) {
        String[] header = {"Tên đăng nhập", "Tên nhân viên", "Tên quyền", "Mật khẩu", "Trạng thái"};
        JTable table = new JTable();
        table.setFont(BASE.font);
        table.setDefaultEditor(Object.class, null); //không cho chỉnh sửa
        table.setRowHeight(40); //thiết lập chiều cao các cột

        // Thiết lập dữ liệu cho JTable
        df = new DefaultTableModel(header, 0);  // Không khởi tạo lại dsSP tại đây!
        for (TaiKhoanDTO row : dsTK) {
            String trangThai = "Đang làm việc";
            if (row.getTrangThai() == 0) {
                trangThai = "Nghỉ việc";
            }
            Object[] data = {row.getTenDN(), row.getTenNV(), row.getQuyen().getTenQuyen(), "********", trangThai};
            df.addRow(data);
        }
        table.setModel(df);

        // Canh giữa nội dung trong mỗi ô trong cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < header.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Bỏ kẻ dọc
        table.setShowVerticalLines(false);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(BASE.color_table_heaer);  // Đặt màu nền cho tiêu đề là màu xám nhạt
        tableHeader.setFont(BASE.font_header);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // Thêm sự kiện chuột để lấy hàng được chọn
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.getSelectedColumn(); // Lấy chỉ số cột được chọn
                int row = table.getSelectedRow(); // Lấy chỉ số hàng được chọn
                if (e.getClickCount() == 2) {
                    if (row >= 0) {
                        selectedTK = dsTK.get(row);
                        XemThongTinTaiKhoanGUI t = new XemThongTinTaiKhoanGUI(selectedTK); //Gọi xem thông tin tài khoản
                    }
                }

                if (row >= 0) {
                    selectedTK = dsTK.get(row);
                }

                if (row >= 0 && col == 3) { // Kiểm tra nếu ô được chọn là cột "Mật khẩu"
                    // Lấy dữ liệu tài khoản tương ứng với hàng
                    TaiKhoanDTO selectedAccount = dsTK.get(row);

                    // Nếu mật khẩu đang ẩn, hiển thị mật khẩu gốc
                    if (!isPasswordVisible) {
                        df.setValueAt(selectedAccount.getMatKhau(), row, col);
                        isPasswordVisible = true;
                    } else {
                        // Ẩn lại mật khẩu
                        df.setValueAt("********", row, col);
                        isPasswordVisible = false;
                    }
                }
            }
        });
        return table;
    }

    private void timKiemTaiKhoan(ArrayList<TaiKhoanDTO> dsTK) {
        String keyword = txtTimKiem.getText().toLowerCase(); // Lấy từ khóa tìm kiếm và chuyển về chữ thường
        DefaultTableModel df = (DefaultTableModel) tbTaiKhoan.getModel(); // Lấy mô hình của JTable
        df.setRowCount(0); // Xóa toàn bộ dữ liệu hiện có trong bảng

        // Lọc dữ liệu dựa trên từ khóa tìm kiếm
        for (TaiKhoanDTO tk : dsTK) {
            String trangThai = "Đang làm việc";
            if (tk.getTrangThai() == 0) {
                trangThai = "Nghỉ việc";
            }
            if (tk.getTenDN().toLowerCase().contains(keyword) || tk.getTenNV().toLowerCase().contains(keyword)) {
                df.addRow(new Object[]{tk.getTenDN(), tk.getTenNV(), tk.getQuyen().getTenQuyen(), tk.getMatKhau(), trangThai});
            }
        }
    }

    public void themTaiKhoan(TaiKhoanDTO tk) {
        if (tkBUS.add(tk)) {
            new ShowDiaLog("Thêm tài khoản thành công!", ShowDiaLog.SUCCESS_DIALOG);
            this.remove(jpTaiKhoan);
            tbTaiKhoan = initContent(dsTK);
            jpTaiKhoan = new JScrollPane(tbTaiKhoan);
            this.add(jpTaiKhoan, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        } else {
            new ShowDiaLog("Thêm tài khoản thất bại!", ShowDiaLog.ERROR_DIALOG);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        TaiKhoanGUI t = new TaiKhoanGUI();
        f.add(t);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == btnThem) {
            ChucNangTaiKhoanGUI t = new ChucNangTaiKhoanGUI(this);
            t.initAdd();
            t.setVisible(true);
            selectedTK = new TaiKhoanDTO();
        }
        if (btn == btnXoa) {
            if (selectedTK == null || selectedTK.getTenDN() == null) {
                new ShowDiaLog("Hãy chọn tài khoản cần xoá!", ShowDiaLog.INFO_DIALOG);
            } else {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá tài khoản này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    if (tkBUS.delete(selectedTK.getTenDN(), false)) {
                        JOptionPane.showMessageDialog(null,
                                "Đã xoá thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);
                        dsTK.remove(selectedTK);
                        this.remove(jpTaiKhoan);
                        tbTaiKhoan = initContent(dsTK);
                        jpTaiKhoan = new JScrollPane(tbTaiKhoan);
                        this.add(jpTaiKhoan, BorderLayout.CENTER);
                        selectedTK = new TaiKhoanDTO();
                        this.revalidate();
                        this.repaint();

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
