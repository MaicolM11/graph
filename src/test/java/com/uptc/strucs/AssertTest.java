package com.uptc.strucs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import java.awt.Point;

public class AssertTest extends Graph<String, Integer> {

    public AssertTest() {
        super((a, b) -> a.compareTo(b));
    }

    @Before
    public void fill() {
        this.addAlone("Andrea");
        this.addAlone("Carlos");
        this.addAlone("Pepe");
        this.addAlone("Julia");

        this.addConn("Andrea", "Carlos", 6);
        this.addConn("Pepe", "Carlos", 12);
        this.addConn("Carlos", "Julia", 18);
    }

    @Test
    public void weightTest() {
        assertEquals(this.getWeight("Pepe", "Carlos"), Integer.valueOf(12));
        assertEquals(this.getWeight("Andrea", "Carlos"), Integer.valueOf(6));
        assertEquals(this.getWeight("Carlos", "Julia"), Integer.valueOf(18));
        assertEquals(this.getWeight("Julia", "Carlos"), Integer.valueOf(18));

        assertNotEquals(this.getWeight("Pepe", "Carlos"), Integer.valueOf(1));
    }

    @Test
    public void weightNull() {
        try {
            //this.getWeight("Pepe", "Carlos");
            this.getWeight("Pepe", "kfdskj");
            fail("El programa debio lanzar una excepcion");
        } catch (NullPointerException e) {
            assertEquals("", "");
        }
    }

    @Test
    public void addRepet() {
        assertTrue(this.addAlone("nuevo", new Point()));
        assertFalse(this.addAlone("Carlos", new Point()));
    }

    @Test
    public void addRepetConn() {
        assertTrue(this.addConn("Carlos", "Julia", 16));
        assertFalse(this.addConn("Carlos", "Julia", 18));        
    } 

}
