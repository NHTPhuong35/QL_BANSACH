package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;

public class SuaNhaCungCapGUI extends JFrame implements MouseListener {

    private JTextField tfTen, tfDiaChi, tfPhone, tfEmail;
    private JPanel btnXacNhan, btnHuy;
    private NhaCungCapGUI nccGUI;
    private NhaCungCapDTO nccDTO;
    
    public SuaNhaCungCapGUI(NhaCungCapDTO nccDTO, NhaCungCapGUI nccGUI) {
        this.nccGUI = nccGUI;
        this.nccDTO = nccDTO;
        
        // Initializing text fields with existing data
        this.tfTen = new JTextField(nccDTO.getTenNhaCungCap());
        this.tfDiaChi = new JTextField(nccDTO.getDiaChi());
        this.tfPhone = new JTextField(nccDTO.getSdt());
        this.tfEmail = new JTextField(nccDTO.getEmail());
        
        init();
    }
    
    public SuaNhaCungCapGUI() {
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(400, 300);
        setPreferredSize(new Dimension(400, 300));
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title panel
        JPanel titleGUI_wrap = new JPanel(new BorderLayout());
        titleGUI_wrap.setPreferredSize(new Dimension(400, 35));
        JLabel titleGUI = new JLabel("Sửa Nhà Cung Cấp", JLabel.CENTER);
        titleGUI.setFont(BASE.font_header);
        titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
        titleGUI_wrap.setBackground(BASE.color_table_heaer);

        add(titleGUI_wrap, BorderLayout.NORTH);

        // Main panel using GridBagLayout
        JPanel pnMain = new JPanel(new GridBagLayout());
        pnMain.setBorder(new EmptyBorder(10, 15, 0, 15));
        pnMain.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        JLabel lbTen = new JLabel("Tên Nhà Cung Cấp:");
        lbTen.setFont(BASE.font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        pnMain.add(lbTen, gbc);

        gbc.gridx = 1;
        pnMain.add(tfTen, gbc);

        // Address field
        JLabel lbDiaChi = new JLabel("Địa chỉ:");
        lbDiaChi.setFont(BASE.font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnMain.add(lbDiaChi, gbc);

        gbc.gridx = 1;
        pnMain.add(tfDiaChi, gbc);

        // Phone field
        JLabel lbPhone = new JLabel("Số điện thoại:");
        lbPhone.setFont(BASE.font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnMain.add(lbPhone, gbc);

        gbc.gridx = 1;
        pnMain.add(tfPhone, gbc);

        // Email field
        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(BASE.font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnMain.add(lbEmail, gbc);

        gbc.gridx = 1;
        pnMain.add(tfEmail, gbc);

        // Buttons panel
        JPanel pnBTN = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnBTN.setBackground(Color.WHITE);

        // Confirm button
        btnXacNhan = new JPanel();
        btnXacNhan.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnXacNhan, "Xác nhận", "btnXacNhan");
        btnXacNhan.addMouseListener(this);

        // Cancel button
        btnHuy = new JPanel();
        btnHuy.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnHuy, "Hủy", "btnHuy");
        btnHuy.addMouseListener(this);

        pnBTN.add(btnXacNhan);
        pnBTN.add(btnHuy);

        // Add buttons to main panel
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        pnMain.add(pnBTN, gbc);

        add(pnMain, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);  // Center on screen
        setVisible(true);
    }

    private void cssBtn(JPanel b, String text, String name) {
        JLabel t = new JLabel(text, JLabel.CENTER);
        t.setForeground(Color.WHITE);  // White text
        b.setBackground(BASE.color_table_heaer);
        b.setName(name);
        b.add(t);
        b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
        b.setOpaque(true);
    }

    private boolean KT_Ten(String ten) {
        String regex = "^[a-zA-ZÀ-ỹ\\s-/]{2,}$";
        boolean isValid = ten.matches(regex);
        if (!isValid){
            new ShowDiaLog("Tên phải lớn hơn 2 kí tự", ShowDiaLog.ERROR_DIALOG);
        }
        return isValid;
    }
    private boolean KT_Email(String email) {
        if (email == null || email.trim().isEmpty()) {
            new ShowDiaLog("email không được để trống", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
    
        // Biểu thức chính quy kiểm tra định dạng email hợp lệ
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";  
        // Kiểm tra tính hợp lệ của email
        boolean isValid = email.matches(regex);
    
        if (!isValid) {
            new ShowDiaLog("Email không hợp lệ", ShowDiaLog.ERROR_DIALOG);
        }
    
        return isValid;
    }
    private boolean KT_SDT(String sdt) {
        // Kiểm tra số điện thoại không được để trống
        if (sdt == null || sdt.trim().isEmpty()) {
            new ShowDiaLog("Số điện thoại không được để trống", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
    
        // Biểu thức chính quy để kiểm tra số điện thoại
        String regex = "^0\\d{9}$"; // Bắt đầu bằng '0' và có tổng cộng 10 chữ số
    
        // Kiểm tra tính hợp lệ
        boolean isValid = sdt.matches(regex);
    
        if (!isValid) {
            new ShowDiaLog("Số điện thoại không hợp lệ", ShowDiaLog.ERROR_DIALOG);
            return false;
        } 
    
        return isValid;
    }
    private boolean KT_DiaChi(String diaChi) {
        // Kiểm tra địa chỉ không được để trống
        if (diaChi == null || diaChi.trim().isEmpty()) {
            new ShowDiaLog("Địa chỉ không được trống", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
    
        // Kiểm tra độ dài của địa chỉ
        int length = diaChi.trim().length();
        if (length < 5 || length > 100) {
            new ShowDiaLog("Địa chỉ phải từ 5 đến 100 kí tự", ShowDiaLog.ERROR_DIALOG);
            return false;
        }
        return true;
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
                    String diaChi = tfDiaChi.getText();
                    String phone = tfPhone.getText();
                    String email = tfEmail.getText();
                    if (!KT_Ten(ten)) {
                        return;
                    }
                    if (!KT_DiaChi(diaChi)) {
                        return;
                    }
                    if (!KT_SDT(phone)) {
                        return;
                    }
                    if (!KT_Email(email)) {;
                        return;
                    }

                    // Set updated values to DTO
                    nccDTO.setTenNhaCungCap(ten);
                    nccDTO.setDiaChi(diaChi);
                    nccDTO.setSdt(phone);
                    nccDTO.setEmail(email);

                    // Update in database (BUS)
                    NhaCungCapBUS busNCC = new NhaCungCapBUS();
                    if (busNCC.SuaNhaCungCap(nccDTO)) {
                        nccGUI.EditRow(nccDTO);
                        dispose();
                    } else {
                        new ShowDiaLog("Sửa nhà cung cấp thất bại", ShowDiaLog.ERROR_DIALOG);
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(BASE.color_table_heaer);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override public void mouseExited(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(BASE.color_heaer);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
