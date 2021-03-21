package org.pujezdsky.raytracer;

public class CollisionResult {
    private final Vector3 collisionPoint;
    private final double distance;

    private final Vector3 normal;

    private final boolean inObject;

    public CollisionResult(Vector3 collisionPoint, double distance, Vector3 normal, boolean inObject) {
        this.collisionPoint = collisionPoint;
        this.distance = distance;
        this.normal = normal;
        this.inObject = inObject;
    }

    public Vector3 getCollisionPoint() {
        return collisionPoint;
    }

    public double getDistance() {
        return distance;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public boolean isInObject() {
        return inObject;
    }
}
