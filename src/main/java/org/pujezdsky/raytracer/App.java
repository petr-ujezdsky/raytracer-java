package org.pujezdsky.raytracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {

    public static String HOME = System.getProperty("user.home");


    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");


        Engine engine = new Engine();

        World world = WorldLoader.initWorld();

        BufferedImage image = engine.render(world);

        ImageIO.write(image, "png", Path.of(HOME, "/temp/out.png").toFile());
    }
}
