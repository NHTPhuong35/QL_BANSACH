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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class KhachHangGUI extends JPanel {

    private JTable tbl;
    private DefaultTableModel dtm;
    private JPanel pnHeader, pnMain;
    private JPanel btAdd, btEdit, pnBtn;
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

        btAdd = new JPanel();
        btAdd = createButton(btAdd, "Thêm", "btAdd.png", BASE.color_btAdd, 100, 35);
        btAdd.addMouseListener(commonMouseListener);

        btEdit = new JPanel();
        btEdit = createButton(btEdit, "Sửa", "btEdit.png", BASE.color_btEdit, 100, 35);
        btEdit.addMouseListener(commonMouseListener);

        pnBtn.add(btAdd);
        pnBtn.add(Box.createHorizontalStrut(30));
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

    private JPanel createButton(JPanel btn, String text, String url, Color color, int width, int height) {
        btn.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        btn.setBackground(color);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, height));
        btn.setMaximumSize(new Dimension(width, height));
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 10); 

        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/" + url));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel lblImage = new JLabel(scaledIcon);
        btn.add(lblImage, gbc);

        JLabel lblText = new JLabel(text);
        lblText.setFont(BASE.font);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        btn.add(lblText, gbc);

        return btn;
    }

    private MouseAdapter createCommonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel clickedPanel = (JPanel) e.getSource();
                    if (clickedPanel == btEdit) {
                        int selectedRow = tbl.getSelectedRow();
                        if (selectedRow != -1) {
                            String maKhachHang = (String) dtm.getValueAt(selectedRow, 0);

                            KhachHangBUS khBUS = new KhachHangBUS();
                            KhachHangDTO kh = khBUS.layKHTheoMa(maKhachHang);

                            new EditCustomer(kh,KhachHangGUI.this);
                        } else {
                            new ShowDiaLog("Vui lòng chọn một khách hàng để sửa", ShowDiaLog.ERROR_DIALOG);
                        }
                    } else if (clickedPanel == btAdd) {
//                        ThemKhachHangGUI khGUI = new ThemKhachHangGUI();
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

    public JPanel getPnBtn() {
        return pnBtn;
    }
    

    public JPanel getBtEdit() {
        return btEdit;
    }

    public JPanel getBtAdd() {
        return btAdd;
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