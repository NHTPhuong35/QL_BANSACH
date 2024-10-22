package GUI;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChonSanPhamPhieuNhapGUI extends JFrame implements MouseListener {

    private ArrayList<SanPhamDTO> dsSP;
    private Map<SanPhamDTO, JButton> productButtons = new HashMap<>();
    private JPanel pnHeader, pnContent, pnTimKiem;
    private JPanel[] product;
    private JLabel exit;
    private JTextField txtTimKiem;
    private JLabel productPrice;
    private int width = 900, height = 781;
    private TaoPhieuNhap taoPhieuNhap;

    private DecimalFormat FormatInt = new DecimalFormat("#,###");

    public ChonSanPhamPhieuNhapGUI(TaoPhieuNhap taoPhieuNhap) {
        this.taoPhieuNhap = taoPhieuNhap;
        SanPhamBUS spBUS = new SanPhamBUS();
        dsSP = spBUS.getDanhSachBan();

        init();
    }

    private void init() {
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        // Tiêu đề
        pnHeader = new JPanel();
        pnHeader.setLayout(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(width, 36));
        pnHeader.setBackground(Color.LIGHT_GRAY); // Replace with a valid color
        pnHeader.setOpaque(true);

        JLabel lblHeader = new JLabel("Chọn sản phẩm", JLabel.CENTER);
        lblHeader.setFont(BASE.font_title);
        lblHeader.setBorder(new EmptyBorder(0, 20, 0, 0));

        ImageIcon icon = new ImageIcon("./src/image/exit_icon.png");
        exit = new JLabel(icon, JLabel.CENTER);
        exit.setPreferredSize(new Dimension(36, 36));
        exit.setBackground(Color.RED);
        exit.setOpaque(true);
        exit.addMouseListener(this);
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnHeader.add(lblHeader, BorderLayout.WEST);
        pnHeader.add(exit, BorderLayout.EAST);

        // Nội dung
        pnContent = new JPanel();
        pnContent.setLayout(new BorderLayout());

        pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JLabel lblTimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
        lblTimKiem.setFont(BASE.font);
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(150, 25));
        pnTimKiem.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);

        JPanel mainPanel = initContent(dsSP);
        pnContent.add(pnTimKiem, BorderLayout.NORTH);
        pnContent.add(mainPanel, BorderLayout.CENTER);

        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnContent, BorderLayout.CENTER);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public JPanel initContent(ArrayList<SanPhamDTO> dsSP) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));

        int dsSize = dsSP.size();
        int rows = (int) Math.ceil((double) dsSize / 4);

        if (dsSize <= 4) {
            panel.setPreferredSize(new Dimension(width, 340));
        } else if (dsSize > 4) {
            panel.setPreferredSize(new Dimension(width, 320 * rows + 20));
        }

        product = new JPanel[dsSize];
        for (int i = 0; i < dsSize; i++) {
            product[i] = createProductPanel(dsSP.get(i));
            panel.add(product[i]);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createProductPanel(SanPhamDTO sp) {
        JPanel productPanel = new JPanel();
        productPanel.setPreferredSize(new Dimension(180, 300));
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setAlignmentY(TOP_ALIGNMENT);
        productPanel.setBackground(Color.WHITE);

        String imagePath = sp.getTenHinh().size() > 0 ? "./src/Image/" + sp.getTenHinh().get(0) : "./src/Image/product.png";
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(180, 210, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel productName = new JLabel(sp.getTenSach(), JLabel.CENTER);
        productName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnChon = new JButton("Chọn");
        btnChon.setPreferredSize(new Dimension(130, 30));
        btnChon.setMaximumSize(new Dimension(130, 30));
        btnChon.setBackground(BASE.btnThem);
        btnChon.setFont(BASE.font);
        btnChon.setOpaque(true);
        btnChon.setBorderPainted(false);
        btnChon.setFocusPainted(false);
        btnChon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChon.setAlignmentX(Component.CENTER_ALIGNMENT);

        productButtons.put(sp, btnChon);
        btnChon.setActionCommand(sp.getTenSach());
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // Create a new JFrame to input soLuong, giaNhap, and loiNhuan
            JFrame inputFrame = new JFrame("Nhập số lượng, giá nhập và lợi nhuận");
            inputFrame.setSize(300, 250);
            inputFrame.setLayout(new BorderLayout());
            inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel lblSoLuong = new JLabel("Số lượng:");
            JTextField txtSoLuong = new JTextField(10);

            JLabel lblGiaNhap = new JLabel("Giá nhập:");
            JTextField txtGiaNhap = new JTextField(10);

            JLabel lblLoiNhuan = new JLabel("Lợi nhuận (%):");
            JTextField txtLoiNhuan = new JTextField(10);

            inputPanel.add(lblSoLuong);
            inputPanel.add(txtSoLuong);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(lblGiaNhap);
            inputPanel.add(txtGiaNhap);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(lblLoiNhuan);
            inputPanel.add(txtLoiNhuan);

            JButton btnSubmit = new JButton("Xác nhận");
            btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                try {
                    int soLuong = Integer.parseInt(txtSoLuong.getText());
                    double giaNhap = Double.parseDouble(txtGiaNhap.getText());
                    double loiNhuan = Double.parseDouble(txtLoiNhuan.getText()) / 100;

                    // Calculate donGia
                            double donGia = giaNhap * (1 + loiNhuan);
                            double giaBia = sp.getGiaBia();
                            if (donGia > giaBia) {
                                donGia = giaBia;
                            }

                    // Notify TaoPhieuNhap with the selected product details
                    taoPhieuNhap.receiveSelectedProduct(sp.getMaSach(), soLuong, donGia);

                    // Close the input frame and the product selection window
                    inputFrame.dispose();
                    dispose();
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    System.err.println("Invalid input: " + ex.getMessage());
                }
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(btnSubmit);

            inputFrame.add(inputPanel, BorderLayout.CENTER);
            inputFrame.add(buttonPanel, BorderLayout.SOUTH);

            inputFrame.setLocationRelativeTo(null);
            inputFrame.setVisible(true);
            }
        });

        productPanel.add(label);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(productName);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(btnChon);

        productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productPanel.addMouseListener(this);

        return productPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lbl = (JLabel) e.getSource();
        if (lbl == exit) {
            this.dispose();
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

    public static void main(String[] args) {
        new ChonSanPhamPhieuNhapGUI(new TaoPhieuNhap());
    }
}
