package com.uptc.strucs;

public class GraphTest extends Graph<String, Integer> {

    public GraphTest() {
        super((a, b) -> a.compareTo(b));
    }

    public void fill() {
        this.addConn("Lola", "Andrea", 12);
        this.addConn("pepe", "Andrea", 12);
        this.addConn("Andrea", "Lola", 18);
        this.addConn("Andrea", "Lola", 12);
    }

 

    public static void main(String[] args) {
        GraphTest graph = new GraphTest();
        graph.fill();
        System.out.println(graph);
        System.out.println("___________________");

        graph.clear();
        System.out.println(graph);
        System.out.println("___________________");

    }

}