package com.uptc.strucs;

import java.awt.Point;

public class DijkstraPoint<T, W> {

    protected double distance;
    protected Connection<T, W> conection;
    protected Vertex<T, W> origin;

    public DijkstraPoint(double distance, Connection<T, W> conection, Vertex<T, W> origin) {
        this.distance = distance;
        this.conection = conection;
        this.origin = origin;
    }
    
    public DijkstraPoint(){

    }

    public Point getPoint() {
        return origin.getPoint();
    }

    public Point getConnPoint() {
        return conection.conn.getPoint();
    }    

}