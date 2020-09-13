package com.uptc.strucs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.joining;

/***
 * Clase encargada de realizar el algoritmo de dijkstra
 */
public class DijkstraV2<T, W> extends Graph<T, W> {

    private Map<Vertex<T, W>, List<PointDijkstra>> process;
    private Vertex<T, W> final_v;
    private Vertex<T, W> inicial_v;
    private List<List<Vertex<T, W>>> ways;
    private PointDijkstra minimum;

    public DijkstraV2(Comparator<T> comp) {
        super(comp);
        this.process = new HashMap<>();
        this.ways = new ArrayList<>();
    }

    public void init(T init_v, T final_v) throws NoSuchElementException, NullPointerException {
        this.graph.forEach(x -> process.put(x, new ArrayList<>()));
        this.final_v = search(final_v).get();
        this.inicial_v = search(init_v).get();
        travel(inicial_v, 0);
    }

    public void init() throws NoSuchElementException, NullPointerException {
        List<Vertex<T, W>> values = getSelect();
        init(values.get(0).getValue(), values.get(1).getValue());
    }

    private void travel(Vertex<T, W> actual, double distance) throws NullPointerException {
        if (comp.compare(actual.value, final_v.value) == 0)
            return;
        actual.isTravel = true;
        actual.connections.stream().filter(x -> !x.conn.isTravel).forEach(adyacent -> {
            double newWeight = distance + Double.valueOf(String.valueOf(adyacent.weight));
            List<PointDijkstra> listAd = this.process.get(adyacent.conn);
            if (listAd.isEmpty() || listAd.get(0).distance > newWeight) {
                listAd.add(0, new PointDijkstra(actual, newWeight));
            } else if (listAd.get(0).distance == newWeight) {
                listAd.get(0).add(actual);
            }
        });
        findSmallest();
        travel(minimum.connections.get(0), minimum.distance);
    }

    private void findSmallest() {
        minimum = new PointDijkstra(null, Double.MAX_VALUE);
        process.forEach((k, v) -> {
            if (!k.isTravel && !v.isEmpty() && v.get(0).distance < minimum.distance) {
                minimum = new PointDijkstra(k, v.get(0).distance);
            }
        });
    }

    public void resetWay() {
        resetSelect();
        this.process.clear();
        this.ways.clear();
    }

    public List<List<Vertex<T, W>>> getWays() {
        addWay(new ArrayList<>(), final_v);
        return new ArrayList<List<Vertex<T, W>>>(ways);
    }

    private void addWay(List<Vertex<T, W>> values, Vertex<T, W> actual) {
        Vertex<T, W> v = actual;
        for (; comp.compare(v.value, inicial_v.value) != 0
                && process.get(v).get(0).connections.size() == 1; v = process.get(v).get(0).connections.get(0)) {
            values.add(v);
        }
        values.add(v);
        if (comp.compare(v.value, inicial_v.value) == 0) {
            ways.add(values);
        } else {
            process.get(v).get(0).connections.forEach(x -> {
                addWay(new ArrayList<>(values), x);
            });
        }
    }

    public double getLength() {
        return this.process.get(final_v).get(0).distance;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        process.forEach((k, v) -> {
            builder.append(k.value);
            builder.append(": [ ");
            builder.append(v.stream().map(Object::toString).collect(joining(", ")));
            builder.append(" ] \n");
        });
        return builder.toString();
    }

    public String getWaysString() {
        StringBuffer builder = new StringBuffer();
        ways.forEach(x -> {
            builder.append("camino: [ ");
            builder.append(x.stream().map(y -> y.value.toString()).collect(joining(", ")) + " ]\n");
        });
        builder.append("Longitud: " + getLength());
        return builder.toString();
    }

    class PointDijkstra {

        protected List<Vertex<T, W>> connections;
        protected double distance;

        public PointDijkstra(Vertex<T, W> conn, double distance) {
            this.connections = new ArrayList<>();
            this.connections.add(conn);
            this.distance = distance;
        }

        public void add(Vertex<T, W> conn) {
            this.connections.add(conn);
        }

        @Override
        public String toString() {
            String exit = "[" + distance + ", ";
            exit += connections.stream().map(x -> x.value.toString()).collect(joining(", "));
            return exit + "]";
        }

    }

}
