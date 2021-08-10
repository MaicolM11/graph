package com.uptc.models;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = false, of = {"id"})
public abstract class VertexTemplate {

    protected boolean isCheck; // for some algorithms
    protected UUID id;
    protected Coordenate coordenate;

    public VertexTemplate(double x, double y) {
        this.coordenate = new Coordenate(x, y);
        this.id = UUID.randomUUID();
        this.isCheck = false;
    }

    public abstract double getDistance(Coordenate other);   
    
}