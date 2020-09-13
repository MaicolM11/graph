package com.uptc.utils;

import java.io.IOException;

import com.uptc.strucs.Graph;

public class FilesSaveTest {

    public static void main(String[] args) throws IOException {
        Graph<String, Double> graph = new Graph<>((a, b) -> a.compareTo(b));

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

        GraphFiles.writeFile("Test.txt", graph);
        System.out.println(graph);
    }
}
