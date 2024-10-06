/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*; // Removed unused import
import javax.swing.table.TableCellRenderer;
import GUI.expands.DateRangePickerPanel;
import BUS.BangThongKeBUS;
import utils.MoneyFormatter;

/**
 *
 * @author nhatm
 */

public class ThongKeGUI extends JPanel{
    private double totalincome;
    private double totalimportprice;
    private int totalbooks;
    private int totalHoaDon;
    private JPanel pickdayPanel;
    private JButton tim;
    
    public ThongKeGUI(){
        JPanel setDatePanel = new JPanel();
        JPanel thongkePanel = new JPanel();
        thongkePanel.setPreferredSize(new Dimension(800, 20));
        JPanel bangthongtinPanel = new JPanel();
        
        tim = new JButton("Tìm");

        JPanel sohoadonPanel = new JPanel();
        JPanel tongtienbanduocPanel = new JPanel();
        JPanel tongtiennhaphangPanel = new JPanel();
        JPanel tienlaiPanel = new JPanel();
        JPanel sosachbanduocPanel = new JPanel();
        
        JLabel sohoadon = new JLabel();
        JLabel tongtienbanduoc = new JLabel();
        JLabel tongtiennhaphang = new JLabel();
        JLabel tienlai = new JLabel();
        JLabel sosachbanduoc= new JLabel();
        
        //Quan trọng
        DateRangePickerPanel datepickerpanel = new DateRangePickerPanel(); //Tạo ngày
        BangThongKeBUS bangthongkebus = new BangThongKeBUS();//Controller
        
        totalincome = bangthongkebus.getTotalIncome(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        totalimportprice = bangthongkebus.getTotalImportPrice(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        totalbooks = bangthongkebus.getTotalBooks(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        totalHoaDon = bangthongkebus.getTotalHoaDon(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        
        
        tongtienbanduoc = new JLabel("Tổng tiền: " + MoneyFormatter.formatToVND(totalincome));
        tongtienbanduoc.setPreferredSize(new Dimension(120, 20));
        
        tongtiennhaphang = new JLabel("Tổng tiền nhập: " + MoneyFormatter.formatToVND(totalimportprice));
        tongtiennhaphang.setPreferredSize(new Dimension(120, 20));

        tienlai = new JLabel("Tiền lãi: " + MoneyFormatter.formatToVND(totalincome - totalimportprice));
        tienlai.setPreferredSize(new Dimension(120, 20));

        sosachbanduoc = new JLabel("Số sách bán: " + totalbooks);
        sosachbanduoc.setPreferredSize(new Dimension(120, 20));

        sohoadon = new JLabel("Số hóa đơn: " + totalHoaDon);
        sohoadon.setPreferredSize(new Dimension(120, 20));
        
        JPanel sohoadontext = new JPanel();
                sohoadontext.setLayout(new FlowLayout(FlowLayout.CENTER));
                sohoadontext.setBackground(Color.decode("#E0E0E0"));
        JPanel tongtienbanduoctext = new JPanel();
                tongtienbanduoctext.setLayout(new FlowLayout(FlowLayout.CENTER));
                tongtienbanduoctext.setBackground(Color.decode("#E0E0E0"));
        JPanel tongtiennhaphangtext = new JPanel();
                tongtiennhaphangtext.setLayout(new FlowLayout(FlowLayout.CENTER));
                tongtiennhaphangtext.setBackground(Color.decode("#E0E0E0"));
        JPanel tienlaitext = new JPanel();
                tienlaitext.setLayout(new FlowLayout(FlowLayout.CENTER));
                tienlaitext.setBackground(Color.decode("#E0E0E0"));
        JPanel sosachbanduoctext = new JPanel();
                sosachbanduoctext.setLayout(new FlowLayout(FlowLayout.CENTER));
                sosachbanduoctext.setBackground(Color.decode("#E0E0E0"));

        
        JPanel sohoadonimage = new JPanel();
        sohoadonimage.setBackground(Color.decode("#A4D6E1"));
        JPanel tongtienbanduocimage = new JPanel();
        tongtienbanduocimage.setBackground(Color.decode("#B3E5B3"));
        JPanel tongtiennhaphangimage = new JPanel();
        tongtiennhaphangimage.setBackground(Color.decode("#EAB8E4"));
        JPanel tienlaiimage = new JPanel();
        tienlaiimage.setBackground(Color.decode("#FFCCB3"));
        JPanel sosachbanduocimage = new JPanel();
        sosachbanduocimage.setBackground(Color.decode("#FFB3A8"));
        
        sohoadontext.add(sohoadon);
        tongtienbanduoctext.add(tongtienbanduoc);
        tongtiennhaphangtext.add(tongtiennhaphang);
        tienlaitext.add(tienlai);
        sosachbanduoctext.add(sosachbanduoc);
        
        JLabel logosohoadon = new JLabel();
        logosohoadon.setIcon(new ImageIcon(getClass().getResource("/Image/money_50px.png")));
        
        
        JLabel logotongtienbanduoc = new JLabel();
        logotongtienbanduoc.setIcon(new ImageIcon(getClass().getResource("/Image/bill_50px.png")));
        
        
        JLabel logotongtiennhaphang = new JLabel();
        logotongtiennhaphang.setIcon(new ImageIcon(getClass().getResource("/Image/money_50px.png")));
        
        
        JLabel logotienlai = new JLabel();
        logotienlai.setIcon(new ImageIcon(getClass().getResource("/Image/money_50px.png")));
        
        
        JLabel logososachbanduoc = new JLabel();
        logososachbanduoc.setIcon(new ImageIcon(getClass().getResource("/Image/money_50px.png")));
        
        
        sohoadonPanel.setLayout(new GridBagLayout());
        sohoadonPanel.setBackground(Color.WHITE);
        GridBagConstraints sohoadonPanelgbc = new GridBagConstraints();
        
        tongtienbanduocPanel.setLayout(new GridBagLayout());
        tongtienbanduocPanel.setBackground(Color.WHITE);
        GridBagConstraints tongtienbanduocPanelgbc = new GridBagConstraints();
        
        tongtiennhaphangPanel.setLayout(new GridBagLayout());
        tongtiennhaphangPanel.setBackground(Color.WHITE);
        GridBagConstraints tongtiennhaphangPanelgbc = new GridBagConstraints();
        
        tienlaiPanel.setLayout(new GridBagLayout());
        tienlaiPanel.setBackground(Color.WHITE);
        GridBagConstraints tienlaiPanelgbc = new GridBagConstraints();
        
        sosachbanduocPanel.setLayout(new GridBagLayout());
        sosachbanduocPanel.setBackground(Color.WHITE);
        GridBagConstraints sosachbanduocPanelgbc = new GridBagConstraints();
        
        sohoadonimage.add(logosohoadon);
        tongtienbanduocimage.add(logotongtienbanduoc);
        tongtiennhaphangimage.add(logotongtiennhaphang);
        tienlaiimage.add(logotienlai);
        sosachbanduocimage.add(logososachbanduoc);
        
        //số hóa đơn
        sohoadonPanelgbc.gridx = 0;
        sohoadonPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        sohoadonPanelgbc.weightx = 0.4; 
        sohoadonPanelgbc.weighty = 1;
        sohoadonPanelgbc.fill = GridBagConstraints.BOTH;
        sohoadonPanelgbc.anchor = GridBagConstraints.CENTER;
        sohoadonPanel.add(sohoadonimage, sohoadonPanelgbc);
        
        sohoadonPanelgbc.gridx = 1;
        sohoadonPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        sohoadonPanelgbc.weightx = 1; 
        sohoadonPanelgbc.weighty = 1; 
        sohoadonPanelgbc.fill = GridBagConstraints.BOTH;
        sohoadonPanel.add(sohoadontext,sohoadonPanelgbc);
        
        //Tổng tiền bán được
        tongtienbanduocPanelgbc.gridx = 0;
        tongtienbanduocPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tongtienbanduocPanelgbc.weightx = 0.4; 
        tongtienbanduocPanelgbc.weighty = 1;
        tongtienbanduocPanelgbc.fill = GridBagConstraints.BOTH;
        tongtienbanduocPanelgbc.anchor = GridBagConstraints.CENTER;
        tongtienbanduocPanel.add(tongtienbanduocimage, tongtienbanduocPanelgbc);
        
        tongtienbanduocPanelgbc.gridx = 1;
        tongtienbanduocPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tongtienbanduocPanelgbc.weightx = 1; 
        tongtienbanduocPanelgbc.weighty = 1;
        tongtienbanduocPanelgbc.fill = GridBagConstraints.BOTH;
        tongtienbanduocPanel.add(tongtienbanduoctext, tongtienbanduocPanelgbc);
        
        //Tổng tiền nhập hàng
        tongtiennhaphangPanelgbc.gridx = 0;
        tongtiennhaphangPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tongtiennhaphangPanelgbc.weightx = 0.4; 
        tongtiennhaphangPanelgbc.weighty = 1; 
        tongtiennhaphangPanelgbc.fill = GridBagConstraints.BOTH;
        tongtiennhaphangPanelgbc.anchor = GridBagConstraints.CENTER;
        tongtiennhaphangPanel.add(tongtiennhaphangimage, tongtiennhaphangPanelgbc);
        
        tongtiennhaphangPanelgbc.gridx = 1;
        tongtiennhaphangPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tongtiennhaphangPanelgbc.weightx = 1; 
        tongtiennhaphangPanelgbc.weighty = 1; 
        tongtiennhaphangPanelgbc.fill = GridBagConstraints.BOTH;
        tongtiennhaphangPanel.add(tongtiennhaphangtext, tongtiennhaphangPanelgbc);
        
        //Tiền lãi
        tienlaiPanelgbc.gridx = 0;
        tienlaiPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tienlaiPanelgbc.weightx = 0.4; 
        tienlaiPanelgbc.weighty = 1;
        tienlaiPanelgbc.fill = GridBagConstraints.BOTH;
        tienlaiPanelgbc.anchor = GridBagConstraints.CENTER;
        tienlaiPanel.add(tienlaiimage, tienlaiPanelgbc);
        
        tienlaiPanelgbc.gridx = 1;
        tienlaiPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tienlaiPanelgbc.weightx = 1; 
        tienlaiPanelgbc.weighty = 1;
        tienlaiPanelgbc.fill = GridBagConstraints.BOTH;
        tienlaiPanel.add(tienlaitext, tienlaiPanelgbc);
        
        //Số sách bán được
        sosachbanduocPanelgbc.gridx = 0;
        sosachbanduocPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        sosachbanduocPanelgbc.weightx = 0.4; 
        sosachbanduocPanelgbc.weighty = 1; 
        sosachbanduocPanelgbc.anchor = GridBagConstraints.CENTER;
        sosachbanduocPanelgbc.fill = GridBagConstraints.BOTH;
        sosachbanduocPanel.add(sosachbanduocimage, sosachbanduocPanelgbc);
        
        sosachbanduocPanelgbc.gridx = 1;
        sosachbanduocPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        sosachbanduocPanelgbc.weightx = 1; 
        sosachbanduocPanelgbc.weighty = 1;
        sosachbanduocPanelgbc.fill = GridBagConstraints.BOTH;
        sosachbanduocPanel.add(sosachbanduoctext, sosachbanduocPanelgbc);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        setDatePanel.setBackground(Color.RED);
        thongkePanel.setBackground(Color.YELLOW);
        bangthongtinPanel.setBackground(Color.GREEN);
        
        bangthongtinPanel.setLayout(new GridBagLayout());
        GridBagConstraints bangthongtinPanelgbc = new GridBagConstraints();
         
        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        bangthongtinPanelgbc.weightx = 1; 
        bangthongtinPanelgbc.weighty = 1; 
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;
        bangthongtinPanel.add(tongtienbanduocPanel, bangthongtinPanelgbc);
        
        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 1; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        bangthongtinPanelgbc.weightx = 1; 
        bangthongtinPanelgbc.weighty = 1; 
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;
        bangthongtinPanel.add(tongtiennhaphangPanel, bangthongtinPanelgbc);
        
        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 2; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        bangthongtinPanelgbc.weightx = 1; 
        bangthongtinPanelgbc.weighty = 1; 
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;
        bangthongtinPanel.add(tienlaiPanel, bangthongtinPanelgbc);
        
        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 3; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        bangthongtinPanelgbc.weightx = 1; 
        bangthongtinPanelgbc.weighty = 1; 
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;
        bangthongtinPanel.add(sosachbanduocPanel, bangthongtinPanelgbc);
        
        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 4; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        bangthongtinPanelgbc.weightx = 1; 
        bangthongtinPanelgbc.weighty = 1; 
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;
        bangthongtinPanel.add(sohoadonPanel, bangthongtinPanelgbc);
        
//        tongtienbanduoc.setVerticalAlignment(SwingConstants.CENTER);
//        tongtiennhaphang.setVerticalAlignment(SwingConstants.CENTER);
//        tienlai.setVerticalAlignment(SwingConstants.CENTER);
//        sosachbanduoc.setVerticalAlignment(SwingConstants.CENTER);
//        sohoadon.setVerticalAlignment(SwingConstants.CENTER);
//        
//        tongtienbanduoc.setHorizontalAlignment(SwingConstants.CENTER);
//        tongtiennhaphang.setHorizontalAlignment(SwingConstants.CENTER);
//        tienlai.setHorizontalAlignment(SwingConstants.CENTER);
//        sosachbanduoc.setHorizontalAlignment(SwingConstants.CENTER);
//        sohoadon.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        gbc.gridx = 1;
        gbc.gridy = 1; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 1; 
        gbc.weighty = 1; 
        gbc.fill = GridBagConstraints.BOTH;
        add(thongkePanel, gbc);
        
        
        
        bangthongkebus.show(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd());
        
        tim.addActionListener(e -> {
            bangthongkebus.show(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd());
            thongkePanel.revalidate();
            thongkePanel.repaint();
        });
  
        //Test ngày
        System.out.println(datepickerpanel.getDateStart());
        System.out.println(datepickerpanel.getDateEnd());
        //
        
        gbc.gridx = 0;
        gbc.gridy = 1;
//        gbc.gridwidth = 1; 
//        gbc.gridheight = 1;
        gbc.weightx = 0.15; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(bangthongtinPanel, gbc);
        
        pickdayPanel = new JPanel();
        pickdayPanel.add(datepickerpanel);
        pickdayPanel.add(tim);
        
        gbc.gridx = 0;  // Column 1
        gbc.gridy = 0;  // Row 0
        gbc.gridwidth = 2; // Take 2 Column
        gbc.gridheight = 1; // Take 1 Row
        gbc.weightx = 1; //Length of component in a column
        gbc.weighty = 0.01; //Width of component in a row
        gbc.fill = GridBagConstraints.BOTH; //Full fill empty space
        add(pickdayPanel, gbc);
    }
    
    public static void main(String[] args) {
        // Thiết lập giao diện Swing chạy trên Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo frame chứa ThongKeGUI
                JFrame frame = new JFrame("Test ThongKeGUI");
                
                // Thiết lập kích thước cho frame
                frame.setSize(800, 600);
                
                // Thiết lập hành động đóng cửa sổ khi nhấn nút "X"
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // Thêm ThongKeGUI vào frame
                ThongKeGUI thongKeGUI = new ThongKeGUI();
                frame.add(thongKeGUI);
                
                // Hiển thị frame
                frame.setVisible(true);
            }
        });
    }
    
}
