/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class BillHistoryGUI extends JPanel {

    private JPanel pnTool, pnMain, btSearch, btRefresh, btDel, btPrint;
    private JTextField tfSearch;
    private JSpinner startDate, endDate;
    private DefaultTableModel dtm;
    private JTable tbl;
    private ArrayList<HoaDonDTO> billList;
    private HoaDonDTO billPrint;

    public BillHistoryGUI() {
        init();
        initComponents();
        HoaDonBUS bus = new HoaDonBUS();
        billList = bus.getDsHD();
        reload(billList);
    }

    public void init() {
        this.setLayout(new BorderLayout());

        pnTool = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));
        pnTool.setBorder(new EmptyBorder(0, 5, 20, 5));

        pnMain = new JPanel(new BorderLayout());

        this.add(pnTool, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void initComponents() {

        //pnTool
        //pnSearch
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.Y_AXIS));
        JLabel lblSearch = new JLabel("Tìm kiếm mã HD, mã KH, mã NV");
        lblSearch.setFont(BASE.font_header);
        tfSearch = new JTextField();
        tfSearch.setPreferredSize(new Dimension(lblSearch.getPreferredSize().width - 30, 25));
        pnSearch.add(lblSearch);
        pnSearch.add(Box.createVerticalStrut(5));
        pnSearch.add(tfSearch);

        //beginDate
        startDate = new JSpinner(new SpinnerDateModel());
        JPanel pnStartDate = pnDate("Ngày bắt đầu", startDate);

        JLabel lblDen = new JLabel("Đến", JLabel.CENTER);
        lblDen.setFont(BASE.font_header);

        //endDate
        endDate = new JSpinner(new SpinnerDateModel());
        JPanel pnEndDate = pnDate("Ngày kết thúc", endDate);

        MouseAdapter commonMouseListener = createCommonMouseListener();

        //bt Search 
        btSearch = new JPanel();
        JPanel pnBtSreach = createButton(btSearch, "Tìm", "search.png", BASE.color_btTim, 80, 30);
        btSearch.addMouseListener(commonMouseListener);

        //bt Refresh
        btRefresh = new JPanel();
        btRefresh.addMouseListener(commonMouseListener);
        JPanel pnBtRefresh = createButton(btRefresh, "Làm mới", "refresh.png", BASE.color_btLamMoi, 110, 30);

        //bt Del
        btDel = new JPanel();
        btDel.addMouseListener(commonMouseListener);
        JPanel pnBtDel = createButton(btDel, "Xóa", "refresh.png", BASE.color_btLamXoa, 100, 60);

        //bt print
        btPrint = new JPanel();
        btPrint.addMouseListener(commonMouseListener);
        JPanel pnBtBDF = createButton(btPrint, "In BDF", "print.png", BASE.color_btBFD, 100, 60);

        // add pnTool
        pnTool.add(pnSearch);
        pnTool.add(pnStartDate);
        pnTool.add(lblDen);
        pnTool.add(pnEndDate);
        pnTool.add(pnBtSreach);
        pnTool.add(pnBtRefresh);
        pnTool.add(pnBtDel);
        pnTool.add(pnBtBDF);

        //pnMain
        Object[] colSP = {"Mã HD", "Mã nhân viên", "Mã khách hàng", "Thời gian", "Ngày", "Giảm giá", "Tổng tiền", "Trạng Thái"};
        dtm = new DefaultTableModel(colSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(dtm);
        styleTable(tbl);
        JScrollPane scrollTbl = new JScrollPane(tbl);
        pnMain.add(scrollTbl, BorderLayout.CENTER);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.rowAtPoint(e.getPoint());

                    if (row != -1) { // Kiểm tra dòng có tồn tại
                        String soHD = tbl.getValueAt(row, 0) + "";
                        String maKH = tbl.getValueAt(row, 2) + "";
                        String tenDN = tbl.getValueAt(row, 1) + "";
                        String tGian = tbl.getValueAt(row, 3) + "";
                        String ngayHD = tbl.getValueAt(row, 4) + "";
                        double tienGiamGia = Double.parseDouble(tbl.getValueAt(row, 5) + "");
                        double tongTien = Double.parseDouble(tbl.getValueAt(row, 6) + "");

                        HoaDonDTO hd = new HoaDonDTO(soHD, maKH, tenDN, tGian, ngayHD, tienGiamGia, tongTien);

                        new invoiceDetails(hd);
                    }
                }
                else if(e.getClickCount() == 1){
                    int row = tbl.rowAtPoint(e.getPoint());

                    if (row != -1) { // Kiểm tra dòng có tồn tại
                        String soHD = tbl.getValueAt(row, 0) + "";
                        String maKH = tbl.getValueAt(row, 2) + "";
                        String tenDN = tbl.getValueAt(row, 1) + "";
                        String tGian = tbl.getValueAt(row, 3) + "";
                        String ngayHD = tbl.getValueAt(row, 4) + "";
                        double tienGiamGia = Double.parseDouble(tbl.getValueAt(row, 5) + "");
                        double tongTien = Double.parseDouble(tbl.getValueAt(row, 6) + "");

                       billPrint = new HoaDonDTO(soHD, maKH, tenDN, tGian, ngayHD, tienGiamGia, tongTien);
                    }
                }
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

    private JPanel pnDate(String name, JSpinner date) {
        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel(name, JLabel.LEFT);
        lbl.setFont(BASE.font_header);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        date.setFont(BASE.font_header);
        date.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(date, "yyyy/MM/dd");
        date.setEditor(startDateEditor);

        lbl.setMaximumSize(new Dimension(200, lbl.getPreferredSize().height));
        date.setMaximumSize(new Dimension(200, date.getPreferredSize().height));

        pn.add(lbl);
        pn.add(Box.createVerticalStrut(5));
        pn.add(date);

        return pn;
    }

    private JPanel createButton(JPanel btn, String text, String url, Color color, int width, int height) {
        btn.setLayout(new BoxLayout(btn, BoxLayout.X_AXIS));
        btn.setBackground(color);
        btn.setBorder(new EmptyBorder(0, 10, 0, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, height));
        btn.setMaximumSize(new Dimension(width, height));

        JLabel lblText = new JLabel(text);
        lblText.setFont(BASE.font_header);

        ImageIcon Icon = new ImageIcon(getClass().getResource("/Image/" + url));
        Image img = Icon.getImage();
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel lblImage = new JLabel(scaledIcon);
        btn.add(lblText);
        btn.add(Box.createHorizontalGlue());
        btn.add(lblImage);
        return btn;
    }

    private void reload(ArrayList<HoaDonDTO> ds) {
        dtm.setRowCount(0);
        for (HoaDonDTO hd : ds) {
            String TrangThai = hd.getTrangThai() == 1 ? "Đã xác nhận" : "Hủy";
            dtm.addRow(new Object[]{hd.getSoHD(), hd.getTenDN(), hd.getMaKH(), hd.getTGian(), hd.getNgayHD(), hd.getTienGiamGia(), hd.getTongTien(), TrangThai});
        }
    }

    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel clickedPanel = (JPanel) e.getSource();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if (clickedPanel == btDel) {
                        Date currentDate = new Date();

                        String formattedCurrentDate = formatter.format(currentDate);

                        int selectedRow = tbl.getSelectedRow();
                        if (selectedRow != -1) {
                            String ngayHDStr = dtm.getValueAt(selectedRow, 4).toString();
                            if (ngayHDStr.equals(formattedCurrentDate)) {
                                HoaDonBUS bus = new HoaDonBUS();
                                String soHD = dtm.getValueAt(selectedRow, 0).toString();

                                if (bus.CapNhatTrangThaiHD(soHD)) {
                                    ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS(soHD);
                                    ArrayList<ChiTietHoaDonDTO> dsctHD = cthdBUS.getDscthd();
                                    HoaDonDTO hdDTO = bus.getHD(soHD);
                                    editStatus(hdDTO);
                                    for (ChiTietHoaDonDTO ct : dsctHD) {
                                        SanPhamBUS spBUS = new SanPhamBUS();
                                        SanPhamDTO spDTO = spBUS.getSP(ct.getMaSach());
                                        int sl = spDTO.getSoLuong() + ct.getSoLuong();
                                        spBUS.CapNhatSoLuongSP(spDTO.getMaSach(), sl);
                                    }
                                }
                            } else {
                                new ShowDiaLog("Chỉ được phép hủy hóa đơn trong Ngày", ShowDiaLog.ERROR_DIALOG);
                            }
                        }
                    } else if (clickedPanel == btSearch) {
                        String searchText = tfSearch.getText().toLowerCase();
                        Date start = (Date) startDate.getValue();
                        Date end = (Date) endDate.getValue();
                        String startDateStr = formatter.format(start);
                        String endDateStr = formatter.format(end);

                        ArrayList<HoaDonDTO> filteredList = new ArrayList<>();

                        for (HoaDonDTO bill : billList) {
                            String billDateStr = bill.getNgayHD();
//                            System.out.println(bill.getNgayHD());

                            boolean isInDateRange = (billDateStr.compareTo(startDateStr) >= 0 && billDateStr.compareTo(endDateStr) <= 0);

                            boolean isMatchSearchText = bill.getSoHD().toLowerCase().contains(searchText) || bill.getTenDN().toLowerCase().contains(searchText)
                                    || bill.getMaKH().toLowerCase().contains(searchText);

                            if (isInDateRange && (searchText.isEmpty() || isMatchSearchText)) {
                                filteredList.add(bill);
                            }
                        }

                        // Reload the table with the filtered list
                        reload(filteredList);

                    } else if (clickedPanel == btRefresh) {
                        Date currentDate = new Date();
                        startDate.setValue(currentDate);
                        endDate.setValue(currentDate);

                        tfSearch.setText("");
                        reload(billList);
                    } else if (clickedPanel == btPrint) {
                        PDFExporter exporter = new PDFExporter();
                        exporter.exportInvoiceToPDF(billPrint);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel pn = (JPanel) e.getSource();

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel pn = (JPanel) e.getSource();

                }
            }
        };
    }

    public void editStatus(HoaDonDTO hd) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(hd.getSoHD())) {
                String TrangThai = (hd.getTrangThai() == 1) ? "Đã xác nhận" : "Hủy";
                dtm.setValueAt(hd.getTenDN(), i, 1);
                dtm.setValueAt(hd.getMaKH(), i, 2);
                dtm.setValueAt(hd.getTGian(), i, 3);
                dtm.setValueAt(hd.getNgayHD(), i, 4);
                dtm.setValueAt(hd.getTienGiamGia(), i, 5);
                dtm.setValueAt(hd.getTongTien(), i, 6);
                dtm.setValueAt(TrangThai, i, 7);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    private void Search() {
        String key = tfSearch.getText().toLowerCase();
        HoaDonBUS bus = new HoaDonBUS();
        ArrayList<HoaDonDTO> ds = new ArrayList<>();
        for (HoaDonDTO hd : billList) {
            if (hd.getSoHD().toLowerCase().contains(key) || hd.getMaKH().toLowerCase().contains(key) || hd.getTenDN().toLowerCase().contains(key)) {
                ds.add(hd);
            }
        }
        reload(ds);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1500, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new BillHistoryGUI());
        f.setVisible(true);
    }
}