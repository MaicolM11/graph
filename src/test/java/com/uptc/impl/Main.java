package com.uptc.impl;

import com.uptc.impl.Segment.SegmentType;
import com.uptc.strucs.Graph;

public class Main {

    public static void main(String[] args) {
        Graph<Statation, Double, Segment> stations = new Graph<>();
        Statation statationA = new Statation("San lorenzo", 1, 1);
        Statation statationB = new Statation("San Patricio", 10, 1);
        System.out.println(stations.addNode(statationA));
        System.out.println(stations.addNode(statationB));
        Segment segment = new Segment("Oriental", SegmentType.STREET, statationA.getCoordenate(),
                statationB.getCoordenate(), 100.0);
        stations.addEdge(statationA.getCoordenate(), statationB.getCoordenate(), segment);
        

    
    }

}
