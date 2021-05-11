package org.pujezdsky.raytracer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RayTracer {
    public static final int
            NUM_REFLECTIONS = 3,
            NUM_REFRACTIONS = 2,
    //NUM_BLOCKS = 16,
    ANTIALIASING = 1;

    private final World world;

    private final AtomicInteger blocksDone = new AtomicInteger(0);

    public RayTracer(World world) {
        this.world = world;
    }

    public FastBitmap render(Camera camera, int width, int height) {
        if (width <= 0 || height <= 0)
            return null;
        camera.setDimensions(width, height);
        FastBitmap fImg = new FastBitmap(width, height);

        //double step = height / (double)NUM_BLOCKS;

        //step = 1;
        //Parallel.For(0, NUM_BLOCKS, new Action<int>(
//        Parallel.For(0, height, new Action<int>(
//                (j)=>
//                {
//                        RenderBlock(j, j + 1, width, height, camera, fImg);
//            }
//            ));

        IntStream.range(0, height).parallel().forEach(y -> renderBlock(y, y + 1, width, height, camera, fImg));

        return fImg;
    }

    private void renderBlock(int y1, int y2, int width, int height, Camera camera, FastBitmap fImg) {
        //Thread.CurrentThread.Priority = ThreadPriority.Lowest;
        //Console.WriteLine("Rendering range {0} - {1}", y1, y2);

        int n = ANTIALIASING;
        double okoli = 1;
        double step = okoli / n;

        for (int y = y1; y < y2; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 1100 && y == 700) {
                }
                ColorD color = new ColorD(ColorD.ZERO);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        Ray ray = new Ray(camera.getPosition(), camera.getDirectionFromPixel(x + i * step, y + j * step));
                        color.mutAdd(trace(ray, world.getN(), 0, 0).multiply(1d / (double) (n * n)));
                    }
                }

                //Ray ray = new Ray(camera.Position, camera.GetDirectionFromPixel(x, y), world.N);
                //ColorD color = Trace(ray, 0, 0);
                fImg.setPixelAt(x, y, color);
            }
        }

        blocksDone.incrementAndGet();
    }

    private ColorD trace(Ray ray, double nFrom, int numReflections, int numRefractions) {
        if (numReflections > NUM_REFLECTIONS || numRefractions > NUM_REFRACTIONS) {
            return new ColorD(ColorD.ZERO);
        }

//        double distance = double.MaxValue;
//        Vector3 collisionPoint, normal;
//        collisionPoint = normal = Vector3.EMPTY;
        SceneObject sceneObject = null;
//        Material material = null;
//        boolean inObject = false;
        CollisionResult collisionResult = null;

        for (SceneObject obj : world.getObjects()) {
            CollisionResult tmpCollisionResult = obj.collision(ray);

            if (tmpCollisionResult != null) {
                if (collisionResult == null || tmpCollisionResult.getDistance() < collisionResult.getDistance()) {
                    collisionResult = tmpCollisionResult;
                    sceneObject = obj;
                }
            }
        }

        //collision occured
        if (sceneObject != null) {
            double distance = collisionResult.getDistance();
            Vector3 collisionPoint = collisionResult.getCollisionPoint();
            Vector3 normal = collisionResult.getNormal();
            boolean inObject = collisionResult.isInObject();

            Material material = sceneObject.getMaterialAt(collisionPoint);
            double nTo = (inObject) ? world.getN() : material.getN();
            ColorD colorReflection = new ColorD(ColorD.ZERO),
                    colorDiffusion = new ColorD(ColorD.ZERO),
                    color = new ColorD(ColorD.ZERO),
                    tmpColor;
            Vector3 tmpVector = Vector3.EMPTY;

            //REFRACTION
            if (material.getRefraction() != 0) {
                Ray rayRefraction = ray.Clone();
                rayRefraction.refractAndMove((inObject) ? normal.negate() : normal, collisionPoint, nFrom, nTo);

                tmpColor = trace(rayRefraction, nTo, numReflections, numRefractions + 1);
                tmpColor.mutMultiply(material.getRefraction());
                color.mutAdd(tmpColor);
                //tmpColor.Zeroize();
            }


            //DIFFUSE & REFLECTION

            ray.reflectAndMove(normal, collisionPoint);
            Vector3 collisionPointPrecise = collisionPoint;
            collisionPoint.mutAdd(normal.multiply(0.000001d));
            double lengthSq = 0;
            for (Light light : world.getLights()) {
                tmpVector = light.getPosition();
                tmpVector.mutSubtract(collisionPoint);
                lengthSq = tmpVector.getLengthSq();
                tmpVector.mutNormalize();
                Ray rayToLight = new Ray(collisionPoint, tmpVector);
                //if (Vector3.Dot(rayToLight.Direction, normal) <= 0)                        continue;

                double dotLightReflection = Vector3.dot(rayToLight.getDirection(), ray.getDirection());
                double dotLightDiffuse = Vector3.dot(rayToLight.getDirection(), normal);

                if ((dotLightReflection > 0 || dotLightDiffuse > 0) &&
                        !world.rayCollide(rayToLight, lengthSq)) {
                    //Reflection
                    if (dotLightReflection > 0) {
                        dotLightReflection = Math.pow(dotLightReflection, material.getGloss());
                        colorReflection.mutAdd((light.getColor().multiply(dotLightReflection)).multiply(material.getColor()));
                    }

                    //Diffuse
                    if (dotLightDiffuse > 0) {
                        tmpColor = new ColorD(material.getColor());
                        tmpColor.mutMultiply(dotLightDiffuse);
                        colorDiffusion.mutAdd(tmpColor);
                    }
                }
            }

            if (material.getDiffusion() != 0) {
                tmpColor = colorDiffusion;
                tmpColor.mutMultiply(material.getDiffusion());
                color.mutAdd(tmpColor);
            }

            //REFLECTION
            if (material.getReflection() != 0) {
                colorReflection.mutMultiply(material.getReflection());
                color.mutAdd(colorReflection);

                tmpColor = trace(ray, nTo, numReflections + 1, numRefractions);
                tmpColor.mutMultiply(material.getReflection());

                color.mutAdd(tmpColor);
            }
            return color;
        } else
            return new ColorD(ColorD.ZERO);
    }
}
