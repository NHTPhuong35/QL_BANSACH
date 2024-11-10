/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package  GUI;

import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomeGUI extends JFrame implements MouseListener {

    private JButton sanphamButton;
    private JButton hoadonButton;
    private JButton khachhangButton;
    private JButton canhanButton;
    private JButton thongkeButton;
    private JButton tacgiaButton;
    private JButton phanquyenButton;
    private JButton theloaiButton;
    private JButton nhacungcapButton;
    private JButton taikhoanButton;
    private JButton phieunhapButton;
    private JButton logoutButton;
    private JButton banhangButton;
    private JPanel menuPanel;
    private JPanel headPanel;
    private JPanel logoPanel;
    private JPanel introductionAndexitPanel;
    private JLabel introduceAppName;
    private JLabel introduceUser;
    private JLabel introduceUserRole;
    private JLabel headAppName;
    private JLabel logoApp, setting;
    private JPanel showPanel;
    private JPanel logoandrolepanel, roleandsetting;
    private GridBagConstraints menuPanelgbc;
    public static TaiKhoanDTO tkUSER;
    
    public HomeGUI(TaiKhoanDTO tkUSER){
        this.tkUSER = tkUSER;
        init();
    }

    public HomeGUI() {
        init();
    }
    
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        //**MENU**
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(Color.decode("#C68642"));
        menuPanelgbc = new GridBagConstraints();
        menuPanelgbc.fill = GridBagConstraints.BOTH;
        menuPanelgbc.gridx = 0;

        logoandrolepanel = new JPanel();
        logoandrolepanel.setLayout(new GridBagLayout());
        logoandrolepanel.setBackground(Color.decode("#C68642"));
        GridBagConstraints logoandrolepanelgbc = new GridBagConstraints();

        logoApp = new JLabel(new ImageIcon(getClass().getResource("/Image/Remove-bg.ai_17287239153771.png")));        
        introduceUser = new JLabel(tkUSER.getTenNV());
        introduceUser.setFont(new Font("Arial", Font.BOLD, 14));
        introduceUserRole = new JLabel(tkUSER.getQuyen().getTenQuyen());
        setting = new JLabel(new ImageIcon(getClass().getResource("/Image/icons8-admin-50.png")));
        
        roleandsetting = new JPanel();
        roleandsetting.add(setting);
        roleandsetting.add(introduceUserRole);
        roleandsetting.setBackground(Color.decode("#C68642"));
        logoandrolepanelgbc.insets = new Insets(7, 7, 7, 7);
        
        logoandrolepanelgbc.gridx = 0;
        logoandrolepanelgbc.gridy = 0;
        logoandrolepanelgbc.gridheight = 2;
        logoandrolepanelgbc.anchor = GridBagConstraints.WEST;
        logoandrolepanel.add(logoApp, logoandrolepanelgbc);
        
        logoandrolepanelgbc.gridheight = 1;

        logoandrolepanelgbc.gridx = 1;
        logoandrolepanelgbc.gridy = 0;
        logoandrolepanelgbc.anchor = GridBagConstraints.WEST;
        logoandrolepanel.add(introduceUser, logoandrolepanelgbc);
        
        logoandrolepanelgbc.insets = new Insets(0, 0, 11, 0); 
        logoandrolepanelgbc.gridy = 1;
        logoandrolepanel.add(roleandsetting, logoandrolepanelgbc);
        
        
        menuPanelgbc.gridy = 0;
        menuPanelgbc.weighty = 0;
        menuPanel.add(logoandrolepanel, gbc);
        menuPanelgbc.fill = GridBagConstraints.HORIZONTAL;
        
        sanphamButton = new JButton("Sản phẩm");
        sanphamButton.setIcon(new ImageIcon(getClass().getResource("/Image/book1.png")));
        sanphamButton.setHorizontalAlignment(SwingConstants.LEFT);
        sanphamButton.setVerticalAlignment(SwingConstants.CENTER);
        sanphamButton.setBorderPainted(false);
        sanphamButton.setBackground(Color.decode("#C68642"));
        sanphamButton.setFocusPainted(false); 
        sanphamButton.addMouseListener(this);
        sanphamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sanphamButton.setBackground(Color.WHITE);
            }

            @Override	
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sanphamButton.setBackground(Color.decode("#C68642"));
            }
        });
        
        banhangButton = new JButton("Bán hàng");
        banhangButton.setIcon(new ImageIcon(getClass().getResource("/Image/banhang.png")));
        banhangButton.setHorizontalAlignment(SwingConstants.LEFT);
        banhangButton.setVerticalAlignment(SwingConstants.CENTER);
        banhangButton.setBorderPainted(false);
        banhangButton.setBackground(Color.decode("#C68642"));
        banhangButton.setFocusPainted(false); 
        banhangButton.addMouseListener(this);
        banhangButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                banhangButton.setBackground(Color.WHITE);
            }

            @Override	
            public void mouseExited(java.awt.event.MouseEvent evt) {
                banhangButton.setBackground(Color.decode("#C68642"));
            }
        });

        hoadonButton = new JButton("Hóa đơn");
        hoadonButton.setIcon(new ImageIcon(getClass().getResource("/Image/bill.png")));
        hoadonButton.setHorizontalAlignment(SwingConstants.LEFT);
        hoadonButton.setVerticalAlignment(SwingConstants.CENTER);
        hoadonButton.setBorderPainted(false);
        hoadonButton.setBackground(Color.decode("#C68642")); 
           hoadonButton.setFocusPainted(false); 
        hoadonButton.addMouseListener(this);
        hoadonButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hoadonButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hoadonButton.setBackground(Color.decode("#C68642"));
            }
        });

        khachhangButton = new JButton("Khách hàng");
        khachhangButton.setIcon(new ImageIcon(getClass().getResource("/Image/customer2.png")));
        khachhangButton.setHorizontalAlignment(SwingConstants.LEFT);
        khachhangButton.setVerticalAlignment(SwingConstants.CENTER);
        khachhangButton.setBorderPainted(false);
        khachhangButton.setBackground(Color.decode("#C68642"));
        khachhangButton.setFocusPainted(false); 
        khachhangButton.addMouseListener(this);
        khachhangButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                khachhangButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                khachhangButton.setBackground(Color.decode("#C68642"));
            }
        });

        canhanButton = new JButton("Cá nhân");
        canhanButton.setIcon(new ImageIcon(getClass().getResource("/Image/homePage.png")));
        canhanButton.setHorizontalAlignment(SwingConstants.LEFT);
        canhanButton.setVerticalAlignment(SwingConstants.CENTER);
        canhanButton.setBorderPainted(false);
        canhanButton.setBackground(Color.decode("#C68642"));
        canhanButton.setFocusPainted(false); 
        canhanButton.addMouseListener(this);
        canhanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                canhanButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canhanButton.setBackground(Color.decode("#C68642"));
            }
        });

        thongkeButton = new JButton("Thống kê");
        thongkeButton.setIcon(new ImageIcon(getClass().getResource("/Image/statistical2.png")));
        thongkeButton.setHorizontalAlignment(SwingConstants.LEFT);
        thongkeButton.setVerticalAlignment(SwingConstants.CENTER);
        thongkeButton.setBorderPainted(false);
        thongkeButton.setBackground(Color.decode("#C68642"));
        thongkeButton.setFocusPainted(false);
        thongkeButton.addMouseListener(this);
        thongkeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongkeButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongkeButton.setBackground(Color.decode("#C68642"));
            }
        });

        tacgiaButton = new JButton("Tác giả");
        tacgiaButton.setIcon(new ImageIcon(getClass().getResource("/Image/author.png")));
        tacgiaButton.setHorizontalAlignment(SwingConstants.LEFT);
        tacgiaButton.setVerticalAlignment(SwingConstants.CENTER);
        tacgiaButton.setBorderPainted(false);
        tacgiaButton.setBackground(Color.decode("#C68642"));
        tacgiaButton.setFocusPainted(false);
        tacgiaButton.addMouseListener(this);
        tacgiaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tacgiaButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tacgiaButton.setBackground(Color.decode("#C68642"));
            }
        });

        phanquyenButton = new JButton("Phân quyền");
        phanquyenButton.setIcon(new ImageIcon(getClass().getResource("/Image/Decentralization.png")));
        phanquyenButton.setHorizontalAlignment(SwingConstants.LEFT);
        phanquyenButton.setVerticalAlignment(SwingConstants.CENTER);
        phanquyenButton.setBorderPainted(false);
        phanquyenButton.setBackground(Color.decode("#C68642"));
        phanquyenButton.setFocusPainted(false);
        phanquyenButton.addMouseListener(this);
        phanquyenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                phanquyenButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                phanquyenButton.setBackground(Color.decode("#C68642"));
            }
        });

        theloaiButton = new JButton("Thể loại");
        theloaiButton.setIcon(new ImageIcon(getClass().getResource("/Image/category2.png")));
        theloaiButton.setHorizontalAlignment(SwingConstants.LEFT);
        theloaiButton.setVerticalAlignment(SwingConstants.CENTER);
        theloaiButton.setBorderPainted(false);
        theloaiButton.setBackground(Color.decode("#C68642"));
        theloaiButton.setFocusPainted(false);
        theloaiButton.addMouseListener(this);
        theloaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                theloaiButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                theloaiButton.setBackground(Color.decode("#C68642"));
            }
        });

        nhacungcapButton = new JButton("Nhà cung cấp");
        nhacungcapButton.setIcon(new ImageIcon(getClass().getResource("/Image/supplier.png")));
        nhacungcapButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhacungcapButton.setVerticalAlignment(SwingConstants.CENTER);
        nhacungcapButton.setBorderPainted(false);
        nhacungcapButton.setBackground(Color.decode("#C68642"));
        nhacungcapButton.setFocusPainted(false);
        nhacungcapButton.addMouseListener(this);
        nhacungcapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nhacungcapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nhacungcapButton.setBackground(Color.decode("#C68642"));
            }
        });

        taikhoanButton = new JButton("Tài khoản");
        taikhoanButton.setIcon(new ImageIcon(getClass().getResource("/Image/account2.png")));
        taikhoanButton.setHorizontalAlignment(SwingConstants.LEFT);
        taikhoanButton.setVerticalAlignment(SwingConstants.CENTER);
        taikhoanButton.setBorderPainted(false);
        taikhoanButton.setBackground(Color.decode("#C68642"));
        taikhoanButton.setFocusPainted(false);
        taikhoanButton.addMouseListener(this);
        taikhoanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taikhoanButton.setBackground(Color.WHITE);
            }

            @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
                taikhoanButton.setBackground(Color.decode("#C68642"));
            }
        });

        phieunhapButton = new JButton("Phiếu nhập");
        phieunhapButton.setIcon(new ImageIcon(getClass().getResource("/Image/bill.png")));
        phieunhapButton.setHorizontalAlignment(SwingConstants.LEFT);
        phieunhapButton.setVerticalAlignment(SwingConstants.CENTER);
        phieunhapButton.setBorderPainted(false);
        phieunhapButton.setBackground(Color.decode("#C68642"));
        phieunhapButton.setFocusPainted(false);
        phieunhapButton.addMouseListener(this);
        phieunhapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                phieunhapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                phieunhapButton.setBackground(Color.decode("#C68642"));
            }
        });

        
        //add into menuPanel
        menuPanelgbc.fill = GridBagConstraints.BOTH;
        menuPanelgbc.weightx = 1.0;
        menuPanelgbc.weighty = 1.0;
        
//        menuPanelgbc.gridy++;
//        menuPanel.add(canhanButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(phieunhapButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(sanphamButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(hoadonButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(tacgiaButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(nhacungcapButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(theloaiButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(khachhangButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(taikhoanButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(phanquyenButton, menuPanelgbc);
//        menuPanelgbc.gridy++;
//        menuPanel.add(thongkeButton, menuPanelgbc);
        
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

        headPanel.setLayout(new GridBagLayout());
        headPanel.setBackground(Color.decode("#F1C27D"));
        GridBagConstraints headPanelgbc = new GridBagConstraints();

        headAppName = new JLabel("BookStore");

        introductionAndexitPanel = new JPanel();
        introductionAndexitPanel.setBackground(Color.decode("#F1C27D"));
        introductionAndexitPanel.setLayout(new GridBagLayout());
        GridBagConstraints introductionAndexitPanelgbc = new GridBagConstraints();

        //Logout Button
        logoutButton = new JButton();
        logoutButton.setIcon(new ImageIcon(getClass().getResource("/Image/icons8-power-off-button-501.png")));
        logoutButton.setBackground(Color.decode("#F1C27D"));
        logoutButton.setBorderPainted(false);
        logoutButton.setSize(30, 30);

        introductionAndexitPanelgbc.gridx = 1;
        introductionAndexitPanelgbc.gridy = 0;
        introductionAndexitPanelgbc.weightx = 0.05;
        introductionAndexitPanelgbc.anchor = GridBagConstraints.EAST;
        introductionAndexitPanel.add(logoutButton, introductionAndexitPanelgbc);

        introductionAndexitPanelgbc.gridx = 0;
        introductionAndexitPanelgbc.gridy = 0;
        introductionAndexitPanelgbc.insets = new Insets(0, 10 , 0 , 0);
        introductionAndexitPanelgbc.anchor = GridBagConstraints.WEST;
        introductionAndexitPanel.add(headAppName, introductionAndexitPanelgbc);

        headPanelgbc.gridx = 0;
        headPanelgbc.gridy = 0;
        headPanelgbc.weightx = 1;
        headPanelgbc.fill = GridBagConstraints.BOTH;;
        headPanel.add(introductionAndexitPanel, headPanelgbc);

        gbc.gridx = 0;  // Column 1
        gbc.gridy = 0;  // Row 0
        gbc.gridwidth = 2; // Take 1 Column
//        gbc.gridheight = 1; // Take 1 Row
        gbc.weightx = 1; //Length of component in a column
        gbc.weighty = 0; //Width of component in a row
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
        gbc.gridwidth = 0;
//        gbc.gridheight = 1;
        gbc.weightx = 0.90;
        gbc.weighty = 0.85;
        add(showPanel, gbc);
        gbc.fill = GridBagConstraints.VERTICAL; //Full fill empty space
        showPanel.revalidate();
        showPanel.repaint();
        //******//

        
        //Settings
        ButtonSettings(menuPanel, new Font("Arial", Font.BOLD, 13), 15, false);
        LabelSettings(headPanel, new Font("Arial", Font.BOLD, 22));

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

    public JButton getBtnLogout() {
            return logoutButton;
    }
        
    public TaiKhoanDTO getTkUser() {
	return tkUSER;
    }
    
    public JButton getBtnSanPham(){
        return sanphamButton;
    }
    
    public JButton getBtnTacGia(){
        return tacgiaButton;
    }
    
    public JButton getBtnLoai(){
        return theloaiButton;
    }
    
    public JButton getBtnNhaCungCap(){
        return nhacungcapButton;
    }
    
    public JButton getBtnKhachHang(){
        return khachhangButton;
    }
    
    public JButton getBtnPhieuNhap(){
        return phieunhapButton;
    }
    
    public JButton getBtnHoaDon(){
        return hoadonButton;
    }
    
    public JButton getBtnThongKe(){
        return thongkeButton;
    }
    
    public JButton getBtnTaiKhoan(){
        return taikhoanButton;
    }
    
    public JButton getBtnPhanQuyen(){
        return phanquyenButton;
    }
    
    public JButton getBtnCaNhan(){
        return canhanButton;
    }
    
    public JButton getBtnBanHang(){
        return banhangButton;
    }
    
    public GridBagConstraints getmenuPanelgbc(){
        return menuPanelgbc;
    }
    
    public JPanel getmenuPanel(){
        return menuPanel;
    }
    
    public JPanel getShowPanel(){
        return showPanel;
    }
    //Ở HomeBUS
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();       
        
        if (btn == hoadonButton) {
            HoaDonGUI billGUI = new HoaDonGUI();
            showPanel.removeAll();
            showPanel.add(billGUI, BorderLayout.CENTER);
            billGUI.setVisible(true);

            showPanel.revalidate();
            showPanel.repaint();
        }
        
        if (btn == banhangButton) {
            BanHangGUI salesgui = new BanHangGUI(tkUSER.getTenDN());
            showPanel.removeAll();
            showPanel.add(salesgui, BorderLayout.CENTER);
            salesgui.setVisible(true);

            showPanel.revalidate();
            showPanel.repaint();
        }

        if (btn == canhanButton) {
            TaiKhoanDN tkDN = new TaiKhoanDN(tkUSER);
            showPanel.removeAll();
            showPanel.add(tkDN, BorderLayout.CENTER);
            showPanel.revalidate();
            showPanel.repaint();
        }
        if(btn == thongkeButton){
            ThongKeGUI tkGUI = new ThongKeGUI();
            showPanel.removeAll();
            showPanel.add(tkGUI, BorderLayout.CENTER);
            tkGUI.setVisible(true);
            showPanel.revalidate(); 
            showPanel.repaint(); 

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
