package org.pujezdsky.raytracer;

import java.util.Objects;

public class Vector3 {

    public static final Vector3 EMPTY = new Vector3(0, 0, 0);
    public static final Vector3 E1 = new Vector3(1, 0, 0);
    public static final Vector3 E2 = new Vector3(0, 1, 0);
    public static final Vector3 E3 = new Vector3(0, 0, 1);

    public double x;
    public double y;
    public double z;

    public Vector3() {
        this(0, 0, 0);
    }
    public Vector3(Vector3 v) {
        this(v.x, v.y, v.z);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getLengthSq() {
        return x * x + y * y + z * z;
    }


    public double getLength() {
        return Math.sqrt(getLengthSq());
    }

    public void mutAdd(Vector3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public void mutSubtract(Vector3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public void mutMultiply(double d) {
        x *= d;
        y *= d;
        z *= d;
    }

    public void mutNormalize() {
        double length = getLength();

        x /= length;
        y /= length;
        z /= length;
    }

    public Vector3 add(Vector3 v) {
        return new Vector3(
                x + v.x,
                y + v.y,
                z + v.z
        );
    }

    public Vector3 subtract(Vector3 v) {
        return new Vector3(
                x - v.x,
                y - v.y,
                z - v.z
        );
    }

    public Vector3 multiply(double d) {
        return new Vector3(
                x * d,
                y * d,
                z * d
        );
    }

    public Vector3 negate() {
        return new Vector3(-x, -y, -z);
    }

    public Vector3 normalize() {
        double length = getLength();

        return new Vector3(
                x / length,
                y / length,
                z / length
        );
    }

    public void mutZeroize() {
        x = y = z = 0;
    }

    public static double dot(Vector3 a, Vector3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vector3 crossProduct(Vector3 a, Vector3 b) {
        return new Vector3(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return Double.compare(vector3.x, x) == 0 && Double.compare(vector3.y, y) == 0 && Double.compare(vector3.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("x=%f, y=%f, z=%f", x, y, z);
    }
}
