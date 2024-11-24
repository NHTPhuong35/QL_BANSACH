/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.BorderLayout;
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

public class ChonKhachHangGUI extends JFrame {

    private JPanel pnHeader, pnMain;
    private JTable tbl;
    private DefaultTableModel dtm;
    private JTextField tfTimKiem;
    private BanHangGUI BanHangGUI;

    public ChonKhachHangGUI(BanHangGUI BanHangGUI) {
        init();
        initComponnent();
        KhachHangBUS khBUS = new KhachHangBUS();
        reload(khBUS.getDs());
        this.BanHangGUI = BanHangGUI;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void init() {
        this.setSize(800, 400);
        this.setPreferredSize(new Dimension(800, 400));
        this.setLayout(new BorderLayout());

        pnHeader = new JPanel(new BorderLayout());
        pnHeader.setPreferredSize(new Dimension(0, 60));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        pnMain = new JPanel(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    private void initComponnent() {
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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tbl.getSelectedRow(); // Lấy chỉ số dòng được chọn
                if (selectedRow != -1) { // Kiểm tra nếu có dòng được chọn
                    String maKhachHang = (String) tbl.getValueAt(selectedRow, 0);
                    BanHangGUI.getTfMaKH().setText(maKhachHang);
                    dispose();
                }
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

    private void filterTable() {
        String key = tfTimKiem.getText().toLowerCase();
        KhachHangBUS khBUS = new KhachHangBUS();
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        ArrayList<KhachHangDTO> ds_all = khBUS.getDs();
        for (KhachHangDTO kh : ds_all) {
            if (kh.getMaKh().toLowerCase().contains(key) || kh.getTenKh().toLowerCase().contains(key)) {
                ds.add(kh);
            }
        }
        reload(ds);
    }

    public static void main(String[] args) {
//        new ChonKhachHangGUI(BanHangGUI);
    }
}
