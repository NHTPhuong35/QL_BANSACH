/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class KhachHangGUI extends JPanel {

    private JTable tbl;
    private DefaultTableModel dtm;
    private JPanel pnHeader, pnMain, pnBtn;
    private JButton btnthem, btnSua;
    private JPanel btAdd, btEdit;
    private JTextField tfTimKiem;

    public KhachHangGUI() {
        init();
        initComponents();
        KhachHangBUS khBUS = new KhachHangBUS();
        reload(khBUS.getDs());
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 600));

        pnHeader = new JPanel(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(0, 60));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        pnMain = new JPanel(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void initComponents() {

        pnBtn = new JPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.X_AXIS));

        MouseAdapter commonMouseListener = createCommonMouseListener();

//        btAdd = new JPanel();
//        btAdd = createButton(btAdd, "Thêm", "btAdd.png", BASE.color_btAdd, 100, 35);
//        btAdd.addMouseListener(commonMouseListener);
//        
//        btEdit = new JPanel();
//        btEdit = createButton(btEdit, "Sửa", "btEdit.png", BASE.color_btEdit, 100, 35);
//        btEdit.addMouseListener(commonMouseListener);
//
//        pnBtn.add(btAdd);
//        pnBtn.add(Box.createHorizontalStrut(30));
//        pnBtn.add(btEdit);

        //Phuong
        btnthem = createBtn("Thêm",BASE.color_btAdd, "btnThem","btAdd.png");
        btnthem.addMouseListener(commonMouseListener);

        btnSua = createBtn("Sửa", BASE.color_btEdit, "btnSua","btEdit.png");
        btnSua.addMouseListener(commonMouseListener);

//        pnBtn.add(btnthem);
//        pnBtn.add(Box.createHorizontalStrut(30));
//        pnBtn.add(btnSua);

        JLabel lblTimKiem = new JLabel("Tìm kiếm");
        lblTimKiem.setFont(BASE.font_header);
        tfTimKiem = new JTextField();
        tfTimKiem.setPreferredSize(new Dimension(150, 30));
        tfTimKiem.setMaximumSize(new Dimension(200, 30));

        JPanel pnFind = new JPanel();
        pnFind.setPreferredSize(new Dimension(250, 30));
        pnFind.setLayout(new BoxLayout(pnFind, BoxLayout.X_AXIS));
        pnFind.add(lblTimKiem);
        pnFind.add(Box.createHorizontalStrut(5));
        pnFind.add(tfTimKiem);

        pnHeader.add(pnBtn, BorderLayout.WEST);
        pnHeader.add(pnFind, BorderLayout.EAST);

        String[] colName = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Điểm tích lũy"};
        dtm = new DefaultTableModel(colName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(dtm);
        styleTable(tbl);
        JScrollPane tableSPScr = new JScrollPane(tbl);

        pnMain.add(tableSPScr, BorderLayout.CENTER);

        tfTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void styleTable(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(BASE.color_header_tbl);
        header.setForeground(BASE.color_text);
        header.setFont(BASE.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(BASE.font);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    public void reload(ArrayList<KhachHangDTO> ds) {
        dtm.setRowCount(0);
        for (KhachHangDTO kh : ds) {
            dtm.addRow(new Object[]{kh.getMaKh(), kh.getTenKh(), kh.getSdt(), kh.getDiemTichluy()});
        }
    }

    public void addRow(KhachHangDTO kh) {
        dtm.addRow(new Object[]{kh.getMaKh(), kh.getTenKh(), kh.getSdt(), kh.getDiemTichluy()});
    }

    public void EditRow(KhachHangDTO kh) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(kh.getMaKh())) {
                dtm.setValueAt(kh.getTenKh(), i, 1);
                dtm.setValueAt(kh.getSdt(), i, 2);
                dtm.setValueAt(kh.getDiemTichluy(), i, 3);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    private void filterTable() {
        String key = tfTimKiem.getText().toLowerCase();
        KhachHangBUS khBUS = new KhachHangBUS();
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        ArrayList<KhachHangDTO> ds_all = khBUS.getDs();
        for (KhachHangDTO kh : ds_all) {
            if (kh.getMaKh().toLowerCase().contains(key) || kh.getTenKh().toLowerCase().contains(key) || kh.getSdt().contains(key)) {
                ds.add(kh);
            }
        }
        reload(ds);
    }

    private JPanel createButton(JPanel btn, String text, String url, Color color, int width, int height) {
        btn.setLayout(new BoxLayout(btn, BoxLayout.X_AXIS));
        btn.setBackground(color);
//        btn.setBorder(new EmptyBorder(0, 10, 0, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, height));
        btn.setMaximumSize(new Dimension(width, height));

        JLabel lblText = new JLabel(text);
        lblText.setFont(BASE.font_header);

        ImageIcon Icon = new ImageIcon(getClass().getResource("/Image/" + url));
        Image img = Icon.getImage();
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel lblImage = new JLabel(scaledIcon);      
        btn.add(lblImage);
        btn.add(Box.createHorizontalStrut(10));
        btn.add(lblText);
        return btn;
    }
    
    //Phuong
    private JButton createBtn(String text, Color color, String name, String url) {
        ImageIcon Icon = new ImageIcon(getClass().getResource("/Image/" + url));
        Image iconImage = Icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Icon = new ImageIcon(iconImage);
        JButton btn = new JButton();
        btn.setName(name);
        btn.setText(text);
        btn.setIcon(Icon);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT); // Đặt văn bản ở bên phải của biểu tượng
        btn.setVerticalTextPosition(SwingConstants.CENTER);   // Căn giữa theo chiều dọc
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setMaximumSize(new Dimension(100, 35));
        btn.setBackground(color);
        btn.setFont(BASE.font);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }


    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JButton) { // đã sửa JPanel thành JButton
                    JButton clickedPanel = (JButton) e.getSource();
                    if (clickedPanel == btnSua) {
                        int selectedRow = tbl.getSelectedRow();
                        if (selectedRow != -1) {
                            String maKhachHang = (String) dtm.getValueAt(selectedRow, 0);

                            KhachHangBUS khBUS = new KhachHangBUS();
                            KhachHangDTO kh = khBUS.layKHTheoMa(maKhachHang);

                            new SuaKhachHangGUI(kh, KhachHangGUI.this);
                        } else {
                            new ShowDiaLog("Vui lòng chọn một khách hàng để sửa", ShowDiaLog.ERROR_DIALOG);
                        }
                    } else if(clickedPanel == btnthem){
                        ThemKhachHangGUI khGUI = new ThemKhachHangGUI();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {

                }
            }
        };
    }
    
//    public JPanel getPnAdd(){
//       return btAdd; 
//    }
//    
//    public JPanel getPnEdit(){
//        return btEdit;
//    }
    public JPanel getPnBtn(){
        return pnBtn;
    }

    public JButton getBtnthem() {
        return btnthem;
    }

    public JButton getBtnSua() {
        return btnSua;
    }
    
    
    public static void main(String[] agrs) {
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new KhachHangGUI());
        f.setVisible(true);
    }
}
