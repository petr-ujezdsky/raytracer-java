package org.pujezdsky.raytracer;

public class RectangleSceneObject extends SceneObject {

    private double width, height;
    private boolean provideInObject;

    public RectangleSceneObject(Vector3 origin, Matrix3 basis, double width, double height) {
        this(origin, basis, width, height, false);
    }

    public RectangleSceneObject(Vector3 origin, Matrix3 basis, double width, double height, boolean provideInObject) {
        super(origin, basis);
        this.width = width;
        this.height = height;
        this.provideInObject = provideInObject;
    }

    @Override
    public CollisionResult collision(Ray ray) {
        Vector3 normal = basis.getBasisVectorZ();

        double dotDirectionNormal = Vector3.dot(ray.getDirection(), normal);
        //Vector3 rayDirLocal = PointWorldToLocal(ray.Direction);

        if (!provideInObject && dotDirectionNormal > 0) {
            dotDirectionNormal = -dotDirectionNormal;
            normal = normal.multiply(-1);
        }

        if (dotDirectionNormal != 0) {
            boolean inObject = false;

            double distance = (Vector3.dot(origin, normal) - Vector3.dot(ray.getPosition(), normal)) / dotDirectionNormal;
            if (distance <= 0) {
                return null;
            }

            if (provideInObject) {
                inObject = (dotDirectionNormal > 0);
            }

            Vector3 collisionPoint = ray.getDirection()
                    .multiply(distance)
                    .add(ray.getPosition());

            Vector3 collisionPointRelative = pointWorldToLocal(collisionPoint);

            if (collisionPointRelative.x < width && collisionPointRelative.y < height && collisionPointRelative.x > 0 && collisionPointRelative.y > 0) {
                return new CollisionResult(
                        collisionPoint,
                        distance,
                        normal,
                        inObject
                );
            }
        }
        return null;
    }

    @Override
    public boolean collision(Ray ray, double maxDistanceSq) {
        Vector3 normal = basis.getBasisVectorZ();
        double dotDirectionNormal = Vector3.dot(ray.getDirection(), normal);

        if (!provideInObject && dotDirectionNormal > 0) {
            dotDirectionNormal = -dotDirectionNormal;
            normal = normal.multiply(-1);
        }

        if (dotDirectionNormal != 0) {
            double distance = (Vector3.dot(origin, normal) - Vector3.dot(ray.getPosition(), normal)) / dotDirectionNormal;
            if (distance <= 0 || distance * distance > maxDistanceSq) {
                return false;
            }

            Vector3 collisionPoint = ray.getDirection()
                    .multiply(distance)
                    .add(ray.getPosition());

            Vector3 collisionPointRelative = pointWorldToLocal(collisionPoint);

            if (collisionPointRelative.x < width && collisionPointRelative.y < height && collisionPointRelative.x > 0 && collisionPointRelative.y > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Material getMaterialAt(Vector3 worldPoint) {
        if (material.getTexture() != null) {
            Vector3 localPoint = pointWorldToLocal(worldPoint);


            int xTexture = (int) Math.round(localPoint.x / width * (material.getTexture().getWidth() - 1));
            int yTexture = (int) Math.round(localPoint.y / height * (material.getTexture().getHeight() - 1));

            ColorD c = material.getTexture().getPixelAt(xTexture, yTexture);
            Material m = new Material(material);
            m.setColor(c);

//            double a = 24;

//            double xx = Math.abs(localPoint.x - width / 2) / width; //0 -- 1
//            double yy = Math.abs(localPoint.y - height / 2) / height;
//            double dist = Math.sqrt(xx * xx + yy * yy);

            return new Material(
//                        1 - m.getRefraction() * m.getRefraction() * m.getRefraction(),
                    0.1,

                    material.getReflection(),

                    //m.Refraction = Math.Abs(2 * x / width - 1) + Math.Abs(2 * y / height - 1);
                    //m.Refraction = Math.Pow(0.5 * m.Refraction, pow);
                    1,

                    material.getN(),
                    material.getGloss(),
                    c
            );
        } else {
            return material;
        }
    }
}
