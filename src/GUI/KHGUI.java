package GUI;

import GUI.ThemKH;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class KHGUI extends JPanel {

    private JTable KHTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton addButton, editButton;
    private JPanel editButtonPanel;
    private JPanel searchPanel;

    public KHGUI() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Điểm tích lũy"};

        tableModel = new DefaultTableModel(columnNames, 0);

        KHTable = new JTable(tableModel);
        KHTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(KHTable);
        add(scrollPane, BorderLayout.CENTER);

        KHTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Arial", Font.BOLD, 16));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBackground(Color.decode("#98DCE2"));
                return label;
            }
        });
        TableColumnModel columnModel = KHTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(300);
        KHTable.setRowHeight(30);
        KHTable.setShowGrid(false);
        KHTable.setFont(new Font("Arial", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        KHTable.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader tableHeader = KHTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(100, 40));
        //dữ liệu mẫu
        tableModel.addRow(new Object[]{"KH01", "Tự giúp bản thân", "0987654321", "Quận 10"});
        tableModel.addRow(new Object[]{"KH02", "Tiểu thuyết", "0897654321", "Quận 5"});
        //panel tìm kiếm, sửa, thêm
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(Color.WHITE);

        editButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        editButtonPanel.setBackground(Color.WHITE);
        searchPanel.setBackground(Color.WHITE);

        controlPanel.add(editButtonPanel, BorderLayout.WEST);
        controlPanel.add(searchPanel, BorderLayout.EAST);

        addButton = new JButton("Thêm khách hàng");
        addButton.setBackground(Color.decode("#A6E3A1"));

        editButton = new JButton("Sửa khách hàng");
        editButton.setBackground(Color.decode("#B4BEFE"));

        searchField = new JTextField(15);
        JButton searchButton = new JButton("Tìm kiếm");

        editButtonPanel.add(addButton);
        editButtonPanel.add(editButton);
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(controlPanel, BorderLayout.NORTH);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemKH dialog = new ThemKH(SwingUtilities.getWindowAncestor(KHGUI.this), KHGUI.this);
                dialog.setVisible(true);
            }
        });
        // "Tìm kiếm"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                KHTable.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
            }
        });
    }

    public void addKH(String ten, String sdt) {
        tableModel.addRow(new Object[]{ten, sdt});
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 500);
        f.add(new KHGUI());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
