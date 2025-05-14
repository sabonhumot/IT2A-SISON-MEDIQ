package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.GradientPaint;

public class GradientPanel extends JPanel {

    private final Color startColor;
    private final Color endColor;
    private final int gradientDirection; // 1: Vertical, 2: Horizontal, 3: Diagonal

    public GradientPanel(Color startColor, Color endColor, int direction) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.gradientDirection = direction;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Important: Call super first!
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();

        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        switch (gradientDirection) {
            case 1: // Vertical
                y2 = h;
                break;
            case 2: // Horizontal
                x2 = w;
                break;
            case 3: // Diagonal
                x2 = w;
                y2 = h;
                break;
            default: // Default to vertical
                y2 = h;
                break;
        }

        GradientPaint gp = new GradientPaint(x1, y1, startColor, x2, y2, endColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
    }
}
