/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.TacGia;

/**
 *
 * @author nhatm
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.TacGiaGUI;

public class ThemTacGiaDialog extends JDialog {
    private JTextField maField, tenField;
    private JButton confirmButton, cancelButton;
    private TacGiaGUI parentPanel;

    public ThemTacGiaDialog(Window owner, TacGiaGUI parentPanel) {
        super(owner, "Thêm nhà tác giả", ModalityType.APPLICATION_MODAL);
        this.parentPanel = parentPanel;
        
        setSize(300, 150);
        setLayout(new BorderLayout());

        // Panel cho nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Mã tác giả:"));
        maField = new JTextField();
        inputPanel.add(maField);
        inputPanel.add(new JLabel("Tên tác giả:"));
        tenField = new JTextField();
        inputPanel.add(tenField);
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
                String ma = maField.getText();
                String ten = tenField.getText();
                if (!ma.isEmpty() && !ten.isEmpty()) {
                    parentPanel.addTacGia(ma, ten);
                    JOptionPane.showMessageDialog(ThemTacGiaDialog.this,
                            "Đã thêm tác giả: " + ten,
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ThemTacGiaDialog.this,
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