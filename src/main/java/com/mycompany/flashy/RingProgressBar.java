/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

/**
 *
 * @author arpan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class RingProgressBar extends JComponent {
    private int progress = 0;
    private final int ringThickness = 10; // Thickness of the ring

    public RingProgressBar() {
        setPreferredSize(new Dimension(120, 120));
    }

    @Override

protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    int diameter = Math.min(getWidth(), getHeight()) - ringThickness;
    int x = (getWidth() - diameter) / 2;
    int y = (getHeight() - diameter) / 2;

    // Draw background ring
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.setStroke(new BasicStroke(ringThickness));
    g2d.drawOval(x, y, diameter, diameter);

    // Draw foreground arc for progress
    g2d.setColor(Color.WHITE);
    g2d.setStroke(new BasicStroke(ringThickness));
    int arcAngle = (int) (360 * (progress / 100.0)); // Calculate the correct arc angle
    g2d.draw(new Arc2D.Double(x, y, diameter, diameter, 90, -arcAngle, Arc2D.OPEN)); // Negative angle to start from the top

    g2d.dispose();
}


    public void setProgress(int progress) {
        this.progress = progress;
        repaint();
    }
}