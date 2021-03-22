package org.pujezdsky.raytracer;

import junit.framework.TestCase;
import org.junit.Test;

public class SphereSceneObjectTest extends TestCase {

    @Test
    public void testCollision() {
        SceneObject obj = new SphereSceneObject(Vector3.EMPTY, Matrix3.BASIS, 2.0);

        Ray ray = new Ray(new Vector3(10, 0, 0), new Vector3(-10, 0, 0).normalize());

        CollisionResult collision = obj.collision(ray);
        assertNotNull(collision);

        assertEquals(new Vector3(2,0,0), collision.getCollisionPoint());
        assertEquals(new Vector3(1,0,0), collision.getNormal());
        assertEquals(8.0, collision.getDistance());
        assertFalse(collision.isInObject());
    }
}
