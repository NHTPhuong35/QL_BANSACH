/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.TaiKhoanBUS;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class ChucNangTaiKhoanGUI extends JFrame implements MouseListener {

    private JLabel lblHeader;
    private JPanel pnContent, pnThaoTac;
    private JPanel[] pnThuocTinh = new JPanel[10];
    private JTextField txtTenNV, txtDiaChi, txtSDT, txtEmail;
    private JPasswordField txtMatKhau, txtNhapLaiMK;
    private JComboBox<QuyenDTO> cbxQuyen;
    private JButton btnXacNhan, btnHuy, btnLuu;

    private ArrayList<QuyenDTO> dsQuyen;
    private TaiKhoanGUI tkGUI;
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    private int width = 400, height = 500;
    private int width_row = 200, height_row = 30;

    public ChucNangTaiKhoanGUI(TaiKhoanGUI tkGUI) {
        this.tkGUI = tkGUI;
        dsQuyen = new ArrayList<>();
        dsQuyen.add(new QuyenDTO("Admin", "Admin"));
        dsQuyen.add(new QuyenDTO("QL", "Quản lý"));
        dsQuyen.add(new QuyenDTO("QNV", "Nhân Viên"));
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // Sửa lại để dùng getContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
//        this.setUndecorated(true);

        lblHeader = new JLabel("Thêm tài khoản", JLabel.CENTER);
        lblHeader.setPreferredSize(new Dimension(width, 36));
        lblHeader.setMaximumSize(new Dimension(width, 36));
        lblHeader.setFont(BASE.font_title);
        lblHeader.setBackground(BASE.color_heaer);
        lblHeader.setOpaque(true);
        lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] data = new String[]{"Tên nhân viên", "Quyền", "Địa chỉ", "Số điện thoại", "Email", "Mật khẩu", "Nhập lại mật khẩu"};
        for (int i = 0; i < data.length; i++) {
            JLabel lbl = new JLabel(data[i], JLabel.LEFT);
            lbl.setFont(BASE.font_header);
            pnThuocTinh[i] = new JPanel();
            pnThuocTinh[i].setPreferredSize(new Dimension(300, height_row));
            pnThuocTinh[i].setMaximumSize(new Dimension(300, height_row));
            pnThuocTinh[i].setLayout(new GridLayout(1, 1, 10, 10));
            pnThuocTinh[i].add(lbl);
        }
        txtTenNV = new JTextField(); //Tên nhân viên
        txtTenNV.setPreferredSize(new Dimension(width_row, height_row));
        txtTenNV.setMaximumSize(new Dimension(width_row, height_row));
        txtTenNV.setFont(BASE.font);

        cbxQuyen = new JComboBox<>(); //Quyền
        cbxQuyen.setPreferredSize(new Dimension(width_row, height_row));
        cbxQuyen.setMaximumSize(new Dimension(width_row, height_row));
        cbxQuyen.setFont(BASE.font);
        for (int i = 0; i < dsQuyen.size(); i++) {
            cbxQuyen.addItem(dsQuyen.get(i));
        }

        txtDiaChi = new JTextField(); //Địa chỉ
        txtDiaChi.setPreferredSize(new Dimension(width_row, height_row));
        txtDiaChi.setMaximumSize(new Dimension(width_row, height_row));
        txtDiaChi.setFont(BASE.font);

        txtSDT = new JTextField(); //Số điện thoại
        txtSDT.setPreferredSize(new Dimension(width_row, height_row));
        txtSDT.setMaximumSize(new Dimension(width_row, height_row));
        txtSDT.setFont(BASE.font);

        txtEmail = new JTextField(); //Số điện thoại
        txtEmail.setPreferredSize(new Dimension(width_row, height_row));
        txtEmail.setMaximumSize(new Dimension(width_row, height_row));
        txtEmail.setFont(BASE.font);

        txtMatKhau = new JPasswordField(); //Mật khẩu
        txtMatKhau.setPreferredSize(new Dimension(width_row, height_row));
        txtMatKhau.setMaximumSize(new Dimension(width_row, height_row));
        txtMatKhau.setFont(BASE.font);

        txtNhapLaiMK = new JPasswordField(); //Nhập lại mật khẩu
        txtNhapLaiMK.setPreferredSize(new Dimension(width_row, height_row));
        txtNhapLaiMK.setMaximumSize(new Dimension(width_row, height_row));
        txtNhapLaiMK.setFont(BASE.font);

        pnThuocTinh[0].add(txtTenNV); //Tên nhân viên
        pnThuocTinh[1].add(cbxQuyen); //Quyền
        pnThuocTinh[2].add(txtDiaChi);
        pnThuocTinh[3].add(txtSDT);
        pnThuocTinh[4].add(txtEmail);
        pnThuocTinh[5].add(txtMatKhau);
        pnThuocTinh[6].add(txtNhapLaiMK);

        //-------------------- các nút xác nhận, lưu, huỷ --------------------------
        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setBackground(BASE.color_heaer);
        btnXacNhan.setOpaque(true);
        btnXacNhan.setFocusPainted(false);
        btnXacNhan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXacNhan.addMouseListener(this);

        btnHuy = new JButton("Huỷ");
        btnHuy.setBackground(BASE.color_heaer);
        btnHuy.setOpaque(true);
        btnHuy.setFocusPainted(false);
        btnHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuy.addMouseListener(this);

        btnLuu = new JButton("Lưu");
        btnLuu.setBackground(BASE.color_heaer);
        btnLuu.setOpaque(true);
        btnLuu.setFocusPainted(false);
        btnLuu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLuu.addMouseListener(this);

        pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));
        pnThaoTac.add(Box.createRigidArea(new Dimension(150, 0)));

        this.add(lblHeader);
        for (int i = 0; i < data.length; i++) {
            this.add(Box.createVerticalStrut(20));
            this.add(pnThuocTinh[i]);
        }
        this.add(Box.createVerticalStrut(20));

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void initAdd() {
        lblHeader.setText("Thêm tài khoản");
        pnThaoTac.add(btnXacNhan);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(btnHuy);
        this.add(pnThaoTac);
        this.add(Box.createVerticalStrut(20));
        setVisible(true);
    }

    public void initEdit() {
        this.setSize(new Dimension(width, 450));
        lblHeader.setText("Sửa tài khoản");
        pnThuocTinh[1].setVisible(false);
        pnThuocTinh[6].setVisible(false);
        pnThaoTac.add(btnLuu);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(btnHuy);
        this.add(pnThaoTac);
        this.add(Box.createVerticalStrut(20));

        txtTenNV.setText(tkGUI.selectedTK.getTenNV());
        txtDiaChi.setText(tkGUI.selectedTK.getDiaChi());
        txtSDT.setText(tkGUI.selectedTK.getSDT());
        txtEmail.setText(tkGUI.selectedTK.getEmail());
        txtMatKhau.setText(tkGUI.selectedTK.getMatKhau());
    }

    public void addTK() {
        if (!xuLyKiemTraTenNV(txtTenNV.getText())) {
            return;
        }
        if (!xuLyKiemTraDiaChi(txtDiaChi.getText())) {
            return;
        }
        if(!xuLyKiemTraSDT(txtSDT.getText())){
            return;
        }
        if(!xuLyKiemTraEmail(txtEmail.getText())){
            return;
        }
        if(!xuLyKiemTraMatKhau(new String(txtMatKhau.getPassword()))){
            return;
        }
        if(!txtNhapLaiMK.equals(txtMatKhau)){
            new ShowDiaLog("<html>Mật khẩu nhập lại phải giống với mật khẩu!</html>", ShowDiaLog.ERROR_DIALOG);
            return;
        }
        QuyenDTO selectedQuyen = (QuyenDTO) cbxQuyen.getSelectedItem();
        TaiKhoanDTO tk = new TaiKhoanDTO("", txtTenNV.getText(),
                txtDiaChi.getText(), txtSDT.getText(), txtEmail.getText(),
                new String(txtMatKhau.getPassword()), selectedQuyen, 1);
        this.dispose();
        tkGUI.themTaiKhoan(tk);
    }

    public void editTK() {
        if (!xuLyKiemTraTenNV(txtTenNV.getText())) {
            return;
        }
        if (!xuLyKiemTraDiaChi(txtDiaChi.getText())) {
            return;
        }
        if(!xuLyKiemTraSDT(txtSDT.getText())){
            return;
        }
        if(!xuLyKiemTraEmail(txtEmail.getText())){
            return;
        }
        if(!xuLyKiemTraMatKhau(new String(txtMatKhau.getPassword()))){
            return;
        }
        TaiKhoanDTO tk = new TaiKhoanDTO(tkGUI.selectedTK.getTenDN(), txtTenNV.getText(),
                txtDiaChi.getText(), txtSDT.getText(), txtEmail.getText(),
                new String(txtMatKhau.getPassword()), tkGUI.selectedTK.getQuyen(), 1);
        this.dispose();
        tkGUI.suaTaiKhoan(tk);
    }

    //----------------------------Các hàm check dữ liệu--------------------------
    public boolean xuLyKiemTraTenNV(String tenNV) {
        String ketQua = tkBUS.kiemTraTenNV(tenNV);
        if (!ketQua.equals("Hợp lệ")) {
            new ShowDiaLog(ketQua, ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    public boolean xuLyKiemTraDiaChi(String diaChi) {
        String ketQua = tkBUS.kiemTraDiaChi(diaChi);
        if (!ketQua.equals("Hợp lệ")) {
            new ShowDiaLog(ketQua, ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    public boolean xuLyKiemTraSDT(String SDT) {
        String ketQua = tkBUS.kiemTraSoDienThoai(SDT);
        if (!ketQua.equals("Hợp lệ")) {
            new ShowDiaLog(ketQua, ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    public boolean xuLyKiemTraEmail(String email) {
        String ketQua = tkBUS.kiemTraEmail(email);
        if (!ketQua.equals("Hợp lệ")) {
            new ShowDiaLog(ketQua, ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    public boolean xuLyKiemTraMatKhau(String matKhau) {
        String ketQua = tkBUS.kiemTraMatKhau(matKhau);
        if (!ketQua.equals("Hợp lệ")) {
            new ShowDiaLog(ketQua, ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == btnXacNhan) {
            tkGUI.selectedTK = new TaiKhoanDTO();
            addTK();
        }
        if (btn == btnHuy) {
            tkGUI.selectedTK = new TaiKhoanDTO();
            this.dispose();
        }
        if (btn == btnLuu) {
            editTK();
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
