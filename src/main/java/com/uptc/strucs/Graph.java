package com.uptc.strucs;

import java.util.HashMap;
import java.util.Map;

import com.uptc.models.Coordenate;
import com.uptc.models.EdgeTemplate;
import com.uptc.models.VertexTemplate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Graph <T extends VertexTemplate, W, E extends EdgeTemplate<W>> {
    
    protected Map<Coordenate, Node<T, E>> map;
    protected Integer numEdges;

    public Graph(){
        this.map = new HashMap<>();
        numEdges = 0;
    }

    public T addNode(T node) {
        if(map.containsKey(node.getCoordenate())) {
            log.error("The node cannot be added because its coordinate already exists");
            return null;
        }
        Node<T, E> n = new Node<>(node);
        map.put(node.getCoordenate(), n);
        return map.get(node.getCoordenate()).value;
    }

    public boolean addEdge(Coordenate a, Coordenate b, E edge) {
        if(!map.containsKey(a) || !map.containsKey(b)){
            log.error("Verify that nodes exists");
            return false;
        }
        boolean result = map.get(a).addEdge(edge) && map.get(b).addEdge(edge);
        if(result) numEdges++;
        else log.error("The connection already exists");
        return result;
    }
  
}