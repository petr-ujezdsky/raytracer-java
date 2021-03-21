package org.pujezdsky.raytracer;

public class ColorD {
    public static final ColorD ZERO = new ColorD(0, 0, 0);

    public static final ColorD FULL = new ColorD(1, 1, 1);

    private double r, g, b;

    public ColorD(double r, double g, double b) {
        this.r = toRange(r);
        this.g = toRange(g);
        this.b = toRange(b);
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
