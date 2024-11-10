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

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class NhaCungCapGUI extends JPanel implements ActionListener{
	
    private JTable tbl;
    private DefaultTableModel dtm;
    private JTextField tfTimKiem;
    private JButton btnThem, btnSua, btnXoa;
    private JPanel pnHeader, pnMain, pnBtn;
    private JScrollPane tableSPScr;
    NhaCungCapDTO selectedL = new NhaCungCapDTO();
    private NhaCungCapBUS nccBUS;
    private ArrayList<NhaCungCapDTO> dsNCC;

    public NhaCungCapGUI() {
        init();
        initComponents();
        nccBUS = new NhaCungCapBUS();
        dsNCC = new ArrayList<>();
        dsNCC = nccBUS.getDs();
        reload(nccBUS.getDs());
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000, 600));

        pnHeader = new JPanel(new BorderLayout());
        pnHeader.setBackground(Color.WHITE);
        pnHeader.setPreferredSize(new Dimension(0, 60));
        pnHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        pnMain = new JPanel(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void initComponents() {

        btnThem = createBtn("Thêm",BASE.color_btAdd, "btnThem","btAdd.png");
        btnThem.addActionListener(this);

        btnSua = createBtn("Sửa", BASE.color_btEdit, "btnSua","btEdit.png");
        btnSua.addActionListener(this);

        btnXoa = createBtn("Xóa", BASE.color_btLamXoa, "btnXoa","bin.png");
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

        String[] colName = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email", "Số điện thoại"};
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
                filterTable(dsNCC);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(dsNCC);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private JTable filterTable(ArrayList<NhaCungCapDTO> dsNCC) {
        String key = tfTimKiem.getText().toLowerCase();
        NhaCungCapBUS nccBUS = new NhaCungCapBUS();
        ArrayList<NhaCungCapDTO> ds = new ArrayList<>();
        ArrayList<NhaCungCapDTO> ds_all = nccBUS.getDs();
        for (NhaCungCapDTO l : ds_all) {
            if (l.getMaNhaCungCap().toLowerCase().contains(key) || l.getTenNhaCungCap().toLowerCase().contains(key) || l.getDiaChi().toLowerCase().contains(key) || l.getEmail().toLowerCase().contains(key)|| l.getSdt().toLowerCase().contains(key)) {
                ds.add(l);
            }
        }
        reload(ds);
        return tbl;
    }

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

    public void reload(ArrayList<NhaCungCapDTO> ds) {
        dtm.setRowCount(0);
        for (NhaCungCapDTO l : ds) {
            dtm.addRow(new Object[]{l.getMaNhaCungCap(), l.getTenNhaCungCap(), l.getDiaChi(), l.getEmail(), l.getSdt()});
        }
    }

    public void EditRow(NhaCungCapDTO l) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(l.getMaNhaCungCap())) {
                dtm.setValueAt(l.getTenNhaCungCap(), i, 1);
                dtm.setValueAt(l.getDiaChi(), i, 2);
                dtm.setValueAt(l.getEmail(), i, 3);
                dtm.setValueAt(l.getSdt(), i, 4);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    public void addRow(NhaCungCapDTO l) {
        dtm.addRow(new Object[]{l.getMaNhaCungCap(), l.getTenNhaCungCap(), l.getDiaChi(), l.getEmail(), l.getSdt()});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getName().equals("btnThem")) {
            ThemNhaCungCapGUI l = new ThemNhaCungCapGUI(NhaCungCapGUI.this);
        } else if (source.getName().equals("btnSua")) {
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                String maNhaCungCap = (String) tbl.getValueAt(selectedRow, 0);

                NhaCungCapBUS nccBUS = new NhaCungCapBUS();
                NhaCungCapDTO l = nccBUS.layNCCTheoMa(maNhaCungCap);

                new SuaNhaCungCapGUI(l, NhaCungCapGUI.this);
            } else {
                new ShowDiaLog("Vui lòng chọn một nhà cung cấp để sửa", ShowDiaLog.ERROR_DIALOG);
            }
        } else if (source.getName().equals("btnXoa")) { //Phuong sua
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá nhà cung cấp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    String maNhaCungCap = (String) tbl.getValueAt(selectedRow, 0);
                    NhaCungCapBUS nccBUS = new NhaCungCapBUS();

                    if (nccBUS.XoaNhaCungCap(maNhaCungCap)) {
                        JOptionPane.showMessageDialog(null,
                                "Đã xoá thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);

                        // Cập nhật model của bảng
                        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                        model.removeRow(selectedRow);

                        // Xóa phần tử khỏi dsTG
                        dsNCC.remove(selectedRow);

                        this.revalidate();
                        this.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                new ShowDiaLog("Hãy chọn nhà cung cấp cần xoá!", ShowDiaLog.INFO_DIALOG); //
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
        f.add(new NhaCungCapGUI());
        f.setVisible(true);
    }
}
