package GUI.renderers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nhatm
 */

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.awt.Insets;

public class RoundJButton extends JButton {
    private int arcWidth;
    private int arcHeight;

    public RoundJButton(String label, int arcWidth, int arcHeight) {
        super(label);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // Để có thể vẽ bo tròn
        setFocusPainted(false); // Loại bỏ viền khi focus
        setMargin(new Insets(10, 20, 10, 20)); // Thiết lập margin
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền nút với màu nền hiện tại
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ viền nút bo tròn
        g2.setColor(Color.GRAY);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));
        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        return new Insets(6, 20, 6, 20); // Thiết lập khoảng cách bên trong (padding)
    }
}


