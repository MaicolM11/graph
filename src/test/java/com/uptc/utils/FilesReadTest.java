package com.uptc.utils;

import java.io.IOException;

import com.uptc.strucs.Graph;

public class FilesReadTest {

    public static void main(String[] args) throws IOException {
        Graph<String, Double> graph = new Graph<>((a,b)-> a.compareTo(b));
        GraphFiles.readFile("Test.txt", graph);
        System.out.println(graph);
    }
}
