package org.spring2023.display;

import org.spring2023.util.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TSPVisualizer extends JPanel {
    private List<Node> nodes;
    private double minX, minY, maxX, maxY;

    public TSPVisualizer(List<Node> nodes) {
        this.nodes = nodes;
        calculateMinMaxValues();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // calculate the padding factor
        double paddingFactor = 0.2;

        // calculate the scaling factor based on the minimum and maximum x and y values of the nodes, with padding
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double scaleX = (panelWidth / (maxX - minX)) * (1.0 - paddingFactor);
        double scaleY = (panelHeight / (maxY - minY)) * (1.0 - paddingFactor);

        // calculate the translation factors based on the minimum x and y values of the nodes and the padding factor

        double translateX = minX - paddingFactor * (maxX - minX) + 0.1 * (maxX - minX);
        double translateY = minY - paddingFactor * (maxY - minY) + 0.1 * (maxY - minY);


        // Drawing path:
        g2d.setColor(Color.RED);
        for (int i = 0; i < nodes.size(); i++) {
            Node node1 = nodes.get(i);
            Node node2 = nodes.get((i + 1) % nodes.size());
            int x1 = (int) ((node1.getX() - translateX) * scaleX);
            int y1 = (int) ((node1.getY() - translateY) * scaleY);
            int x2 = (int) ((node2.getX() - translateX) * scaleX);
            int y2 = (int) ((node2.getY() - translateY) * scaleY);
            g2d.drawLine(x1 + 5, y1 + 5, x2 + 5, y2 + 5);
        }

        // drawing the nodes:
        g2d.setColor(Color.BLACK);
        for (Node node : nodes) {
            // Scale the coordinates of the node
            double x = (node.getX() - translateX) * scaleX;
            double y = (node.getY() - translateY) * scaleY;
            int ovalSize = 10; // The size of the oval representing the node
            g2d.fillOval((int) x, (int) y, ovalSize, ovalSize);
        }
    }

    private void calculateMinMaxValues() {
        // Find the minimum and maximum x and y values of the nodes
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
        for (Node node : nodes) {
            double x = node.getX();
            double y = node.getY();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
    }
}
