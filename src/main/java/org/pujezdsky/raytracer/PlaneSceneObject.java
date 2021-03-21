package org.pujezdsky.raytracer;

public class PlaneSceneObject extends SceneObject {

    public PlaneSceneObject(Vector3 origin, Matrix3 basis) {
        super(origin, basis);
    }

    @Override
    public CollisionResult collision(Ray ray) {
        Vector3 normal = basis.getBasisVectorZ();
        double dotDirectionNormal = Vector3.dot(ray.getDirection(), normal);
        //Vector3 rayDirLocal = PointWorldToLocal(ray.Direction);

        if (dotDirectionNormal > 0) {
            dotDirectionNormal = -dotDirectionNormal;
            normal.mutMultiply(-1);
        }

        if (dotDirectionNormal != 0) {
            double distance = (Vector3.dot(origin, normal) - Vector3.dot(ray.getPosition(), normal)) / dotDirectionNormal;
            if (distance <= 0) {
                return null;
            }

            Vector3 collisionPoint = new Vector3(ray.getDirection());
            collisionPoint.mutMultiply(distance);
            collisionPoint.mutAdd(ray.getPosition());

            return new CollisionResult(
                    collisionPoint,
                    distance,
                    normal,
                    false
            );
        }

        return null;
    }

    @Override
    public boolean collision(Ray ray, double maxDistanceSq) {
        Vector3 normal = basis.getBasisVectorZ();
        double dotDirectionNormal = Vector3.dot(ray.getDirection(), normal);

        if (dotDirectionNormal > 0) {
            dotDirectionNormal = -dotDirectionNormal;
            normal.mutMultiply(-1);
        }

        if (dotDirectionNormal != 0) {
            double distance = (Vector3.dot(origin, normal) - Vector3.dot(ray.getPosition(), normal)) / dotDirectionNormal;
            return (distance > 0 && distance * distance <= maxDistanceSq);
        }
        return false;
    }

    @Override
    public Material getMaterialAt(Vector3 worldPoint) {
        if (material.getTexture() != null) {
            double width = 100, height = 75;
            Vector3 localPoint = pointWorldToLocal(worldPoint);

            int xTexture = ((int) Math.round(localPoint.x / width * (material.getTexture().getWidth() - 1))) % material.getTexture().getWidth();
            int yTexture = ((int) Math.round(localPoint.y / height * (material.getTexture().getHeight() - 1))) % material.getTexture().getHeight();

            ColorD c = material.getTexture().getPixelAt(Math.abs(xTexture), Math.abs(yTexture));
            Material m = new Material(material);
            m.setColor(c);
            return m;
        } else
            return material;

    }
}
