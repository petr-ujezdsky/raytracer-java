package org.pujezdsky.raytracer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class TextureLoader {

    public static IBitmap loadFromFile(Path path) {
        final BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(path.toFile());
        } catch (IOException e) {
            return null;
        }

        return new IBitmap() {
            @Override
            public ColorD getPixelAt(int x, int y) {
                return new ColorD(bufferedImage.getRGB(x, y));
            }

            @Override
            public int getWidth() {
                return bufferedImage.getWidth();
            }

            @Override
            public int getHeight() {
                return bufferedImage.getHeight();
            }
        };
    }
}
