package com.uptc.models;
import java.awt.geom.Point2D.Double;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordenate extends Double {

    public Coordenate(double x, double y ){
        super(x, y);    
    }

}
