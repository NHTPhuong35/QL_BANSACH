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
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class KhachHangGUI extends JPanel {

    private JTable tbl;
    private DefaultTableModel dtm;
    private JPanel pnHeader, pnMain, pnBtn;
    private JButton btAdd, btEdit ;
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
        pnHeader.setPreferredSize(new Dimension(0, 80));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        pnMain = new JPanel(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void initComponents() {

        pnBtn = new JPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.X_AXIS));

        MouseAdapter commonMouseListener = createCommonMouseListener();


        btEdit = createButtonWithIcon("Sửa", "./src/image/btEdit.png",BASE.color_btEdit, BASE.font, new Dimension(100,35));
        btEdit.addMouseListener(commonMouseListener);

        pnBtn.add(btEdit);

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

    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JButton) {
                    JButton clickedPanel = (JButton) e.getSource();
                    if (clickedPanel == btEdit) {
                        int selectedRow = tbl.getSelectedRow();
                        if (selectedRow != -1) {
                            String maKhachHang = (String) dtm.getValueAt(selectedRow, 0);

                            KhachHangBUS khBUS = new KhachHangBUS();
                            KhachHangDTO kh = khBUS.layKHTheoMa(maKhachHang);
                            
                            new SuaKhachHangGUI(kh,KhachHangGUI.this);
                        } else {
                            new ShowDiaLog("Vui lòng chọn một khách hàng để sửa", ShowDiaLog.ERROR_DIALOG);
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() instanceof JButton) {

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof JButton) {

                }
            }
        };
    }
    
    public JButton createButtonWithIcon(String text, String iconPath, Color bgColor, Font font, Dimension size) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        
        JButton button = new JButton(text, icon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setBackground(bgColor);
        button.setFont(font);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    public JPanel getPnBtn() {
        return pnBtn;
    }
    

    public JButton getBtEdit() {
        return btEdit;
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