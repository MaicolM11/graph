package com.uptc.strucs;

public class Connection<T, W> implements Comparable<Connection<T, W>> {

    protected W weight;
    protected Vertex<T, W> conn;

    public Connection(Vertex<T, W> value, W weight) {
        this.conn = value;
        this.weight = weight;
    }

    public Vertex<T, W> getVertex() {
        return conn;
    }

    public W getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Connection<T, W> o) {
        return this.conn.comp.compare(this.conn.value, o.conn.value)
                + (weight.equals(o.weight) ? 0 : Integer.MAX_VALUE);
    }

}