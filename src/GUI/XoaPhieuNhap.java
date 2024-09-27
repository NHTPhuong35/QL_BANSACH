package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class XoaPhieuNhap extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton deleteButton;

    public XoaPhieuNhap() {
        setTitle("Xóa Phiếu Nhập");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        label = new JLabel("Nhập mã phiếu nhập:");
        textField = new JTextField(15);
        deleteButton = new JButton("Xóa");
        deleteButton.setBackground(java.awt.Color.decode("#EBA0AC"));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPhieuNhap = textField.getText();
                // Add code here to delete the item with the given maPhieuNhap
                JOptionPane.showMessageDialog(null, "Phiếu nhập " + maPhieuNhap + " đã được xóa.");
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(deleteButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XoaPhieuNhap().setVisible(true);
            }
        });
    }
}
