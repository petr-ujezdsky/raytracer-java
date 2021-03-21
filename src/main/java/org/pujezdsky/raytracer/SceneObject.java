package org.pujezdsky.raytracer;

public abstract class SceneObject {

    protected Material material;

    protected Matrix3 basis = Matrix3.IDENTITY;

    protected Vector3 origin = new Vector3();

    public SceneObject(Vector3 origin, Matrix3 basis) {
        this.basis = basis;
        this.origin = origin;
    }

    public abstract CollisionResult collision(Ray ray);
    public abstract boolean collision(Ray ray, double maxDistanceSq);


    public Vector3 pointWorldToLocal(Vector3 world) {
        world.mutSubtract(origin);
        return basis.multiply(world);
    }

    public Vector3 pointLocalToWorld(Vector3 local) {
        Matrix3 m = basis.transpose();
        Vector3 world = m.multiply(local);
        world.mutAdd(origin);
        return world;
    }

    protected void transform(Matrix3 rotation, Vector3 translation) {
        basis = rotation.multiply(basis);
        origin = translation;
    }

    public Material getMaterialAt(Vector3 worldPoint) {
        return material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
