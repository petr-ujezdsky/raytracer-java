package org.pujezdsky.raytracer;

public interface IBitmap {

    ColorD getPixelAt(int x, int y);

    int getWidth();

    int getHeight();
}
