package org.pujezdsky.raytracer;

import junit.framework.TestCase;
import org.junit.Test;

public class Vector3Test extends TestCase {

    @Test
    public void testGetLength() {
        assertEquals(3.7416573867739413, new Vector3(1, 2, 3).getLength());
        assertEquals(1.0, new Vector3(1, 0, 0).getLength());
        assertEquals(1.0, new Vector3(0, 1, 0).getLength());
        assertEquals(1.0, new Vector3(0, 0, 1).getLength());
    }

    @Test
    public void testGetLengthSq() {
        assertEquals(27.0, new Vector3(3, 3, 3).getLengthSq());
        assertEquals(1.0, new Vector3(1, 0, 0).getLengthSq());
        assertEquals(1.0, new Vector3(0, 1, 0).getLengthSq());
        assertEquals(1.0, new Vector3(0, 0, 1).getLengthSq());
    }

    @Test
    public void testAdd() {
        Vector3 v = new Vector3(1, 2, 3);

        Vector3 v2 = v.add(new Vector3(5, 6, 7));

        assertEquals(6.0, v2.getX());
        assertEquals(8.0, v2.getY());
        assertEquals(10.0, v2.getZ());
    }

    @Test
    public void testSubtract() {
        Vector3 v = new Vector3(1, 2, 3);

        Vector3 v2 = v.subtract(new Vector3(5, 6, 7));

        assertEquals(-4.0, v2.getX());
        assertEquals(-4.0, v2.getY());
        assertEquals(-4.0, v2.getZ());
    }

    @Test
    public void testMultiply() {
        Vector3 v = new Vector3(1, 2, 3);

        Vector3 v2 = v.multiply(5.0);

        assertEquals(5.0, v2.getX());
        assertEquals(10.0, v2.getY());
        assertEquals(15.0, v2.getZ());
    }
}
