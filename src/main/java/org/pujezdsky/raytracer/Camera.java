package org.pujezdsky.raytracer;

public class Camera extends Ray {

    Vector3 up;
    public int width, height;
    double fov, betaX, betaY;

    public Camera(Vector3 position, Vector3 direction, Vector3 up, double fov) {
        super(position, direction);
        this.up = up;
        this.fov = fov;
    }

    public Vector3 getDirectionFromPixel(double x, double y) {
        double bX = -fov / 2 + betaX * x;
        double bY = -fov / 2 + betaY * y;
//        Matrix3 transform = Matrix3.rotation(up, -bX) * Matrix3.rotation(Vector3.crossProduct(direction, up),-bY);
//        return transform * direction;
        Matrix3 transform = Matrix3.multiply(Matrix3.rotation(up, -bX), Matrix3.rotation(Vector3.crossProduct(direction, up), -bY));
        return transform.multiply(direction);
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;

        if (width > height) {
            betaX = betaY = fov / width;
        } else {
            betaX = betaY = fov / height;
        }
    }
}
