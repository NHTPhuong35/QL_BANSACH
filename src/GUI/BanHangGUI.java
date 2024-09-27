/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class BanHangGUI extends JPanel {

    private JPanel pnThongTinSP, pnThanhToan, pnDanhSachSp;
    private DefaultTableModel dtmSP;
    private JPanel btnThanhToan;
    private JTable tableSP;
    private JButton btnThemSp;

    public BanHangGUI() {
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
        JTextField tfMaKH, tfMaNV, tfGiamGia, tfTongCong, tfTongHD;

        // Tạo JPanel để chứa lblMaKH, tfMaKH và btnTaoKH
        JPanel pnMaKH = new JPanel(new FlowLayout(FlowLayout.LEFT));

        lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setFont(BASE.font);
        tfMaKH = new JTextField(15);
        tfMaKH.setFont(BASE.font);
        tfMaKH.setPreferredSize(new Dimension(tfMaKH.getPreferredSize().width, 30));

        JButton btnTaoKH = new JButton("Tạo mới");
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
        gbc.gridy = 2;
        inputPn.add(tfMaNV, gbc);

        lblTongCong = new JLabel("Tổng cộng");
        lblTongCong.setFont(BASE.font);
        gbc.gridy = 3;
        inputPn.add(lblTongCong, gbc);

        tfTongCong = new JTextField();
        tfTongCong.setFont(BASE.font);
        tfTongCong.setEditable(false);
        tfTongCong.setPreferredSize(new Dimension(tfTongCong.getPreferredSize().width, 30));
        gbc.gridy = 4;
        inputPn.add(tfTongCong, gbc);

        lblGiamGia = new JLabel("Giảm giá");
        lblGiamGia.setFont(BASE.font);
        gbc.gridy = 5;
        inputPn.add(lblGiamGia, gbc);

        tfGiamGia = new JTextField();
        tfGiamGia.setFont(BASE.font);
        tfGiamGia.setEditable(false);
        tfGiamGia.setPreferredSize(new Dimension(tfGiamGia.getPreferredSize().width, 30));
        gbc.gridy = 6;
        inputPn.add(tfGiamGia, gbc);

        lblTongHD = new JLabel("Tổng hóa đơn");
        lblTongHD.setFont(BASE.font);
        gbc.gridy = 7;
        inputPn.add(lblTongHD, gbc);

        tfTongHD = new JTextField();
        tfTongHD.setFont(BASE.font);
        tfTongHD.setEditable(false);
        tfTongHD.setPreferredSize(new Dimension(tfTongHD.getPreferredSize().width, 30));
        gbc.gridy = 8;
        inputPn.add(tfTongHD, gbc);

        btnThanhToan = new JPanel();
        styleBtn(btnThanhToan, "Thanh Toán Hóa Đơn", "btnThanhToan");
        gbc.gridy = 9;
        inputPn.add(btnThanhToan, gbc);

        pnThanhToan.add(inputPn, BorderLayout.NORTH);
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

        String[] columnNames = {"Tên sản phẩm", "Số lượng", "Thành tiền", ""};
        dtmSP = new DefaultTableModel(columnNames, 0);
        tableSP = new JTable(dtmSP) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        styleTable(tableSP);

        tableSP.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tableSP);
        panel.add(scrollPane, BorderLayout.CENTER);

        tableSP.getColumnModel().getColumn(3).setCellRenderer(new DeleteButtonRenderer());

        // Thêm dữ liệu mẫu
        addSampleData();

        return panel;
    }

    private void addSampleData() {
        // Thêm sản phẩm mẫu
        addProduct("Sản phẩm 1", 10, 100.0);
        addProduct("Sản phẩm 2", 5, 150.0);
    }

    private void addProduct(String name, int quantity, double price) {
        Object[] rowData = {name, quantity, price, createDeleteButton()}; // Thêm nút xóa
        dtmSP.addRow(rowData);
    }

    private JButton createDeleteButton() {
        JButton deleteButton = new JButton("Xóa");
        deleteButton.addActionListener(e -> {
            int row = tableSP.getSelectedRow();
            if (row != -1) {
                dtmSP.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa.");
            }
        });
        return deleteButton;
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

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new BanHangGUI());
        f.setVisible(true);
    }
}
