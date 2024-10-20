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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class SanPhamGUI extends JPanel implements MouseListener {

    private SanPhamBUS spBUS;
    private JPanel pnHeader, pnContent;
    private JPanel pnChiTietSP, pnThaoTac;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnExport;
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
        pnHeader.setLayout(new BorderLayout());

        spMacDinh = new SanPhamDTO("SP00", "Tên sách", "NXB", 0, 0, 0, 0, 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        pnChiTietSP = initChiTietSP(spMacDinh);

        pnThaoTac = new JPanel(); //Thao tác thêm, sửa, xoá, import, export excel
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));

        JPanel pnLeft = new JPanel(); //thêm sửa xoá
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

        btnThem = new JButton("+ Thêm sách");
        btnThem.setPreferredSize(new Dimension(130, 30));
        btnThem.setMaximumSize(new Dimension(130, 30));
        btnThem.setBackground(BASE.btnThem);
        btnThem.setFont(BASE.font);
        btnThem.setOpaque(true);
        btnThem.setFocusPainted(false);
        btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThem.addMouseListener(this);

        btnSua = new JButton("+ Sửa sách");
        btnSua.setPreferredSize(new Dimension(130, 30));
        btnSua.setMaximumSize(new Dimension(130, 30));
        btnSua.setBackground(BASE.btnSua);
        btnSua.setFont(BASE.font);
        btnSua.setOpaque(true);
        btnSua.setFocusPainted(false);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.addMouseListener(this);

        btnXoa = new JButton("+ Xoá sách");
        btnXoa.setPreferredSize(new Dimension(130, 30));
        btnXoa.setMaximumSize(new Dimension(130, 30));
        btnXoa.setBackground(BASE.btnXoa);
        btnXoa.setFont(BASE.font);
        btnXoa.setOpaque(true);
        btnXoa.setFocusPainted(false);
        btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoa.addMouseListener(this);
        pnLeft.add(btnThem);
        pnLeft.add(Box.createVerticalStrut(20));
        pnLeft.add(btnSua);
        pnLeft.add(Box.createVerticalStrut(20));
        pnLeft.add(btnXoa);

        JPanel pnRight = new JPanel();
        pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));

        ImageIcon exportIcon = new ImageIcon("./src/image/export_icon.jpg");
        Image exportImage = exportIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        exportIcon = new ImageIcon(exportImage);
        btnExport = new JButton("Export Excel", exportIcon);
        btnExport.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExport.setPreferredSize(new Dimension(120, 80));
        btnExport.setMaximumSize(new Dimension(120, 80));
        btnExport.setBackground(Color.decode("#FFE4B5"));
        btnExport.setFont(BASE.font);
        btnExport.setOpaque(true);
        btnExport.setFocusPainted(false);
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.addMouseListener(this);
        pnRight.add(Box.createVerticalStrut(20));
        pnRight.add(btnExport);

        pnThaoTac.add(pnLeft);
        pnThaoTac.add(Box.createHorizontalStrut(50));
        pnThaoTac.add(pnRight);

        pnHeader.add(pnChiTietSP, BorderLayout.WEST);
        pnHeader.add(pnThaoTac, BorderLayout.EAST);
        pnHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 250));

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
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                selectedSP = new SanPhamDTO();
                timKiemSanPham(dsSP);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                selectedSP = new SanPhamDTO();
                timKiemSanPham(dsSP);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JLabel lblLoai = new JLabel("Thể loại");
        lblLoai.setFont(BASE.font);
        cbxLoai = new JComboBox<>();
        cbxLoai.setFont(BASE.font);
        cbxLoai.setPreferredSize(new Dimension(100, 25));
        cbxLoai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        for (int i = 0; i < loai.size(); i++) {
            cbxLoai.addItem(loai.get(i));
        }
        cbxLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSP = new SanPhamDTO();
                String selected = (String) cbxLoai.getSelectedItem();

                ArrayList<SanPhamDTO> dsTimKiem = new ArrayList<>();
                // Thực hiện tìm kiếm theo giá trị đã chọn
                if (!selected.equals("Tất cả")) {
                    for (int i = 0; i < dsSP.size(); i++) {
                        for (LoaiDTO loai : dsSP.get(i).getLoai()) {
                            if (loai.getTenLoai().equals(selected)) {
                                dsTimKiem.add(dsSP.get(i));
                                break;
                            }
                        }

                    }
                    timKiemSanPham((dsTimKiem));
                } else {
                    timKiemSanPham((dsSP));
                }
            }
        });

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
        table.setDefaultEditor(Object.class, null); //không cho chỉnh sửa
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//        panel.setPreferredSize(new Dimension(650, 300));
//        panel.setMaximumSize(new Dimension(650, 500));
        panel.add(Box.createHorizontalStrut(20));

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

        String tenSach = sp.getTenSach();
        if (sp.getTenSach().length() >= 30) {
            int index = tenSach.lastIndexOf(" ", 30); // Tìm khoảng trắng gần nhất trước vị trí 30
            tenSach = tenSach.substring(0, index) + "<br>" + tenSach.substring(index + 1);
        }
        JLabel tenSP = new JLabel("<html>" + tenSach + "</html>");
        tenSP.setFont(BASE.font_title);

        JLabel giaBan = new JLabel("Giá: " + FormatInt.format(sp.getGiaBan()) + "đ"); //Giá
        giaBan.setFont(BASE.font);

        String tenTacGia = sp.getTacGiaToString(); //Tác giả
        if (sp.getTacGiaToString().length() >= 30) {
            int index = tenTacGia.lastIndexOf(" ", 30);
            tenTacGia = tenTacGia.substring(0, index) + "<br>" + tenTacGia.substring(index + 1);
        }
        JLabel tacGia = new JLabel("<html>Tác giả: " + tenTacGia + "</html>");
        tacGia.setPreferredSize(new Dimension(100,57));
        tacGia.setFont(BASE.font);
        
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(tenSP);
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(giaBan);
        pnThongTin.add(Box.createVerticalStrut(20));
        pnThongTin.add(tacGia);

        pnThongTin.add(Box.createVerticalGlue());
        panel.add(pnAnh);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(pnThongTin);

        return panel;
    }

    private void timKiemSanPham(ArrayList<SanPhamDTO> dsSP) {
        String keyword = txtTimKiem.getText().toLowerCase(); // Lấy từ khóa tìm kiếm và chuyển về chữ thường
        DefaultTableModel df = (DefaultTableModel) tbSanPham.getModel(); // Lấy mô hình của JTable
        df.setRowCount(0); // Xóa toàn bộ dữ liệu hiện có trong bảng

        // Lọc dữ liệu dựa trên từ khóa tìm kiếm theo tên sách, nhà xuất bản
        for (SanPhamDTO sp : dsSP) {
            String giaBia = FormatInt.format(sp.getGiaBia());
            if (sp.getTenSach().toLowerCase().contains(keyword) || sp.getNxb().toLowerCase().contains(keyword)) {
                df.addRow(new Object[]{sp.getTenSach(), sp.getLoaiToString(), sp.getNxb(), sp.getNamXB(), sp.getSoLuong(), giaBia});
            }
        }
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
        if (spBUS.add(sp)) {
            new ShowDiaLog("Thêm sản phẩm thành công!", ShowDiaLog.SUCCESS_DIALOG);
            pnContent.remove(jpSanPham);
            tbSanPham = initContent(dsSP);
            jpSanPham = new JScrollPane(tbSanPham);
            pnContent.add(jpSanPham, BorderLayout.CENTER);
            pnContent.revalidate();
            pnContent.repaint();
        } else {
            new ShowDiaLog("Thêm sản phẩm thất bại!", ShowDiaLog.ERROR_DIALOG);
        }
    }

    public void EditSP(SanPhamDTO sp) {
        if (spBUS.set(sp)) {
            new ShowDiaLog("Sửa sản phẩm thành công!", ShowDiaLog.SUCCESS_DIALOG);
            pnContent.remove(jpSanPham);
            tbSanPham = initContent(dsSP);
            jpSanPham = new JScrollPane(tbSanPham);
            pnContent.add(jpSanPham, BorderLayout.CENTER);
            pnContent.revalidate();
            pnContent.repaint();
        } else {
            new ShowDiaLog("Sửa sản phẩm thất bại!", ShowDiaLog.ERROR_DIALOG);
        }
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
        if (btn == btnLamMoi) {
            reset();
        }
        if (btn == btnThem) {
            tbSanPham.clearSelection();
            selectedSP = new SanPhamDTO();

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
                        new ShowDiaLog("Đã xoá sản phẩm thành công!", ShowDiaLog.SUCCESS_DIALOG);
                        dsSP.remove(selectedSP);
                        tbSanPham.clearSelection();
                        selectedSP = new SanPhamDTO();
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
                        new ShowDiaLog("Xoá sản phẩm thất bại!", ShowDiaLog.ERROR_DIALOG);
                    }
                }

            }
        }
        if (btn == btnExport) {
            xuLyFileExcelSanPham exExcel = new xuLyFileExcelSanPham();
            exExcel.xuatExcel(dsSP);
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
