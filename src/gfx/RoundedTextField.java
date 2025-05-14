package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {

    private final int cornerRadius;

    public RoundedTextField(int radius) {
        this.cornerRadius = radius;
        setOpaque(false); // Crucial for transparency
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2d.setColor(getBackground()); // Or a specific color if you prefer
        g2d.fill(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, cornerRadius, cornerRadius));

        super.paintComponent(g); // Paint the text

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2d.setColor(Color.LIGHT_GRAY); // Border color
        g2d.draw(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, cornerRadius, cornerRadius));

        g2d.dispose();
    }
}