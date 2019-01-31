package com.subwranglers.wickedarrows.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWArrow extends EntityArrow {

    public EntityWArrow(World worldIn) {
        super(worldIn);
    }

    public EntityWArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityWArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    protected void onHit(RayTraceResult trace) {
        if (trace.getBlockPos() != null && trace.typeOfHit == RayTraceResult.Type.BLOCK)
            onBlockHit(trace);

        super.onHit(trace);
    }

    protected void onBlockHit(RayTraceResult trace) {
        // No-op, inheriting classes will implement
    }
}
