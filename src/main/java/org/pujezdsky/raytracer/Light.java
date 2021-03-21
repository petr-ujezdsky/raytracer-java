package org.pujezdsky.raytracer;

public class Light extends Ray {
    double spread;
    ColorD color;

    public Light(Vector3 position, Vector3 direction, double spread) {
        this(position, direction, spread, ColorD.FULL);
    }

    public Light(Vector3 position, Vector3 direction, double spread, ColorD color) {
        super(position, direction);
        this.spread = spread;
        this.color = color;
    }

    public double getSpread() {
        return spread;
    }

    public ColorD getColor() {
        return color;
    }
}
