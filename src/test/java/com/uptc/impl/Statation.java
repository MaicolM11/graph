package com.uptc.impl;

import com.uptc.impl.station_utils.Utils;
import com.uptc.models.Coordenate;
import com.uptc.models.VertexTemplate;

public class Statation extends VertexTemplate {

    protected String name;

    public Statation(String name,double x, double y) {
        super(x, y);
        this.name= name;
    }

    @Override
    public double getDistance(Coordenate other) {
        return Utils.getGeographicDistance(this.coordenate.x, this.coordenate.y, 
        other.x, other.y);
    }

    @Override
    public String toString() {
        return "Statation [name=" + name + ", coordenate="+ coordenate + "]";
    }


  



}
