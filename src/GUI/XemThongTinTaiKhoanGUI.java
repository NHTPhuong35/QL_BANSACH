/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class XemThongTinTaiKhoanGUI extends JFrame{
    JPanel pnHeader, pnContent;
    JLabel exit;
    int width = 400, height = 300;
    TaiKhoanDTO tkDTO = new TaiKhoanDTO();
    private Font font = new Font("typeface", Font.PLAIN, 16);

    public XemThongTinTaiKhoanGUI(TaiKhoanDTO tkDTO) {
        this.tkDTO = tkDTO;
        init();
    }

    private void init() {
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //Tiêu đề
        pnHeader = new JPanel();
        pnHeader.setLayout(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(width, 36));
        pnHeader.setBackground(BASE.color_heaer);
        pnHeader.setOpaque(true);

        JLabel lblHeader = new JLabel("Thông tin tài khoản", JLabel.CENTER);
        lblHeader.setFont(BASE.font_title);
        lblHeader.setBorder(new EmptyBorder(0, 20, 0, 0));

        ImageIcon icon = new ImageIcon("./src/image/exit_icon.png");
        exit = new JLabel(icon, JLabel.CENTER);
        exit.setPreferredSize(new Dimension(36, 36));
        exit.setBackground(Color.RED);
        exit.setOpaque(true);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                XemThongTinTaiKhoanGUI.this.dispose();
            }
            
        });
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnHeader.add(lblHeader, BorderLayout.WEST);
        pnHeader.add(exit, BorderLayout.EAST);
        
        //Nội dung
        pnContent = new JPanel();
        pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.Y_AXIS));
        JPanel[] pnThongTin = new JPanel[5];
        String[] thuocTinh = {"Tên đăng nhập: ","Tên nhân viên: ","Số điện thoại: ","Email: ","Địa chỉ: "};
        String[] data = {tkDTO.getTenDN(),tkDTO.getTenNV(),tkDTO.getSDT(),tkDTO.getEmail(),tkDTO.getSDT()};
        for(int i=0; i<pnThongTin.length; i++){
            
            JLabel lblThuocTinh = new JLabel(thuocTinh[i],JLabel.LEFT);
            lblThuocTinh.setFont(font);
            lblThuocTinh.setPreferredSize(new Dimension(150,30));
            
            JLabel lblData = new JLabel(data[i],JLabel.LEFT);
            lblData.setFont(font);
            lblData.setPreferredSize(new Dimension(170,30));
            
            pnThongTin[i] = new JPanel();
            pnThongTin[i].add(lblThuocTinh);
            pnThongTin[i].add(lblData);
            pnContent.add(pnThongTin[i]);
        }
        
        pnMain.add(pnHeader,BorderLayout.NORTH);
        pnMain.add(pnContent,BorderLayout.CENTER);
        this.add(pnMain,BorderLayout.CENTER);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        QuyenDTO q = new QuyenDTO("QL", "Quản lý");
        TaiKhoanDTO tkDTO = new TaiKhoanDTO("NV07", "Phương123", "Quận 8", "0983456789", "Phuong579@gmail.com", "55345678", q, 0);
        new XemThongTinTaiKhoanGUI(tkDTO);
    }
}
