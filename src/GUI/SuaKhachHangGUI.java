/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SuaKhachHangGUI extends JFrame implements MouseListener {

    private JTextField tfTen, tfSdt;
    private JPanel btnXacNhan, btnHuy;
    private KhachHangGUI KHGUI;
    private KhachHangDTO khDTO;
    private String sdtGoc;

    public SuaKhachHangGUI(KhachHangDTO khDTO, KhachHangGUI KHGUI) {
        this.KHGUI = KHGUI;
        this.khDTO = khDTO;
        this.tfTen = new JTextField(khDTO.getTenKh());
        this.tfTen.setPreferredSize(new Dimension(300, 30));
        this.tfTen.setFont(BASE.font);
        this.tfTen.setMaximumSize(new Dimension(400, 30));
        this.tfSdt = new JTextField(khDTO.getSdt());
        this.tfSdt.setPreferredSize(new Dimension(300, 30));
        this.tfSdt.setFont(BASE.font);
        this.tfSdt.setMaximumSize(new Dimension(400, 30));
        this.sdtGoc = khDTO.getSdt();
        init();
    }

    public SuaKhachHangGUI() {
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(300, 185);
        setPreferredSize(new Dimension(300, 185));
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo tiêu đề giao diện
        JPanel titleGUI_wrap = new JPanel(new BorderLayout());
        titleGUI_wrap.setPreferredSize(new Dimension(360, 35));
        JLabel titleGUI = new JLabel("Sửa Khách hàng", JLabel.CENTER);
        titleGUI.setFont(BASE.font_header);
        titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
        titleGUI_wrap.setBackground(BASE.color_header_tbl);

        add(titleGUI_wrap, BorderLayout.NORTH);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(new EmptyBorder(10, 15, 0, 15));
        pnMain.setBackground(Color.WHITE);

        JPanel pnTenKH = new JPanel();
        pnTenKH.setBackground(Color.WHITE);
        pnTenKH.setLayout(new BoxLayout(pnTenKH, BoxLayout.X_AXIS));

        JLabel lbTen = new JLabel("Tên khách hàng:");
        lbTen.setFont(BASE.font);

//        tfTen = new JTextField();
//        tfTen.setPreferredSize(new Dimension(300, 30));
//        tfTen.setMaximumSize(new Dimension(400, 30));
        JPanel pnSDT = new JPanel();
        pnSDT.setBackground(Color.WHITE);
        pnSDT.setLayout(new BoxLayout(pnSDT, BoxLayout.X_AXIS));

        JLabel lbSdt = new JLabel("Số điện thoại:");
        lbSdt.setFont(BASE.font);

//        tfSdt = new JTextField();
//        tfSdt.setPreferredSize(new Dimension(300, 30));
//        tfSdt.setMaximumSize(new Dimension(300, 30));
        JPanel pnBTN = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnBTN.setBackground(Color.WHITE);
        btnXacNhan = new JPanel();
        btnXacNhan.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnXacNhan, "Xác nhận", "btnXacNhan", BASE.color_btXacNhan);
        btnXacNhan.addMouseListener(this);

        btnHuy = new JPanel();
        btnHuy.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnHuy, "Hủy", "btnHuy", BASE.color_btHuy);
        btnHuy.addMouseListener(this);

        pnTenKH.add(lbTen);
        pnTenKH.add(Box.createRigidArea(new Dimension(10, 10)));
        pnTenKH.add(tfTen);

        pnSDT.add(lbSdt);
        pnSDT.add(Box.createRigidArea(new Dimension(28, 10)));
        pnSDT.add(tfSdt);

        pnBTN.add(btnXacNhan);
        pnBTN.add(btnHuy);

        pnMain.add(pnTenKH);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        pnMain.add(pnSDT);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        pnMain.add(pnBTN);
        add(pnMain, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);  // Canh giữa màn hình
        setVisible(true);
    }

    private void cssBtn(JPanel b, String text, String name, Color color) {
        JLabel t = new JLabel(text, JLabel.CENTER);
//        t.setForeground(Color.WHITE);  // Đảm bảo chữ trắng để nổi bật
        b.setBackground(color);
        b.setName(name);
        b.add(t);
        b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
        b.setOpaque(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
                case "btnHuy":
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục thoát?", "Thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                    break;
                case "btnXacNhan":
                    String ten = tfTen.getText();
                    String sdt = tfSdt.getText();
                    khDTO.setTenKh(ten);
                    khDTO.setSdt(sdt);
                    KhachHangBUS busKH = new KhachHangBUS();
                    boolean success = true;
                    for (KhachHangDTO kh : busKH.getDs()) {
                        if (kh.getSdt().equals(khDTO.getSdt()) && !khDTO.getSdt().equals(sdtGoc)) {
                            success = false;
                            break;
                        }
                    }
                    if (success) {
                        KhachHangBUS khBUS = new KhachHangBUS();
                        if (khBUS.SuaKhachHang(khDTO)) {
                            KHGUI.EditRow(khDTO);
                            dispose();
                        }else{
                            new ShowDiaLog("Sửa khách hàng thất bại", ShowDiaLog.ERROR_DIALOG);
                        }
                    }else {
                        new ShowDiaLog("Số điện thoại đã tồn tại", ShowDiaLog.ERROR_DIALOG);
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
//        try {
//            JPanel btn = (JPanel) e.getSource();
//            btn.setBackground(BASE.color_table_heaer);
//            btn.setOpaque(true);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        try {
//            JPanel btn = (JPanel) e.getSource();
//            btn.setBackground(BASE.color_heaer);
//            btn.setOpaque(true);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    public static void main(String[] args) {
        new SuaKhachHangGUI();
    }
}
