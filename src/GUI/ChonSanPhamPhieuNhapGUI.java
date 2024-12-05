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
        dsSP = spBUS.getDsSP();

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
        pnHeader.setBackground(BASE.color_main);
        pnHeader.setOpaque(true);

        JLabel lblHeader = new JLabel("Chọn sách", JLabel.CENTER);
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

        // Tính toán kích thước dựa trên số lượng sản phẩm
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

        String imagePath = sp.getTenHinh().size() > 0 ? "./src/Image/" + sp.getTenHinh().get(0) : "./src/Image/iconBook.jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(180, 210, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Tên sản phẩm
        JLabel productName = new JLabel(sp.getTenSach(), JLabel.CENTER);
        productName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Giá sản phẩm
        productPrice = new JLabel("Giá bìa: " + FormatInt.format(sp.getGiaBia()) + " đ", JLabel.CENTER);
        productPrice.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

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
                // Notify TaoPhieuNhap with the selected product details
                taoPhieuNhap.receiveSelectedProduct(sp.getMaSach());

                // Close the product selection window
                dispose();
            }
        });

        productPanel.add(label);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(productName);
         productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(productPrice);
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
        new ChonSanPhamPhieuNhapGUI(new TaoPhieuNhap(HomeGUI.tkUSER));
    }
}
