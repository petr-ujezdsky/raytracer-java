package org.pujezdsky.raytracer;

public class Matrix3 {

    public static final Matrix3 ZERO = new Matrix3(0,0,0,0,0,0,0,0,0);

    public static final Matrix3 IDENTITY = new Matrix3(
            1,0,0,
            0,1,0,
            0,0,1);

    private double
            m11, m12, m13,
            m21, m22, m23,
            m31, m32, m33;

    public Matrix3(double m11, double m12, double m13,
                   double m21, double m22, double m23,
                   double m31, double m32, double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public void mutTranspose() {
        double tmp;

        tmp = m12;
        m12 = m21;
        m21 = tmp;

        tmp = m13;
        m13 = m31;
        m31 = tmp;

        tmp = m23;
        m23 = m32;
        m32 = tmp;
    }

    public double getDeterminant() {
        return m11 * m22 * m33 + m21 * m32 * m13 + m31 * m12 * m23
                - m13 * m22 * m31 - m23 * m32 * m11 - m33 * m12 * m21;
    }

    public double getTrace() {
        return m11 * m22 * m33;
    }

    public Matrix3 transpose() {
        return new Matrix3(
                m11, m21, m31,
                m12, m22, m32,
                m13, m23, m33
        );
    }

    public Matrix3 add(Matrix3 m) {
        return add(this, m);
    }

    public Matrix3 subtract(Matrix3 m) {
        return subtract(this, m);
    }

    public Matrix3 negate() {
        return negate(this);
    }

    public Matrix3 multiply(Matrix3 m) {
        return multiply(this, m);
    }

    public Matrix3 multiply(double d) {
        return multiply(this, d);
    }

    public Vector3 multiply(Vector3 v) {
        return multiply(this, v);
    }

    public static Matrix3 add(Matrix3 a, Matrix3 b) {
        return new Matrix3(
                a.m11 + b.m11, a.m12 + b.m12, a.m13 + b.m13,
                a.m21 + b.m21, a.m22 + b.m22, a.m23 + b.m23,
                a.m31 + b.m31, a.m32 + b.m32, a.m33 + b.m33);
    }

    public static Matrix3 subtract(Matrix3 a, Matrix3 b) {
        return new Matrix3(
                a.m11 - b.m11, a.m12 - b.m12, a.m13 - b.m13,
                a.m21 - b.m21, a.m22 - b.m22, a.m23 - b.m23,
                a.m31 - b.m31, a.m32 - b.m32, a.m33 - b.m33);
    }

    public static Matrix3 negate(Matrix3 a) {
        return new Matrix3(
                -a.m11, -a.m12, -a.m13,
                -a.m21, -a.m22, -a.m23,
                -a.m31, -a.m32, -a.m33);
    }

    public static Matrix3 multiply(Matrix3 a, Matrix3 b) {
        return new Matrix3(
                a.m11 * b.m11 + a.m12 * b.m21 + a.m13 * b.m31, a.m11 * b.m12 + a.m12 * b.m22 + a.m13 * b.m32, a.m11 * b.m13 + a.m12 * b.m23 + a.m13 * b.m33,
                a.m21 * b.m11 + a.m22 * b.m21 + a.m23 * b.m31, a.m21 * b.m12 + a.m22 * b.m22 + a.m23 * b.m32, a.m21 * b.m13 + a.m22 * b.m23 + a.m23 * b.m33,
                a.m31 * b.m11 + a.m32 * b.m21 + a.m33 * b.m31, a.m31 * b.m12 + a.m32 * b.m22 + a.m33 * b.m32, a.m31 * b.m13 + a.m32 * b.m23 + a.m33 * b.m33
        );
    }

    public static Matrix3 multiply(Matrix3 a, double d) {
        return new Matrix3(
                a.m11 * d, a.m12 * d, a.m13 * d,
                a.m21 * d, a.m22 * d, a.m23 * d,
                a.m31 * d, a.m32 * d, a.m33 * d
        );
    }

    public static Vector3 multiply(Matrix3 m, Vector3 v) {
        return new Vector3(
                m.m11 * v.x + m.m12 * v.y + m.m13 * v.z,
                m.m21 * v.x + m.m22 * v.y + m.m23 * v.z,
                m.m31 * v.x + m.m32 * v.y + m.m33 * v.z
        );
    }

    public static Matrix3 rotation(Vector3 axis, double angle) {
        double c = Math.cos(angle);
        double s = Math.sin(angle);

        return new Matrix3(
                axis.x * axis.x + (1 - axis.x * axis.x) * c, axis.x * axis.y * (1 - c) - axis.z * s, axis.x * axis.z * (1 - c) + axis.y * s,
                axis.x * axis.y * (1 - c) + axis.z * s, axis.y * axis.y + (1 - axis.y * axis.y) * c, axis.y * axis.z * (1 - c) - axis.x * s,
                axis.x * axis.z * (1 - c) - axis.y * s, axis.y * axis.z * (1 - c) + axis.x * s, axis.z * axis.z + (1 - axis.z * axis.z) * c
        );
    }

    public Vector3 getBasisVectorX() {
        return new Vector3(m11, m12, m13);
    }

    public Vector3 getBasisVectorY() {
        return new Vector3(m21, m22, m23);
    }

    public Vector3 getBasisVectorZ() {
        return new Vector3(m31, m32, m33);
    }

    @Override
    public String toString() {
        return String.format("%f %f %f\n%f %f %f\n%f %f %f", m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }
}
