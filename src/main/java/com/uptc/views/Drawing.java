package com.uptc.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

import java.util.List;

import com.uptc.strucs.Vertex;

public class Drawing<T, W> {

    private static final int RADIUS = Constants.RADIUS;

    public void paintLine(Graphics g, Point initial_c, Point final_c, String distance) {
        g.setColor(Constants.COLOR_LINE);
        g.drawLine(initial_c.x, initial_c.y, final_c.x, final_c.y);
        g.setColor(Constants.COLOR_WEIGHT);
        g.drawString(distance, ((initial_c.x + final_c.x) / 2) - 5, ((initial_c.y + final_c.y) / 2) + 5);
    }

    public void paintCircle(Graphics g, Point point, String title, Color color) {
        g.setColor(color);
        g.fillOval(point.x - (RADIUS / 2), point.y - (RADIUS / 2), RADIUS, RADIUS);
        g.drawString(title, point.x - (RADIUS / 2), point.y - (RADIUS / 2));
    }

    public void paintDijkstra(Graphics g, List<Vertex<T, W>> wayDijkstra) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Constants.COLOR_DIJKSTRA);

        for(int i = 0; i<wayDijkstra.size()-1; i++){
            Point initial_c = wayDijkstra.get(i).getLocation();
            Point final_c = wayDijkstra.get(i+1).getLocation();
            g2.drawLine(initial_c.x, initial_c.y, final_c.x, final_c.y);
            paintCircle(g, initial_c, "", Constants.COLOR_DIJKSTRA);
            paintCircle(g, final_c, "", Constants.COLOR_DIJKSTRA);
        }

    }
}