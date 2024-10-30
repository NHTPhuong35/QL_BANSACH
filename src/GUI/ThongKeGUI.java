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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utils.MoneyFormatter;

/**
 *
 * @author nhatm
 */

public class ThongKeGUI extends JPanel{
    private double totalincome;
    private int totalreturn;
    private int totalbooks;
    private int totalHoaDon;
    private JPanel pickdayPanel;
    private JButton tim;
    private JLabel chitietbutton;
    private JTable ThongKeTable;
    private DefaultTableModel tableModel;
    
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
     
//        totalincome = bangthongkebus.getTotalIncome(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
//        totalreturn = bangthongkebus.getSoHoaDonHuy(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
//        totalbooks = bangthongkebus.getTotalBooks(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
//        totalHoaDon = bangthongkebus.getTotalHoaDon(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        
        JPanel tongtienbanduocpanel = new JPanel();
        tongtienbanduocpanel.setBackground(Color.decode("#A3B4B5"));
        tongtienbanduocpanel.setLayout(new BoxLayout(tongtienbanduocpanel, BoxLayout.Y_AXIS));
        JLabel tongtienbanduocTitle = new JLabel("Tổng doanh thu:");
        JLabel tongtienbanduocTotal = new JLabel(MoneyFormatter.formatToVND(totalincome));
        tongtienbanduocpanel.add(tongtienbanduocTitle);
        tongtienbanduocpanel.add(tongtienbanduocTotal);

        JPanel trahangpanel = new JPanel();
        trahangpanel.setBackground(Color.decode("#A3B4B5"));
        trahangpanel.setLayout(new BoxLayout(trahangpanel, BoxLayout.Y_AXIS));
        JLabel trahangTitle = new JLabel("Trả hàng:");
        JLabel trahangTotal = new JLabel(String.valueOf(totalreturn));
        trahangpanel.add(trahangTitle);
        trahangpanel.add(trahangTotal);

        JPanel sosachbanduocpanel = new JPanel();
        sosachbanduocpanel.setBackground(Color.decode("#A3B4B5"));
        sosachbanduocpanel.setLayout(new BoxLayout(sosachbanduocpanel, BoxLayout.Y_AXIS));
        JLabel sosachbanduocTitle = new JLabel("Số sách bán:");
        JLabel sosachbanduocTotal = new JLabel(String.valueOf(totalbooks));
        sosachbanduocpanel.add(sosachbanduocTitle);
        sosachbanduocpanel.add(sosachbanduocTotal);
        
        JPanel sohoadonpanel = new JPanel();
        sohoadonpanel.setBackground(Color.decode("#A3B4B5"));
        sohoadonpanel.setLayout(new BoxLayout(sohoadonpanel, BoxLayout.Y_AXIS));
        JLabel sohoadonTitle = new JLabel("Số hóa đơn:");
        JLabel sohoadonTotal = new JLabel(String.valueOf(totalHoaDon));
        sohoadonpanel.add(sohoadonTitle);
        sohoadonpanel.add(sohoadonTotal);
        
        JPanel sohoadontext = new JPanel();
                sohoadontext.setLayout(new FlowLayout(FlowLayout.CENTER));
                sohoadontext.setBackground(Color.decode("#A3B4B5"));
        JPanel tongtienbanduoctext = new JPanel();
                tongtienbanduoctext.setLayout(new FlowLayout(FlowLayout.CENTER));
                tongtienbanduoctext.setBackground(Color.decode("#A3B4B5"));
        JPanel trahangtext = new JPanel();
                trahangtext.setLayout(new FlowLayout(FlowLayout.CENTER));
                trahangtext.setBackground(Color.decode("#A3B4B5"));
        JPanel sosachbanduoctext = new JPanel();
                sosachbanduoctext.setLayout(new FlowLayout(FlowLayout.CENTER));
                sosachbanduoctext.setBackground(Color.decode("#A3B4B5"));

        
        JPanel sohoadonimage = new JPanel();
        sohoadonimage.setBackground(Color.decode("#A3B4B5"));
        
        JPanel tongtienbanduocimage = new JPanel();
        tongtienbanduocimage.setBackground(Color.decode("#A3B4B5"));
        
        JPanel trahangimage = new JPanel();
        trahangimage.setBackground(Color.decode("#A3B4B5"));
        
        JPanel sosachbanduocimage = new JPanel();
        sosachbanduocimage.setBackground(Color.decode("#A3B4B5"));
        
        sohoadontext.add(sohoadonpanel);
        tongtienbanduoctext.add(tongtienbanduocpanel);
        trahangtext.add(trahangpanel);
        sosachbanduoctext.add(sosachbanduocpanel);
        
        JLabel logosohoadon = new JLabel();
        logosohoadon.setIcon(new ImageIcon(getClass().getResource("/Image/bill2.png")));
        
        
        JLabel logotongtienbanduoc = new JLabel();
        logotongtienbanduoc.setIcon(new ImageIcon(getClass().getResource("/Image/money.png")));
    
        
        JLabel logotrahang = new JLabel();
        logotrahang.setIcon(new ImageIcon(getClass().getResource("/Image/undo.png")));
        
        
        JLabel logososachbanduoc = new JLabel();
        logososachbanduoc.setIcon(new ImageIcon(getClass().getResource("/Image/book-tk.png")));
        
        
        sohoadonPanel.setLayout(new GridBagLayout());
        sohoadonPanel.setBackground(Color.WHITE);
        GridBagConstraints sohoadonPanelgbc = new GridBagConstraints();
        
        tongtienbanduocPanel.setLayout(new GridBagLayout());
        tongtienbanduocPanel.setBackground(Color.WHITE);
        GridBagConstraints tongtienbanduocPanelgbc = new GridBagConstraints();
     
        tienlaiPanel.setLayout(new GridBagLayout());
        tienlaiPanel.setBackground(Color.WHITE);
        GridBagConstraints tienlaiPanelgbc = new GridBagConstraints();
        
        sosachbanduocPanel.setLayout(new GridBagLayout());
        sosachbanduocPanel.setBackground(Color.WHITE);
        GridBagConstraints sosachbanduocPanelgbc = new GridBagConstraints();
        
        sohoadonimage.add(logosohoadon);
        tongtienbanduocimage.add(logotongtienbanduoc);
        trahangimage.add(logotrahang);
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
          
        //Trả hàng
        tienlaiPanelgbc.gridx = 0;
        tienlaiPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tienlaiPanelgbc.weightx = 0.4; 
        tienlaiPanelgbc.weighty = 1;
        tienlaiPanelgbc.fill = GridBagConstraints.BOTH;
        tienlaiPanelgbc.anchor = GridBagConstraints.CENTER;
        tienlaiPanel.add(trahangimage, tienlaiPanelgbc);
        
        tienlaiPanelgbc.gridx = 1;
        tienlaiPanelgbc.gridy = 0; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        tienlaiPanelgbc.weightx = 1; 
        tienlaiPanelgbc.weighty = 1;
        tienlaiPanelgbc.fill = GridBagConstraints.BOTH;
        tienlaiPanel.add(trahangtext, tienlaiPanelgbc);
        
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
        
        bangthongtinPanel.setLayout(new GridBagLayout());
        GridBagConstraints bangthongtinPanelgbc = new GridBagConstraints();
         
        bangthongtinPanelgbc.insets = new Insets(5, 10, 5, 10);
        bangthongtinPanelgbc.weightx = 1;
        bangthongtinPanelgbc.weighty = 1;
        bangthongtinPanelgbc.fill = GridBagConstraints.BOTH;

        bangthongtinPanelgbc.gridx = 0;
        bangthongtinPanelgbc.gridy = 0;
        bangthongtinPanel.add(tongtienbanduocPanel, bangthongtinPanelgbc);

        bangthongtinPanelgbc.gridx = 1;
        bangthongtinPanel.add(tienlaiPanel, bangthongtinPanelgbc);

        bangthongtinPanelgbc.gridx = 2;
        bangthongtinPanel.add(sosachbanduocPanel, bangthongtinPanelgbc);

        bangthongtinPanelgbc.gridx = 3;
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
        
        JPanel tongquanpanel = new JPanel();
        tongquanpanel.add(new JLabel("Tổng quan"));
        chitietbutton = new JLabel("<html><u>Chi tiết</u></html>");
        chitietbutton.setForeground(Color.decode("#8CC44F"));
        tongquanpanel.add(chitietbutton);
        
        gbc.gridx = 0;
        gbc.gridy = 2; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0; 
        gbc.weighty = 0; 
        gbc.fill = GridBagConstraints.BOTH;
        add(tongquanpanel, gbc);
        
        ChiTietThongKeGUI chitietthongkegui = new ChiTietThongKeGUI();
        bangthongkebus.putdschuoithongtin(chitietthongkegui, datepickerpanel.getDateStart(), datepickerpanel.getDateEnd());
        
        chitietbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(thongkePanel.isVisible()){
                    thongkePanel.setVisible(false);
                     
                    gbc.gridx = 0;
                    gbc.gridy = 3; 
            //        gbc.gridwidth = 1;
            //        gbc.gridheight = 1;
                    gbc.weightx = 0; 
                    gbc.weighty = 1; 
                    gbc.fill = GridBagConstraints.BOTH;
                    add(chitietthongkegui, gbc);
                    chitietthongkegui.setVisible(true);
                    
                    chitietthongkegui.revalidate(); 
                    chitietthongkegui.repaint();
                    
                }else{
                    chitietthongkegui.setVisible(false);
                    
                    gbc.gridx = 0;
                    gbc.gridy = 3; 
            //        gbc.gridwidth = 1;
            //        gbc.gridheight = 1;
                    gbc.weightx = 0; 
                    gbc.weighty = 1; 
                    gbc.fill = GridBagConstraints.BOTH;
                    add(thongkePanel, gbc);
                    thongkePanel.setVisible(true);
                }
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                chitietbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
 
        gbc.gridx = 0;
        gbc.gridy = 3; 
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
        gbc.weightx = 0; 
        gbc.weighty = 1; 
        gbc.fill = GridBagConstraints.BOTH;
        add(thongkePanel, gbc);
        
        
        
        bangthongkebus.showDateByTheLoai(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai());
        totalincome = bangthongkebus.getTotalIncomeByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), datepickerpanel.getLoaiThoiGian());
        totalreturn = bangthongkebus.getTotalHoaDonBiHuyByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), datepickerpanel.getLoaiThoiGian());
        totalbooks = bangthongkebus.getTotalBooksByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), datepickerpanel.getLoaiThoiGian());
        totalHoaDon = bangthongkebus.getTotalHoaDonByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), datepickerpanel.getLoaiThoiGian());
                    
        tim.addActionListener(e -> {
            String loaithoigian = datepickerpanel.getLoaiThoiGian();
            switch(loaithoigian){
                case "Ngày" -> {
                    bangthongkebus.showDateByTheLoai(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai());
                    thongkePanel.revalidate();
                    thongkePanel.repaint();
                    
                    totalincome = bangthongkebus.getTotalIncomeByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalreturn = bangthongkebus.getTotalHoaDonBiHuyByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalbooks = bangthongkebus.getTotalBooksByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalHoaDon = bangthongkebus.getTotalHoaDonByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);

                    tongtienbanduocTotal.setText(MoneyFormatter.formatToVND(totalincome));
                    trahangTotal.setText(String.valueOf(totalreturn));
                    sosachbanduocTotal.setText(String.valueOf(totalbooks));
                    sohoadonTotal.setText(String.valueOf(totalHoaDon));
                    
                    ((DefaultTableModel) chitietthongkegui.getTableModel()).setRowCount(0);
                    bangthongkebus.putdschuoithongtincuatheloai(chitietthongkegui, datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    chitietthongkegui.revalidate(); 
                    chitietthongkegui.repaint();
                    
                    break;
                }
                case "Tháng" -> {
                    bangthongkebus.showMonthByTheLoai(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai());
                    thongkePanel.revalidate();
                    thongkePanel.repaint();
                    
                    totalincome = bangthongkebus.getTotalIncomeByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalreturn = bangthongkebus.getTotalHoaDonBiHuyByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalbooks = bangthongkebus.getTotalBooksByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalHoaDon = bangthongkebus.getTotalHoaDonByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);

                    tongtienbanduocTotal.setText(MoneyFormatter.formatToVND(totalincome));
                    trahangTotal.setText(String.valueOf(totalreturn));
                    sosachbanduocTotal.setText(String.valueOf(totalbooks));
                    sohoadonTotal.setText(String.valueOf(totalHoaDon));
                    
                    ((DefaultTableModel) chitietthongkegui.getTableModel()).setRowCount(0);
                    bangthongkebus.putdschuoithongtincuatheloai(chitietthongkegui, datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    chitietthongkegui.revalidate(); 
                    chitietthongkegui.repaint();
            
                    break;
                }
                case "Năm" -> {
                    bangthongkebus.showYearByTheLoai(thongkePanel,datepickerpanel.getDateStart(),datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai());
                    thongkePanel.revalidate();
                    thongkePanel.repaint();
                    
                    totalincome = bangthongkebus.getTotalIncomeByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalreturn = bangthongkebus.getTotalHoaDonBiHuyByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalbooks = bangthongkebus.getTotalBooksByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    totalHoaDon = bangthongkebus.getTotalHoaDonByTheLoai(datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);

                    tongtienbanduocTotal.setText(MoneyFormatter.formatToVND(totalincome));
                    trahangTotal.setText(String.valueOf(totalreturn));
                    sosachbanduocTotal.setText(String.valueOf(totalbooks));
                    sohoadonTotal.setText(String.valueOf(totalHoaDon));
                    
                    ((DefaultTableModel) chitietthongkegui.getTableModel()).setRowCount(0);
                    bangthongkebus.putdschuoithongtincuatheloai(chitietthongkegui, datepickerpanel.getDateStart(), datepickerpanel.getDateEnd(), datepickerpanel.getTheLoai(), loaithoigian);
                    chitietthongkegui.revalidate(); 
                    chitietthongkegui.repaint();
            
                    break;
                }
            }
        });
  
        //Test ngày
        System.out.println(datepickerpanel.getDateStart());
        System.out.println(datepickerpanel.getDateEnd());
        //
        
        gbc.gridx = 0;
        gbc.gridy = 1;
//        gbc.gridwidth = 1; 
//        gbc.gridheight = 1;
        gbc.weightx = 1; 
        gbc.weighty = 0;
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
