package com.uptc.strucs;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.toList;

import java.awt.Point;

public class Graph<T, W> {

    protected Set<Vertex<T, W>> graph;
    protected final Comparator<T> comp;
    private int countSelect;

    public Graph(Comparator<T> comp) {
        this.graph = new TreeSet<>();
        this.comp = comp;
        countSelect = 0;
    }

    // agregar conexiones, de forma logica
    public boolean addConn(T child, T parent, W weight) {
        addAlone(parent);
        addAlone(child);
        Optional<Vertex<T, W>> parent_v = search(parent);
        Optional<Vertex<T, W>> child_v = search(child);
        child_v.get().addConnection(parent_v.get(), weight); // quitar si no es bidireccional
        return parent_v.get().addConnection(child_v.get(), weight);
    }

    // agregar conexiones, para la grafica
    public boolean addConn(W weight) throws NoSuchElementException {
        List<Vertex<T, W>> values = getSelect();
        return addConn(values.get(0).value, values.get(1).value, weight);
    }

    public boolean deleteConnection(T vA, T vB) {
        Optional<Vertex<T, W>> vertexA = search(vA);
        Optional<Vertex<T, W>> vertexB = search(vB);
        vertexA.get().deleteConnection(vB);
        return vertexB.get().deleteConnection(vA);
    }

    public boolean deleteConnection() throws NoSuchElementException {
        List<Vertex<T, W>> values = getSelect();
        return deleteConnection(values.get(0).value, values.get(1).value);
    }

    public void clear() {
        this.countSelect =0;
        this.graph.clear();
    }

    protected void resetSelect(){
        this.graph.stream().filter(v-> v.isTravel).forEach(x -> x.isTravel = false);
    }

    protected Optional<Vertex<T, W>> search(T valueSearch) {
        return this.graph.stream().filter(x -> comp.compare(x.value, valueSearch) == 0).findAny();
    }

    public void deleteVertex(T info) {
        this.graph.removeIf(x -> comp.compare(x.value, info) == 0);
        this.graph.forEach(x -> x.deleteConnection(info));
    }

    public boolean addAlone(T newValue, Point point) {
        Vertex<T, W> vertex = new Vertex<>(newValue, comp, point);
        return this.graph.add(vertex);
    }

    public boolean addAlone(T newValue) {
        Vertex<T, W> vertex = new Vertex<>(newValue, comp);
        return this.graph.add(vertex);
    }

    public W getWeight(T vertexA, T vertexB) {
        Optional<Vertex<T, W>> parent = search(vertexA);
        if (parent.isPresent()) {
            Optional<Connection<T, W>> conn = parent.get().searchConn(vertexB);
            if (conn.isPresent()) {
                return conn.get().weight;
            }
        }
        throw new NullPointerException();
    }

    protected List<Vertex<T, W>> getSelect() {
        if (countSelect == 2) {
            List<Vertex<T, W>> vertexConn = graph.stream().filter(x -> x.isSelect).collect(toList());
            vertexConn.forEach(x -> x.isSelect = false);
            countSelect = 0;
            return vertexConn;
        }
        throw new NoSuchElementException();
    }

    public Set<Vertex<T, W>> getGraph() {
        return graph;
    }

    public void addSelect(Vertex<T, W> vertex) {
        if (!vertex.isSelect) { // si lo va a marcar
            if (countSelect == 2) {
                this.graph.forEach(x -> x.isSelect = false);
                countSelect = 0;
            }
            vertex.isSelect = true;
            countSelect++;
        } else {
            vertex.isSelect = false;
            countSelect--;
        }
    }

    @Override
    public String toString() {
        StringBuffer builder = new StringBuffer();
        for (Vertex<T, W> vertex : graph) {
            builder.append(vertex.value.toString()).append("\n");
            for (Connection<T, W> conn : vertex.connections) {
                builder.append(String.format("\t%s   %s\n", conn.conn.value.toString(), conn.weight));
            }
        }
        return builder.toString();
    }

}