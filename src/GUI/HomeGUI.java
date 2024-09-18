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

public class HomeGUI extends JFrame {
    private JButton SanPhamButton;
    private JButton HoaDonButton;
    private JButton KhachHangButton;
    private JButton NhanVienButton;
    private JButton ThongKeButton;
    private JButton TacGiaButton;
    private JButton PhanQuyenButton;
    private JButton TheLoaiButton;
    private JButton NhaCungCapButton;
    private JButton TaiKhoanButton;
    private JButton PhieuNhapButton;
    private JButton LogoutButton;
    private JPanel MenuPanel;
    private JPanel HeadPanel;
    private JPanel LogoPanel;
    private JPanel IntroductionAndExitPanel;
    private JLabel IntroduceAppName;
    private JLabel IntroduceUser;
    private JLabel IntroduceUserRole;
    private JLabel AppName;
    private JLabel HeadAppName;
    private JLabel LogoApp;
    private JPanel ShowPanel;
    
    public HomeGUI(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //**MENU**
        
        MenuPanel = new JPanel();
        MenuPanel.setLayout(new GridLayout(0, 1, 0, 0));
        MenuPanel.setBackground(Color.decode("#98DCE2"));
        
        AppName = new JLabel("Cửa hàng bán sách nhóm 2");
        Font AppNamefont = new Font("Arial", Font.BOLD, 13);
        AppName.setFont(AppNamefont);
        AppName.setHorizontalAlignment(SwingConstants.CENTER); 
        AppName.setVerticalAlignment(SwingConstants.CENTER);
        
        SanPhamButton = new JButton("Sản phẩm");
        SanPhamButton.setIcon(new ImageIcon(getClass().getResource("/Image/product.png")));
        SanPhamButton.setHorizontalAlignment(SwingConstants.LEFT);
        SanPhamButton.setVerticalAlignment(SwingConstants.CENTER);
        SanPhamButton.setBorderPainted(false);
        SanPhamButton.setBackground(Color.decode("#98DCE2"));
        SanPhamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SanPhamButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SanPhamButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        HoaDonButton = new JButton("Hóa đơn");
        HoaDonButton.setIcon(new ImageIcon(getClass().getResource("/Image/bill.png")));
        HoaDonButton.setHorizontalAlignment(SwingConstants.LEFT);
        HoaDonButton.setVerticalAlignment(SwingConstants.CENTER);
        HoaDonButton.setBorderPainted(false);
        HoaDonButton.setBackground(Color.decode("#98DCE2"));
        HoaDonButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoaDonButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HoaDonButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        KhachHangButton = new JButton("Khách hàng");
        KhachHangButton.setIcon(new ImageIcon(getClass().getResource("/Image/customer.png")));
        KhachHangButton.setHorizontalAlignment(SwingConstants.LEFT);
        KhachHangButton.setVerticalAlignment(SwingConstants.CENTER);
        KhachHangButton.setBorderPainted(false);
        KhachHangButton.setBackground(Color.decode("#98DCE2"));
        KhachHangButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KhachHangButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KhachHangButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        NhanVienButton = new JButton("Nhân viên");               
        NhanVienButton.setIcon(new ImageIcon(getClass().getResource("/Image/staff.png")));
        NhanVienButton.setHorizontalAlignment(SwingConstants.LEFT);
        NhanVienButton.setVerticalAlignment(SwingConstants.CENTER);
        NhanVienButton.setBorderPainted(false);
        NhanVienButton.setBackground(Color.decode("#98DCE2"));
        NhanVienButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NhanVienButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NhanVienButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        ThongKeButton = new JButton("Thống kê");
        ThongKeButton.setIcon(new ImageIcon(getClass().getResource("/Image/statistical.png")));
        ThongKeButton.setHorizontalAlignment(SwingConstants.LEFT);
        ThongKeButton.setVerticalAlignment(SwingConstants.CENTER);
        ThongKeButton.setBorderPainted(false);
        ThongKeButton.setBackground(Color.decode("#98DCE2"));
        ThongKeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ThongKeButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ThongKeButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        TacGiaButton = new JButton("Tác giả");
        TacGiaButton.setIcon(new ImageIcon(getClass().getResource("/Image/author.png")));
        TacGiaButton.setHorizontalAlignment(SwingConstants.LEFT);
        TacGiaButton.setVerticalAlignment(SwingConstants.CENTER);
        TacGiaButton.setBorderPainted(false);
        TacGiaButton.setBackground(Color.decode("#98DCE2"));
        TacGiaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TacGiaButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TacGiaButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        PhanQuyenButton = new JButton("Phân quyền");              
        PhanQuyenButton.setIcon(new ImageIcon(getClass().getResource("/Image/Decentralization.png")));
        PhanQuyenButton.setHorizontalAlignment(SwingConstants.LEFT);
        PhanQuyenButton.setVerticalAlignment(SwingConstants.CENTER);
        PhanQuyenButton.setBorderPainted(false);
        PhanQuyenButton.setBackground(Color.decode("#98DCE2"));
        PhanQuyenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PhanQuyenButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PhanQuyenButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        TheLoaiButton = new JButton("Thể loại");
        TheLoaiButton.setIcon(new ImageIcon(getClass().getResource("/Image/category.png")));
        TheLoaiButton.setHorizontalAlignment(SwingConstants.LEFT);
        TheLoaiButton.setVerticalAlignment(SwingConstants.CENTER);
        TheLoaiButton.setBorderPainted(false);
        TheLoaiButton.setBackground(Color.decode("#98DCE2"));
        TheLoaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TheLoaiButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TheLoaiButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        NhaCungCapButton = new JButton("Nhà cung cấp");
        NhaCungCapButton.setIcon(new ImageIcon(getClass().getResource("/Image/supplier.png")));
        NhaCungCapButton.setHorizontalAlignment(SwingConstants.LEFT);
        NhaCungCapButton.setVerticalAlignment(SwingConstants.CENTER);
        NhaCungCapButton.setBorderPainted(false);
        NhaCungCapButton.setBackground(Color.decode("#98DCE2"));
        NhaCungCapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NhaCungCapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NhaCungCapButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        TaiKhoanButton = new JButton("Tài khoản");
        TaiKhoanButton.setIcon(new ImageIcon(getClass().getResource("/Image/account.png")));
        TaiKhoanButton.setHorizontalAlignment(SwingConstants.LEFT);
        TaiKhoanButton.setVerticalAlignment(SwingConstants.CENTER);
        TaiKhoanButton.setBorderPainted(false);
        TaiKhoanButton.setBackground(Color.decode("#98DCE2"));
        TaiKhoanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TaiKhoanButton.setBackground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                TaiKhoanButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        PhieuNhapButton = new JButton("Phiếu nhập");
        PhieuNhapButton.setIcon(new ImageIcon(getClass().getResource("/Image/goods-receipt.png")));
        PhieuNhapButton.setHorizontalAlignment(SwingConstants.LEFT);
        PhieuNhapButton.setVerticalAlignment(SwingConstants.CENTER);
        PhieuNhapButton.setBorderPainted(false);
        PhieuNhapButton.setBackground(Color.decode("#98DCE2"));
        PhieuNhapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PhieuNhapButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PhieuNhapButton.setBackground(Color.decode("#98DCE2"));
            }
        });
        
        //add into Menupanel
        MenuPanel.add(AppName);
        MenuPanel.add(SanPhamButton);
        MenuPanel.add(HoaDonButton);
        MenuPanel.add(KhachHangButton);
        MenuPanel.add(NhanVienButton);
        MenuPanel.add(ThongKeButton);
        MenuPanel.add(TacGiaButton);
        MenuPanel.add(PhanQuyenButton);
        MenuPanel.add(TheLoaiButton);
        MenuPanel.add(NhaCungCapButton);
        MenuPanel.add(TaiKhoanButton);
        MenuPanel.add(PhieuNhapButton);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
//        gbc.gridwidth = 0; 
//        gbc.gridheight = 1; 
        gbc.fill = GridBagConstraints.BOTH; // Full fill space
        gbc.weightx = 0.10 ;
        gbc.weighty = 0.90;
        add(MenuPanel, gbc);
        
        //***
      
        //**HEADER**
        HeadPanel = new JPanel();
        LogoPanel = new JPanel();
        
        HeadPanel.setLayout(new GridBagLayout());
        HeadPanel.setBackground(Color.decode("#98DCE2"));
        GridBagConstraints HeadPanelgbc = new GridBagConstraints();
        
        HeadAppName = new JLabel("Cửa hàng bán sách nhóm 2");     
        
        IntroductionAndExitPanel = new JPanel();
        IntroductionAndExitPanel.setBackground(Color.white);
        IntroductionAndExitPanel.setLayout(new GridBagLayout());
        GridBagConstraints IntroductionAndExitPanelgbc = new GridBagConstraints();
        
        //Logout Button
        LogoutButton = new JButton();
        LogoutButton.setIcon(new ImageIcon(getClass().getResource("/Image/icon-off.png")));
        LogoutButton.setBackground(Color.white);
        LogoutButton.setBorderPainted(false);
        LogoutButton.setSize(30,30);
        
        IntroductionAndExitPanelgbc.gridx = 0;
        IntroductionAndExitPanelgbc.gridy = 0;
        IntroductionAndExitPanelgbc.weightx = 0.2;
        IntroductionAndExitPanelgbc.anchor = GridBagConstraints.WEST;
        IntroductionAndExitPanelgbc.insets = new Insets(0, 10, 0, 0); // Margin top left bottom right
        IntroductionAndExitPanel.add(HeadAppName, IntroductionAndExitPanelgbc);
        
        IntroductionAndExitPanelgbc.gridx = 1;
        IntroductionAndExitPanelgbc.gridy = 0;
        IntroductionAndExitPanelgbc.weightx = 0.05;
        IntroductionAndExitPanelgbc.anchor = GridBagConstraints.EAST;
        IntroductionAndExitPanel.add(LogoutButton, IntroductionAndExitPanelgbc);
        
        IntroduceUser = new JLabel("Xin chào: Admin12345");
        IntroduceUserRole = new JLabel("Vai trò: Admin");
             
        LogoApp = new JLabel();
        LogoApp.setIcon(new ImageIcon(getClass().getResource("/Image/icon-main.png")));
        LogoPanel.setBackground(Color.decode("#98DCE2"));
        LogoPanel.add(LogoApp);
       
        HeadPanelgbc.gridx = 0;
        HeadPanelgbc.gridy = 0;
        HeadPanelgbc.weightx = 0.2;
        HeadPanelgbc.fill = GridBagConstraints.BOTH;
        HeadPanel.add(IntroductionAndExitPanel,HeadPanelgbc);
        
        HeadPanelgbc.gridx = 0;
        HeadPanelgbc.gridy = 1;
        HeadPanelgbc.weightx = 0.2;
        HeadPanelgbc.fill = GridBagConstraints.BOTH;
        HeadPanelgbc.insets = new Insets(20, 40, 0, 0);
        HeadPanel.add(IntroduceUser,HeadPanelgbc);
        
        HeadPanelgbc.gridx = 0;
        HeadPanelgbc.gridy = 2;
        HeadPanelgbc.weighty = 1;
        HeadPanelgbc.fill = GridBagConstraints.BOTH;
        HeadPanelgbc.insets = new Insets(0, 40, 0, 0); // Margin top left bottom right
        HeadPanel.add(IntroduceUserRole,HeadPanelgbc);
          
        gbc.gridx = 0;
        gbc.gridy = 0;  
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0.10;
        gbc.weighty = 0.10;
        gbc.fill = GridBagConstraints.BOTH;
        add(LogoPanel, gbc);
        
        gbc.gridx = 1;  // Column 0
        gbc.gridy = 0;  // Row 0
//        gbc.gridwidth = 1; // Take 1 Column
//        gbc.gridheight = 1; // Take 1 Row
        gbc.weightx = 0.90; //Length of component in a column
        gbc.weighty = 0.15; //Width of component in a row
        gbc.fill = GridBagConstraints.BOTH; //Full fill empty space
        add(HeadPanel, gbc);
     
        //***
        
        //**SHOW PANEL**
        
        ShowPanel = new JPanel();
        ShowPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0.90;
        gbc.weighty = 0.85;
        add(ShowPanel, gbc);
        
        ShowPanel.revalidate();
        ShowPanel.repaint();
        //******//
        
        
        //Settings
        ButtonSettings(MenuPanel, new Font("Arial", Font.BOLD, 13), 15, false);
        LabelSettings(HeadPanel, new Font("Arial", Font.BOLD, 13));
        
        setSize(1100, 700);
        setMinimumSize(new Dimension(1100, 800));       
        setVisible(true);
        setLocationRelativeTo(null);
        
//        GridBagConstraints Menuconstraints = new GridBagConstraints();
//        constraints.gridwidth = 2;
//        constraints.fill = GridBagConstraints.HORIZONTAL; //lấp đầy theo chiều
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
}
