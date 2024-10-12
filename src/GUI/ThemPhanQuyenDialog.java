/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PhanQuyenBUS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author nhatm
 */
public class ThemPhanQuyenDialog extends JDialog{
    private JLabel nhaptenphanquyen;
    private JButton them;
    private JPanel inputPanel;
    private PhanQuyenBUS phanquyenBUS;
    private PhanQuyenGUI phanquyenGUI;
    private String getInput;
    private JTextField nhaptenphanquyenField;
    public ThemPhanQuyenDialog(JPanel parent){
        
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
    
    public void InitComponent(){
        setLayout(new GridLayout(2, 1));
        setTitle("Thêm phân quyền");
        nhaptenphanquyen = new JLabel("Tên phân quyền: ");
        them = new JButton("Thêm");
        nhaptenphanquyenField = new JTextField();
        inputPanel = new JPanel();
        inputPanel.add(nhaptenphanquyen);
        inputPanel.add(nhaptenphanquyenField);
        add(inputPanel);
        add(them);
        setSize(300, 150);
        nhaptenphanquyenField.setPreferredSize(new Dimension(150, 30));
        setLocationRelativeTo(null);
    }
}
