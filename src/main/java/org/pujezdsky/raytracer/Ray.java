package org.pujezdsky.raytracer;

public class Ray {

    protected Vector3 position, direction;

    protected ColorD color;

    public Ray(Vector3 position, Vector3 direction) {
        this.position = position;
        this.direction = direction;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getDirection() {
        return direction;
    }


    public void reflectAndMove(Vector3 normal, Vector3 point) {
        //double k = 0.1;
        //direction = direction - (2 + Math.Pow(rnd.NextDouble(), 20) * k) * (Vector3.Dot(normal, direction)) * normal;
        //direction += (0.2 + Math.Pow(rnd.NextDouble(), 20) * k) * Vector3.CrossProduct(direction, normal).ToNormal();

        //direction = direction - 2 * (Vector3.Dot(normal, direction)) * normal;
        direction = direction.subtract(normal.multiply(2 * (Vector3.dot(normal, direction))));

        //        position.Add(0.000001d * direction);
        position = point.add(direction.multiply(0.000001d));
    }

    public void refractAndMove(Vector3 normal, Vector3 point, double nFrom, double nTo) {
        double dotRayToNormal = -Vector3.dot(direction, normal);
        if (dotRayToNormal < 0) {
        }
        double n12 = nFrom / nTo;
        double c = Math.sqrt(1 - n12 * n12 * (1 - dotRayToNormal * dotRayToNormal));

//        direction = n12 * direction + (n12 * dotRayToNormal - c) * normal;
        direction = direction.multiply(n12).add(normal.multiply(n12 * dotRayToNormal - c));

        position = point;
//        position += 0.000001d * direction;
        position = position.add(direction.multiply(0.000001d));

    }

    public Ray Clone() {
        Ray ray = new Ray(position, direction);
        ray.color = color;
        return ray;
    }
}
