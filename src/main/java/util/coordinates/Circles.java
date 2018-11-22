package util.coordinates;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>Generates coordinates in a circle, given a radius and coordinates for the center.</p>
 * <p>https://en.wikipedia.org/wiki/Midpoint_circle_algorithm</p>
 */
public class Circles {

    public static Set<Point3D> getCircleCoords(int x0, int y, int z0, int radius, Consumer<Point3D> coordFound) {
        Set<Point3D> points = new HashSet<>();

        int x = radius - 1;
        int z = 0;
        int dx = 1;
        int dz = 1;
        int err = dx - (radius << 1);

        while (x >= z) {
            Point3D p = new Point3D(x0 + x, y, z0 + z);
            if (points.add(p)) coordFound.accept(p);

            Point3D p2 = new Point3D(x0 + z, y, z0 + x);
            if (points.add(p2)) coordFound.accept(p2);

            Point3D p3 = new Point3D(x0 - z, y, z0 + x);
            if (points.add(p3)) coordFound.accept(p3);

            Point3D p4 = new Point3D(x0 - x, y, z0 + z);
            if (points.add(p4)) coordFound.accept(p4);

            Point3D p5 = new Point3D(x0 - x, y, z0 - z);
            if (points.add(p5)) coordFound.accept(p5);

            Point3D p6 = new Point3D(x0 - z, y, z0 - x);
            if (points.add(p6)) coordFound.accept(p6);

            Point3D p7 = new Point3D(x0 + z, y, z0 - x);
            if (points.add(p7)) coordFound.accept(p7);

            Point3D p8 = new Point3D(x0 + x, y, z0 - z);
            if (points.add(p8)) coordFound.accept(p8);


            if (err <= 0) {
                z++;
                err += dz;
                dz += 2;
            }

            if (err > 0) {
                x--;
                dx += 2;
                err += dx - (radius << 1);
            }
        }

        return points;
    }
}
