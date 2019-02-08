package util.coordinates;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.math3.util.FastMath;

public class SphereCoordsBetween {

    Vec3d from;
    Vec3d to;
    Vec3d o;

    double radius;
    double pitch;
    double yaw;

    /**
     * Calculates the radius, yaw and pitch values between the provided <b>from</b> and <b>to</b> coordinates. This
     * constructor's xyz values <i>represent Cartesian values, meaning the Z axis is actually height.</i> Take note of
     * this if you use this constructor, since Minecraft uses the Y axis for height. The other constructors provided
     * will actually swap Y & Z values to adhere to Minecraft for you.
     *
     * @param fromX starting x-axis coordinate
     * @param fromY starting y-axis coordinate
     * @param fromZ starting z-axis coordinate
     * @param toX   ending x-axis coordinate
     * @param toY   ending y-axis coordinate
     * @param toZ   ending z-axis coordinate
     */
    public SphereCoordsBetween(double fromX, double fromY, double fromZ, double toX, double toY, double toZ) {
        from = new Vec3d(fromX, fromY, fromZ);
        to = new Vec3d(toX, toY, toZ);

        // Since spherical coords are done using origin we need make a Vector relating these coords to origin.
        o = to.subtract(from);
        radius = FastMath.sqrt(o.x * o.x + o.y * o.y + o.z * o.z);
        pitch = FastMath.atan2(FastMath.sqrt(o.x * o.x + o.y * o.y), o.z);
        yaw = FastMath.atan2(o.y, o.x);
    }

    public SphereCoordsBetween(BlockPos from, BlockPos to) {
        this(from.getX(), from.getZ(), from.getY(), to.getX(), to.getZ(), to.getY());
    }

    public SphereCoordsBetween(Entity from, Entity to) {
        this(from.posX, from.posZ, from.posY, to.posX, to.posZ, to.posY);
    }

    /*

        Getters

     */

    /**
     * The starting cartesian coordinates.
     * @return {@link Vec3d}
     */
    public Vec3d getFrom() {
        return from;
    }

    /**
     * The ending cartesian coordinates.
     * @return {@link Vec3d}
     */
    public Vec3d getTo() {
        return to;
    }

    /**
     * Spherical coordinate calculations are done based on a point's relation to <i>origin</i> -- not between any two
     * points in a cartesian graph. This value is calculated by subtracting <b>from</b> from <b>to</b>, so we aren't
     * <i>truly</i> calculating angles between the two given points, but it works all the same.
     * @return {@link Vec3d}
     */
    public Vec3d getFromOrigin() {
        return o;
    }

    /**
     * The distance between the provided starting and ending points.
     *
     * @return {@link Double}
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the pitch angle from the starting coordinate to the ending coordinate.
     *
     * @return {@link Double}, converted to degrees and corrected to Minecraft's pitch representation
     */
    public double getPitch() {
        return FastMath.toDegrees(pitch) - 90;
    }

    /**
     * Gets the yaw angle from the starting coordinate to the ending coordinate.
     *
     * @return {@link Double}, converted to degrees and corrected to Minecraft's yaw representation
     */
    public double getYaw() {
        return MathHelper.wrapDegrees(FastMath.toDegrees(yaw) - 90);
    }

    /**
     * Convenience method for getting the pitch angle as a {@link Float}
     *
     * @return returns {@link #getPitch()}'s value after converting it to a {@link Float}
     */
    public float getPitchF() {
        return (float) getPitch();
    }

    /**
     * Convenience method for getting the yaw angle as a {@link Float}
     *
     * @return returns {@link #getYaw()}'s value after converting it to a {@link Float}
     */
    public float getYawF() {
        return (float) getYaw();
    }
}
