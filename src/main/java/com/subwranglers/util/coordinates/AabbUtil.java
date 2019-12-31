package com.subwranglers.util.coordinates;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class AabbUtil {

    public static AxisAlignedBB getRadiusAabb(double x, double y, double z, double radius) {
        return new AxisAlignedBB(
                Math.floor(x) + -radius,
                Math.floor(y) + -radius,
                Math.floor(z) + -radius,
                Math.floor(x) + radius,
                Math.floor(y) + radius,
                Math.floor(z) + radius);
    }

    public static AxisAlignedBB getRadiusAabb(BlockPos pos, double radius) {
        return getRadiusAabb(pos.getX(), pos.getY(), pos.getZ(), radius);
    }
}
