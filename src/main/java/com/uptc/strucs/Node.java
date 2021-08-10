package com.uptc.strucs;

import java.util.ArrayList;
import java.util.List;

public class Node <T, E> {

    protected T value;
    protected List<E> edges;

    public Node(T v){
        this.value = v;
        this.edges = new ArrayList<>();
    }

    public boolean addEdge(E e) {
        boolean exist = this.edges.contains(e);
        if(!exist) this.edges.add(e);
        return !exist;
    }
    
}