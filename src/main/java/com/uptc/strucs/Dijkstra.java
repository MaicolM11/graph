package com.uptc.strucs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Dijkstra<T, W> extends Graph<T, W> {

    private final List<DijkstraPoint<T, W>> listProcess;
    private final List<DijkstraPoint<T, W>> listFinal;

    private T final_v;
    private T inicial_v;

    public Dijkstra(Comparator<T> comp) {
        super(comp);
        listFinal = new ArrayList<>();
        listProcess = new ArrayList<>();
    }

    public void init(T init_v, T final_v) {
        Vertex<T, W> init = this.search(init_v).get();
        this.search(final_v).get();
        this.final_v = final_v;
        this.inicial_v = init_v;
        travel(init, 0);
    }

    public void init() throws NoSuchElementException {
        List<Vertex<T, W>> values = getSelect();
        init(values.get(0).getValue(), values.get(1).getValue());
    }

    private void travel(Vertex<T, W> vertex_actual, double distance) throws NoSuchElementException {
        /*
         * if (comp.compare(vertex_actual.value, final_v) != 0) { vertex_actual.isTravel
         * = true; addAdyacents(vertex_actual, distance);
         * 
         * DijkstraPoint<T, W> minimumNode = findMinimum(); if (minimumNode != null) {
         * listProcess.remove(searchConn(minimumNode).get());
         * listFinal.add(minimumNode); travel(minimumNode.conection.conn,
         * minimumNode.distance); } else { throw new NoSuchElementException(); } }
         */
        while (comp.compare(vertex_actual.value, final_v) != 0) {  // ciclo < complejidad que recursividad ->  + rápido
            vertex_actual.isTravel = true;              // marca el vertice
            addAdyacents(vertex_actual, distance);      // agrega los vertices adyacentes

            DijkstraPoint<T, W> minimumNode = findMinimum();        //encuentra el punto con menor distancia
            listProcess.remove(searchConn(minimumNode).get());      // lo elimina y repite proceso
            listFinal.add(minimumNode);

            vertex_actual = minimumNode.conection.conn;
            distance = minimumNode.distance;
        }
    }

    private DijkstraPoint<T, W> findMinimum() {
        DijkstraPoint<T, W> minimumNode = null;
        double minimum = Double.MAX_VALUE;
        for (DijkstraPoint<T, W> element : listProcess) {
            if (minimum > element.distance) {
                minimumNode = element;
                minimum = element.distance;
            }
        }
        return minimumNode;
    }

    private void addAdyacents(Vertex<T, W> init, double distance) {
        for (Connection<T, W> adyacent : init.connections) {
            if (!adyacent.conn.isTravel) {
                DijkstraPoint<T, W> newRegister = new DijkstraPoint<>(
                        Double.valueOf(String.valueOf(adyacent.weight)) + distance, adyacent, init); // [12,1]

                Optional<DijkstraPoint<T, W>> search = searchConn(newRegister);
                if (search.isEmpty()) {
                    listProcess.add(newRegister);
                } else if (search.get().distance > newRegister.distance) {
                    search.get().distance = newRegister.distance;
                    search.get().origin = init;
                }
            }
        }
    }

    private Optional<DijkstraPoint<T, W>> searchConn(DijkstraPoint<T, W> value) {
        return listProcess.stream()
                .filter(x -> this.comp.compare(value.conection.conn.value, x.conection.conn.value) == 0).findAny();
    }

    public List<DijkstraPoint<T, W>> getWay() {
        return verification();
    }

    public void resetWay() {
        this.getGraph().forEach(x -> x.isTravel = false);
        this.listFinal.clear();
        this.listProcess.clear();
    }

    // cuando escoge vertices por ser menores pero cambia de camino. O vuelve a
    // pasar por el origen
    private List<DijkstraPoint<T, W>> verification() {
        for (int i = listFinal.size() - 1; i > 0; i--) {
            if (comp.compare(listFinal.get(i).origin.value, inicial_v) == 0) {
                return this.listFinal.subList(i, listFinal.size());
            }
            if (comp.compare(listFinal.get(i).origin.value, listFinal.get(i - 1).conection.conn.value) != 0) {
                listFinal.remove(i - 1);
            }
        }
        return this.listFinal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        verification().forEach(x -> builder.append(String.format("Desde %s, destino %s, distancia %s \n", x.origin.value,
                x.conection.conn.value, x.distance)));
        return builder.toString();
    }

}