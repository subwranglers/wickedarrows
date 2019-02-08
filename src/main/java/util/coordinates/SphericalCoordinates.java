package util.coordinates;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

// TODO: 08/02/19 Merge this implementation with SphereCoordsBetween.java
public class SphericalCoordinates {

    /**
     * Given a starting point (<var>center</var>), gets a new {@link Vec3d Vector} coordinate using spherical coordinate
     * conversions.
     *
     * @param center  the point to get new coordinates from
     * @param radius  the distance from <var>center</var>
     * @param theta   the Yaw rotation, in Degrees
     * @param azimuth the Pitch rotation, in Degrees
     * @return a new {@link Vec3d} coordinate based on rotations of Yaw and Pitch
     */
    public static Vec3d getFromSphere(Vec3d center, float radius, float theta, float azimuth) {
        return center.add(fromRadiusPitchYaw(radius, azimuth, theta));
    }

    static Vec3d fromRadiusPitchYaw(float radius, float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = radius * -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = radius * MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d(-(double) (f1 * f2), -(double) f3, (double) (f * f2));
    }
}
