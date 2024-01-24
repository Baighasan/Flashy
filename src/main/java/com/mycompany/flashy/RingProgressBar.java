package com.mycompany.flashy;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

// RingProgressBar class extends JComponent to create a custom ring-style progress bar.
public class RingProgressBar extends JComponent {
    private int progress = 0; // Variable to store the current progress percentage.
    private final int ringThickness = 10; // Constant defining the thickness of the ring.

    // Constructor for the RingProgressBar class.
    public RingProgressBar() {
        // Set the preferred size of the component. This determines the initial size of the ring progress bar.
        setPreferredSize(new Dimension(120, 120));
    }

    // Overriding the paintComponent method to draw the custom ring progress bar.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call to the superclass's method to ensure proper painting of the component.
        
        Graphics2D g2d = (Graphics2D) g.create(); // Create a Graphics2D object for improved drawing capabilities.

        // Calculate the diameter of the ring based on the component's width, height, and ring thickness.
        int diameter = Math.min(getWidth(), getHeight()) - ringThickness;
        // Calculate the x and y coordinates to centrally position the ring in the component.
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // Draw the background ring.
        g2d.setColor(Color.LIGHT_GRAY); // Set the color for the background ring.
        g2d.setStroke(new BasicStroke(ringThickness)); // Set the stroke thickness for the ring.
        g2d.drawOval(x, y, diameter, diameter); // Draw the background ring as an oval.

        // Draw the foreground arc to represent the progress.
        g2d.setColor(Color.WHITE); // Set the color for the foreground arc.
        g2d.setStroke(new BasicStroke(ringThickness)); // Set the stroke thickness for the arc.
        // Calculate the arc angle based on the current progress.
        int arcAngle = (int) (360 * (progress / 100.0));
        // Draw the foreground arc. Note that the angle is negative to make the arc start from the top.
        g2d.draw(new Arc2D.Double(x, y, diameter, diameter, 90, -arcAngle, Arc2D.OPEN));

        g2d.dispose(); // Dispose of the Graphics2D context to free up resources.
    }

    // Method to set the progress of the ring progress bar.
    public void setProgress(int progress) {
        this.progress = progress; // Update the progress variable.
        repaint(); // Call repaint to trigger a call to paintComponent and redraw the progress bar with the new progress.
    }
}
