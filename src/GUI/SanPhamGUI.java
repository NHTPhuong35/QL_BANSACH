package GUI;

import BUS.SanPhamBUS;
import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import DTO.TacGiaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class SanPhamGUI extends JPanel implements MouseListener {

    private SanPhamBUS spBUS;
    private JPanel pnHeader, pnContent;
    private JPanel pnChiTietSP, pnThaoTac;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnLamMoi;
    private JPanel pnTimKiem;
    private JTextField txtTimKiem;
    private JComboBox<String> cbxLoai;
    private JTable tbSanPham;
    private JScrollPane jpSanPham;
    private DefaultTableModel df;
    private int width, height;
    private ArrayList<String> loai; //Danh sách loại
    private ArrayList<SanPhamDTO> dsSP; //Danh sách sản phẩm
    SanPhamDTO selectedSP = new SanPhamDTO();
    SanPhamDTO spMacDinh; //sản phẩm hiển thị mặc định khi chưa chọn sản phẩm

    // Định dạng sử dụng dấu phân cách hàng nghìn
    DecimalFormat FormatInt = new DecimalFormat("#,###");

    public SanPhamGUI() {
    }

    public SanPhamGUI(int width, int height) {
        this.width = width;
        this.height = height;

        spBUS = new SanPhamBUS();
        dsSP = spBUS.getDsSP();

        loai = new ArrayList<>();
        loai.add("Tất cả");
        loai.add("Kỹ năng sống");
        loai.add("Tâm lý học");
        loai.add("Quản trị - lãnh đạo");
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));

        pnHeader = new JPanel();
        pnHeader.setLayout(new GridLayout(1, 2));

        spMacDinh = new SanPhamDTO("SP00", "Tên sách", "NXB", 0, 0, 0, 0, 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        pnChiTietSP = initChiTietSP(spMacDinh);

        pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.Y_AXIS));

        btnThem = new JButton("+ Thêm sách");
        btnThem.setPreferredSize(new Dimension(130, 30));
        btnThem.setMaximumSize(new Dimension(130, 30));
        btnThem.setBackground(BASE.btnThem);
        btnThem.setFont(BASE.font);
        btnThem.setOpaque(true);
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.addMouseListener(this);

        btnSua = new JButton("+ Sửa sách");
        btnSua.setPreferredSize(new Dimension(130, 30));
        btnSua.setMaximumSize(new Dimension(130, 30));
        btnSua.setBackground(BASE.btnSua);
        btnSua.setFont(BASE.font);
        btnSua.setOpaque(true);
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.addMouseListener(this);

        btnXoa = new JButton("+ Xoá sách");
        btnXoa.setPreferredSize(new Dimension(130, 30));
        btnXoa.setMaximumSize(new Dimension(130, 30));
        btnXoa.setBackground(BASE.btnXoa);
        btnXoa.setFont(BASE.font);
        btnXoa.setOpaque(true);
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoa.addMouseListener(this);

        pnThaoTac.add(Box.createVerticalStrut(80));
        pnThaoTac.add(btnThem);
        pnThaoTac.add(Box.createVerticalStrut(20));
        pnThaoTac.add(btnSua);
        pnThaoTac.add(Box.createVerticalStrut(20));
        pnThaoTac.add(btnXoa);
        pnThaoTac.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        pnHeader.add(pnChiTietSP);
        pnHeader.add(pnThaoTac);

        //Content
        pnContent = new JPanel();
        pnContent.setLayout(new BorderLayout());

        //thanh Tìm kiếm
        pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JLabel lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        lblTimKiem.setFont(BASE.font);
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(BASE.font);
        txtTimKiem.setPreferredSize(new Dimension(150, 25));
        JLabel lblLoai = new JLabel("Thể loại");
        lblLoai.setFont(BASE.font);
        cbxLoai = new JComboBox<>();
        cbxLoai.setFont(BASE.font);
        cbxLoai.setPreferredSize(new Dimension(100, 25));
        cbxLoai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        for (int i = 0; i < loai.size(); i++) {
            cbxLoai.addItem(loai.get(i));
        }
        btnTim = new JButton("Tìm");
        btnTim.setBackground(BASE.color_heaer);
        btnTim.setFont(BASE.font);
        btnTim.setOpaque(true);
        btnTim.setBorderPainted(false);
        btnTim.setFocusPainted(false);
        btnTim.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTim.setPreferredSize(new Dimension(80, 25));
        btnTim.addMouseListener(this);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(BASE.color_heaer);
        btnLamMoi.setFont(BASE.font);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLamMoi.setPreferredSize(new Dimension(100, 25));
        btnLamMoi.addMouseListener(this);

        pnTimKiem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(lblLoai);
        pnTimKiem.add(cbxLoai);
        pnTimKiem.add(Box.createRigidArea(new Dimension(10, 0)));
        pnTimKiem.add(btnTim);
        pnTimKiem.add(btnLamMoi);

        //Bảng danh sách sản phẩm
        tbSanPham = initContent(dsSP);
        jpSanPham = new JScrollPane(tbSanPham);

        pnContent.add(pnTimKiem, BorderLayout.NORTH);
        pnContent.add(jpSanPham, BorderLayout.CENTER);

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnContent, BorderLayout.CENTER);
    }

    public JTable initContent(ArrayList<SanPhamDTO> dsSP) {
        String[] header = {"Tên sách", "Thể loại", "Nhà xuất bản", "Năm xuất bản", "Số lượng", "Giá bìa"};
        JTable table = new JTable();
        table.setFont(BASE.font);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(40); //thiết lập chiều cao các cột

        // Thiết lập dữ liệu cho JTable
        df = new DefaultTableModel(header, 0);  // Không khởi tạo lại dsSP tại đây!
        for (SanPhamDTO row : dsSP) {
            String giaBia = FormatInt.format(row.getGiaBia());
            Object[] data = {row.getTenSach(), row.getLoaiToString(), row.getNxb(), row.getNamXB(), row.getSoLuong(), giaBia};
            df.addRow(data);
        }
        table.setModel(df);

        // Canh giữa nội dung trong mỗi ô trong cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < header.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Đặt kích thước cho các cột
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);  // "Tên sách"
        columnModel.getColumn(1).setPreferredWidth(100);  // "Thể loại"
        columnModel.getColumn(2).setPreferredWidth(200);  // "Nhà xuất bản"
        columnModel.getColumn(3).setPreferredWidth(100);   // "Năm xuất bản"
        columnModel.getColumn(4).setPreferredWidth(60);   // "Số lượng"
        columnModel.getColumn(5).setPreferredWidth(100);  // "Giá bìa"

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
                int row = table.getSelectedRow(); // Lấy chỉ số hàng được chọn
                if (row >= 0) {
                    selectedSP = dsSP.get(row);

                    // Cập nhật panel chi tiết sản phẩm
                    pnChiTietSP.removeAll();
                    pnChiTietSP.add(initChiTietSP(selectedSP)); // Thêm nội dung mới
                    pnChiTietSP.revalidate();
                    pnChiTietSP.repaint();
                }
            }
        });
        return table;
    }

    public JPanel initChiTietSP(SanPhamDTO sp) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JPanel pnAnh = new JPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));
        pnAnh.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel pnAnhNho = new JPanel();
        pnAnhNho.setLayout(new FlowLayout());

        // Hình ảnh của sản phẩm
        if (sp.getTenHinh() == null || sp.getTenHinh().isEmpty()) {
            ImageIcon icon = new ImageIcon("./src/image/iconBook.jpg");
            Image scaledImage = icon.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);

            JLabel lblAnhLon = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
            lblAnhLon.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
            pnAnh.add(lblAnhLon);
        } else {
            ImageIcon icon = new ImageIcon("./src/image/" + sp.getTenHinh().get(0));
            Image scaledImage = icon.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
            JLabel lblAnhLon = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
            lblAnhLon.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
            pnAnh.add(lblAnhLon);
            if (sp.getTenHinh().size() > 1) {
                for (int i = 0; i < sp.getTenHinh().size(); i++) {
                    icon = new ImageIcon("./src/image/" + sp.getTenHinh().get(i));
                    scaledImage = icon.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
                    JLabel lblAnhNho = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
                    pnAnhNho.add(lblAnhNho);

                    // Thêm sự kiện cho ảnh nhỏ
                    final String imagePath = sp.getTenHinh().get(i);
                    lblAnhNho.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            ImageIcon newIcon = new ImageIcon("./src/image/" + imagePath);
                            Image newScaledImage = newIcon.getImage().getScaledInstance(190, 250, Image.SCALE_SMOOTH);
                            lblAnhLon.setIcon(new ImageIcon(newScaledImage));
                        }
                    });
                }
            }
        }

        pnAnh.add(Box.createVerticalStrut(10));
        pnAnh.add(pnAnhNho);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
        JLabel tenSP = new JLabel(sp.getTenSach());
        tenSP.setFont(BASE.font_title);
        JLabel giaBan = new JLabel("Giá: " + FormatInt.format(sp.getGiaBan()) + "đ");
        giaBan.setFont(BASE.font);
        JLabel tacGia = new JLabel("Tác giả: " + sp.getTacGiaToString());
        tacGia.setFont(BASE.font);
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(tenSP);
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(giaBan);
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(tacGia);

        panel.add(pnAnh);
        panel.add(pnThongTin);
        return panel;
    }

    public void SearchSP(String ten, String loai) {
        df.setRowCount(0);
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for (SanPhamDTO sp : dsSP) {
            boolean matches = true;

            // Kiểm tra thể loại
            if (!loai.equals("Tất cả")) {
                boolean found = false;
                for (LoaiDTO loaiDTO : sp.getLoai()) {
                    if (loaiDTO.getTenLoai().equals(loai)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    matches = false;
                }
            }

            // Kiểm tra tên sách
            if (!ten.isEmpty() && !sp.getTenSach().toLowerCase().contains(ten.toLowerCase())) {
                matches = false;
            }

            // Nếu cả hai điều kiện đều thỏa mãn, thêm sản phẩm vào danh sách
            if (matches) {
                ds.add(sp);
            }
        }
        pnContent.remove(jpSanPham); // Bỏ bảng cũ
        tbSanPham = initContent(ds); // Tạo bảng mới với dữ liệu tìm được
        jpSanPham = new JScrollPane(tbSanPham); // Tạo JScrollPane mới
        pnContent.add(jpSanPham, BorderLayout.CENTER); // Thêm bảng mới vào nội dung
        pnContent.revalidate(); // Cập nhật lại giao diện
        pnContent.repaint();
    }

    public void reset() {
        txtTimKiem.setText("");
        cbxLoai.setSelectedIndex(0);
        pnContent.remove(jpSanPham);
        tbSanPham = initContent(dsSP);
        jpSanPham = new JScrollPane(tbSanPham);
        pnContent.add(jpSanPham, BorderLayout.CENTER);
        pnContent.revalidate();
        pnContent.repaint();
    }

    public void AddSP(SanPhamDTO sp) {
        spBUS.add(sp);
        pnContent.remove(jpSanPham);
        tbSanPham = initContent(dsSP);
        jpSanPham = new JScrollPane(tbSanPham);
        pnContent.add(jpSanPham, BorderLayout.CENTER);
        pnContent.revalidate();
        pnContent.repaint();

    }

    public void EditSP(SanPhamDTO sp) {
        spBUS.set(sp);
        pnContent.remove(jpSanPham);
        tbSanPham = initContent(dsSP);
        jpSanPham = new JScrollPane(tbSanPham);
        pnContent.add(jpSanPham, BorderLayout.CENTER);
        pnContent.revalidate();
        pnContent.repaint();
    }

    public static void main(String[] args) {
//        String anh[] = {"10-van-cau-hoi-vi-sao.jpg", "damtreodaiduong.jpg", "co-mot-ngay-bo-me-se-gia-di.jpg"};
//        SanPhamDTO sp = new SanPhamDTO("abc", "Đắc nhân tâm", "TAMLIHOC", "Văn học Nhà Nội", 2024, 10, 1, 500, 500, anh, "a,b,c");
        SanPhamGUI t = new SanPhamGUI(800, 500);
//        t.init(sp);
        JFrame f = new JFrame();
        f.add(t);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == btnTim) {
            SearchSP(txtTimKiem.getText(), (String) cbxLoai.getSelectedItem());
        }
        if (btn == btnLamMoi) {
            reset();
        }
        if (btn == btnThem) {
            ChucNangSanPhamGUI t = new ChucNangSanPhamGUI(this);
            t.initAdd();
            t.setVisible(true);
        }
        if (btn == btnSua) {
            if (selectedSP == null || selectedSP.getMaSach() == null) {
                new ShowDiaLog("Hãy chọn sách cần sửa!", ShowDiaLog.ERROR_DIALOG);
            } else {
                ChucNangSanPhamGUI t = new ChucNangSanPhamGUI(this);
                t.initEdit();
            }
        }
        if (btn == btnXoa) {
            if (selectedSP == null || selectedSP.getMaSach() == null) {
                JOptionPane.showMessageDialog(null,
                        "Hãy chọn sách cần xoá!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá sách này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    if (spBUS.delete(selectedSP.getMaSach(), false)) {
                        JOptionPane.showMessageDialog(null,
                                "Đã xoá thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);
                        dsSP.remove(selectedSP);
                        pnContent.remove(jpSanPham);
                        tbSanPham = initContent(dsSP);
                        jpSanPham = new JScrollPane(tbSanPham);
                        pnContent.add(jpSanPham, BorderLayout.CENTER);

                        // Cập nhật panel chi tiết sản phẩm
                        selectedSP = new SanPhamDTO();
                        pnChiTietSP.removeAll();
                        pnChiTietSP.add(initChiTietSP(spMacDinh));
                        pnChiTietSP.revalidate();
                        pnChiTietSP.repaint();
                        pnContent.revalidate();
                        pnContent.repaint();
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
