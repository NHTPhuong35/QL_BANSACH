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
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CTHD extends JFrame implements MouseListener {

    JLabel lblNgayLap, lblMaHD, lblNV, lblKH, lblTongCong, lblGiamGia, lblThanhTien;
    private JPanel pnHeader, pnMain, pnFooter;
    private DefaultTableModel dtm;
    private JTable table;
    private JButton btnX;

    public CTHD() {
        this.setLayout(new BorderLayout());
        this.setSize(750, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);

        pnHeader = new JPanel();
        pnHeader.setBackground(BASE.color_heaer);
        pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.X_AXIS));
        pnHeader.setPreferredSize(new Dimension(0, 30));

        btnX = new JButton("X");
        btnX.setPreferredSize(new Dimension(50, 30));
        btnX.setBackground(Color.red);
        btnX.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnX.setOpaque(true);
        btnX.setFont(BASE.font_title);
        btnX.addMouseListener(this);

        pnHeader.add(Box.createHorizontalGlue());
        pnHeader.add(btnX);

        pnMain = new JPanel(new BorderLayout());
        JPanel pnInfo = new JPanel(new BorderLayout());

        JLabel lblHoaDon = new JLabel("Hóa đơn");
        lblHoaDon.setFont(new Font(BASE.typeface, Font.PLAIN, 50));
        lblHoaDon.setForeground(Color.decode("#2F7679"));

        lblNgayLap = new JLabel("Ngày lập:20/09/2024");
        lblNgayLap.setFont(BASE.font);
        lblMaHD = new JLabel("Mã hóa đơn: HD01");
        lblMaHD.setFont(BASE.font);

        JPanel pnInfoHD = new JPanel();
        pnInfoHD.setLayout(new BoxLayout(pnInfoHD, BoxLayout.Y_AXIS));
        pnInfoHD.add(lblHoaDon);
        pnInfoHD.add(lblNgayLap);
        pnInfoHD.add(lblMaHD);

        JPanel pnInfoPerson = new JPanel();
        pnInfoPerson.setLayout(new BoxLayout(pnInfoPerson, BoxLayout.X_AXIS));
        pnInfoPerson.setBorder(new EmptyBorder(20, 0, 20, 20));
        lblNV = new JLabel("Bình Yên");
        JPanel pnNv = createTitle("Người lập hóa đơn", lblNV);
        lblKH = new JLabel("Yên Tĩnh");
        JPanel pnKh = createTitle("Khách hàng", lblKH);
        pnInfoPerson.add(pnNv);
        pnInfoPerson.add(Box.createRigidArea(new Dimension(40, 0)));
        pnInfoPerson.add(pnKh);

        pnInfo.add(pnInfoHD, BorderLayout.NORTH);
        pnInfo.add(pnInfoPerson, BorderLayout.SOUTH);

        pnMain.add(pnInfo, BorderLayout.CENTER);
        pnMain.setBorder(new EmptyBorder(0, 20, 0, 20));

        JPanel pnTable = new JPanel(new BorderLayout());
        Object[] colSP = {"Tên sản phẩm", "Thể loại", "Số lượng", "Đơn giá", "Thành tiền"};
        dtm = new DefaultTableModel(colSP, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(dtm);
        JScrollPane tableSPScr = new JScrollPane(table);
        styleTable(table);
        pnTable.add(tableSPScr, BorderLayout.CENTER);

        lblTongCong = new JLabel("Tổng cộng: 0");
        StyleLable(lblTongCong);
        lblGiamGia = new JLabel("Giảm giá: 0");
        StyleLable(lblGiamGia);
        lblThanhTien = new JLabel("Thành tiền: 0");
        StyleLable(lblThanhTien);
        JPanel pnMoney = new JPanel();
        pnMoney.setLayout(new BoxLayout(pnMoney, BoxLayout.Y_AXIS));
        pnMoney.add(lblTongCong);
        pnMoney.add(lblGiamGia);
        pnMoney.add(lblThanhTien);

        pnMain.add(pnInfo, BorderLayout.NORTH);
        pnMain.add(pnTable, BorderLayout.CENTER);

        pnFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnFooter.setBorder(new EmptyBorder(0, 20, 0, 20));
        pnFooter.add(pnMoney);

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
        this.add(pnFooter, BorderLayout.SOUTH);
        this.setVisible(true);
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

    private JPanel createTitle(String title, JLabel lblName) {
        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font(BASE.typeface, Font.PLAIN, 18));
        lblTitle.setForeground(Color.decode("#2F7679"));
        lblName.setFont(BASE.font);

        pn.add(lblTitle);
        pn.add(lblName);

        return pn;
    }

    private JLabel StyleLable(JLabel lbl) {
        lbl.setFont(new Font(BASE.typeface, Font.PLAIN, 18));
        lbl.setForeground(Color.decode("#2F7679"));
        return lbl;
    }

    public static void main(String[] args) {
        new CTHD();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == btnX) { 
            dispose(); 
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
