package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class addCustomerGUI extends JFrame {

    private JPanel pnHeader, pnMain, pnFooter;
    private JPanel btXacNhan, btHuy;
    private JTextField tfName, tfPhone;
    private JLabel errorName, errorPhone;
    private BanHangGUI SalesGUI;

    public addCustomerGUI() {
        init();
    }

    public addCustomerGUI(BanHangGUI SalesGUI) {
        this.SalesGUI = SalesGUI;
        init();
    }

    public void init() {
        // Tạo một JPanel cho viền
        JPanel borderPanel = new JPanel();
        borderPanel.setBackground(Color.BLACK); // Màu viền
        borderPanel.setBorder(new EmptyBorder(1, 1, 1, 1)); // Khoảng cách bên trong
        borderPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        setSize(340, 240);
        setUndecorated(true);
        setPreferredSize(new Dimension(300, 240));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnHeader = new JPanel(new BorderLayout());
        pnHeader.setBackground(BASE.color_header_tbl);
        pnHeader.setPreferredSize(new Dimension(0, 35));
        JLabel lblHeader = new JLabel("Thêm khách hàng", JLabel.CENTER);
        lblHeader.setFont(BASE.font_header);
        pnHeader.add(lblHeader, BorderLayout.CENTER);

        pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(new EmptyBorder(10, 10, 0, 10));

        JPanel pnName = new JPanel();
        pnName.setLayout(new BoxLayout(pnName, BoxLayout.X_AXIS));
        JLabel lblName = new JLabel("Tên khách hàng");
        lblName.setFont(BASE.font);
        tfName = new JTextField();
        tfName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pnName.add(lblName);
        pnName.add(Box.createHorizontalStrut(10));
        pnName.add(tfName);
        pnName.add(Box.createHorizontalGlue());

        errorName = new JLabel("  ");
        errorName.setFont(BASE.font_error);
        errorName.setForeground(Color.RED);
        errorName.setPreferredSize(new Dimension(300, 30));
        errorName.setMaximumSize(new Dimension(300, 30));
        errorName.setMinimumSize(new Dimension(300, 30));

        JPanel pnPhone = new JPanel();
        pnPhone.setLayout(new BoxLayout(pnPhone, BoxLayout.X_AXIS));
        JLabel lblPhone = new JLabel("Số điện thoại");
        lblPhone.setFont(BASE.font);
        tfPhone = new JTextField();
        tfPhone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pnPhone.add(lblPhone);
        pnPhone.add(Box.createHorizontalStrut(28));
        pnPhone.add(tfPhone);
        pnPhone.add(Box.createHorizontalGlue());

        errorPhone = new JLabel(" ");
        errorPhone.setFont(BASE.font_error);
        errorPhone.setForeground(Color.RED);
        errorPhone.setPreferredSize(new Dimension(300, 30));
        errorPhone.setMaximumSize(new Dimension(300, 30));
        errorPhone.setMinimumSize(new Dimension(300, 30));

        pnMain.add(pnName);
        pnMain.add(errorName);
        pnMain.add(Box.createVerticalStrut(10));
        pnMain.add(pnPhone);
        pnMain.add(errorPhone);

        MouseAdapter commonMouseListener = createCommonMouseListener();

        pnFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btXacNhan = new JPanel();
        btXacNhan.setPreferredSize(new Dimension(120, 30));
        cssBtn(btXacNhan, "Xác nhận", "btnXacNhan", BASE.color_btXacNhan);
        btXacNhan.addMouseListener(commonMouseListener);

        btHuy = new JPanel();
        btHuy.setPreferredSize(new Dimension(120, 30));
        cssBtn(btHuy, "Hủy", "btnHuy", BASE.color_btHuy);
        btHuy.addMouseListener(commonMouseListener);

        pnFooter.add(btXacNhan);
        pnFooter.add(btHuy);

        // Thêm các thành phần vào borderPanel
        borderPanel.add(pnHeader, BorderLayout.NORTH);
        borderPanel.add(pnMain, BorderLayout.CENTER);
        borderPanel.add(pnFooter, BorderLayout.SOUTH);

        // Thêm borderPanel vào JFrame
        add(borderPanel);

        this.setVisible(true);

        tfName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateName();
            }
        });

        tfPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validatePhone();
            }
        });
    }

    private void validateName() {
        String name = tfName.getText();
        Pattern validNamePattern = Pattern.compile("^[\\p{L} .'-]+$");
        int maxLength = 35;

        if (name.isEmpty()) {
            errorName.setText("Tên không được để trống.");
        } else if (name.length() > maxLength) {
            errorName.setText("Tên không được quá 35 ký tự.");
        } else if (name.startsWith(" ")) {
            errorName.setText("Tên không được chứa khoảng trắng ở đầu.");
        } else if (name.endsWith(" ")) {
            errorName.setText("Tên không được chứa khoảng trắng ở cuối.");
        } else if (!validNamePattern.matcher(name).matches()) {
            errorName.setText("Tên không được chứa ký tự đặc biệt.");
        } else {
            errorName.setText(" ");
        }
    }

    private void validatePhone() {
        String phone = tfPhone.getText();
        Pattern digitPattern = Pattern.compile("^[0-9]+$");
        KhachHangBUS khBUS = new KhachHangBUS();

        if (phone.isEmpty()) {
            errorPhone.setText("Số điện thoại không được để trống.");
        } else if (!phone.startsWith("0")) {
            errorPhone.setText("Số điện thoại phải bắt đầu bằng số 0.");
        } else if (!digitPattern.matcher(phone).matches()) {
            errorPhone.setText("Số điện thoại là chữ số.");
        } else if (phone.length() != 10) {
            errorPhone.setText("Số điện thoại có độ dài 10 chữ số.");
        } else if (khBUS.checkPhoneExits(phone)) {
            errorPhone.setText("Số điện thoại Đã tồn tại");
        } else {
            errorPhone.setText(" ");
        }
    }

    private void cssBtn(JPanel b, String text, String name, Color color) {
        JLabel t = new JLabel(text, JLabel.CENTER);
        b.setBackground(color);
        b.setName(name);
        b.add(t);
        b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
        b.setOpaque(true);
    }

    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel clickedPanel = (JPanel) e.getSource();
                    if (clickedPanel == btHuy) {
                        Object[] options = {"Có", "Không"};
                        int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục thoát?", "Thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (r1 == JOptionPane.YES_OPTION) {
                            dispose();
                        }
                    } else if (clickedPanel == btXacNhan) {
                        String name = tfName.getText();
                        String phone = tfPhone.getText();
                        KhachHangBUS khBUS = new KhachHangBUS();
                        KhachHangDTO kh = new KhachHangDTO(name, phone);
                        kh.setMaKh(khBUS.TaoMaKH());
                        if (khBUS.ThemKhachHang(kh)) {
                            SalesGUI.getTfMaKH().setText(kh.getMaKh());
                            new ShowDiaLog("Thêm khách hàng thành công", ShowDiaLog.SUCCESS_DIALOG);
                            dispose();
                        } else {
                            new ShowDiaLog("Thêm khách hàng thất bại", ShowDiaLog.ERROR_DIALOG);
                        }
                    }
                }
            }
        };
    }

    public static void main(String[] agrs) {
        new addCustomerGUI();
    }
}
