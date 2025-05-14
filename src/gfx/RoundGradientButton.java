package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundGradientButton extends JButton {

    private Color color1;
    private Color color2;
    private Color hoverColor1;
    private Color hoverColor2;
    private final int cornerRadius;

    public RoundGradientButton(String text, Color color1, Color color2, int cornerRadius) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        this.hoverColor1 = color1.darker(); // Default hover color
        this.hoverColor2 = color2.darker();
        this.cornerRadius = cornerRadius;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setColors(hoverColor1, hoverColor2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setColors(color1, color2);
            }
        });
    }

    // Method to set custom hover colors
    public void setHoverColors(Color newHoverColor1, Color newHoverColor2) {
        this.hoverColor1 = newHoverColor1;
        this.hoverColor2 = newHoverColor2;
    }

    public void setColors(Color newColor1, Color newColor2) {
        this.color1 = newColor1;
        this.color2 = newColor2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(0, 0, color1, w, 0, color2);
        g2d.setPaint(gp);

        g2d.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);

        g2d.dispose();
        super.paintComponent(g);
    }
}
