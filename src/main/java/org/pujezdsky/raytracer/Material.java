package org.pujezdsky.raytracer;

public class Material {

    public static final Material GLASS = new Material(
            0,
            0,
            1,
            1.4,
            100,
            new ColorD(0, 1, 0)
    );

    public static final Material MIRROR = new Material(
            0,
            1,
            0,
            1,
            200,
            new ColorD(0, 1, 0)
    );

    private double diffusion, reflection, refraction, N, gloss;

    private ColorD color;

    private IBitmap texture;

    public Material() {
        diffusion = 1;
        reflection = 0;
        refraction = 0;
        color = ColorD.ZERO;
        N = 1;
        gloss = 1;
    }

    public Material(double diffusion, double reflection, double refraction, double n, double gloss, ColorD color) {
        this.diffusion = diffusion;
        this.reflection = reflection;
        this.refraction = refraction;
        N = n;
        this.gloss = gloss;
        this.color = color;
    }

    public Material(Material m) {
        this(m.diffusion, m.reflection, m.refraction, m.N, m.gloss, m.color);
    }

    public double getGloss() {
        return gloss;
    }

    public double getN() {
        return N;
    }

    public double getReflection() {
        return reflection;
    }

    public double getRefraction() {
        return refraction;
    }

    public IBitmap getTexture() {
        return texture;
    }

    public void setColor(ColorD color) {
        this.color = color;
    }

    public void setRefraction(double refraction) {
        this.refraction = refraction;
    }
}
