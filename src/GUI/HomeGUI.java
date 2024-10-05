/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author nhatm
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HomeGUI extends JFrame implements MouseListener {

    private JButton sanphamButton;
    private JButton hoadonButton;
    private JButton khachhangButton;
    private JButton nhanvienButton;
    private JButton thongkeButton;
    private JButton tacgiaButton;
    private JButton phanquyenButton;
    private JButton theloaiButton;
    private JButton nhacungcapButton;
    private JButton taikhoanButton;
    private JButton phieunhapButton;
    private JButton logoutButton;
    private JPanel menuPanel;
    private JPanel headPanel;
    private JPanel logoPanel;
    private JPanel introductionAndexitPanel;
    private JLabel introduceAppName;
    private JLabel introduceUser;
    private JLabel introduceUserRole;
    private JLabel appName;
    private JLabel headAppName;
    private JLabel logoApp;
    private JPanel showPanel;

    public HomeGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       

        //**MENU**
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 0, 0));
        menuPanel.setBackground(Color.decode("#98DCE2"));

        appName = new JLabel("Cửa hàng bán sách nhóm 2");
        Font AppNamefont = new Font("Arial", Font.BOLD, 13);
        appName.setFont(AppNamefont);
        appName.setHorizontalAlignment(SwingConstants.CENTER);
        appName.setVerticalAlignment(SwingConstants.CENTER);

        sanphamButton = new JButton("Sản phẩm");
        sanphamButton.setIcon(new ImageIcon(getClass().getResource("/Image/product.png")));
        sanphamButton.setHorizontalAlignment(SwingConstants.LEFT);
        sanphamButton.setVerticalAlignment(SwingConstants.CENTER);
        sanphamButton.setBorderPainted(false);
        sanphamButton.setBackground(Color.decode("#98DCE2"));
        sanphamButton.addMouseListener(this);
        sanphamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sanphamButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sanphamButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        hoadonButton = new JButton("Hóa đơn");
        hoadonButton.setIcon(new ImageIcon(getClass().getResource("/Image/bill.png")));
        hoadonButton.setHorizontalAlignment(SwingConstants.LEFT);
        hoadonButton.setVerticalAlignment(SwingConstants.CENTER);
        hoadonButton.setBorderPainted(false);
        hoadonButton.setBackground(Color.decode("#98DCE2"));
        hoadonButton.addMouseListener(this);
        hoadonButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hoadonButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hoadonButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        khachhangButton = new JButton("Khách hàng");
        khachhangButton.setIcon(new ImageIcon(getClass().getResource("/Image/customer.png")));
        khachhangButton.setHorizontalAlignment(SwingConstants.LEFT);
        khachhangButton.setVerticalAlignment(SwingConstants.CENTER);
        khachhangButton.setBorderPainted(false);
        khachhangButton.setBackground(Color.decode("#98DCE2"));
        khachhangButton.addMouseListener(this);
        khachhangButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                khachhangButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                khachhangButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        nhanvienButton = new JButton("Nhân viên");
        nhanvienButton.setIcon(new ImageIcon(getClass().getResource("/Image/staff.png")));
        nhanvienButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhanvienButton.setVerticalAlignment(SwingConstants.CENTER);
        nhanvienButton.setBorderPainted(false);
        nhanvienButton.setBackground(Color.decode("#98DCE2"));
        nhanvienButton.addMouseListener(this);
        nhanvienButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nhanvienButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nhanvienButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        thongkeButton = new JButton("Thống kê");
        thongkeButton.setIcon(new ImageIcon(getClass().getResource("/Image/statistical.png")));
        thongkeButton.setHorizontalAlignment(SwingConstants.LEFT);
        thongkeButton.setVerticalAlignment(SwingConstants.CENTER);
        thongkeButton.setBorderPainted(false);
        thongkeButton.setBackground(Color.decode("#98DCE2"));
        thongkeButton.addMouseListener(this);
        thongkeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongkeButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongkeButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        tacgiaButton = new JButton("Tác giả");
        tacgiaButton.setIcon(new ImageIcon(getClass().getResource("/Image/author.png")));
        tacgiaButton.setHorizontalAlignment(SwingConstants.LEFT);
        tacgiaButton.setVerticalAlignment(SwingConstants.CENTER);
        tacgiaButton.setBorderPainted(false);
        tacgiaButton.setBackground(Color.decode("#98DCE2"));
        tacgiaButton.addMouseListener(this);
        tacgiaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tacgiaButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tacgiaButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        phanquyenButton = new JButton("Phân quyền");
        phanquyenButton.setIcon(new ImageIcon(getClass().getResource("/Image/Decentralization.png")));
        phanquyenButton.setHorizontalAlignment(SwingConstants.LEFT);
        phanquyenButton.setVerticalAlignment(SwingConstants.CENTER);
        phanquyenButton.setBorderPainted(false);
        phanquyenButton.setBackground(Color.decode("#98DCE2"));
        phanquyenButton.addMouseListener(this);
        phanquyenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                phanquyenButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                phanquyenButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        theloaiButton = new JButton("Thể loại");
        theloaiButton.setIcon(new ImageIcon(getClass().getResource("/Image/category.png")));
        theloaiButton.setHorizontalAlignment(SwingConstants.LEFT);
        theloaiButton.setVerticalAlignment(SwingConstants.CENTER);
        theloaiButton.setBorderPainted(false);
        theloaiButton.setBackground(Color.decode("#98DCE2"));
        theloaiButton.addMouseListener(this);
        theloaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                theloaiButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                theloaiButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        nhacungcapButton = new JButton("Nhà cung cấp");
        nhacungcapButton.setIcon(new ImageIcon(getClass().getResource("/Image/supplier.png")));
        nhacungcapButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhacungcapButton.setVerticalAlignment(SwingConstants.CENTER);
        nhacungcapButton.setBorderPainted(false);
        nhacungcapButton.setBackground(Color.decode("#98DCE2"));
        nhacungcapButton.addMouseListener(this);
        nhacungcapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nhacungcapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nhacungcapButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        taikhoanButton = new JButton("Tài khoản");
        taikhoanButton.setIcon(new ImageIcon(getClass().getResource("/Image/account.png")));
        taikhoanButton.setHorizontalAlignment(SwingConstants.LEFT);
        taikhoanButton.setVerticalAlignment(SwingConstants.CENTER);
        taikhoanButton.setBorderPainted(false);
        taikhoanButton.setBackground(Color.decode("#98DCE2"));
        taikhoanButton.addMouseListener(this);
        taikhoanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taikhoanButton.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                taikhoanButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        phieunhapButton = new JButton("Phiếu nhập");
        phieunhapButton.setIcon(new ImageIcon(getClass().getResource("/Image/goods-receipt.png")));
        phieunhapButton.setHorizontalAlignment(SwingConstants.LEFT);
        phieunhapButton.setVerticalAlignment(SwingConstants.CENTER);
        phieunhapButton.setBorderPainted(false);
        phieunhapButton.setBackground(Color.decode("#98DCE2"));
        phieunhapButton.addMouseListener(this);
        phieunhapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                phieunhapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                phieunhapButton.setBackground(Color.decode("#98DCE2"));
            }
        });

        //add into menuPanel
        menuPanel.add(appName);
        menuPanel.add(sanphamButton);
        menuPanel.add(hoadonButton);
        menuPanel.add(khachhangButton);
        menuPanel.add(nhanvienButton);
        menuPanel.add(thongkeButton);
        menuPanel.add(tacgiaButton);
        menuPanel.add(phanquyenButton);
        menuPanel.add(theloaiButton);
        menuPanel.add(nhacungcapButton);
        menuPanel.add(taikhoanButton);
        menuPanel.add(phieunhapButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
//        gbc.gridwidth = 0; 
//        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.BOTH; // Full fill space
        gbc.weightx = 0.10;
        gbc.weighty = 0.90;
        add(menuPanel, gbc);

        //***
        //**HEADER**
        headPanel = new JPanel();
        logoPanel = new JPanel();

        headPanel.setLayout(new GridBagLayout());
        headPanel.setBackground(Color.decode("#98DCE2"));
        GridBagConstraints headPanelgbc = new GridBagConstraints();

        headAppName = new JLabel("Cửa hàng bán sách nhóm 2");

        introductionAndexitPanel = new JPanel();
        introductionAndexitPanel.setBackground(Color.white);
        introductionAndexitPanel.setLayout(new GridBagLayout());
        GridBagConstraints introductionAndexitPanelgbc = new GridBagConstraints();

        //Logout Button
        logoutButton = new JButton();
        logoutButton.setIcon(new ImageIcon(getClass().getResource("/Image/icon-off.png")));
        logoutButton.setBackground(Color.white);
        logoutButton.setBorderPainted(false);
        logoutButton.setSize(30, 30);

        introductionAndexitPanelgbc.gridx = 0;
        introductionAndexitPanelgbc.gridy = 0;
        introductionAndexitPanelgbc.weightx = 0.2;
        introductionAndexitPanelgbc.anchor = GridBagConstraints.WEST;
        introductionAndexitPanelgbc.insets = new Insets(0, 10, 0, 0); // Margin top left bottom right
        introductionAndexitPanel.add(headAppName, introductionAndexitPanelgbc);

        introductionAndexitPanelgbc.gridx = 1;
        introductionAndexitPanelgbc.gridy = 0;
        introductionAndexitPanelgbc.weightx = 0.05;
        introductionAndexitPanelgbc.anchor = GridBagConstraints.EAST;
        introductionAndexitPanel.add(logoutButton, introductionAndexitPanelgbc);

        introduceUser = new JLabel("Xin chào: Admin12345");
        introduceUserRole = new JLabel("Vai trò: Admin");

        logoApp = new JLabel();
        logoApp.setIcon(new ImageIcon(getClass().getResource("/Image/icon-main.png")));
        logoPanel.setBackground(Color.decode("#98DCE2"));
        logoPanel.add(logoApp);

        headPanelgbc.gridx = 0;
        headPanelgbc.gridy = 0;
        headPanelgbc.weightx = 0.2;
        headPanelgbc.fill = GridBagConstraints.BOTH;
        headPanel.add(introductionAndexitPanel, headPanelgbc);

        headPanelgbc.gridx = 0;
        headPanelgbc.gridy = 1;
        headPanelgbc.weightx = 0.2;
        headPanelgbc.fill = GridBagConstraints.BOTH;
        headPanelgbc.insets = new Insets(20, 40, 0, 0);
        headPanel.add(introduceUser, headPanelgbc);

        headPanelgbc.gridx = 0;
        headPanelgbc.gridy = 2;
        headPanelgbc.weighty = 1;
        headPanelgbc.fill = GridBagConstraints.BOTH;
        headPanelgbc.insets = new Insets(0, 40, 0, 0); // Margin top left bottom right
        headPanel.add(introduceUserRole, headPanelgbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0.10;
        gbc.weighty = 0.10;
        gbc.fill = GridBagConstraints.BOTH;
        add(logoPanel, gbc);

        gbc.gridx = 1;  // Column 1
        gbc.gridy = 0;  // Row 0
//        gbc.gridwidth = 1; // Take 1 Column
//        gbc.gridheight = 1; // Take 1 Row
        gbc.weightx = 0.90; //Length of component in a column
        gbc.weighty = 0.15; //Width of component in a row
        gbc.fill = GridBagConstraints.BOTH; //Full fill empty space
        add(headPanel, gbc);

        //***
        //**SHOW PANEL**
        showPanel = new JPanel();
        showPanel.setBackground(Color.WHITE);
        showPanel.setLayout(new BorderLayout());
        showPanel.setPreferredSize(new Dimension(940, 527));

        gbc.gridx = 1;
        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0.90;
        gbc.weighty = 0.85;
        add(showPanel, gbc);

        showPanel.revalidate();
        showPanel.repaint();
        //******//

        //Settings
        ButtonSettings(menuPanel, new Font("Arial", Font.BOLD, 13), 15, false);
        LabelSettings(headPanel, new Font("Arial", Font.BOLD, 13));

        setSize(1200, 700);
        setMinimumSize(new Dimension(1100, 500));
        setVisible(true);
        setLocationRelativeTo(null);

//        GridBagConstraints Menuconstraints = new GridBagConstraints();
//        constraints.gridwidth = 2;
//        constraints.fill = GridBagConstraints.HORIZONTAL; //
//        add(jPanel, constraints);
    }

    //*****Setting for JButton Function*****
    public void ButtonSettings(Container container, Font font, int IconTextGap, boolean UntextBordered) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setFont(font);
                button.setIconTextGap(IconTextGap);
                button.setFocusPainted(UntextBordered);
            }
            if (component instanceof Container) {
                ButtonSettings((Container) component, font, IconTextGap, UntextBordered);
            }
        }
    }

    //*****Setting for JButton Function*****
    public void LabelSettings(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setFont(font);
            }
            if (component instanceof Container) {
                LabelSettings((Container) component, font);
            }
        }
    }

    public static void main(String[] args) {
        new HomeGUI();
    }

    private Icon setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == sanphamButton) {
            int panelWidth = showPanel.getWidth();
            int panelHeight = showPanel.getHeight();
            SanPhamGUI spGUI = new SanPhamGUI(panelWidth, panelHeight);
            showPanel.removeAll();
            showPanel.add(spGUI, BorderLayout.CENTER);
            spGUI.setVisible(true);

            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == hoadonButton) {
            HoaDonGUI hdGUI = new HoaDonGUI();
            showPanel.removeAll();
            showPanel.add(hdGUI, BorderLayout.CENTER);
            hdGUI.setVisible(true);

            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == khachhangButton) {
            KhachHangGUI khGUI = new KhachHangGUI();
            showPanel.removeAll();
            showPanel.add(khGUI, BorderLayout.CENTER);
            khGUI.setVisible(true);

            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == nhanvienButton) {
            //Nhân viên
        }
        if (btn == thongkeButton) {
            //Thống kê
        }
        if (btn == tacgiaButton) {
            TacGiaGUI tgGUI = new TacGiaGUI();
            showPanel.removeAll();
            showPanel.add(tgGUI, BorderLayout.CENTER);
            tgGUI.setVisible(true);
            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == phanquyenButton) {
            PhanQuyenGUI phanquyenGUI = new PhanQuyenGUI();
            showPanel.removeAll();
            showPanel.add(phanquyenGUI, BorderLayout.CENTER);
            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == theloaiButton) {
            //Thể loại
        }
        if (btn == nhacungcapButton) {
            //Nhà cung cấp
        }
        if (btn == taikhoanButton) {
            TaiKhoanGUI tkGUI = new TaiKhoanGUI();
            showPanel.removeAll();
            showPanel.add(tkGUI, BorderLayout.CENTER);
            showPanel.revalidate();
            showPanel.repaint();
        }
        if (btn == phieunhapButton) {
            //Phiếu nhập
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
