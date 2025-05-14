/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    private int cornerRadius;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Important: Make background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call super first
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // For smoother curves

        int w = getWidth();
        int h = getHeight();

        // Fill the rounded rectangle (background)
        g2d.setColor(getBackground()); // Or set a specific background color
        g2d.fill(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, cornerRadius, cornerRadius));

        g2d.dispose();
    }

//    @Override
//    protected void paintBorder(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        int w = getWidth();
//        int h = getHeight();
//
//        g2d.setColor(getForeground()); // Border color
//        g2d.draw(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, cornerRadius, cornerRadius));
//
//        g2d.dispose();
//    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rounded Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RoundedPanel roundedPanel = new RoundedPanel(20); // Corner radius of 20
        roundedPanel.setBackground(Color.BLUE); // Set background color
        roundedPanel.setForeground(Color.BLACK); // Set border color.
        roundedPanel.setPreferredSize(new Dimension(200, 150)); // Set size

        frame.add(roundedPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
