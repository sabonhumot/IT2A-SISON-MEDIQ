package gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutlinedButton extends JButton {

    public OutlinedButton(String text, Color outlineColor) {
        super(text);

        // Set transparent background
        setContentAreaFilled(false);
        setFocusPainted(false);

        // Set border and text color
        setBorder(BorderFactory.createLineBorder(outlineColor, 2));
        setForeground(outlineColor);

        // Hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setOpaque(true);
                setBackground(outlineColor);
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
                setForeground(outlineColor);
            }
        });
    }
}
