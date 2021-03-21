package org.pujezdsky.raytracer;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Light> lights = new ArrayList<>();

    private final List<SceneObject> objects = new ArrayList<>();

    public List<Light> getLights() {
        return lights;
    }

    public List<SceneObject> getObjects() {
        return objects;
    }

    public boolean rayCollide(Ray ray) {
        return rayCollide(ray, Double.MAX_VALUE);
    }

    public boolean rayCollide(Ray ray, double maxDistanceSq) {
        for (SceneObject obj : objects) {
            if (obj.collision(ray, maxDistanceSq))
                return true;
        }
        return false;
    }

    public double getN() {
        return 1.003;
    }
}
