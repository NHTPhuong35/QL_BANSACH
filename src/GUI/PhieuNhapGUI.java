package GUI;

import BUS.PhieuNhapBUS; // Import the PhieuNhapBUS class
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO; // Import the PhieuNhapDTO class

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class PhieuNhapGUI extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel toolBar;
    
    public PhieuNhapGUI() {
        // Tạo các nút chức năng với màu sắc như trong hình
        addButton = createBtn("Thêm",BASE.color_btAdd, "btnThem","btAdd.png");
        
        editButton = createBtn("Sửa", BASE.color_btEdit, "btnSua","btEdit.png");

        deleteButton = createBtn("Xóa", BASE.color_btLamXoa, "btnXoa","bin.png");

        // Bố trí các nút theo dạng FlowLayout (căn ngang)
        toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Căn trái và có khoảng cách
//        toolBar.add(addButton);
//        toolBar.add(deleteButton);
//        toolBar.add(editButton);

        // Add action listener to the addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the TaoPhieuNhap panel
                JFrame taoPhieuNhapFrame = new JFrame("Tạo Phiếu Nhập");
                taoPhieuNhapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                taoPhieuNhapFrame.setSize(800, 600);
                
                TaoPhieuNhap taoPhieuNhapPanel = new TaoPhieuNhap(HomeGUI.tkUSER);
                taoPhieuNhapPanel.setMaNV("NV01");

                taoPhieuNhapFrame.add(new TaoPhieuNhap(HomeGUI.tkUSER)); // Assuming TaoPhieuNhap is a JPanel
                taoPhieuNhapFrame.setVisible(true);
            }
        });

        // Add action listener to the editButton
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Get data from the selected row
                String maPN = (String) table.getValueAt(selectedRow, 0);
                String tenNV = (String) table.getValueAt(selectedRow, 1);
                String nhaCC = (String) table.getValueAt(selectedRow, 2);
                Date ngayLap = (Date) table.getValueAt(selectedRow, 3);
                Double tongTien = (Double) table.getValueAt(selectedRow, 4);

                // Check if today is the same day as ngayLap
                    String today = java.time.LocalDate.now().toString();
                System.out.println("Ngày lập: " + ngayLap.toString());
                System.out.println("Hôm nay: " + today);
                if (ngayLap.toString() == today) {
                // Show the SuaPhieuNhap panel with data
                JFrame suaPhieuNhapFrame = new JFrame("Sửa Phiếu Nhập");
                suaPhieuNhapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                suaPhieuNhapFrame.setSize(800, 600);
                SuaPhieuNhap suaPhieuNhapPanel = new SuaPhieuNhap();
                suaPhieuNhapPanel.setMaPhieuNhap(maPN);
                suaPhieuNhapPanel.setMaNhanVien(tenNV);
                suaPhieuNhapPanel.setNhaCungCap(nhaCC);
                suaPhieuNhapFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadData(); // Reload the table data after closing the SuaPhieuNhap frame
                    }
                });
                suaPhieuNhapPanel.setNgay(ngayLap);
                suaPhieuNhapPanel.setTongTien(tongTien);

                suaPhieuNhapFrame.add(suaPhieuNhapPanel);
                suaPhieuNhapFrame.setVisible(true);
                } else {
                JOptionPane.showMessageDialog(null, "Chỉ có thể sửa phiếu nhập trong ngày.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để sửa.");
            }
            }
        });

        // Add action listener to the deleteButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu nhập này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String maPN = (String) table.getValueAt(selectedRow, 0);
                        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
                        phieuNhapBUS.XoaPhieuNhap(maPN);
                        model.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu nhập để xóa.");
                }
            }
        });

        String[] columns = { "Mã PN", "Tên nhân viên", "Nhà cung cấp", "Ngày lập", "Tổng tiền", "Chi tiết" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only the "Chi tiết" column is editable
            }
        };

        // Custom renderer and editor for the "Chi tiết" column
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

        // Load data into the table
        loadData();

        // Select the first row
        if (model.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }

        // Tạo thanh tìm kiếm
        JTextField searchField = new JTextField(15);
        searchField.setPreferredSize(new Dimension(150,25));
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Căn phải
        JLabel lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(BASE.font);
        searchPanel.add(lblSearch);
        searchPanel.add(searchField);

        // Tạo bảng cuộn cho bảng dữ liệu
        JScrollPane scrollPane = new JScrollPane(table);


        table.getTableHeader().setBackground(BASE.color_table_heaer);
        table.getTableHeader().setFont(BASE.font_header);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 30));
        table.setBackground(Color.WHITE);
        table.setFont(BASE.font);
        table.setRowHeight(30); // thiết lập chiều cao các cột

        // Bố cục tổng thể
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(toolBar, BorderLayout.WEST); // Đưa các nút về phía trái
        topPanel.add(searchPanel, BorderLayout.EAST); // Thanh tìm kiếm ở phía phải
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // Đặt layout cho JPanel
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH); // Thanh công cụ và tìm kiếm ở trên cùng
        add(scrollPane, BorderLayout.CENTER); // Bảng dữ liệu ở giữa với dữ liệu mẫu
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phiếu Nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 400);
        frame.add(new PhieuNhapGUI());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void loadData() {
        model.setRowCount(0);
        PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
        List<PhieuNhapDTO> phieuNhapList = phieuNhapBUS.getAllPhieuNhap();
        for (PhieuNhapDTO phieuNhap : phieuNhapList) {
            model.addRow(new Object[] {
                    phieuNhap.getMaPN(),
                    phieuNhap.getTenDN(),
                    phieuNhap.getMaNCC(),
                    phieuNhap.getNgayNhap(),
                    phieuNhap.getTongTien(),
                    "XEM"
            });
        }
    }
    
    public JButton getBtnThem(){
        return addButton;
    }
    
    public JButton getBtnSua(){
        return editButton;
    }
    
    public JButton getBtnXoa(){
        return deleteButton;
    }
    
    public JPanel getToolBar(){
        return toolBar;
    }

    // Custom renderer for the button
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(Color.white);
            setFont(BASE.font);
            setOpaque(true);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
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

    // Custom editor for the button
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
            // Show the description in a new table
            int selectedRow = table.getSelectedRow();
            String maPN = (String) table.getValueAt(selectedRow, 0);
            PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
            ArrayList<ChiTietPhieuNhapDTO> phieuNhap = phieuNhapBUS.LayChiTietPhieuNhap(maPN);

            // Create a new frame to show the details
            JFrame detailsFrame = new JFrame("Chi Tiết Phiếu Nhập");
            detailsFrame.setSize(600, 400);
            detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a table to display the details
            String[] detailColumns = { "Mã Sách", "Số Lượng", "Thành Tiền" };
            DefaultTableModel detailModel = new DefaultTableModel(detailColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the detail table not editable
            }
            };
            JTable detailTable = new JTable(detailModel);

            // Add data to the detail table
            for (ChiTietPhieuNhapDTO chiTiet : phieuNhap) {
                detailModel.addRow(new Object[] {
                        chiTiet.getMASACH(),
                        chiTiet.getSOLUONG(), // Assuming getGiaNhap() is the correct method
                        chiTiet.getTONGTIEN()
                });
            }
            
            // Set the table to auto resize all columns
            detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            // Custom table header and table appearance
            detailTable.getTableHeader().setBackground(BASE.color_table_heaer);
            detailTable.getTableHeader().setFont(BASE.font_header);
            detailTable.setBackground(Color.WHITE);
            detailTable.setFont(BASE.font);
            detailTable.setRowHeight(40); // Set row height
            
            // Add a "Xong" button to close the frame
            JButton closeButton = new JButton("Xong");
            closeButton.setBackground(BASE.color_header_tbl);
            closeButton.setFont(BASE.font);
            closeButton.setFocusPainted(false);
            closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));            
            closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsFrame.dispose();
            }
            });

            // Add the close button to the frame
            detailsFrame.add(closeButton, BorderLayout.SOUTH);

            // Add the detail table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(detailTable);
            detailsFrame.add(scrollPane);

            // Show the details frame
            detailsFrame.setVisible(true);
            detailsFrame.setLocationRelativeTo(null);
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
