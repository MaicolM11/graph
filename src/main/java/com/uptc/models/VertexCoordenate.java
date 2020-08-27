package com.uptc.models;

import java.awt.Point;

public class VertexCoordenate {

    public static final int RADIUS = 30;

    private Point point;

    public VertexCoordenate(int x, int y) {
        point = new Point(x, y);
    }

    public VertexCoordenate() {
        point = new Point(RADIUS, RADIUS);
    }

    public void translate(int dx, int dy) {
        this.point.x += dx;
        this.point.y += dy;
    }

    public boolean searchCircle(Point d) {
        return d.distance(point) <= RADIUS / 2;
    }

    public Point getPoint() {
        return this.point;
    }

}
