package com.uptc.strucs;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.awt.Point;

import com.uptc.models.VertexCoordenate;

@SuppressWarnings("serial")
public class Vertex<T, W> extends VertexCoordenate implements Comparable<Vertex<T, W>> {

    protected T value;
    protected Set<Connection<T, W>> connections;
    protected Comparator<T> comp;
    protected boolean isTravel;
    protected boolean isSelect;

    public Vertex(T value, Comparator<T> comp, Point point) {
        super(point.x, point.y);
        init(value, comp);
    }

    public Vertex(T value, Comparator<T> comp) {
        super();
        init(value, comp);
    }

    private void init(T value, Comparator<T> comp) {
        this.comp = comp;
        this.value = value;
        this.connections = new TreeSet<>();
        isTravel = false;
        isSelect = false;
    }

    public boolean addConnection(Vertex<T, W> conection, W weight) {
        return this.connections.add(new Connection<>(conection, weight));
    }

    public T getValue() {
        return value;
    }

    public boolean deleteConnection(T info) {
        return connections.removeIf(x -> comp.compare(x.conn.value, info) == 0);
    }

    public Optional<Connection<T, W>> searchConn(T value) {
        return this.connections.stream().filter(x -> comp.compare(x.conn.value, value) == 0).findAny();
    }

    public Set<Connection<T, W>> getConnections() {
        return connections;
    }

    public boolean isSelect() {
        return isSelect;
    }

    @Override
    public int compareTo(Vertex<T, W> o) {
        return comp.compare(this.value, o.value);
    }

}