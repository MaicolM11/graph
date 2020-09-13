package com.uptc.strucs;

import static java.util.stream.Collectors.joining;

public class DijstraMoreWays {

    public static void main(String[] args) {
        DijkstraV2<String, Double> graph = new DijkstraV2<>((a, b) -> a.compareTo(b));

        graph.addConn("o", "a", 2.0);
        graph.addConn("o", "c", 4.0);
        graph.addConn("o", "b", 5.0);
        graph.addConn("a", "b", 2.0);
        graph.addConn("a", "d", 7.0);
        graph.addConn("c", "b", 1.0);
        graph.addConn("c", "e", 4.0);
        graph.addConn("b", "d", 4.0);
        graph.addConn("b", "e", 3.0);
        graph.addConn("e", "d", 1.0);
        graph.addConn("e", "t", 7.0);
        graph.addConn("d", "t", 5.0);

        graph.init("o", "t");

        System.out.println(graph);

        graph.getWays().forEach(x -> {
            System.out.print("camino: [ ");
            System.out.println(x.stream().map(y -> y.value.toString()).collect(joining(", ")) + " ]");
        });
        System.out.println("Longitud: " + graph.getLength());
    }
}
