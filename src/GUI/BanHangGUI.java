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
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class BanHangGUI extends JPanel {

    private String MaNV;
    private JPanel pnProductList, pnPay, btChoose, btCustomerCreate, btPay, btChonKH;
    private DefaultTableModel dtm;
    private JTable tbl;
    private JTextField tfMaKH, tfMaNv, tfTongCong, tfGiamGia, tfTongHD;
//    private KhachHangDTO KhTao;
    private ArrayList<ChiTietHoaDonDTO> billList = new ArrayList<>();
    private ArrayList<ChiTietHoaDonDTO> billDetailList = new ArrayList<>();

    public BanHangGUI(String MaNV) {
        this.MaNV = MaNV;
        init();
        initComponents();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        pnProductList = new JPanel(new BorderLayout());

        pnPay = new JPanel();
        pnPay.setLayout(new BoxLayout(pnPay, BoxLayout.Y_AXIS));
        pnPay.setPreferredSize(new Dimension(350, 0));

        this.add(pnProductList, BorderLayout.CENTER);
        this.add(pnPay, BorderLayout.EAST);
    }

    private void initComponents() {
        JPanel pnHeaderProduct = new JPanel();
        pnHeaderProduct.setLayout(new BoxLayout(pnHeaderProduct, BoxLayout.X_AXIS));
        pnHeaderProduct.setPreferredSize(new Dimension(0, 80));
        pnHeaderProduct.setBorder(new EmptyBorder(10, 0, 20, 0));

        btChoose = new JPanel();
        btChoose.setLayout(new BoxLayout(btChoose, BoxLayout.X_AXIS));
        btChoose.setBackground(Color.decode("#249171"));
        btChoose.setBorder(new EmptyBorder(0, 10, 0, 10));
        btChoose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel lblChooseProduct = new JLabel("Chọn sách");
        lblChooseProduct.setFont(BASE.font);
        ImageIcon productIcon = new ImageIcon(getClass().getResource("/Image/choose.png"));
        Image img = productIcon.getImage();
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel lblProductImage = new JLabel(scaledIcon);
        btChoose.add(lblChooseProduct);
        btChoose.add(lblProductImage);

        pnHeaderProduct.add(Box.createHorizontalGlue());
        pnHeaderProduct.add(btChoose);

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá sách", "Thành tiền", ""};
        dtm = new DefaultTableModel(columnNames, 0);
        tbl = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 2) {
                    JFormattedTextField formattedTextField = new JFormattedTextField();
                    formattedTextField.setValue(0); // Default value is 0
                    formattedTextField.setHorizontalAlignment(JFormattedTextField.RIGHT);

                    NumberFormat format = NumberFormat.getIntegerInstance();
                    format.setGroupingUsed(false);
                    NumberFormatter formatter = new NumberFormatter(format);
                    formatter.setValueClass(Integer.class);
                    formatter.setAllowsInvalid(false);
                    formatter.setMinimum(0);
                    formattedTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));

                    String productCode = tbl.getValueAt(row, 0).toString();

                    SanPhamBUS busSP = new SanPhamBUS();
                    int remainingStock = busSP.getSLSP(productCode);

                    formattedTextField.setMaximumSize(new Dimension(remainingStock, formattedTextField.getPreferredSize().height));

                    formattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                        @Override
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            try {
                                int enteredQuantity = Integer.parseInt(formattedTextField.getText());
                                if (enteredQuantity > remainingStock) {
                                    formattedTextField.setValue(remainingStock);
                                    JOptionPane.showMessageDialog(null, "Số lượng không thể lớn hơn số lượng còn lại: " + remainingStock);
                                    tbl.setValueAt(remainingStock, row, 2);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập một số hợp lệ.");
                            }
                        }
                    });

                    return new DefaultCellEditor(formattedTextField);
                }
                return super.getCellEditor(row, column);
            }

        };

        tbl.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 2) { // Cột số lượng
                int row = e.getFirstRow();
                try {
                    int soLuong = Integer.parseInt(tbl.getValueAt(row, 2).toString());
                    double donGia = Double.parseDouble(tbl.getValueAt(row, 3).toString());
                    dtm.setValueAt(soLuong * donGia, row, 4);
                    tinhToanHD();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi trong quá trình cập nhật thành tiền: " + ex.getMessage());
                }
            }
        });

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.rowAtPoint(e.getPoint());
                int column = tbl.columnAtPoint(e.getPoint());

                if (row != -1 && column == tbl.getColumnCount() - 1) {
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xóa sản phẩm này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        ChiTietHoaDonDTO productToRemove = billList.get(row);
                        billList.remove(productToRemove);
                        dtm.removeRow(row);
                        tinhToanHD();
                        toggleDeleteColumnVisibility();
                    }
                }
            }
        });

        styleTable(tbl);
        tbl.setFillsViewportHeight(true);
        JScrollPane scrollTbl = new JScrollPane(tbl);

        pnProductList.add(pnHeaderProduct, BorderLayout.NORTH);
        pnProductList.add(scrollTbl, BorderLayout.CENTER);

        //pnPay
        JPanel pnInfoBill = createTopBottomJL("Thông tin hóa đơn");

        JLabel lblMaKH = new JLabel("Mã khách hàng", Label.LEFT);
        lblMaKH.setFont(BASE.font_header);
        JPanel pnlblMAKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlblMAKH.setPreferredSize(new Dimension(500, 35));
        pnlblMAKH.setMaximumSize(new Dimension(500, 35));
        pnlblMAKH.add(lblMaKH);

        tfMaKH = new JTextField();
        tfMaKH.setFont(BASE.font);
        tfMaKH.setPreferredSize(new Dimension(800, 35));
        tfMaKH.setMaximumSize(new Dimension(800, 35));
        tfMaKH.setEnabled(false);
        btCustomerCreate = new JPanel();
        btCustomerCreate.setMaximumSize(new Dimension(40, 40));
        btCustomerCreate.setLayout(new BoxLayout(btCustomerCreate, BoxLayout.X_AXIS));
        btCustomerCreate.setBackground(Color.decode("#5DADE2"));
        btCustomerCreate.setBorder(new EmptyBorder(0, 10, 0, 10));
        btCustomerCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel lblCustomer = new JLabel("Tạo mới");
        lblCustomer.setFont(BASE.font);
        ImageIcon CustomerIcon = new ImageIcon(getClass().getResource("/Image/btAdd.png"));
        Image CustomerImg = CustomerIcon.getImage();
        Image scaledCustomerImg = CustomerImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledCustomerIcon = new ImageIcon(scaledCustomerImg);
        JLabel lblCustomerImage = new JLabel(scaledCustomerIcon);
        btCustomerCreate.add(lblCustomer);
        btCustomerCreate.add(lblCustomerImage);

        btChonKH = new JPanel();
        btChonKH.setMaximumSize(new Dimension(40, 40));
        btChonKH.setLayout(new BoxLayout(btChonKH, BoxLayout.X_AXIS));
        btChonKH.setBackground(Color.decode("#F5B041"));
        btChonKH.setBorder(new EmptyBorder(0, 10, 0, 10));
        btChonKH.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel lblChonKH = new JLabel("Chọn KH");
        lblChonKH.setFont(BASE.font);
        ImageIcon KhIcon = new ImageIcon(getClass().getResource("/Image/person.png"));
        Image khImg = KhIcon.getImage();
        Image scaledKhImg = khImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledkhIcon = new ImageIcon(scaledKhImg);
        JLabel lblKhImage = new JLabel(scaledkhIcon);
        btChonKH.add(lblChonKH);
        btChonKH.add(lblKhImage);

        JPanel pnkhachhang = new JPanel();
        pnkhachhang.setLayout(new BoxLayout(pnkhachhang, BoxLayout.X_AXIS));
        pnkhachhang.add(tfMaKH);
        pnkhachhang.add(Box.createVerticalStrut(10));
        pnkhachhang.add(btChonKH);
        pnkhachhang.add(Box.createVerticalStrut(10));
        pnkhachhang.add(btCustomerCreate);

        JPanel pnKH = new JPanel();
        pnKH.setLayout(new BoxLayout(pnKH, BoxLayout.Y_AXIS));
        pnKH.add(pnlblMAKH);
        pnKH.add(Box.createVerticalStrut(10));
        pnKH.add(pnkhachhang);

        tfMaNv = new JTextField();
        tfMaNv.setText(MaNV);
        JPanel pnMaNV = createPnBill("Mã nhân viên", tfMaNv);

        tfTongCong = new JTextField();
        JPanel pnTongCong = createPnBill("Tổng cộng", tfTongCong);

        tfGiamGia = new JTextField();
        JPanel pnGiamGia = createPnBill("Giảm giá", tfGiamGia);

        tfTongHD = new JTextField();
        JPanel pnTongHD = createPnBill("Tổng thanh toán", tfTongHD);

        btPay = createTopBottomJL("Thanh toán hóa đơn");
        btPay.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pnPay.add(pnInfoBill);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(pnKH);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(pnMaNV);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(pnTongCong);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(pnGiamGia);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(pnTongHD);
        pnPay.add(Box.createVerticalStrut(15));
        pnPay.add(btPay);

        MouseAdapter commonMouseListener = createCommonMouseListener();
        btChoose.addMouseListener(commonMouseListener);
        btCustomerCreate.addMouseListener(commonMouseListener);
        btPay.addMouseListener(commonMouseListener);
        btChonKH.addMouseListener(commonMouseListener);

        tfMaKH.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                tinhToanHD();
            }
        });
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(BASE.color_header_tbl);
        header.setForeground(BASE.color_text);
        header.setFont(BASE.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(BASE.font);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                if (clickedPanel == btChoose) {
                    ChonSanPhamGUI spGUI = new ChonSanPhamGUI(BanHangGUI.this);
                    spGUI.setVisible(true);
                } else if (clickedPanel == btCustomerCreate) {
                    TaoKhachHangGUI addCustomer = new TaoKhachHangGUI(BanHangGUI.this);
                }else if(clickedPanel == btChonKH){
                    ChonKhachHangGUI kh = new ChonKhachHangGUI(BanHangGUI.this);
                }else if (clickedPanel == btPay) {
                    if (tfMaKH.getText().isEmpty()) {
                        new ShowDiaLog("Vui lòng nhập mã khách hàng!", ShowDiaLog.ERROR_DIALOG);
                        return;
                    }

                    if (dtm.getRowCount() == 0) {
                        new ShowDiaLog("Danh sách sản phẩm trống!", ShowDiaLog.ERROR_DIALOG);
                        return;
                    }

                    KhachHangBUS khBUS = new KhachHangBUS();
                    String MaKH = tfMaKH.getText();
                    KhachHangDTO khDTO = khBUS.layKHTheoMa(MaKH);

                    double TongTien = tinhTongTien();
                    double diemtichluy = khDTO.getDiemTichluy();
                    double giamgia = tinhQuyDoiDiem(diemtichluy);
                    double ThanhTien;

                    if (TongTien > giamgia) {
                        double diemtl = tinhDiemTichLuy(TongTien);
                        khBUS.CapNhatDiemTL(MaKH, diemtl);
                        ThanhTien = TongTien - giamgia;
                    } else {
                        double diemconlai = giamgia - TongTien;
                        double diemtl = tinhDiemTichLuy(TongTien);
                        khBUS.CapNhatDiemTL(MaKH, diemtl + diemconlai);
                        ThanhTien = 0;
                    }

                    HoaDonBUS hdBUS = new HoaDonBUS();
                    HoaDonDTO hd = new HoaDonDTO(tfMaKH.getText(), tfMaNv.getText(), giamgia, ThanhTien);
                    hd.setSoHD(hdBUS.TaoMaHD());
                    if (hdBUS.ThemHoaDon(hd)) {
                        DetailBillList(hd.getSoHD());
                        ChiTietHoaDonBUS ctBUS = new ChiTietHoaDonBUS();
                        for (ChiTietHoaDonDTO ct : billDetailList) {
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

            @Override
            public void mouseEntered(MouseEvent e) {
                JPanel pn = (JPanel) e.getSource();
                if (pn == btChoose) {
                    pn.setBackground(Color.decode("#3BAF75"));
                } else if (pn == btCustomerCreate) {
                    pn.setBackground(Color.decode("#4988B2"));
                } else if (pn == btPay) {
                    pn.setBackground(Color.decode("#DAB378"));
                }else if (pn == btChonKH) {
                    pn.setBackground(Color.decode("#D68910"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JPanel pn = (JPanel) e.getSource();
                if (pn == btChoose) {
                    pn.setBackground(Color.decode("#249171"));
                } else if (pn == btPay) {
                    pn.setBackground(BASE.color_header_tbl);
                } else if (pn == btCustomerCreate) {
                    pn.setBackground(Color.decode("#5DADE2"));
                }
                else if (pn == btChonKH) {
                    pn.setBackground(Color.decode("#F5B041"));
                }
            }
        };
    }

    private JPanel createTopBottomJL(String name) {
        JPanel pn = new JPanel(new BorderLayout());
        pn.setPreferredSize(new Dimension(500, 35));
        pn.setMaximumSize(new Dimension(500, 35));
        pn.setBackground(BASE.color_main);
        JLabel lbl = new JLabel(name, JLabel.CENTER);
        lbl.setFont(BASE.font_header);
        pn.add(lbl, BorderLayout.CENTER);
        return pn;
    }

    private JPanel createPnBill(String name, JTextField tf) {
        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(name);
        lbl.setFont(BASE.font_header);
        tf.setPreferredSize(new Dimension(800, 35));
        tf.setBackground(Color.WHITE);
        tf.setMaximumSize(new Dimension(800, 35));
        tf.setEditable(false);
        tf.setFont(BASE.font);
        pn.add(lbl);
        pn.add(Box.createVerticalStrut(10));
        pn.add(tf);
        return pn;
    }

    public void addProduct(ChiTietHoaDonDTO ct) {
        dtm.addRow(new Object[]{ct.getSp().getMaSach(), ct.getSp().getTenSach(), ct.getSoLuong(), ct.getdonGia(), ct.getSoLuong() * ct.getdonGia(), "Xóa"});
        tinhToanHD();
    }

    public void reLoadData(ArrayList<ChiTietHoaDonDTO> selectedProducts) {
        dtm.setRowCount(0);
        for (ChiTietHoaDonDTO ct : selectedProducts) {
            addProduct(ct);
        }
        toggleDeleteColumnVisibility();
    }

    public ArrayList<ChiTietHoaDonDTO> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<ChiTietHoaDonDTO> billList) {
        this.billList = billList;
    }

    public JTextField getTfMaKH() {
        return tfMaKH;
    }

    public void setTfMaKH(JTextField tfMaKH) {
        this.tfMaKH = tfMaKH;
    }

    private void reset() {
        tfMaKH.setText("");
        tfTongCong.setText("");
        tfGiamGia.setText("");
        tfTongHD.setText("");
        billList.clear();
        reLoadData(billList);
    }

    private double tinhTongTien() {
        double tong = 0.0;
        int sl_dong = dtm.getRowCount();
        for (int i = 0; i < sl_dong; i++) {
            double gia = Double.parseDouble(dtm.getValueAt(i, 4) + "");
            tong += gia;
        }
        return tong;
    }

    private double tinhDiemTichLuy(double TongTien) {
        return Math.round(TongTien / 100000);
    }

    private double tinhQuyDoiDiem(double diemtichluy) {
        return diemtichluy * 1000;
    }

    private void toggleDeleteColumnVisibility() {
        if (tbl.getRowCount() == 0) {
            // Hide the last column (delete column)
            tbl.getColumnModel().getColumn(tbl.getColumnCount() - 1).setMinWidth(0);
            tbl.getColumnModel().getColumn(tbl.getColumnCount() - 1).setMaxWidth(0);
        } else {
            // Show the last column (delete column)
            tbl.getColumnModel().getColumn(tbl.getColumnCount() - 1).setMinWidth(75);
            tbl.getColumnModel().getColumn(tbl.getColumnCount() - 1).setMaxWidth(75);
        }
    }

    public void tinhToanHD() {
        String MaKH = tfMaKH.getText();
        KhachHangBUS khBUS = new KhachHangBUS();
        KhachHangDTO khDTO = khBUS.layKHTheoMa(MaKH);

        double TongTien = tinhTongTien();
        tfTongCong.setText(String.valueOf(TongTien));

        // Kiểm tra nếu khách hàng không tồn tại
        if (khDTO == null) {
            tfGiamGia.setText("0.0");
            tfTongHD.setText(String.valueOf(TongTien)); // Tổng hóa đơn bằng tổng tiền
            return;
        }

        double diemtichluy = khDTO.getDiemTichluy();
        double giamgia = tinhQuyDoiDiem(diemtichluy);
        tfGiamGia.setText(String.valueOf(giamgia));

        if (TongTien <= giamgia) {
            tfTongHD.setText("0.0");
            tfGiamGia.setText(String.valueOf(TongTien));
        } else {
            double TongHD = TongTien - giamgia;
            tfTongHD.setText(String.valueOf(TongHD));
        }
    }

    public void DetailBillList(String SoHD) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String MaSP = (String) dtm.getValueAt(i, 0);
            int soLuong = Integer.parseInt(dtm.getValueAt(i, 2).toString());
            double donGia = Double.parseDouble(dtm.getValueAt(i, 3).toString());

            ChiTietHoaDonDTO chiTiet = new ChiTietHoaDonDTO(SoHD, MaSP, soLuong, donGia);
            billDetailList.add(chiTiet);
        }
    }

//    public KhachHangDTO getKhTao() {
//        return KhTao;
//    }
//
//    public void setKhTao(KhachHangDTO KhTao) {
//        this.KhTao = KhTao;
//    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1500, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new BanHangGUI("NV01"));
        f.setVisible(true);
    }
}
