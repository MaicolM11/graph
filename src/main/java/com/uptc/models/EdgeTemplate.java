package com.uptc.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class EdgeTemplate<W> {

    protected Coordenate p1;
    protected Coordenate p2;
    protected W weight;

    // for no add equals edges
    @Override
    public abstract boolean equals(Object obj);

}
