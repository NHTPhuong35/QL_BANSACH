package GUI;

import BUS.PhanQuyenBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author nhatm
 */
public class ThemPhanQuyenDialog extends JDialog {
    private JLabel nhaptenphanquyen;
    private JButton them;
    private JPanel inputPanel, headerPanel;
    private PhanQuyenBUS phanquyenBUS;
    private PhanQuyenGUI phanquyenGUI;
    private String getInput;
    private JTextField nhaptenphanquyenField;

    public ThemPhanQuyenDialog(JPanel parent) {
        phanquyenBUS = new PhanQuyenBUS();
        phanquyenGUI = new PhanQuyenGUI();

        InitComponent();
        them.addActionListener((ActionEvent e) -> {
            getInput = nhaptenphanquyenField.getText();
            phanquyenBUS.ThemQuyen(getInput);
            System.out.println(getInput);
            dispose();
        });
    }

    public void InitComponent() {
        setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#C68642"));
        JLabel titleLabel = new JLabel("Thêm phân quyền");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        inputPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        inputPanel.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        nhaptenphanquyen = new JLabel("Tên phân quyền: ");
        inputPanel.add(nhaptenphanquyen, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        nhaptenphanquyenField = new JTextField(15);
        nhaptenphanquyenField.setPreferredSize(new Dimension(200, 30));
        inputPanel.add(nhaptenphanquyenField, gbc);

        them = new JButton("Thêm");
        them.setBackground(Color.decode("#5DADE2"));
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER; 
        inputPanel.add(them, gbc);

        // Add components to dialog
        add(headerPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        
        // Set dialog size and location
        setSize(300, 200);
        setLocationRelativeTo(null); 
    }
}
