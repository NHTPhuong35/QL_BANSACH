/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;

public class TacGiaGUI extends JPanel implements ActionListener {

    private JTable tbl;
    private DefaultTableModel dtm;
    private JTextField tfTimKiem;
    private JButton btnThem, btnSua, btnXoa;
    private JPanel pnHeader, pnMain, pnBtn;
    private JScrollPane tableSPScr;
    TacGiaDTO selectedTG = new TacGiaDTO();
    private TacGiaBUS tgBUS;
    private ArrayList<TacGiaDTO> dsTG;

    public TacGiaGUI() {
        init();
        initComponents();
        tgBUS = new TacGiaBUS();
        dsTG = new ArrayList<>();
        dsTG = tgBUS.getDs();
        reload(tgBUS.getDs());
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

        pnBtn.add(btnThem);
        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
        pnBtn.add(btnSua);
        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
        pnBtn.add(btnXoa);

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

        String[] colName = {"Mã tác giả", "Tên tác giả"};
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
                filterTable(dsTG);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(dsTG);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private JTable filterTable(ArrayList<TacGiaDTO> dsTG) {
        String key = tfTimKiem.getText().toLowerCase();
        TacGiaBUS tgBUS = new TacGiaBUS();
        ArrayList<TacGiaDTO> ds = new ArrayList<>();
        ArrayList<TacGiaDTO> ds_all = tgBUS.getDs();
        for (TacGiaDTO tg : ds_all) {
            if (tg.getMaTG().toLowerCase().contains(key) || tg.getTenTG().toLowerCase().contains(key)) {
                ds.add(tg);
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

    public void reload(ArrayList<TacGiaDTO> ds) {
        dtm.setRowCount(0);
        for (TacGiaDTO tg : ds) {
            dtm.addRow(new Object[]{tg.getMaTG(), tg.getTenTG()});
        }
    }

    public void EditRow(TacGiaDTO tg) {
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (dtm.getValueAt(i, 0).equals(tg.getMaTG())) {
                dtm.setValueAt(tg.getTenTG(), i, 1);
                break;
            }
        }
        dtm.fireTableDataChanged();
    }

    public void addRow(TacGiaDTO tg) {
        dtm.addRow(new Object[]{tg.getMaTG(), tg.getTenTG()});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getName().equals("btnThem")) {
            ThemTacGiaGUI tg = new ThemTacGiaGUI(TacGiaGUI.this);
        } else if (source.getName().equals("btnSua")) {
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                String maTacGia = (String) tbl.getValueAt(selectedRow, 0);

                TacGiaBUS tgBUS = new TacGiaBUS();
                TacGiaDTO tg = tgBUS.layTGTheoMa(maTacGia);

                new SuaTacGia(tg, TacGiaGUI.this);
            } else {
                new ShowDiaLog("Vui lòng chọn một tác giả để sửa", ShowDiaLog.ERROR_DIALOG);
            }
        } else if (source.getName().equals("btnXoa")) { //Phuong sua
            int selectedRow = tbl.getSelectedRow();
            if (selectedRow != -1) {
                Object[] options = {"Có", "Không"};
                int result = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xoá tác giả này không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    String maTacGia = (String) tbl.getValueAt(selectedRow, 0);
                    TacGiaBUS tgBUS = new TacGiaBUS();

                    if (tgBUS.XoaTacGia(maTacGia)) {
                        JOptionPane.showMessageDialog(null,
                                "Đã xoá thành công", "Thông báo", JOptionPane.DEFAULT_OPTION);

                        // Cập nhật model của bảng
                        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                        model.removeRow(selectedRow);

                        // Xóa phần tử khỏi dsTG
                        dsTG.remove(selectedRow);

                        this.revalidate();
                        this.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Thất bại", "Thông báo", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                new ShowDiaLog("Hãy chọn tác giả cần xoá!", ShowDiaLog.INFO_DIALOG); //
            }

        }
    }
    
    public JButton getBtnThem(){
        return btnThem;
    }
    
    public JButton getBtnSua(){
        return btnSua;
    }
    
    public JButton getBtnXoa(){
        return btnXoa;
    }
    
    public JPanel getPnBtn(){
        return pnBtn;
    }
    
    public static void main(String[] agrs) {
        JFrame f = new JFrame();
        f.setSize(1000, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new TacGiaGUI());
        f.setVisible(true);
    }
}
