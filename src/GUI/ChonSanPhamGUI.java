package GUI;

import DTO.LoaiDTO;
import DTO.SanPhamDTO;
import DTO.TacGiaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

public class ChonSanPhamGUI extends JFrame implements MouseListener{

    private ArrayList<SanPhamDTO> dsSP;
    private ArrayList<SanPhamDTO> SelectedListSP;
    private JPanel pnHeader, pnContent, pnTimKiem;
    private JPanel[] product;
    private JLabel exit;
    private JTextField txtTimKiem;
    private JButton btnChon;
    private JLabel productPrice;
    private int width=900, height=781;

    private DecimalFormat FormatInt = new DecimalFormat("#,###");

    public ChonSanPhamGUI(String id) {
        ArrayList<TacGiaDTO> tgia = new ArrayList<>();
        tgia.add(new TacGiaDTO("TG01", "Tác giả A"));
        tgia.add(new TacGiaDTO("TG02", "Tác giả B"));

        ArrayList<LoaiDTO> loaiSP = new ArrayList<>();
        loaiSP.add(new LoaiDTO("L01", "Loai A"));
        loaiSP.add(new LoaiDTO("L02", "Loai B"));

        ArrayList<String> anh = new ArrayList<>();
        anh.add("tu-duy-nguoc-1.jpg");
        anh.add("tu-duy-nguoc-2.jpg");
        anh.add("tu-duy-nguoc-3.jpg");

        ArrayList<String> anh1 = new ArrayList<>();
        anh.add("mot-doi-quan-tri-1.jpg");
        anh.add("mot-doi-quan-tri-2.jpg");
        anh.add("mot-doi-quan-tri-3.jpg");
        SanPhamDTO sp = new SanPhamDTO("SP001", "Tư duy ngược", "Văn học Nhà Nội", 2024, 10, 1, 500, 500, anh, tgia, loaiSP);
        dsSP = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dsSP.add(sp);
        }
        SanPhamDTO sp1 = new SanPhamDTO("thn", "Một đời quản trị", "Văn học Nhà Nội", 2024, 10, 1, 500, 500, anh1, tgia, loaiSP);
        dsSP.add(sp1);
        
        init();
        if(id.equals("PN")){
            productPrice.setVisible(false);
        }
    }

    private void init() {
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setUndecorated(true);

        //Tiêu đề
        pnHeader = new JPanel();
        pnHeader.setLayout(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(width, 36));
        pnHeader.setBackground(BASE.color_heaer);
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

        //Nội dung
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
        productPanel.setBackground(Color.WHITE); // Đặt màu nền trắng hoặc tùy ý

        // Hình ảnh sản phẩm
        ImageIcon icon = new ImageIcon("./src/image/" + (sp.getTenHinh().size() > 0 ? sp.getTenHinh().get(0) : "iconBook.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(180, 210, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Tên sản phẩm
        JLabel productName = new JLabel(sp.getTenSach(), JLabel.CENTER);
        productName.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        // Giá sản phẩm
        productPrice = new JLabel("Giá: " + FormatInt.format(sp.getGiaBan()) + " đ", JLabel.CENTER);
        productPrice.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang

        //button chon
        btnChon = new JButton("Chọn");
        btnChon.setPreferredSize(new Dimension(130, 30));
        btnChon.setMaximumSize(new Dimension(130, 30));
        btnChon.setBackground(BASE.btnThem);
        btnChon.setFont(BASE.font);
        btnChon.setOpaque(true);
        btnChon.setBorderPainted(false);
        btnChon.setFocusPainted(false);
        btnChon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChon.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
//        btnChon.addActionListener(e -> layDanhSachSP(sp));

        // Thêm các thành phần vào productPanel
        productPanel.add(label);
        productPanel.add(Box.createVerticalStrut(5)); // Khoảng cách giữa các thành phần
        productPanel.add(productName);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(productPrice);
        productPanel.add(Box.createVerticalStrut(5));
        productPanel.add(btnChon);

        // Cài đặt con trỏ chuột
        productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productPanel.addMouseListener(this);

        return productPanel;
    }

//    private void layDanhSachSP(SanPhamDTO sp){
//        SelectedListSP.add(sp);
//    }
    
    public static void main(String[] args) {
        ChonSanPhamGUI t = new ChonSanPhamGUI(""); //Hoá đơn
//        ChonSanPhamGUI t = new ChonSanPhamGUI("PN"); //Phiếu nhập
        }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lbl = (JLabel) e.getSource();
        if(lbl == exit){
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
    }
