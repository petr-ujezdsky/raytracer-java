package org.pujezdsky.raytracer;

public class Engine {

    public IBitmap render(World world) {
        double dOmega = Math.PI / 30;
        int time = 179;
        int ratio = 1;
        int width = 640;
        int height = 480;


        Vector3 pos = new Vector3(-10, 0, -5 * Math.cos(dOmega * time * 3 + 3));
        Matrix3 m = Matrix3.rotationZ(dOmega * time);
        pos = m.multiply(pos);

        Vector3 dir = //-Vector3.E2;
                pos
                        .normalize()
                        .negate();

        Vector3 up = Vector3.crossProduct(Vector3.crossProduct(dir, Vector3.E3), dir).normalize();
        Camera camera = new Camera(pos, dir, up, Math.PI / 4);

        Light lightEye = world.getLights().get(0);
        //lightEye.Direction = dir;
        //lightEye.Position = pos;

//        DateTime start = DateTime.Now;
        long start = System.currentTimeMillis();

        //Bitmap imgTmp = new Bitmap(pictureBox.Size.Width, pictureBox.Size.Height);
        //pictureBox.Image = imgTmp;
        double ratioInv = 1.0 / ratio;
        FastBitmap fImg = new RayTracer(world).render(camera, (int) Math.round(width * ratioInv), (int) Math.round(height * ratioInv));

        String msg = String.format("i = %d FPS = %d Time = %dms",
                time,
                Math.round(1.00 / (System.currentTimeMillis() - start)),
                System.currentTimeMillis() - start);
        System.out.println(msg);

        return fImg;
    }
}
