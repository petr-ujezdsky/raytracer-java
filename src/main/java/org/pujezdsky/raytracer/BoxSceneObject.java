package org.pujezdsky.raytracer;

public class BoxSceneObject extends SceneObject {

    private final double width, height, depth;
    private final RectangleSceneObject[] rectangles = new RectangleSceneObject[6];

    public BoxSceneObject(Vector3 origin, Matrix3 basis, double width, double height, double depth) {
        super(origin, basis);
        this.width = width;
        this.height = height;
        this.depth = depth;

        Vector3 dirX = basis.getBasisVectorX(),
                dirY = basis.getBasisVectorY(),
                dirZ = basis.getBasisVectorZ();

        rectangles[0] = new RectangleSceneObject(origin, new Matrix3(dirY, dirX, dirZ.negate(), true), height, width, true);//podlaha
        rectangles[1] = new RectangleSceneObject(origin, new Matrix3(dirX, dirZ, dirY.negate(), true), width, depth, true);//predni stena
        rectangles[2] = new RectangleSceneObject(origin, new Matrix3(dirZ, dirY, dirX.negate(), true), depth, height, true);//leva stena

        origin = origin
                .add(dirX.multiply(width))
                .add(dirY.multiply(height))
                .add(dirZ.multiply(depth));
        rectangles[3] = new RectangleSceneObject(origin, new Matrix3(dirX.negate(), dirY.negate(), dirZ, true), width, height, true);//strop
        rectangles[4] = new RectangleSceneObject(origin, new Matrix3(dirY.negate(), dirZ.negate(), dirX, true), height, depth, true);//prava stena
        rectangles[5] = new RectangleSceneObject(origin, new Matrix3(dirZ.negate(), dirX.negate(), dirY, true), depth, width, true);//zadni stena
    }


    @Override
    public CollisionResult collision(Ray ray) {
        CollisionResult collisionResult = null;

        for (RectangleSceneObject rec : rectangles) {
            CollisionResult tmpCollisionResult = rec.collision(ray);
            if (tmpCollisionResult != null) {
                if (collisionResult == null || tmpCollisionResult.getDistance() < collisionResult.getDistance()) {
                    collisionResult = tmpCollisionResult;
                }
            }
        }

        return collisionResult;
    }

    @Override
    public boolean collision(Ray ray, double maxDistanceSq) {
        for (RectangleSceneObject rec : rectangles) {
            if (rec.collision(ray, maxDistanceSq)) {
                return true;
            }
        }
        return false;
    }
}
