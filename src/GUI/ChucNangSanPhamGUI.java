package GUI;

import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicSliderUI;

public class ChucNangSanPhamGUI extends JFrame implements MouseListener {

    private JLabel lblHeader;
    private JPanel pnContent, pnThaoTac, pnDsAnh;
    private JTextField txtTenSach, txtNamXB, txtNhaXB, txtGiaBia;
    private JButton btnXacNhan, btnLuu, btnHuy, btnChonLoai, btnChonTacGia;
//    private JComboBox<String> cbxLoai;
    private SanPhamDTO spDTO;
    private JPanel[] pnThuocTinh = new JPanel[10];
    private ArrayList<String> imageName; // tên ảnh đã chọn
    private JLabel imageNameLabel; //hien thi ten anh chon
    JPanel imagePanelDefault; //khi chưa chọn ảnh

    private ArrayList<String> dsLoai = new ArrayList<>();
    private int width = 450, height = 710;
    private int width_row = 200, height_row = 30;

    Font bh1 = new Font("Tahoma", Font.BOLD, 18);
    Font h3 = new Font("Tahoma", Font.PLAIN, 14);

    public ChucNangSanPhamGUI() {
        dsLoai = new ArrayList<>();
        dsLoai.add("văn học");
        dsLoai.add("truyện");
        init();
    }

    public ChucNangSanPhamGUI(SanPhamDTO spDTO) {
        dsLoai = new ArrayList<>();
        dsLoai.add("văn học");
        dsLoai.add("truyện");
        this.spDTO = spDTO;
        init();
    }

    public void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); // Sửa lại để dùng getContentPane
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
        this.setUndecorated(true);

        lblHeader = new JLabel("Thêm sách", JLabel.CENTER);
        lblHeader.setPreferredSize(new Dimension(width, 36));
        lblHeader.setMaximumSize(new Dimension(width, 36));
        lblHeader.setFont(bh1);
        lblHeader.setBackground(BASE.color_heaer);
        lblHeader.setOpaque(true);
        lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnDsAnh = new JPanel();
        pnDsAnh.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnDsAnh.setMinimumSize(new Dimension(200, 230));
        pnDsAnh.setPreferredSize(new Dimension(200, 230));
        pnDsAnh.setMaximumSize(new Dimension(width, 230));

        JScrollPane scrollPane = new JScrollPane(pnDsAnh);
        scrollPane.setPreferredSize(new Dimension(200, 230));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        imageName = new ArrayList<>();
        ImageIcon icon = new ImageIcon("./src/image/iconBook.png");
        Image scaledImage = icon.getImage().getScaledInstance(174, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(resizedIcon, JLabel.CENTER);
        imagePanelDefault = new JPanel();
        imagePanelDefault.setLayout(new BorderLayout());
        imagePanelDefault.setPreferredSize(new Dimension(174, 220));
        imagePanelDefault.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanelDefault.add(label);
        pnDsAnh.add(imagePanelDefault);

        // Tạo JLabel để hiển thị tên hình ảnh đã chọn
        imageNameLabel = new JLabel("Không có ảnh", JLabel.CENTER); // Mặc định là không có hình ảnh được chọn
        imageNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageNameLabel.setPreferredSize(new Dimension(200, 30)); // Đặt kích thước cố định để tránh bị dịch chuyển

        JButton chooseImageButton = new JButton("Chọn ảnh");
        chooseImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chooseImageButton.setPreferredSize(new Dimension(100, 30));
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnDsAnh.remove(imagePanelDefault);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true); // Cho phép chọn nhiều tệp
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif"));

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();

                    int checkHinhTrung = 0; //biến xem khi sửa ảnh,tên ảnh có bị trùng/không trùng(1/0)
                    for (File selectedFile : selectedFiles) {
                        for (int i = 0; i < imageName.size(); i++) {
                            if (selectedFile.getName().equals(imageName.get(i))) {
                                checkHinhTrung = 1;
                                break;
                            }
                        }
                        if (checkHinhTrung == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "Tên hình không được trùng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            imageName.add(selectedFile.getName());
                            imageNameLabel.setText("Ảnh đã chọn: " + imageName); // Hiển thị tên tệp

                            addImage(selectedFile); // Thêm hình ảnh vào pnDsAnh
                        }
                    }
                }
            }
        });

        String[] data = new String[]{"Tên sách", "Nhà Xuất bản", "Năm xuất bản", "Giá bìa", "Mã loại", "Tác giả"};
        for (int i = 0; i < data.length; i++) {
            JLabel lbl = new JLabel(data[i], JLabel.LEFT);
            lbl.setFont(h3);
            pnThuocTinh[i] = new JPanel();
            pnThuocTinh[i].setPreferredSize(new Dimension(300, height_row));
            pnThuocTinh[i].setMaximumSize(new Dimension(300, height_row));
            pnThuocTinh[i].setLayout(new GridLayout(1, 1, 10, 10));
            pnThuocTinh[i].add(lbl);
        }
        txtTenSach = new JTextField();
        txtTenSach.setPreferredSize(new Dimension(width_row, height_row));
        txtTenSach.setMaximumSize(new Dimension(width_row, height_row));

//        cbxLoai = new JComboBox();
//        for (int i = 0; i < dsLoai.size(); i++) {
//            cbxLoai.addItem(dsLoai.get(i));
//        }

        txtNhaXB = new JTextField();
        txtNhaXB.setPreferredSize(new Dimension(width_row, height_row));
        txtNhaXB.setMaximumSize(new Dimension(width_row, height_row));

        txtNamXB = new JTextField();
        txtNamXB.setPreferredSize(new Dimension(width_row, height_row));
        txtNamXB.setMaximumSize(new Dimension(width_row, height_row));

        txtGiaBia = new JTextField();
        txtGiaBia.setPreferredSize(new Dimension(width_row, height_row));
        txtGiaBia.setMaximumSize(new Dimension(width_row, height_row));
        
        btnChonLoai = new JButton("Chọn loại");
        btnChonLoai.setBackground(Color.decode("#FFFFFF"));
        btnChonLoai.setPreferredSize(new Dimension(100, height_row));
        btnChonLoai.setMaximumSize(new Dimension(100, height_row));
        btnChonLoai.setOpaque(true);
        btnChonLoai.setFocusPainted(false);
        btnChonLoai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChonLoai.addMouseListener(this);

        btnChonTacGia = new JButton("Chọn tác giả");
        btnChonTacGia.setBackground(Color.decode("#FFFFFF"));
        btnChonTacGia.setPreferredSize(new Dimension(150, height_row));
        btnChonTacGia.setMaximumSize(new Dimension(150, height_row));
        btnChonTacGia.setOpaque(true);
        btnChonTacGia.setFocusPainted(false);
        btnChonTacGia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChonTacGia.addMouseListener(this);

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

        pnThuocTinh[0].add(txtTenSach); //Tên sách
        
        pnThuocTinh[1].add(txtNhaXB);
        pnThuocTinh[2].add(txtNamXB);
        pnThuocTinh[3].add(txtGiaBia);
        pnThuocTinh[4].setLayout(new BoxLayout(pnThuocTinh[4], BoxLayout.X_AXIS));
        pnThuocTinh[4].add(Box.createRigidArea(new Dimension(160, 0)));
        pnThuocTinh[4].add(btnChonLoai); 
        pnThuocTinh[5].setLayout(new BoxLayout(pnThuocTinh[5], BoxLayout.X_AXIS));
        pnThuocTinh[5].add(Box.createRigidArea(new Dimension(150, 0)));
        pnThuocTinh[5].add(btnChonTacGia);

        pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BoxLayout(pnThaoTac, BoxLayout.X_AXIS));
        pnThaoTac.add(Box.createRigidArea(new Dimension(150, 0)));

        this.add(lblHeader);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(imageNameLabel);
        this.add(Box.createVerticalStrut(5));
        this.add(chooseImageButton);

        for (int i = 0; i <= 5; i++) {
            this.add(Box.createVerticalStrut(20));
            this.add(pnThuocTinh[i]);
        }
        this.add(Box.createVerticalStrut(20));

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void addImage(File file) {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(174, 220));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(174, 200));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JButton deleteButton = new JButton("X");
        deleteButton.setPreferredSize(new Dimension(20, 20));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnDsAnh.remove(imagePanel); // Xóa panel hình ảnh
                imageName.remove(file.getName());
                if (imageName.isEmpty()) {
                    pnDsAnh.add(imagePanelDefault);
                    imageNameLabel.setText("Không có ảnh");

                } else {
                    imageNameLabel.setText("Ảnh đã chọn: " + imageName);
                }
                adjustScrollPane();
                revalidate();
                repaint();
            }
        });
        imagePanel.add(deleteButton, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon(file.getPath());
        Image scaledImage = icon.getImage().getScaledInstance(174, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        pnDsAnh.add(imagePanel);
        adjustScrollPane();
        revalidate();
        repaint();
    }

    private void adjustScrollPane() {
        // Tính toán kích thước dựa trên số lượng hình ảnh chọn
        int soLuongAnh = imageName.size();
        int rows = (int) Math.ceil((double) soLuongAnh / 2);

        if (soLuongAnh <= 2) {
            pnDsAnh.setPreferredSize(new Dimension(width, 230));

        } else if (soLuongAnh > 2) {
            pnDsAnh.setPreferredSize(new Dimension(width, 230 * rows + 5));
        }
    }

    public void initAdd() {
        lblHeader.setText("Thêm sách");
        pnThaoTac.add(btnXacNhan);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(btnHuy);
        this.add(pnThaoTac);
        this.add(Box.createVerticalStrut(20));
        setVisible(true);
    }

    public void initEdit() {
        this.setSize(new Dimension(width, 650));
        lblHeader.setText("Sửa sách");
        pnThuocTinh[4].setVisible(false);
        pnThuocTinh[5].setVisible(false);
        pnThaoTac.add(btnLuu);
        pnThaoTac.add(Box.createRigidArea(new Dimension(10, 0)));
        pnThaoTac.add(btnHuy);
        this.add(pnThaoTac);
        this.add(Box.createVerticalStrut(20));
        
        txtTenSach.setText(spDTO.getTenSach());
        txtNhaXB.setText(spDTO.getNxb());
        txtNamXB.setText(spDTO.getNamXB()+"");
        txtGiaBia.setText(spDTO.getGiaBia()+"");
        setVisible(true);
    }

    public static void main(String[] args) {

        ChucNangSanPhamGUI t = new ChucNangSanPhamGUI();
//        t.initAdd();
            t.initEdit();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn == btnHuy){
            this.dispose();
            spDTO = new SanPhamDTO();
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
