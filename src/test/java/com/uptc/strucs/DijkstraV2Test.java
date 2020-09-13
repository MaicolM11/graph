package com.uptc.strucs;

import java.util.stream.Collectors;

public class DijkstraV2Test {
    
    public static void main(String[] args) {
        DijkstraV2<String, Double> graph = new DijkstraV2<>((a, b) -> a.compareTo(b));
    
        /* graph.addConn("a", "b", 4.0);
        graph.addConn("c", "a", 2.0);
        graph.addConn("b", "d", 5.0);
        graph.addConn("b", "c", 1.0);
        graph.addConn("c", "e", 10.0);
        graph.addConn("c", "d", 8.0);
        graph.addConn("d", "e", 2.0);
        graph.addConn("d", "t", 6.0);
        graph.addConn("e", "t", 2.0); 
        graph.init("a","t"); 

        graph.getWays().forEach(x ->{
            System.out.print("camino: [ ");
            System.out.println(x.stream().map( y-> y.value.toString())
            .collect(Collectors.joining(", "))+" ]");
        }); */

        graph.addConn("a", "b", 2.0); 
        graph.addConn("a", "c", 1.0); 
        graph.addConn("b", "d", 1.0); 
        graph.addConn("c", "d", 2.0); 

        graph.init("a", "d");
        System.out.println(graph);
        
        graph.getWays().forEach(x ->{
            System.out.print("camino: [ ");
            System.out.println(x.stream().map( y-> y.value.toString())
            .collect(Collectors.joining(", "))+" ]");
        }); 
        graph.resetWay();
        graph.init("a", "c");
        System.out.println(graph);
        
        graph.getWays().forEach(x ->{
            System.out.print("camino: [ ");
            System.out.println(x.stream().map( y-> y.value.toString())
            .collect(Collectors.joining(", "))+" ]");
        }); 
    }
}
