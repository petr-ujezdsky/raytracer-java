package org.pujezdsky.raytracer;

import java.awt.*;
import java.nio.file.Paths;

public class WorldLoader {

    public World initWorld() {
        World world = new World();
        SceneObject sphereLeft = new SphereSceneObject(new Vector3(0, 0, 0), Matrix3.rotationZ(3.14 / 1), 3);
        SceneObject sphereRight = new SphereSceneObject(new Vector3(-5, 0, 2), Matrix3.BASIS, 2);

        SceneObject boundsDown = new PlaneSceneObject(new Vector3(0, 0, -20), Matrix3.BASIS);
        boundsDown.getMaterial().setTexture(TextureLoader.loadFromFile(Paths.get("~/Obrázky/Star-Wars-Falcon.jpeg")));
        SceneObject boundsUp = new PlaneSceneObject(new Vector3(0, 0, 20), Matrix3.rotationX(Math.PI));
        SceneObject boundsLeft = new PlaneSceneObject(new Vector3(0, 20, 0), Matrix3.rotationX(Math.PI / 2));
        SceneObject boundsRight = new PlaneSceneObject(new Vector3(0, -20, 0), Matrix3.rotationX(-Math.PI / 2));
        SceneObject boundsBack = new PlaneSceneObject(new Vector3(20, 0, 0), Matrix3.rotationY(-Math.PI / 2));

        //SceneObject boundsDown = new SOSphere(new Vector3(0, 0, -10000), Matrix3.Basis, 9995);
        //SceneObject boundsUp = new SOSphere(new Vector3(0, 0, 10000), Matrix3.Basis, 9980);
        //SceneObject boundsLeft = new SOSphere(new Vector3(0, 10000, 0), Matrix3.Basis, 9980);
        //SceneObject boundsRight = new SOSphere(new Vector3(0, -10000, 0), Matrix3.Basis, 9980);
        //SceneObject boundsBack = new SOSphere(new Vector3(100000, 0, 0), Matrix3.Basis, 99980);

        sphereLeft.getMaterial().setColor(new ColorD(Color.YELLOW));
        sphereLeft.getMaterial().setDiffusion(1);
        sphereLeft.getMaterial().setReflection(1 - sphereLeft.getMaterial().getDiffusion());
        sphereLeft.getMaterial().setTexture(TextureLoader.loadFromFile(Paths.get("~/Obrázky/wanted.jpg")));

        sphereRight.getMaterial().setColor(new ColorD(Color.BLUE));
        sphereRight.getMaterial().setDiffusion(0.2);
        sphereRight.getMaterial().setReflection(1 - sphereRight.getMaterial().getDiffusion());
        sphereRight.setMaterial(Material.GLASS);
        sphereRight.getMaterial().setN(1.1);

        SceneObject rect = new RectangleSceneObject(new Vector3(0, 0, 3), Matrix3.BASIS, 10, 6, false);
        //SceneObject rect = new SORectangle(Vector3.E3, new Vector3(-5, 3, -1), Vector3.E1, 10, 6, false);
        rect.getMaterial().setColor(new ColorD(Color.RED));
        rect.getMaterial().setDiffusion(1);// 0.6;
        rect.getMaterial().setReflection(0);// 1 - rect.Material.Diffusion;
        rect.getMaterial().setTexture(TextureLoader.loadFromFile(Paths.get("~/Obrázky/wanted.jpg")));
        rect.getMaterial().setN(world.getN());
        //rect.Material = Material.Mirror;

        //SceneObject box = new SOBox(new Vector3(0, 0, 0),Matrix3.Basis, 1, 3, 2);
        SceneObject box = new BoxSceneObject(new Vector3(0, 0, 0), Matrix3.rotationY(3.14 / 2), 1, 3, 2);
        box.getMaterial().setColor(new ColorD(Color.ORANGE));
        box.getMaterial().setDiffusion(1);
        box.getMaterial().setRefraction(0);
        //box.Material = Material.Glass;

        boundsDown.getMaterial().setColor(new ColorD(Color.GREEN));
        boundsUp.getMaterial().setColor(new ColorD(Color.ORANGE));
        boundsLeft.getMaterial().setColor(new ColorD(Color.CYAN));
        boundsRight.getMaterial().setColor(new ColorD(Color.RED));
        boundsBack.getMaterial().setColor(new ColorD(Color.GREEN.darker()));

        boundsDown.getMaterial().setDiffusion(1);
        boundsUp.getMaterial().setDiffusion(1);
        boundsLeft.getMaterial().setDiffusion(1);
        boundsRight.getMaterial().setDiffusion(1);
        boundsBack.getMaterial().setDiffusion(1);

        boundsDown.getMaterial().setReflection(0);
        boundsUp.getMaterial().setReflection(0);
        boundsLeft.getMaterial().setReflection(0);
        boundsRight.getMaterial().setReflection(0);
        boundsBack.getMaterial().setReflection(0);

        double stepY = 1, stepX = 1;
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 5; k++) {
                SceneObject sphere = new SphereSceneObject(new Vector3(stepX * k, -5 + j * stepY, 0), Matrix3.BASIS, 0.4);
                sphere.getMaterial().setColor(new ColorD(Color.RED));
                sphere.getMaterial().setDiffusion(0.2);
                sphere.getMaterial().setReflection(1 - sphere.getMaterial().getReflection());
                //world.Objects.Add(sphere);
            }
        }


        Light lightEye = new Light(new Vector3(-10, -10, 0), Vector3.E1, Math.PI);
        Light lightUp = new Light(new Vector3(-5, 0.2, 10), Vector3.E3.negate(), Math.PI);
        Light lightDown = new Light(new Vector3(0, 0, -10), Vector3.E3, Math.PI, new ColorD(0, 0, 1));

        world.getObjects().add(sphereLeft);
        world.getObjects().add(sphereRight);
        world.getObjects().add(rect);
        //world.getObjects().add(box);

        world.getObjects().add(boundsDown);
        world.getObjects().add(boundsUp);
        world.getObjects().add(boundsLeft);
        world.getObjects().add(boundsRight);
        world.getObjects().add(boundsBack);

        world.getLights().add(lightEye);
        world.getLights().add(lightUp);
        //world.Lights.Add(light3);

        return world;
    }
}
