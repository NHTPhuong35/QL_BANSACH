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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ThongTinHDGUI extends JPanel {

    private JPanel pnControl, pnHD, pnThaoTac;
    private DefaultTableModel dtm;
    private JPanel btnThanhToan;
    private JTable table;
    private JButton btnHuy, btnLamMoi, btnBDF, btnTim;
    private JSpinner begin, end;
    private JTextField tfCode;
    private ArrayList<HoaDonDTO> DsHoaDon;

    public ThongTinHDGUI() {
        init();
        initComponents();
        HoaDonBUS bus = new HoaDonBUS();
        DsHoaDon = bus.getDsHD();
        Reload(DsHoaDon);
    }

    public void init() {
        this.setLayout(new BorderLayout());

        pnControl = new JPanel();
        pnControl.setLayout(new BoxLayout(pnControl, BoxLayout.X_AXIS));
        pnControl.setBackground(Color.WHITE);

        pnHD = new JPanel(new BorderLayout());

        this.add(pnControl, BorderLayout.NORTH);
        this.add(pnHD, BorderLayout.CENTER);
    }

    public void initComponents() {

        JLabel lblCode = new JLabel("mã HD, mã NV, mã KH");
        lblCode.setFont(BASE.font_header);
        tfCode = new JTextField();
        tfCode.setPreferredSize(new Dimension(150, 25));
        tfCode.setMaximumSize(new Dimension(350, 25));
        JPanel pnCode = new JPanel();
        pnCode.setBackground(Color.WHITE);
        pnCode.setLayout(new BoxLayout(pnCode, BoxLayout.Y_AXIS));
        pnCode.add(lblCode);
        pnCode.add(Box.createRigidArea(new Dimension(0, 10)));
        pnCode.add(tfCode);

        JLabel lblbegin = new JLabel("Ngày bắt đầu");
        lblbegin.setFont(BASE.font_header);
        begin = new JSpinner(new SpinnerDateModel());
        begin.setPreferredSize(new Dimension(110, 28));
        begin.setFont(BASE.font);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(begin, "dd/MM/yyyy");
        begin.setEditor(startDateEditor);
        begin.setValue(new java.util.Date());

        JPanel pnBatdau = new JPanel();
        pnBatdau.setBackground(Color.WHITE);
        pnBatdau.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 0); // Khoảng cách giữa các thành phần

        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        pnBatdau.add(lblbegin, gbc);

        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 1; // Hàng thứ hai
        gbc.anchor = GridBagConstraints.WEST; // Căn lề trái
        pnBatdau.add(begin, gbc);

        JLabel lblDen = new JLabel("Đến");
        lblDen.setFont(BASE.font_header);

        // Tạo JPanel cho ngày kết thúc
        JLabel lblend = new JLabel("Ngày kết thúc");
        lblend.setFont(BASE.font_header);
        end = new JSpinner(new SpinnerDateModel());
        end.setPreferredSize(new Dimension(110, 28));
        end.setFont(BASE.font);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(end, "dd/MM/yyyy");
        end.setEditor(endDateEditor);
        end.setValue(new java.util.Date());

        JPanel pnKetthuc = new JPanel();
        pnKetthuc.setBackground(Color.WHITE);
        pnKetthuc.setLayout(new GridBagLayout());

        GridBagConstraints gbcEnd = new GridBagConstraints();
        gbcEnd.insets = new Insets(0, 0, 10, 0);

        gbcEnd.gridx = 0;
        gbcEnd.gridy = 0;
        gbcEnd.anchor = GridBagConstraints.WEST;
        pnKetthuc.add(lblend, gbcEnd);

        gbcEnd.gridx = 0;
        gbcEnd.gridy = 1;
        gbcEnd.anchor = GridBagConstraints.WEST;
        pnKetthuc.add(end, gbcEnd);

        JPanel pnBtnTim = new JPanel();
        pnBtnTim.setBackground(Color.WHITE);
        pnBtnTim.setLayout(new BoxLayout(pnBtnTim, BoxLayout.Y_AXIS));
        btnTim = createBtn("Tìm", "btnTim");
        btnLamMoi = createBtn("Làm mới", "btnLamMoi");
        pnBtnTim.add(btnTim);
        pnBtnTim.add(Box.createRigidArea(new Dimension(0, 10)));
        pnBtnTim.add(btnLamMoi);

        JPanel pnTimkiem = new JPanel();
        pnTimkiem.setPreferredSize(new Dimension(600, 200));
        pnTimkiem.setMaximumSize(new Dimension(600, 200));
        pnTimkiem.setBackground(Color.WHITE);
        pnTimkiem.setLayout(new BoxLayout(pnTimkiem, BoxLayout.X_AXIS));
        pnTimkiem.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Tìm kiếm",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                BASE.font_title,
                BASE.color_text
        ));

        pnTimkiem.add(pnCode);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnBatdau);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(lblDen);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnKetthuc);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnBtnTim);
        pnTimkiem.setPreferredSize(new Dimension(600, 110));

        pnThaoTac = new JPanel();
        pnThaoTac.setBackground(Color.WHITE);
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));
        pnThaoTac.setPreferredSize(new Dimension(240, 110));
        pnThaoTac.setMaximumSize(new Dimension(240, 110));
        pnThaoTac.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Thao tác",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                BASE.font_title,
                BASE.color_text
        ));
        btnHuy = createBtnTT("Huỷ HD", "btnHuy", "/Image/cancel.png");
        btnBDF = createBtnTT("In BDF", "btnBDF", "/Image/bdf.png");

//        pnThaoTac.add(btnHuy);
//        pnThaoTac.add(Box.createRigidArea(new Dimension(20, 0)));
        pnThaoTac.add(btnBDF);

        pnControl.add(pnTimkiem);
        pnControl.add(Box.createRigidArea(new Dimension(50, 0)));
        pnControl.add(pnThaoTac);

        pnControl.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        Object[] colSP = {"Mã HD", "Mã nhân viên", "Mã khách hàng", "Thời gian", "Ngày", "Giảm giá", "Tổng tiền", "Trạng Thái"};
        dtm = new DefaultTableModel(colSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(dtm);
        JScrollPane tableSPScr = new JScrollPane(table);
        styleTable(table);

        pnHD.add(tableSPScr, BorderLayout.CENTER);

        tfCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByDate();
            }
        });

        begin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Date startDate = (Date) begin.getValue();
                Date endDate = (Date) end.getValue();
                if (startDate.after(endDate)) {
                    // Nếu ngày bắt đầu lớn hơn ngày kết thúc, thiết lập lại ngày kết thúc bằng ngày bắt đầu
                    end.setValue(startDate);
                }
            }
        });

        end.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Date startDate = (Date) begin.getValue();
                Date endDate = (Date) end.getValue();
                if (endDate.before(startDate)) {
                    end.setValue(startDate);
                }
            }
        });

        btnLamMoi.addActionListener(e -> {
            Date currentDate = new Date();
            begin.setValue(currentDate);
            end.setValue(currentDate);

            tfCode.setText("");
            HoaDonBUS bus = new HoaDonBUS();
            Reload(bus.getDsHD());
        });

        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy ngày hiện tại
                Date currentDate = new Date();
                // Lấy hàng được chọn trong bảng
                int selectedRow = table.getSelectedRow();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRow != -1) {
                    // Lấy thông tin ngày hóa đơn từ bảng
                    String ngayHDStr = dtm.getValueAt(selectedRow, 4).toString();
                    Date invoiceDate = convertStringToDate(ngayHDStr);
                    if (isSameDay(currentDate, ngayHDStr)) {
                        HoaDonBUS bus = new HoaDonBUS();
                        String soHD = dtm.getValueAt(selectedRow, 0).toString();
                        
                        if (bus.CapNhatTrangThaiHD(soHD)) {
                            ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS(soHD);
                            ArrayList<ChiTietHoaDonDTO> dsctHD = cthdBUS.getDscthd();
                            HoaDonDTO hdDTO = bus.getHD(soHD);
                            EditStatus(hdDTO);
                            for (ChiTietHoaDonDTO ct : dsctHD) {
                                SanPhamBUS spBUS = new SanPhamBUS();
                                SanPhamDTO spDTO = spBUS.getSP(ct.getMaSach());
                                int sl = spDTO.getSoLuong() + ct.getSoLuong();
                                spBUS.CapNhatSoLuongSP(spDTO.getMaSach(), sl);
                            }  
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Chỉ được phép hủy hóa đơn trong Ngày");
                    }
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());

                    if (row != -1) { // Kiểm tra dòng có tồn tại
                        String soHD = table.getValueAt(row, 0) + "";
                        String maKH = table.getValueAt(row, 2) + "";
                        String tenDN = table.getValueAt(row, 1) + "";
                        String tGian = table.getValueAt(row, 3) + "";
                        String ngayHD = table.getValueAt(row, 4) + "";
                        double tienGiamGia = Double.parseDouble(table.getValueAt(row, 5) + "");
                        double tongTien = Double.parseDouble(table.getValueAt(row, 6) + "");

                        HoaDonDTO hd = new HoaDonDTO(soHD, maKH, tenDN, tGian, ngayHD, tienGiamGia, tongTien);

                        new CTHoaDon(hd);
                    }
                }
            }
        });

    }
    
    public JButton getBtnHuy(){
        return btnHuy;
    }
    
    public JPanel getPnThaoTac(){
        return pnThaoTac;
    }
    private void Reload(ArrayList<HoaDonDTO> ds) {

        dtm.setRowCount(0);
        for (HoaDonDTO hd : ds) {
            String TrangThai = hd.getTrangThai() == 1 ? "Đã xác nhận" : "Hủy";
            String NgayHD = convertDate(hd.getNgayHD());
            dtm.addRow(new Object[]{hd.getSoHD(), hd.getTenDN(), hd.getMaKH(), hd.getTGian(), NgayHD, hd.getTienGiamGia(), hd.getTongTien(), TrangThai});
        }
    }

    private void filterTable() {
        String key = tfCode.getText().toLowerCase();
        HoaDonBUS bus = new HoaDonBUS();
        ArrayList<HoaDonDTO> ds = new ArrayList<>();
        for (HoaDonDTO hd : DsHoaDon) {
            if (hd.getSoHD().toLowerCase().contains(key) || hd.getMaKH().toLowerCase().contains(key) || hd.getTenDN().toLowerCase().contains(key)) {
                ds.add(hd);
            }
        }
        Reload(ds);
    }

    private void searchByDate() {
        Date startDate = (Date) begin.getValue();
        Date endDate = (Date) end.getValue();
        String key = tfCode.getText().toLowerCase();

        // Đảm bảo rằng thời gian của ngày bắt đầu và ngày kết thúc được đặt về đầu ngày và cuối ngày
        startDate = resetTimeToStartOfDay(startDate);
        endDate = resetTimeToEndOfDay(endDate);

        ArrayList<HoaDonDTO> filteredList = new ArrayList<>();

        for (HoaDonDTO hd : DsHoaDon) {
            Date invoiceDate = convertStringToDate(hd.getNgayHD());

            // Kiểm tra xem ngày hóa đơn có nằm trong khoảng không
            boolean dateInRange = (invoiceDate.compareTo(startDate) >= 0) && (invoiceDate.compareTo(endDate) <= 0);
            boolean matchesKey = hd.getSoHD().toLowerCase().contains(key)
                    || hd.getMaKH().toLowerCase().contains(key)
                    || hd.getTenDN().toLowerCase().contains(key);

            // Kiểm tra nếu ngày hóa đơn nằm trong khoảng và từ khóa tìm kiếm có khớp
            if (dateInRange && (key.isEmpty() || matchesKey)) {
                filteredList.add(hd);
            }
        }

        Reload(filteredList);
    }

    private Date resetTimeToStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date resetTimeToEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        calendar.set(java.util.Calendar.MINUTE, 59);
        calendar.set(java.util.Calendar.SECOND, 59);
        calendar.set(java.util.Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public String convertDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        inputFormat.setLenient(false); // Đặt chế độ không khoan nhượng để kiểm tra ngày hợp lệ
        outputFormat.setLenient(false);

        try {
            // Kiểm tra định dạng đầu vào
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) { // yyyy/MM/dd
                Date parsedDate = inputFormat.parse(date);
                return outputFormat.format(parsedDate); // Chuyển sang dd/MM/yyyy
            } else if (date.matches("\\d{2}-\\d{2}-\\d{4}")) { // dd/MM/yyyy
                Date parsedDate = outputFormat.parse(date);
                return inputFormat.format(parsedDate); // Chuyển sang yyyy/MM/dd
            } else {
                throw new IllegalArgumentException("Ngày không hợp lệ! Vui lòng nhập đúng định dạng.");
            }
        } catch (ParseException e) {
            // Thông báo lỗi cho người dùng
            System.err.println("Ngày không hợp lệ: " + date + ". Vui lòng kiểm tra lại.");
            return null; // Trả về null nếu có lỗi
        } catch (IllegalArgumentException e) {
            // Thông báo lỗi cho người dùng
            System.err.println(e.getMessage());
            return null; // Trả về null nếu có lỗi
        }
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isSameDay(Date date1, String date2Str) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng ngày theo dạng dd-MM-yyyy
        try {
            Date date2 = sdf.parse(date2Str); // Chuyển đổi chuỗi thành đối tượng Date
            return sdf.format(date1).equals(sdf.format(date2)); // So sánh hai ngày đã định dạng
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi khi chuyển đổi, trả về false
        }
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
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setForeground(Color.BLACK);
        btn.setBackground(BASE.color_table_heaer);
        btn.setName(name);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    private JButton createBtnTT(String txt, String name, String iconPath) {
        JButton btn = new JButton(txt);
        btn.setPreferredSize(new Dimension(90, 60));
        btn.setMaximumSize(new Dimension(90, 60));
        btn.setForeground(Color.BLACK);
        btn.setBackground(BASE.color_table_heaer);
        btn.setName(name);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image image = icon.getImage(); // Chuyển đổi ImageIcon thành Image
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT); // Điều chỉnh kích thước
        btn.setIcon(new ImageIcon(scaledImage)); // Đặt icon mới vào nút

        // Đặt vị trí cho văn bản và icon
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM); // Icon nằm trên văn bản
        return btn;
    }

    public void EditStatus(HoaDonDTO hd) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(hd.getSoHD())) {
                String TrangThai = (hd.getTrangThai() == 1) ? "Đã xác nhận" : "Hủy";
                String NgayHD = convertDate(hd.getNgayHD());
                dtm.setValueAt(hd.getTenDN(), i, 1);
                dtm.setValueAt(hd.getMaKH(), i, 2);
                dtm.setValueAt(hd.getTGian(), i, 3);
                dtm.setValueAt(NgayHD, i, 4);
                dtm.setValueAt(hd.getTienGiamGia(), i, 5);
                dtm.setValueAt(hd.getTongTien(), i, 6);
                dtm.setValueAt(TrangThai, i, 7);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ThongTinHDGUI());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
