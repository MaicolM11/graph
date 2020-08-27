package com.uptc.strucs;

public class DijkstraTest {

    public static void main(String[] args) {

        Dijkstra<String, Double> graph = new Dijkstra<>((a, b) -> a.compareTo(b));
        graph.addConn("a", "b", 4.0);
        graph.addConn("c", "a", 2.0);
        graph.addConn("b", "d", 5.0);
        graph.addConn("b", "c", 1.0);
        graph.addConn("c", "e", 10.0);
        graph.addConn("c", "d", 8.0);
        graph.addConn("d", "e", 2.0);
        graph.addConn("d", "t", 6.0);
        graph.addConn("e", "t", 2.0); 
        graph.init("a","t"); 

        System.out.println(graph);
    }
}