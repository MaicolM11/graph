package com.uptc.impl;

import com.uptc.models.Coordenate;
import com.uptc.models.EdgeTemplate;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, of = {"name"})
public class Segment extends EdgeTemplate<Double> {

    protected String name;
    protected SegmentType type;

    public Segment(String name, SegmentType type, Coordenate p1, Coordenate p2, Double weight) {
        super(p1, p2, weight);
        this.name = name;
        this.type = type;
    }

    
    enum SegmentType {STREET};
}
