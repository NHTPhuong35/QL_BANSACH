package GUI;

/**
 *
 * @author nhatm
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import GUI.KHGUI;
public class ThemKH extends JDialog {
    private JTextField sdtField, tenField;
    private JButton confirmButton, cancelButton;
    private KHGUI parentPanel;
    
    public ThemKH(Window owner, KHGUI parentPanel) {
        super(owner, "Thêm khách hàng", ModalityType.APPLICATION_MODAL);
        this.parentPanel = parentPanel;
        
        setSize(300, 150);
        setLayout(new BorderLayout());
        // panel cho  nhập
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Tên khách hàng:"));
        tenField = new JTextField();
        inputPanel.add(tenField);
        
        inputPanel.add(new JLabel("Số điện thoại:"));
        sdtField = new JTextField();
        inputPanel.add(sdtField);
        add(inputPanel, BorderLayout.CENTER);
        
        // Panel cho các nút
        JPanel buttonPanel = new JPanel();
        confirmButton = new JButton("Xác nhận");
        cancelButton = new JButton("Hủy");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Xử lý "Xác nhận"
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ten = tenField.getText();
                String sdt = sdtField.getText();
                if (!sdt.isEmpty() && !ten.isEmpty()) {
                    parentPanel.addKH(ten,sdt);
                    JOptionPane.showMessageDialog(ThemKH.this,
                            "Đã thêm tác giả: " + ten,
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ThemKH.this,
                            "Vui lòng nhập đầy đủ thông tin",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Xử lý "Hủy"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setLocationRelativeTo(owner);
    } 
}