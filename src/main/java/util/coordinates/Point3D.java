package util.coordinates;

import net.minecraft.entity.EntityLivingBase;

public class Point3D {
    double x;
    double y;
    double z;
    boolean compareAsInt;

    public Point3D(double x, double y, double z, boolean compareAsInt) {
        this.x = x;
        this.z = z;
        this.compareAsInt = compareAsInt;
    }

    public Point3D(double x, double y, double z) {
        this(x, y, z, false);
    }

    public Point3D(EntityLivingBase living, boolean compareAsInt) {
        this(living.posX, living.posY, living.posZ, compareAsInt);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point3D))
            return false;

        Point3D in = (Point3D) obj;
        return compareAsInt
                ? ((int) x) == ((int) in.x) && ((int)y == (int)in.y) && ((int) z) == ((int) in.z)
                : x == in.x && y == in.y && z == in.z;
    }

    @Override
    public int hashCode() {
        return compareAsInt
                ? Integer.hashCode((int) x) - Integer.hashCode((int) y) - Integer.hashCode((int) z)
                : Double.hashCode(x) - Double.hashCode(y) - Double.hashCode(z);
    }

    @Override
    public String toString() {
        return String.format("(as int) x,z: (%b)  %f  %f", compareAsInt, x, z);
    }
}
