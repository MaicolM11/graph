package com.uptc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

import java.awt.Point;

import com.uptc.strucs.Graph;
import com.uptc.strucs.Vertex;

public class GraphFiles {

    public static void readFile(String path, Graph<String, Double> graph)
            throws IOException, ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] values = Files.readString(Path.of(path)).split("end");
        addPoints(values[0].split("\n"), graph);
        addIntersections(values[1].split("\n"), graph);
    }

    private static void addIntersections(String[] intersections, Graph<String, Double> graph) {
        for (String intersec : intersections) {
            String[] in = intersec.split(";");
            for (int i = 1; i < in.length; i++) {
                String[] conn = in[i].split(" ");
                graph.addConn(conn[0], in[0], Double.parseDouble(conn[1]));
            }
        }
    }

    private static void addPoints(String[] points, Graph<String, Double> graph) {
        for (String point : points) {
            String[] p = point.split(" ");
            graph.addAlone(p[0], new Point(Integer.parseInt(p[1]), Integer.parseInt(p[2])));
        }
    }

    public static void writeFile(String path, Graph<String, Double> graph) throws IOException {
        String points = "";
        StringBuffer intersections = new StringBuffer();
        for (Vertex<String, Double> v : graph.getGraph()) {
            points += v.getValue() + " " + v.x + " " + v.y + "\n";

            intersections.append(v.getValue() + ";");
            intersections.append(v.getConnections().stream().map(x -> x.getVertex().getValue() + " " + x.getWeight())
                    .collect(Collectors.joining(";")) + "\n");
        }
        points += "end\n" + intersections.toString();
        Files.writeString(Path.of(path), points.strip(), StandardOpenOption.CREATE);
    }

}
