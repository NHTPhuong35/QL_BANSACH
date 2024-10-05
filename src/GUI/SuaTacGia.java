package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;

public class SuaTacGia extends JFrame implements MouseListener {

    private JTextField tfTen;
    private JPanel btnXacNhan, btnHuy;
    private TacGiaGUI tgGUI;
    private TacGiaDTO tgDTO;


    public SuaTacGia(TacGiaDTO tgDTO, TacGiaGUI tgGUI) {
        this.tgGUI = tgGUI;
        this.tgDTO = tgDTO;
        this.tfTen = new JTextField(tgDTO.getTenTG());
        this.tfTen.setPreferredSize(new Dimension(300, 30));
        this.tfTen.setFont(BASE.font);
        this.tfTen.setMaximumSize(new Dimension(400, 30));
        init();
    }
    
    public SuaTacGia() {
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        setSize(300, 185);
        setPreferredSize(new Dimension(300, 185));
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo tiêu đề giao diện
        JPanel titleGUI_wrap = new JPanel(new BorderLayout());
        titleGUI_wrap.setPreferredSize(new Dimension(360, 35));
        JLabel titleGUI = new JLabel("Sửa Tác Giả", JLabel.CENTER);
        titleGUI.setFont(BASE.font_header);
        titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
        titleGUI_wrap.setBackground(BASE.color_table_heaer);

        add(titleGUI_wrap, BorderLayout.NORTH);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBorder(new EmptyBorder(10, 15, 0, 15));
        pnMain.setBackground(Color.WHITE);

        JPanel pnTenTG = new JPanel();
        pnTenTG.setBackground(Color.WHITE);
        pnTenTG.setLayout(new BoxLayout(pnTenTG, BoxLayout.X_AXIS));

        JLabel lbTen = new JLabel("Tên Tác Giả:");
        lbTen.setFont(BASE.font);

//        tfTen = new JTextField();
//        tfTen.setPreferredSize(new Dimension(300, 30));
//        tfTen.setMaximumSize(new Dimension(400, 30));

        JPanel pnBTN = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pnBTN.setBackground(Color.WHITE);
        btnXacNhan = new JPanel();
        btnXacNhan.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnXacNhan, "Xác nhận", "btnXacNhan");
        btnXacNhan.addMouseListener(this);

        btnHuy = new JPanel();
        btnHuy.setPreferredSize(new Dimension(120, 30));
        cssBtn(btnHuy, "Hủy", "btnHuy");
        btnHuy.addMouseListener(this);

        pnTenTG.add(lbTen);
        pnTenTG.add(Box.createRigidArea(new Dimension(10, 10)));
        pnTenTG.add(tfTen);

        pnBTN.add(btnXacNhan);
        pnBTN.add(btnHuy);

        pnMain.add(pnTenTG);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        pnMain.add(pnBTN);
        add(pnMain, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);  // Canh giữa màn hình
        setVisible(true);
    }

    private void cssBtn(JPanel b, String text, String name) {
        JLabel t = new JLabel(text, JLabel.CENTER);
        t.setForeground(java.awt.Color.WHITE);  // Đảm bảo chữ trắng để nổi bật
        b.setBackground(BASE.color_table_heaer);
        b.setName(name);
        b.add(t);
        b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
        b.setOpaque(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
                case "btnHuy":
                    Object[] options = {"Có", "Không"};
                    int r1 = JOptionPane.showOptionDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục thoát?", "Thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (r1 == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                    break;
                case "btnXacNhan":
                    String ten = tfTen.getText();
                    tgDTO.setTenTG(ten);
                    TacGiaBUS busTG = new TacGiaBUS();
                    boolean success = true;
                    if (success) {
                        TacGiaBUS tgBUS = new TacGiaBUS();
                        if (tgBUS.SuaTacGia(tgDTO)) {
                            tgGUI.EditRow(tgDTO);
                            dispose();
                        }else{
                            new ShowDiaLog("Sửa tác giả thất bại", ShowDiaLog.ERROR_DIALOG);
                        }
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(BASE.color_table_heaer);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }		
	}

	@Override
	public void mouseExited(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(BASE.color_heaer);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }		
	}

}
