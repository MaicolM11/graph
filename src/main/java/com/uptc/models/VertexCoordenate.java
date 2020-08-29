package com.uptc.models;

import java.awt.Point;

import com.uptc.views.Constants;

@SuppressWarnings("serial")
public class VertexCoordenate extends Point {

    private static final int RADIUS = Constants.RADIUS;

    public VertexCoordenate(int x, int y) {
        super(x, y);
    }

    public VertexCoordenate() {
        super(RADIUS, RADIUS);
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean searchCircle(Point d) {
        return d.distance(this) <= RADIUS / 2;
    }

}
