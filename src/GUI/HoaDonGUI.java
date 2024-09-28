/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HoaDonGUI extends JPanel {

    private JPanel pnHeader, pnMain;

    public HoaDonGUI() {
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 600));

        pnHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel pnBanHang = new JPanel(new BorderLayout());
        pnBanHang.setBackground(BASE.color_heaer);
        pnBanHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnBanHang.setPreferredSize(new Dimension(150, 30));
        pnBanHang.setMaximumSize(new Dimension(150, 30));
        JLabel lblBanHang = new JLabel("Bán hàng", JLabel.CENTER);
        lblBanHang.setFont(BASE.font);
        pnBanHang.add(lblBanHang, BorderLayout.CENTER);

        JPanel pnHD = new JPanel(new BorderLayout());
        pnHD.setBackground(BASE.color_heaer);
        pnHD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnHD.setPreferredSize(new Dimension(150, 30));
        pnHD.setMaximumSize(new Dimension(150, 30));
        JLabel lblHD = new JLabel("Hóa đơn", JLabel.CENTER);
        lblHD.setFont(BASE.font);
        pnHD.add(lblHD, BorderLayout.CENTER);

        pnHeader.add(pnBanHang);
        pnHeader.add(pnHD);

        CardLayout cardLayout = new CardLayout();
        JPanel banHang = new BanHangGUI();
        JPanel thongTinHD = new ThongTinHDGUI();

        pnMain = new JPanel(cardLayout);
        pnMain.add(banHang, "banhang");
        pnMain.add(thongTinHD, "thongtinhhd");

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
        this.setVisible(true);

        pnBanHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(pnMain, "banhang");
            }
        });

        pnHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(pnMain, "thongtinhhd");
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new HoaDonGUI());
        f.setVisible(true);
    }
}
