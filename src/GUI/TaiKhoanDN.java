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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TaiKhoanDN extends JPanel {

    private JPanel pnLeft, pnRight;
    private JTextField[] txtContentLeft;
    private JPasswordField[] txtContentRight;
    private JButton btnSua, btnDoiMK;
    int width = 500, height = 750;

    private TaiKhoanDTO tkUSER;
    private TaiKhoanBUS tkBUS = new TaiKhoanBUS();

    public TaiKhoanDN(TaiKhoanDTO tkUSER) {
        this.tkUSER = tkUSER;
        init();
        setData();
    }

    private void init() {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(1, 2));

        pnLeft = new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
        JLabel lblHeadLeft = new JLabel("Thông tin người dùng");
        lblHeadLeft.setPreferredSize(new Dimension(width - 60, 36));
        lblHeadLeft.setMaximumSize(new Dimension(width - 60, 36));
        lblHeadLeft.setFont(BASE.font_title);
        lblHeadLeft.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Thứ tự {0: Tên đăng nhập, 1: Tên người dùng, 2: Địa chỉ, 3: Số điện thoại, 4: Email}
        String[] textLableLeft = {"Tên đăng nhập", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Email"}; //Nội dung JLable
        JPanel[] pnContentLeft = new JPanel[textLableLeft.length]; //JPanel cho từng trường thông tin

        txtContentLeft = new JTextField[textLableLeft.length]; //JTextField cho từng nội dung
        for (int i = 0; i < pnContentLeft.length; i++) {
            JLabel lbl = new JLabel(textLableLeft[i]);
            lbl.setFont(BASE.font_header);

            txtContentLeft[i] = new JTextField();
            txtContentLeft[i].setPreferredSize(new Dimension(200, 30));
            txtContentLeft[i].setMaximumSize(new Dimension(200, 30));
            txtContentLeft[i].setFont(BASE.font);

            pnContentLeft[i] = new JPanel();
            pnContentLeft[i].setMaximumSize(new Dimension(width - 60, 30));
            pnContentLeft[i].setLayout(new GridLayout(1, 2));

            pnContentLeft[i].add(lbl);
            pnContentLeft[i].add(txtContentLeft[i]);
        }
        txtContentLeft[0].setEditable(false);
        btnSua = new JButton("Sửa");
        btnSua.setFocusPainted(false);
        btnSua.setBackground(BASE.color_heaer);
        btnSua.setOpaque(true);
        btnSua.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tenDN = txtContentLeft[0].getText();
                String tenND = txtContentLeft[1].getText();
                String diaChi = txtContentLeft[2].getText();
                String sdt = txtContentLeft[3].getText();
                String email = txtContentLeft[4].getText();

                if (!xuLyKiemTraTenNV(tenND)) {
                    txtContentLeft[1].setText(tkUSER.getTenNV());
                    return;
                }
                if (!xuLyKiemTraDiaChi(diaChi)) {
                    txtContentLeft[2].setText(tkUSER.getDiaChi());
                    return;
                }
                if (!sdt.equals(tkUSER.getSDT())) {
                    if (!xuLyKiemTraSDT(sdt)) {
                        txtContentLeft[3].setText(tkUSER.getSDT());
                        return;
                    }
                }
                if (!xuLyKiemTraEmail(email)) {
                    txtContentLeft[4].setText(tkUSER.getEmail());
                    return;
                }
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn với thông tin muốn sửa không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    TaiKhoanDTO tk = new TaiKhoanDTO(tenDN, tenND, diaChi, sdt, email, tkUSER.getMatKhau(), tkUSER.getQuyen(), 1);
                    tkBUS.set(tk);
                    new ShowDiaLog("<html>Sửa thông tin thành công!", ShowDiaLog.SUCCESS_DIALOG);
                } else {
                    setData();
                }
            }
        });

        pnLeft.add(Box.createVerticalStrut(20));
        pnLeft.add(lblHeadLeft);
        for (int i = 0; i < pnContentLeft.length; i++) {
            pnLeft.add(Box.createVerticalStrut(20));
            pnLeft.add(pnContentLeft[i]);
        }
        pnLeft.add(Box.createVerticalStrut(20));
        pnLeft.add(btnSua);

        pnRight = new JPanel();
        pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
        JLabel lblHeadRight = new JLabel("Đổi mật khẩu người dùng");
        lblHeadRight.setPreferredSize(new Dimension(width - 60, 36));
        lblHeadRight.setMaximumSize(new Dimension(width - 60, 36));
        lblHeadRight.setFont(BASE.font_title);
        lblHeadRight.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Thứ tự {0: Nhập mật khẩu cũ, 1: Nhập mật khẩu mới, 2: Nhập lại}
        String[] textLableRight = {"Nhập mật khẩu cũ", "Nhập mật khẩu mới", "Nhập lại"};
        JPanel[] pnContentRight = new JPanel[textLableRight.length];

        txtContentRight = new JPasswordField[textLableRight.length];
        for (int i = 0; i < pnContentRight.length; i++) {
            JLabel lbl = new JLabel(textLableRight[i]);
            lbl.setFont(BASE.font_header);

            txtContentRight[i] = new JPasswordField();
            txtContentRight[i].setPreferredSize(new Dimension(200, 30));
            txtContentRight[i].setMaximumSize(new Dimension(200, 30));
            txtContentRight[i].setFont(BASE.font);

            pnContentRight[i] = new JPanel();
            pnContentRight[i].setMaximumSize(new Dimension(width - 60, 30));
            pnContentRight[i].setLayout(new GridLayout(1, 2));

            pnContentRight[i].add(lbl);
            pnContentRight[i].add(txtContentRight[i]);
        }
        txtContentRight[0].setEditable(false);
        btnDoiMK = new JButton("Đổi mật khẩu");
        btnDoiMK.setFocusPainted(false);
        btnDoiMK.setBackground(BASE.color_heaer);
        btnDoiMK.setOpaque(true);
        btnDoiMK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDoiMK.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String matKhau = new String(txtContentRight[0].getPassword());
                String matKhauMoi = new String(txtContentRight[1].getPassword());
                String nhapLai = new String(txtContentRight[2].getPassword());
                
                if(!xuLyKiemTraMatKhau(matKhauMoi)){
                    return;
                }
                if(nhapLai.equals("")){
                    new ShowDiaLog("<html>Mật khẩu nhập lại không được để trống</html>", ShowDiaLog.ERROR_DIALOG);
                    return;
                }
                if(!nhapLai.equals(matKhauMoi)){
                    new ShowDiaLog("<html>Nhập lại mật khẩu phải giống<br> với mật khẩu mới</html>", ShowDiaLog.ERROR_DIALOG);
                    return;
                }
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn sửa mật khẩu không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    TaiKhoanDTO tk = new TaiKhoanDTO(tkUSER.getTenDN(), 
                            tkUSER.getTenNV(), tkUSER.getDiaChi(), 
                            tkUSER.getSDT(), tkUSER.getEmail(), 
                            matKhauMoi, tkUSER.getQuyen(), 1);
                    tkBUS.set(tk);
                    new ShowDiaLog("<html>Sửa mật khẩu thành công!", ShowDiaLog.SUCCESS_DIALOG);
                    txtContentRight[1].setText("");
                    txtContentRight[2].setText("");
                } else {
                    txtContentRight[1].setText("");
                    txtContentRight[2].setText("");
                }  
            }
        });

        pnRight.add(Box.createVerticalStrut(20));
        pnRight.add(lblHeadRight);
        for (int i = 0; i < pnContentRight.length; i++) {
            pnRight.add(Box.createVerticalStrut(20));
            pnRight.add(pnContentRight[i]);
        }
        pnRight.add(Box.createVerticalStrut(20));
        pnRight.add(btnDoiMK);

        this.add(pnLeft);
        this.add(pnRight);
    }

    private void setData() {
        //Thứ tự {0: Tên đăng nhập, 1: Tên người dùng, 2: Địa chỉ, 3: Số điện thoại, 4: Email}
        txtContentLeft[0].setText(tkUSER.getTenDN());
        txtContentLeft[1].setText(tkUSER.getTenNV());
        txtContentLeft[2].setText(tkUSER.getDiaChi());
        txtContentLeft[3].setText(tkUSER.getSDT());
        txtContentLeft[4].setText(tkUSER.getEmail());

        //Mật khẩu
        txtContentRight[0].setText(tkUSER.getMatKhau());
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

    public static void main(String[] args) {
        QuyenDTO q = new QuyenDTO("QL", "Quản lý");
        TaiKhoanDTO tkDTO = new TaiKhoanDTO("NV07", "Phương123", "Quận 8", "0983456789", "Phuong579@gmail.com", "55345678", q, 0);
        JFrame f = new JFrame();
        TaiKhoanDN t = new TaiKhoanDN(tkDTO);
        f.add(t);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 500);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
