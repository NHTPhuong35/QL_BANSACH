/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ThongTinHDGUI extends JPanel {

    private JPanel pnControl, pnHD;
    private DefaultTableModel dtm;
    private JPanel btnThanhToan;
    private JTable table;
    private JButton btnHuy, btnLamMoi, btnBDF, btnTim;
    private JSpinner begin, end;

    public ThongTinHDGUI() {
        init();
        initComponents();
    }

    public void init() {
        this.setLayout(new BorderLayout());

        pnControl = new JPanel();
        pnControl.setLayout(new BoxLayout(pnControl, BoxLayout.X_AXIS));
        pnControl.setBackground(Color.WHITE);

        pnHD = new JPanel(new BorderLayout());
//        pnHD.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.add(pnControl, BorderLayout.NORTH);
        this.add(pnHD, BorderLayout.CENTER);
    }

    public void initComponents() {

        JPanel pnSreach = createPn("mã HD, mã NV, mã KH");

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

        pnTimkiem.add(pnSreach);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnBatdau);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(lblDen);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnKetthuc);
        pnTimkiem.add(Box.createRigidArea(new Dimension(20, 0)));
        pnTimkiem.add(pnBtnTim);
        pnTimkiem.setPreferredSize(new Dimension(600, 110));

        JPanel pnThaoTac = new JPanel();
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
        btnBDF = createBtnTT("In BDF", "btnBDF","/Image/bdf.png");

        pnThaoTac.add(btnHuy);
        pnThaoTac.add(Box.createRigidArea(new Dimension(20, 0)));
        pnThaoTac.add(btnBDF);

        pnControl.add(pnTimkiem);
        pnControl.add(Box.createRigidArea(new Dimension(50, 0)));
        pnControl.add(pnThaoTac);

        pnControl.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        Object[] colSP = {"Mã HD", "Mã nhân viên", "Mã khách hàng", "Thời gian", "Ngày", "Giảm giá", "Tổng tiền"};
        dtm = new DefaultTableModel(colSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dtm.addRow(new Object[]{"HD01", "NV01", "KH01", "10:10:2", "25/09/2024", "0", "990.999"});
        dtm.addRow(new Object[]{"HD02", "NV01", "KH01", "10:10:2", "25/09/2024", "0", "990.999"});
        dtm.addRow(new Object[]{"HD03", "NV01", "KH01", "10:10:2", "25/09/2024", "0", "990.999"});
        dtm.addRow(new Object[]{"HD04", "NV01", "KH01", "10:10:2", "25/09/2024", "0", "990.999"});

        table = new JTable(dtm);
        JScrollPane tableSPScr = new JScrollPane(table);
        styleTable(table);

        pnHD.add(tableSPScr, BorderLayout.CENTER);
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(BASE.color_table_heaer);
        header.setForeground(BASE.color_text);
        header.setFont(BASE.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(BASE.font);

//        table.setCellSelectionEnabled(true);
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private JPanel createPn(String txt) {
        JLabel lbl = new JLabel(txt);
        lbl.setFont(BASE.font_header);
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(150, 25));
        tf.setMaximumSize(new Dimension(350, 25));
        JPanel pn = new JPanel();
        pn.setBackground(Color.WHITE);
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        pn.add(lbl);
        pn.add(Box.createRigidArea(new Dimension(0, 10)));
        pn.add(tf);
        return pn;
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
//        btn.setFont();
        btn.setPreferredSize(new Dimension(90, 60));
        btn.setMaximumSize(new Dimension(90, 60));
        btn.setForeground(Color.BLACK);
        btn.setBackground(BASE.color_table_heaer);
        btn.setName(name);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Tải icon và điều chỉnh kích thước
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image image = icon.getImage(); // Chuyển đổi ImageIcon thành Image
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT); // Điều chỉnh kích thước
        btn.setIcon(new ImageIcon(scaledImage)); // Đặt icon mới vào nút

        // Đặt vị trí cho văn bản và icon
        btn.setHorizontalTextPosition(SwingConstants.CENTER); 
        btn.setVerticalTextPosition(SwingConstants.BOTTOM); // Icon nằm trên văn bản
        return btn;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ThongTinHDGUI());
        f.setVisible(true);
    }
}
