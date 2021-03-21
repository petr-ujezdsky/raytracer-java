package org.pujezdsky.raytracer;

public class FastBitmap {

    private final int width, height;

    private final ColorD[] pixels;

    public FastBitmap(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new ColorD[width * height];
    }

    public ColorD getPixelAt(int x, int y) {
        return pixels[y * width + x];
    }

    public void setPixelAt(int x, int y, ColorD color) {
        pixels[y * width + x] = color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
