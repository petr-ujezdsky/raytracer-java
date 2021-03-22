package org.pujezdsky.raytracer;

import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

public class ColorDTest extends TestCase {

    @Test
    public void testFromARGB() {
        ColorD color = new ColorD(Color.RED.getRGB());
        assertEquals(1.0, color.getR());
        assertEquals(0.0, color.getG());
        assertEquals(0.0, color.getB());

        color = new ColorD(Color.GREEN.getRGB());
        assertEquals(0.0, color.getR());
        assertEquals(1.0, color.getG());
        assertEquals(0.0, color.getB());

        color = new ColorD(Color.BLUE.getRGB());
        assertEquals(0.0, color.getR());
        assertEquals(0.0, color.getG());
        assertEquals(1.0, color.getB());

        color = new ColorD(Color.PINK.getRGB());
        assertEquals(1.0, color.getR());
        assertEquals(0.6862745098039216, color.getG());
        assertEquals(0.6862745098039216, color.getB());
    }

    @Test
    public void testToARGB() {
        ColorD color = new ColorD(Color.RED.getRGB());
        assertEquals(Color.RED.getRGB(), color.toARGB());

        color = new ColorD(Color.GREEN.getRGB());
        assertEquals(Color.GREEN.getRGB(), color.toARGB());

        color = new ColorD(Color.BLUE.getRGB());
        assertEquals(Color.BLUE.getRGB(), color.toARGB());

        color = new ColorD(Color.PINK.getRGB());
        assertEquals(Color.PINK.getRGB(), color.toARGB());
    }
}
