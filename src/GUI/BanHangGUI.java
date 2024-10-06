/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class BanHangGUI extends JPanel {
    
    private String MaNV;
    private JPanel pnThongTinSP, pnThanhToan, pnDanhSachSp;
    private DefaultTableModel dtmSP;
    private JPanel btnThanhToan;
    private JTable tableSP;
    private JButton btnThemSp;
    private JTextField tfTongCong, tfMaNV, tfMaKH, tfGiamGia, tfTongHD;
    private JButton btnTaoKH;
    private ArrayList<ChiTietHoaDonDTO> ctHoaDon = new ArrayList<>();
    private ArrayList<ChiTietHoaDonDTO> ListBook = new ArrayList<>();

    public BanHangGUI() {
        this.MaNV = "NV01";
        init();
        initComponents();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);

        pnThongTinSP = new JPanel();
        pnThongTinSP.setLayout(new BorderLayout());

        pnThanhToan = new JPanel();
        pnThanhToan.setLayout(new BorderLayout());
        pnThanhToan.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Thông tin Hóa đơn",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                BASE.font_title,
                BASE.color_text
        ));

        this.add(pnThongTinSP, BorderLayout.CENTER);
        this.add(pnThanhToan, BorderLayout.EAST);

        this.setVisible(true);
    }

    private void initComponents() {
        JLabel lblTitleSP = new JLabel("Thông tin sản phẩm");
        lblTitleSP.setPreferredSize(new Dimension(300, 50));
        lblTitleSP.setFont(BASE.font_title);
        btnThemSp = createBtn("Chọn sản phẩm", "btnChon");

        JPanel pnlayoutnull = new JPanel();
        pnlayoutnull.add(btnThemSp);

        JPanel pnH = new JPanel(new BorderLayout(10, 10));
        pnH.add(lblTitleSP, BorderLayout.WEST);
        pnH.add(pnlayoutnull, BorderLayout.EAST);

        pnDanhSachSp = createPnDanhSachSp();

        pnThongTinSP.add(pnH, BorderLayout.NORTH);
        pnThongTinSP.add(pnDanhSachSp, BorderLayout.CENTER);

        JLabel lblTitleHD = new JLabel("Thông tin Hóa đơn");
        lblTitleHD.setPreferredSize(new Dimension(300, 50));
        lblTitleHD.setFont(BASE.font_title);

        JLabel lblMaKH, lblMaNV, lblGiamGia, lblTongCong, lblTongHD;

        // Tạo JPanel để chứa lblMaKH, tfMaKH và btnTaoKH
        JPanel pnMaKH = new JPanel(new FlowLayout(FlowLayout.LEFT));

        lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setFont(BASE.font);
        tfMaKH = new JTextField(15);
        tfMaKH.setFont(BASE.font);
        tfMaKH.setPreferredSize(new Dimension(tfMaKH.getPreferredSize().width, 30));

        btnTaoKH = new JButton("Tạo mới");
        btnTaoKH.setIcon(new ImageIcon(getClass().getResource("/Image/staff.png")));
        btnTaoKH.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm các thành phần vào pnMaKH
        pnMaKH.add(lblMaKH);
        pnMaKH.add(tfMaKH);
        pnMaKH.add(btnTaoKH);

        // Sử dụng GridBagLayout cho các thành phần khác
        JPanel inputPn = new JPanel();
        inputPn.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        // Thêm pnMaKH vào vị trí đầu tiên
        gbc.gridy = 0;
        inputPn.add(pnMaKH, gbc);

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(BASE.font);
        gbc.gridy = 1;
        inputPn.add(lblMaNV, gbc);

        tfMaNV = new JTextField();
        tfMaNV.setFont(BASE.font);
        tfMaNV.setEnabled(false);
        tfMaNV.setPreferredSize(new Dimension(tfMaNV.getPreferredSize().width, 30));
        tfMaNV.setText(MaNV);
        gbc.gridy = 2;
        inputPn.add(tfMaNV, gbc);

        lblTongCong = new JLabel("Tổng cộng");
        lblTongCong.setFont(BASE.font);
        gbc.gridy = 3;
        inputPn.add(lblTongCong, gbc);

        tfTongCong = new JTextField(TongTien() + "");
        tfTongCong.setFont(BASE.font);
        tfTongCong.setEditable(false);
        tfTongCong.setPreferredSize(new Dimension(tfTongCong.getPreferredSize().width, 30));
        gbc.gridy = 4;
        inputPn.add(tfTongCong, gbc);

        lblGiamGia = new JLabel("Giảm giá");
        lblGiamGia.setFont(BASE.font);
        gbc.gridy = 5;
        inputPn.add(lblGiamGia, gbc);

        tfGiamGia = new JTextField("0.0");
        tfGiamGia.setFont(BASE.font);
        tfGiamGia.setEditable(false);
        tfGiamGia.setPreferredSize(new Dimension(tfGiamGia.getPreferredSize().width, 30));
        gbc.gridy = 6;
        inputPn.add(tfGiamGia, gbc);

        lblTongHD = new JLabel("Tổng hóa đơn");
        lblTongHD.setFont(BASE.font);
        gbc.gridy = 7;
        inputPn.add(lblTongHD, gbc);

        tfTongHD = new JTextField("0.0");
        tfTongHD.setFont(BASE.font);
        tfTongHD.setEditable(false);
        tfTongHD.setPreferredSize(new Dimension(tfTongHD.getPreferredSize().width, 30));
        gbc.gridy = 8;
        inputPn.add(tfTongHD, gbc);

        btnThanhToan = new JPanel();
        styleBtn(btnThanhToan, "Thanh Toán Hóa Đơn", "btnThanhToan");
        gbc.gridy = 9;
        inputPn.add(btnThanhToan, gbc);

        btnThemSp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChonSanPhamGUI spGUI = new ChonSanPhamGUI(BanHangGUI.this);

                spGUI.setVisible(true);
            }
        });

        pnThanhToan.add(inputPn, BorderLayout.NORTH);

        btnThanhToan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                if ("btnThanhToan".equals(clickedPanel.getName())) {
                    if (tfMaKH.getText().isEmpty()) {
                        new ShowDiaLog("Vui lòng nhập mã khách hàng!", ShowDiaLog.ERROR_DIALOG);
                        return;
                    }

                    if (dtmSP.getRowCount() == 0) {
                        new ShowDiaLog("Danh sách sản phẩm trống!", ShowDiaLog.ERROR_DIALOG);
                        return;
                    }
                    String MaKH = tfMaKH.getText();
                    KhachHangBUS khBUS = new KhachHangBUS();
                    KhachHangDTO khDTO = khBUS.layKHTheoMa(MaKH);

                    double TongTien = TongTien();
                    double diemtichluy = khDTO.getDiemTichluy();
                    double giamgia = DoiDiem(diemtichluy);
                    double ThanhTien = 0;
                    if (TongTien > giamgia) {
                        double diemtl = DiemTichLuy(TongTien);
                        khBUS.CapNhatDiemTL(MaKH, diemtl);
                        ThanhTien = TongTien - giamgia;

                    } else if (TongTien < giamgia) {
                        double diemconlai = giamgia - TongTien;
                        double diemtl = DiemTichLuy(TongTien);
                        khBUS.CapNhatDiemTL(MaKH, diemtl + diemconlai);
                        ThanhTien = 0;
                    }

                    HoaDonBUS hdBUS = new HoaDonBUS();
                    HoaDonDTO hd = new HoaDonDTO(tfMaKH.getText(), tfMaNV.getText(), giamgia, ThanhTien);
                    hd.setSoHD(hdBUS.TaoMaHD());
                    if (hdBUS.ThemHoaDon(hd)) {
                        ThemCTHD(hd.getSoHD());
                        ChiTietHoaDonBUS ctBUS = new ChiTietHoaDonBUS();
                        for (ChiTietHoaDonDTO ct : ListBook) {
                            ctBUS.ThemCTHoaDon(ct);
                            SanPhamBUS spBUS = new SanPhamBUS();
                            SanPhamDTO spDTO = spBUS.getSP(ct.getMaSach());
                            int sl = spDTO.getSoLuong() - ct.getSoLuong();
                            spBUS.CapNhatSoLuongSP(spDTO.getMaSach(), sl);
                        }

                        new ShowDiaLog("Thanh Toán thành công", ShowDiaLog.SUCCESS_DIALOG);
                        reset();
                    } else {
                        new ShowDiaLog("Thanh Toán thất tại", ShowDiaLog.ERROR_DIALOG);
                    }
                }
            }
        });

        tfMaKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TinhToanHD();
            }
        });
         btnTaoKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemKhachHangGUI kh = new ThemKhachHangGUI(BanHangGUI.this);
                
            }
        });
    }

    private void styleBtn(JPanel b, String text, String name) {
        JLabel t = new JLabel(text, JLabel.CENTER);

        b.setFont(BASE.font_title);
        t.setForeground(Color.WHITE);
        b.setName(name);
        b.setLayout(new BorderLayout());
        b.add(t, BorderLayout.CENTER);
        b.setBackground(BASE.color_table_heaer);
        b.setPreferredSize(new Dimension(100, 40));
        b.setOpaque(true);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JPanel createPnDanhSachSp() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá sách", "Thành tiền", ""};
        dtmSP = new DefaultTableModel(columnNames, 0);
        tableSP = new JTable(dtmSP) {
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 2) { // Chỉ áp dụng cho cột "Số lượng"
                    // Tạo JFormattedTextField chỉ cho phép nhập số nguyên
                    JFormattedTextField formattedTextField = new JFormattedTextField();
                    formattedTextField.setValue(0); // Giá trị mặc định là 0
                    formattedTextField.setHorizontalAlignment(JFormattedTextField.RIGHT);

                    // Định dạng để chỉ chấp nhận số nguyên >= 0
                    NumberFormat format = NumberFormat.getIntegerInstance();
                    format.setGroupingUsed(false); // Không hiển thị dấu phân cách
                    NumberFormatter formatter = new NumberFormatter(format);
                    formatter.setValueClass(Integer.class);
                    formatter.setAllowsInvalid(false); // Không cho phép ký tự không hợp lệ
                    formatter.setMinimum(0); // Chỉ cho phép giá trị >= 0
                    formattedTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));

                    return new DefaultCellEditor(formattedTextField);
                }
                return super.getCellEditor(row, column);
            }
        };

        tableSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableSP.rowAtPoint(e.getPoint());
                int col = tableSP.columnAtPoint(e.getPoint());

                // Kiểm tra nếu bảng có ít nhất một dòng và người dùng click vào cột xóa
                if (row >= 0 && col == 5) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xóa dòng này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        dtmSP.removeRow(row);
                        TinhToanHD();
                    }
                }
            }
        });

        tableSP.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 2) { // Cột số lượng
                int row = e.getFirstRow();

                try {
                    // Lấy số lượng mới (kiểm tra null và ép kiểu đúng)
                    Object soLuongObj = tableSP.getValueAt(row, 2);
                    int soLuong = (soLuongObj instanceof Integer) ? (Integer) soLuongObj : Integer.parseInt(soLuongObj.toString());

                    // Lấy giá sách (kiểm tra null và ép kiểu đúng)
                    Object donGiaObj = tableSP.getValueAt(row, 3);
                    double donGia = (donGiaObj instanceof Double) ? (Double) donGiaObj : Double.parseDouble(donGiaObj.toString());

                    // Tính lại thành tiền
                    double thanhTien = soLuong * donGia;
                    dtmSP.setValueAt(thanhTien, row, 4);

                    // Cập nhật lại tổng cộng và tổng hóa đơn
                    TinhToanHD();
                } catch (Exception ex) {
                    // Bắt lỗi và thông báo
                    JOptionPane.showMessageDialog(null, "Lỗi trong quá trình cập nhật thành tiền: " + ex.getMessage());
                }
            }
        });

        styleTable(tableSP);

        tableSP.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tableSP);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private double TongTien() {
        double tong = 0.0;
        int sl_dong = dtmSP.getRowCount();
        for (int i = 0; i < sl_dong; i++) {
            double gia = Double.parseDouble(dtmSP.getValueAt(i, 4) + "");
            tong += gia;
        }
        return tong;
    }

    public void DSHD(ArrayList<ChiTietHoaDonDTO> selectedProducts) {
        dtmSP.setRowCount(0);
        for (ChiTietHoaDonDTO ct : selectedProducts) {
            addProduct(ct);
        }
    }

    public void addProduct(ChiTietHoaDonDTO ct) {
        dtmSP.addRow(new Object[]{ct.getSp().getMaSach(), ct.getSp().getTenSach(), ct.getSoLuong(), ct.getdonGia(), ct.getSoLuong() * ct.getdonGia(), "Xóa"});
        TinhToanHD();

    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(BASE.color_table_heaer);
        header.setForeground(BASE.color_text);
        header.setFont(BASE.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(BASE.font);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private JButton createBtn(String txt, String name) {
        JButton btn = new JButton(txt);
        btn.setFont(BASE.font);
        btn.setPreferredSize(new Dimension(170, 30));
        btn.setMaximumSize(new Dimension(170, 30));
        btn.setForeground(Color.BLACK);
        btn.setBackground(BASE.color_table_heaer);
        btn.setName(name);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    public ArrayList<ChiTietHoaDonDTO> getCtHoaDon() {
        return ctHoaDon;
    }

    public void setCtHoaDon(ArrayList<ChiTietHoaDonDTO> ctHoaDon) {
        this.ctHoaDon = ctHoaDon;
    }

    public void TinhToanHD() {
        String MaKH = tfMaKH.getText();
        KhachHangBUS khBUS = new KhachHangBUS();
        KhachHangDTO khDTO = khBUS.layKHTheoMa(MaKH);

        double TongTien = TongTien();

        tfTongCong.setText(TongTien + "");

        if (khDTO == null) {
            tfGiamGia.setText("0.0");
            tfTongHD.setText("0.0");
        } else {
            double diemtichluy = khDTO.getDiemTichluy();
            double giamgia = DoiDiem(diemtichluy);
            tfGiamGia.setText(giamgia + "");
            if (TongTien == 0) {
                tfTongHD.setText("0.0");
            } else if (TongTien > giamgia) {
                double TongHD = TongTien - giamgia;
                tfTongHD.setText(TongHD + "");
            } else if (TongTien < giamgia) {
                double diemconlai = giamgia - TongTien;
                tfTongHD.setText("0.0");
                tfGiamGia.setText(TongTien + "");
            }
        }

    }


    public JTextField getTfMaKH() {
        return tfMaKH;
    }
    
    

    private double DiemTichLuy(double TongTien) {
        return Math.round(TongTien / 100000);
    }

    private double DoiDiem(double diemtichluy) {
        return diemtichluy * 1000;
    }

    public void ThemCTHD(String SoHD) {
        int rowCount = dtmSP.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String MaSP = (String) dtmSP.getValueAt(i, 0);
            int soLuong = (Integer) dtmSP.getValueAt(i, 2);
            double donGia = (Double) dtmSP.getValueAt(i, 3);

            ChiTietHoaDonDTO chiTiet = new ChiTietHoaDonDTO(SoHD, MaSP, soLuong, donGia);
            ListBook.add(chiTiet);
        }
    }

    private void reset() {
        tfMaKH.setText("");
        tfTongCong.setText("");
        tfGiamGia.setText("");
        tfTongHD.setText("");
        ctHoaDon.clear();
        DSHD(ctHoaDon);
    }

    
    
    

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1500, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new BanHangGUI());
        f.setVisible(true);
    }
}
