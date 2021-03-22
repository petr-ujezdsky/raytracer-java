package org.pujezdsky.raytracer;

import java.awt.*;

public class ColorD {
    public static final ColorD ZERO = new ColorD(0, 0, 0);

    public static final ColorD FULL = new ColorD(1, 1, 1);

    private double r, g, b;

    private static double BYTE_MAX_INV = 1.0 / 255;

    public ColorD(double r, double g, double b) {
        this.r = toRange(r);
        this.g = toRange(g);
        this.b = toRange(b);
    }

    public ColorD(Color color) {
        this(
                BYTE_MAX_INV * color.getRed(),
                BYTE_MAX_INV * color.getGreen(),
                BYTE_MAX_INV * color.getBlue());
    }

    public ColorD(ColorD color) {
        this(
                color.r,
                color.g,
                color.b);
    }

    public ColorD(int rgb) {
        this(
                BYTE_MAX_INV * ((rgb >> 16) & 0x000000FF),
                BYTE_MAX_INV * ((rgb >> 8) & 0x000000FF),
                BYTE_MAX_INV * ((rgb) & 0x000000FF)
        );
    }

    public void mutAdd(ColorD color) {
        r = toRange(r + color.r);
        g = toRange(g + color.g);
        b = toRange(b + color.b);
    }

    public void mutMultiply(double d) {
        r *= d;
        g *= d;
        b *= d;
    }

    public ColorD add(ColorD color) {
        return new ColorD(
                r + color.r,
                g + color.g,
                b + color.b);
    }

    public ColorD multiply(double d) {
        return new ColorD(
                r * d,
                g * d,
                b * d);
    }

    public ColorD multiply(ColorD color) {
        return new ColorD(
                r * color.r,
                g * color.g,
                b * color.b);
    }

    public int toARGB() {
        return
                0xFF000000 +
                        ((int) (r * 255) << 16) +
                        ((int) (g * 255) << 8) +
                        ((int) (b * 255));
    }

    private double toRange(double d) {
        return Math.min(Math.max(0, d), 1);
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    @Override
    public String toString() {
        return String.format("R:%f G:%f B:%f", r, g, b);
    }
}
