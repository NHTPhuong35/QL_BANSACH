/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class KhachHangGUI extends JPanel implements ActionListener {

    private JTable tbl;
    private DefaultTableModel dtm;
    private JPanel pnHeader, pnMain;
    private JButton btnthem, btnSua;
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
        pnHeader.setBackground(Color.WHITE);
        pnHeader.setPreferredSize(new Dimension(0, 50));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        pnMain = new JPanel(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void initComponents() {
        btnthem = createBtn("Thêm khách hàng", "#A6E3A1", "btnThem");
        btnthem.addActionListener(this);

        btnSua = createBtn("Sửa khách hàng", "#B4BEFE", "btnSua");
        btnSua.addActionListener(this);
        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.X_AXIS));
        pnBtn.setBackground(Color.WHITE);

        pnBtn.add(btnthem);
        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
        pnBtn.add(btnSua);

        JLabel lblTimKiem = new JLabel("Tìm kiếm");
        lblTimKiem.setFont(BASE.font);
        tfTimKiem = new JTextField();
        tfTimKiem.setPreferredSize(new Dimension(150, 30));

        JPanel pnFind = new JPanel();
        pnFind.setPreferredSize(new Dimension(250, 30));
        pnFind.setLayout(new BoxLayout(pnFind, BoxLayout.X_AXIS));
        pnFind.add(Box.createRigidArea(new Dimension(20, 0)));
        Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border innerBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        pnFind.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        pnFind.add(lblTimKiem);
        pnFind.add(Box.createRigidArea(new Dimension(5, 0)));
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
        header.setBackground(BASE.color_table_heaer);
        header.setForeground(BASE.color_text);
        header.setFont(BASE.font_header);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.setRowHeight(35);
        table.setFont(BASE.font);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    private JButton createBtn(String text, String color, String name) {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(170, 30));
        btn.setMaximumSize(new Dimension(170, 30));
        btn.setName(name);
        btn.setText(text);
        btn.setBackground(Color.decode(color));
        btn.setFont(BASE.font);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
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

    public static void main(String[] agrs) {
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new KhachHangGUI());
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getName().equals("btnThem")) {
//            BanHangGUI banHangGUI = new BanHangGUI();
//            ThemKhachHangGUI kh = new ThemKhachHangGUI();
        } else if (source.getName().equals("btnSua")) {
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                String maKhachHang = (String) dtm.getValueAt(selectedRow, 0);

                KhachHangBUS khBUS = new KhachHangBUS();
                KhachHangDTO kh = khBUS.layKHTheoMa(maKhachHang);

                new SuaKhachHangGUI(kh, KhachHangGUI.this);
            } else {
                new ShowDiaLog("Vui lòng chọn một khách hàng để sửa", ShowDiaLog.ERROR_DIALOG);
            }
        }
    }
}
