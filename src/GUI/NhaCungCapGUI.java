package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhaCungCapGUI extends JPanel {


    public NhaCungCapGUI() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        btnADDNCC = new JButton();
        btnDELETENCC = new JButton();
        btnEDITNCC = new JButton();
        jLabel1 = new JLabel();
        txtsearch = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTableNCC = new JTable();

        // Nút Thêm NCC
        btnADDNCC.setBackground(new Color(102, 255, 102));
        btnADDNCC.setText("+ Thêm NCC");

        // Nút Xóa NCC
        btnDELETENCC.setBackground(new Color(255, 153, 153));
        btnDELETENCC.setText("+ Xóa NCC");

        // Nút Sửa NCC
        btnEDITNCC.setBackground(new Color(153, 153, 255));
        btnEDITNCC.setText("+ Sửa NCC");

        // Label Tìm kiếm
        jLabel1.setText("Tìm kiếm");

        // Cài đặt Layout cho panel chứa các nút
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setAutoCreateGaps(true);
        jPanel1Layout.setAutoCreateContainerGaps(true);

        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createSequentialGroup()
                .addComponent(btnADDNCC)
                .addComponent(btnEDITNCC)
                .addComponent(btnDELETENCC)
                .addComponent(jLabel1)
                .addComponent(txtsearch, 150, Short.MAX_VALUE, Short.MAX_VALUE) // Kích thưddớc ô tìm kiếm có thể thay đổi
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnADDNCC)
                .addComponent(btnEDITNCC)
                .addComponent(btnDELETENCC)
                .addComponent(jLabel1)
                .addComponent(txtsearch)
        );

        // Định nghĩa bảng JTable
        jTableNCC.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Mã NCC", "Tên NCC", "Địa Chỉ", "Email", "Số Điện Thoại"
            }
        ));
        jTableNCC.setRowHeight(25);
        jScrollPane1.setViewportView(jTableNCC);

        // Sử dụng BorderLayout để bảng có thể bao phủ hết formkjhghjjhggfdf
        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.NORTH);  // Đặt các nút ở phía trên
        add(jScrollPane1, BorderLayout.CENTER);  // Đặt bảng ở giữa và mở rộng hết cỡ

        // Loại bỏ kích thước cố định của JScrollPane
        jScrollPane1.setPreferredSize(null); // Xóa kích thước cố định
    }

 


    // Biến toàn cục
    private JButton btnADDNCC;
    private JButton btnDELETENCC;
    private JButton btnEDITNCC;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTableNCC;
    private JTextField txtsearch;
}