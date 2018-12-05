package com.subwranglers.wickedarrows.entity;

import com.subwranglers.wickedarrows.base.EntityWArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTorchArrow extends EntityWArrow {

    public EntityTorchArrow(World worldIn) {
        super(worldIn);
    }

    public EntityTorchArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityTorchArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected void onBlockHit(RayTraceResult trace) {
        world.setBlockState(trace.getBlockPos().offset(trace.sideHit), Blocks.TORCH.getDefaultState());
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.setFire(3);
    }
}
