package org.pujezdsky.raytracer;

public class SphereSceneObject extends SceneObject {
    private final double r, rOver;

    public SphereSceneObject(Vector3 origin, Matrix3 basis, double r) {
        super(origin, basis);
        this.r = r;
        this.rOver = 1.0 / r;
    }

    @Override
    public CollisionResult collision(Ray ray) {
        Vector3 collisionPoint = new Vector3();
        Vector3 normal = new Vector3();
        double distance = 0;
        Vector3 v = ray.getPosition().subtract(origin);
        double tmp1 = -(Vector3.dot(v, ray.getDirection()));
        double tmp2 = tmp1 * tmp1 - v.getLengthSq() + r * r;
        //collision
        if (tmp2 >= 0) {
            double tmp3 = Math.sqrt(tmp2);
            double t1 = tmp1 + tmp3;
            double t2 = tmp1 - tmp3;
            double t = Math.min(t1, t2);
            //sphere is in front of us
            if (t >= 0) {
                collisionPoint = (ray.getPosition().add(ray.getDirection().multiply(t)));
                normal = new Vector3(collisionPoint);
                normal.mutSubtract(origin);
                normal.mutMultiply(rOver);
                //normal = GetNormalAt(collisionPoint);
                return new CollisionResult(
                        collisionPoint,
                        t,
                        normal,
                        false
                );
            } else {
                t = Math.max(t1, t2);
                //we are in the sphere
                if (t >= 0) {
                    collisionPoint = (ray.getPosition().add(ray.getDirection().multiply(t)));
                    normal = new Vector3(collisionPoint);
                    normal.mutSubtract(origin);
                    normal.mutMultiply(rOver);
                    //normal = GetNormalAt(collisionPoint);
                    return new CollisionResult(
                            collisionPoint,
                            t,
                            normal,
                            true
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean collision(Ray ray, double maxDistanceSq) {
        Vector3 v = ray.getPosition().subtract(origin);
        double tmp1 = -(Vector3.dot(v, ray.getDirection()));
        double tmp2 = tmp1 * tmp1 - v.getLengthSq() + r * r;
        //collision
        if (tmp2 >= 0) {
            double tmp3 = Math.sqrt(tmp2);
            double t1 = tmp1 + tmp3;
            double t2 = tmp1 - tmp3;
            double t = Math.min(t1, t2);
            //sphere is in front of us
            if (t >= 0) {
                return t * t <= maxDistanceSq;
            } else {
                t = Math.max(t1, t2);
                //we are in the sphere
                if (t >= 0) {
                    return t * t <= maxDistanceSq;
                }
            }
        }
        return false;
    }

    @Override
    public Material getMaterialAt(Vector3 worldPoint) {
        if (material.getTexture() != null)
        {
            Vector3 localPoint = pointWorldToLocal(worldPoint);
            double Phi = Math.atan2(localPoint.y, localPoint.x) + Math.PI;
            double Theta = Math.acos(localPoint.z / localPoint.getLength());

            double pp = Phi / (2 * Math.PI);
            double tt = Theta / Math.PI;

            Material m = new Material(material);
            m.setColor(material.getTexture().getPixelAt((int) Math.floor(pp * (material.getTexture().getWidth() - 1)), (int) Math.floor(tt * (material.getTexture().getHeight() - 1))));
            return m;
        }
        else
            return material;
    }
}
