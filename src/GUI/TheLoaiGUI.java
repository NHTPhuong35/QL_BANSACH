package GUI;

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
import javax.swing.JOptionPane;
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

import BUS.TheLoaiBUS;
import DTO.LoaiDTO;

public class TheLoaiGUI extends JPanel implements ActionListener{
	
    private JTable tbl;
    private DefaultTableModel dtm;
    private JTextField tfTimKiem;
    private JButton btnThem, btnSua, btnXoa;
    private JPanel pnHeader, pnMain, pnBtn;
    private JScrollPane tableSPScr;
    LoaiDTO selectedL = new LoaiDTO();
    private TheLoaiBUS tlBUS;
    private ArrayList<LoaiDTO> dsTL;

    public TheLoaiGUI() {
        init();
        initComponents();
        tlBUS = new TheLoaiBUS();
        dsTL = new ArrayList<>();
        dsTL = tlBUS.getDs();
        reload(tlBUS.getDs());
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

        btnThem = createBtn("+Thêm thể loại", "#A6E3A1", "btnThem");
        btnThem.addActionListener(this);

        btnSua = createBtn("+Sửa thể loại", "#B4BEFE", "btnSua");
        btnSua.addActionListener(this);

        btnXoa = createBtn("+Xóa thể loại", "#EBA0AC", "btnXoa");
        btnXoa.addActionListener(this);

        pnBtn = new JPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn, BoxLayout.X_AXIS));
        pnBtn.setBackground(Color.WHITE);

//        pnBtn.add(btnThem);
//        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
//        pnBtn.add(btnSua);
//        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
//        pnBtn.add(btnXoa);

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

        String[] colName = {"Mã loại", "Tên loại"};
        dtm = new DefaultTableModel(colName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(dtm);
        styleTable(tbl);
        tableSPScr = new JScrollPane(tbl);

        pnMain.add(tableSPScr, BorderLayout.CENTER);

        tfTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(dsTL);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(dsTL);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private JTable filterTable(ArrayList<LoaiDTO> dsTL) {
        String key = tfTimKiem.getText().toLowerCase();
        TheLoaiBUS tlBUS = new TheLoaiBUS();
        ArrayList<LoaiDTO> ds = new ArrayList<>();
        ArrayList<LoaiDTO> ds_all = tlBUS.getDs();
        for (LoaiDTO l : ds_all) {
            if (l.getMaLoai().toLowerCase().contains(key) || l.getTenLoai().toLowerCase().contains(key)) {
                ds.add(l);
            }
        }
        reload(ds);
        return tbl;
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

    public void reload(ArrayList<LoaiDTO> ds) {
        dtm.setRowCount(0);
        for (LoaiDTO l : ds) {
            dtm.addRow(new Object[]{l.getMaLoai(), l.getTenLoai()});
        }
    }

    public void EditRow(LoaiDTO l) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(l.getMaLoai())) {
                dtm.setValueAt(l.getTenLoai(), i, 1);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    public void addRow(LoaiDTO l) {
        dtm.addRow(new Object[]{l.getMaLoai(), l.getTenLoai()});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getName().equals("btnThem")) {
            ThemLoaiGUI l = new ThemLoaiGUI(TheLoaiGUI.this);
        } else if (source.getName().equals("btnSua")) {
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                String maLoai = (String) tbl.getValueAt(selectedRow, 0);

                TheLoaiBUS tlBUS = new TheLoaiBUS();
                LoaiDTO l = tlBUS.layTLTheoMa(maLoai);

                new SuaTheLoai(l, TheLoaiGUI.this);
            } else {
                new ShowDiaLog("Vui lòng chọn một thể loại để sửa", ShowDiaLog.ERROR_DIALOG);
            }
        } else if (source.getName().equals("btnXoa")) { //Phuong sua
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá thể loại này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    String maLoai = (String) tbl.getValueAt(selectedRow, 0);
                    TheLoaiBUS tlBUS = new TheLoaiBUS();

                    if (tlBUS.XoaTheLoai(maLoai)) {
                        JOptionPane.showMessageDialog(null,
                                "Đã xoá thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);

                        // Cập nhật model của bảng
                        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                        model.removeRow(selectedRow);

                        // Xóa phần tử khỏi dsTG
                        dsTL.remove(selectedRow);

                        this.revalidate();
                        this.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                new ShowDiaLog("Hãy chọn thể loại cần xoá!", ShowDiaLog.INFO_DIALOG); //
            }

        }
    }
    
    public JButton getBtnThem(){
        return btnThem;
    }
    
    public JButton getBtnXoa(){
        return btnXoa;
    }
    
    public JButton getBtnSua(){
        return btnSua;
    }
    
    public JPanel getPnBtn(){
        return pnBtn;
    }

    public static void main(String[] agrs) {
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new TheLoaiGUI());
        f.setVisible(true);
    }
}
